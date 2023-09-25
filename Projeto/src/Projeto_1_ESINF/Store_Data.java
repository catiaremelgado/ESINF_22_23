package Projeto_1_ESINF;

import Projeto_1_ESINF.info.CompletedInfo;

import java.io.*;

import static java.lang.Integer.parseInt;

public class Store_Data {

    public CompletedInfo storeInfo(String fileName) throws FileNotFoundException {

        //Initializes the line that corresponds to a line of the file
        String line = "";
        //Will be used to split in case there's a comma between any words in the file
        String specialSplit = ",\"";

        //Path of the file
        String file = "Projeto"+ File.separator+"out"+ File.separator+"production"+ File.separator+"teste"+ File.separator+ fileName;

        //Will receive the fruit name and country correspondent to each line fruit
        //and country
        String fruit, country;
        //Will receive the quantity of production and the year correspondent to each line quantity
        //of production and the year
        int quantity, year;
        //Object that will have all the info that is in file organized
        CompletedInfo completedInfo = new CompletedInfo();

        try {
            //Initializes the BufferedReader to read the file that has the received file name
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //Skips the header line
            reader.readLine();

            //Cycle that will read each line from the file until it reaches the end
            while((line = reader.readLine()) != null) {
                String[] data;

                //Test to see if the file has quotation marks in the data
                if(line.contains("\"")) {
                    //Separates the data by the specialSlip String
                    data = line.split(specialSplit);
                    //Cycle for that will erase the left quotation marks on the data
                    for (int i = 1; i < data.length; i++) {
                        //Erasing the quotation marks
                        data[i] = data[i].substring(0, data[i].length() - 1);
                    }
                }else{
                    //Since the file doesn't have quotation marks we do a normal split by comma
                    data = line.split(",");
                }

                //Test to see if any of the necessary data is null
                if (!(data[3]).equals("") && !(data[7]).equals("") && !(data[9]).equals("") && !(data[11]).equals("")) {

                    //Attribution of the data in the line to their respective meaning
                    country = data[3];
                    year = parseInt(data[9]);
                    fruit = data[7];
                    quantity = parseInt(data[11]);

                    //Tests if the fruit of the line already exists in the Treemap with all the data
                    //extracted from the file till now
                    if (completedInfo.containsFruit(fruit)) {

                        //Since the fruit already we test if the country of the line
                        // already exists in the Treemap since the fruit already exists
                        if (completedInfo.containsCountry(fruit, country)) {
                            //Since the country already exists in the Treemap we add a new year and its respective quantity
                            completedInfo.addAnotherYear(fruit, country, year, quantity);

                        } else {
                            //Since the country doesn't exist in the Treemap associated to the respective fruit
                            // we add a new country and its respective quantity
                            completedInfo.addNewCountry(fruit, country, year, quantity);
                        }

                    } else {
                        //Since the fruit doesn't exist we will need to add a new fruit and country with its
                        //respective quantity
                        completedInfo.addNewFruitAndCountry(fruit, country, year, quantity);
                    }
                }
            }
        //Warning if the file is not found
        }catch (IOException e){
            System.out.println("The file does not exist");
        }

        //Returns the Treemap with all the extracted info from the file
        return completedInfo;
    }
}

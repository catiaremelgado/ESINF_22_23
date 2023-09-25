package Projeto_2_ESINF.fileReaderUtils;

import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.BST;
import Projeto_2_ESINF.dataStructures.Three;
import Projeto_2_ESINF.model.*;

import java.io.*;
import java.util.*;

/**
 * @author annavasylyshyna
 */
public class ReadDataFromFiles {

    /**
     * @param fileName the name of the file with the coordinates
     * @param areaAVL  binary search of areas, to save only cooresponding coordinates
     * @return a list with a three object(Area,float,float) that allows for organization of 2d tree
     */
    public static List<Three<Area, Float, Float>> storeDataFromFileCoordinates(String fileName, AVL<Area> areaAVL) {

        //Initializes the line that corresponds to a line of the file
        String line;

        //Path of the file
        String file = "Projeto" + File.separator + "out" + File.separator + "production" + File.separator + "teste_p2" + File.separator + fileName;

        //Object that will have all the info that is in the file
        List<Three<Area, Float, Float>> listFromCoordinates = new ArrayList<>();
        BST.Node<Area> root = areaAVL.root();
        if (root != null) {
            if (areaAVL.root().getElement() instanceof AreaName) {
                try {

                    InputStream input = new FileInputStream(file);
                    FastReader reader = new FastReader(input);

                    //Skips the header line
                    reader.nextLine();
                    line = reader.nextLine();

                    String split = ",";
                    //Test to see if the file has quotation marks in the data
                    if (line.contains("\",\"")) {
                        split = "\",\"";
                    }

                    //Cycle that will read each line from the file until it reaches the end
                    while (line != null) {

                        String[] data;
                        data = line.split(split);
                        if (data.length == 4) {
                            //Floats são com pontos
//                    data[2] = data[2].replaceAll("\\.",",");
//                    data[1] = data[1].replaceAll("\\.",",");
                            for (int i = 0; i < data.length; i++) {
                                data[i] = data[i].replaceAll("\"", "");
                            }

                            //if (!(data[0]).equals("") && !(data[1]).equals("") && !(data[2]).equals("") && !(data[3]).equals("")) {
                            if (verifyIfEmptyOrNull(data)) {
                                try {
                                    Float latitude = Float.parseFloat(data[1]);
                                    Float longitude = Float.parseFloat(data[2]);
                                    BST.Node<Area> anode = areaAVL.find(new AreaName(null, null, data[3]));
                                    if (anode != null) {
                                        listFromCoordinates.add(new Three<>(anode.getElement(), latitude, longitude));
                                    }
                                } catch (Exception e) {
                                    System.out.println("There is something wrong with the longitude or latitude of the country: " + data[3] +
                                            "\nThe data from country " + data[3] + " will not be saved.\n\n\n");
                                }

                            }
                        }
                        line = reader.nextLine();
                    }
                } catch (IOException e) {
                    System.out.println("The file does not exist");
                }
            }
        } else {
            //throw new NullPointerException("Data tree does not exist!");
            System.out.println("The AVL does not have data or it's the wrong type.");
        }

        return listFromCoordinates;
    }


    /**
     * Method to read the data from the file with the flags
     *
     * @param fileName the name of the file with the flags
     * @return a TreeMap with all the flags existent in the file
     */
    private static TreeMap<String, String> storeDataFromFileFlag(String fileName) {

        //Initializes the line that corresponds to a line of the file
        String line;

        //Path of the file
        String file = "Projeto" + File.separator + "out" + File.separator + "production" + File.separator + "teste_p2" + File.separator + fileName;

        //Object that will have all the info that is in the file
        TreeMap<String, String> listFromProduction = new TreeMap<>();
        try {

            InputStream input = new FileInputStream(file);
            FastReader reader = new FastReader(input);

            //Skip the header line
            reader.nextLine();
            line = reader.nextLine();
            String split = ",";

            if (line.contains("\",\"")) {
                split = "\",\"";
            }

            while (line != null) {
                String[] data;
                data = line.split(split);

                if (data.length == 2 && verifyIfEmptyOrNull(data)) {
                    if (split.equals(",")) {
                        data[0] = data[0].replaceAll("\"", "");
                        data[1] = data[1].replaceAll("\"", "");
                    }
                    listFromProduction.put(data[0].toLowerCase(Locale.ROOT), data[1].toLowerCase(Locale.ROOT));
                }
                line = reader.nextLine();
            }
        } catch (IOException e) {
            System.out.println("The file does not exist");
        }
        return listFromProduction;
    }

    /**
     * stores the data of the production file in a balanced binary search tree
     *
     * @param fileName    the file name of the production vales
     * @param fileFlags   the file corresponding to the flag meanings
     * @param areaType    the type of area(how to filter)
     * @param itemType    the type of item (how to filter)
     * @param elementType the type of element (how to filter)
     * @return fully loaded AVL with the indicated filters
     */
    public static AVL<Area> storeDataFromFileProduction(String fileName, String fileFlags, String areaType, String itemType, String elementType) {
        //Initializes the line that corresponds to a line of the file
        String line;

        //Path of the file
        String file = "Projeto" + File.separator + "out" + File.separator + "production" + File.separator + "teste_p2" + File.separator + fileName;
        AVL<Area> areaAVL = new AVL<>();
        TreeMap<String, String> flagIndexes = storeDataFromFileFlag(fileFlags);
        try {
            InputStream input = new FileInputStream(file);
            FastReader reader = new FastReader(input);

            boolean isAreaCode = areaType.equalsIgnoreCase("AreaCode");
            boolean isAreaCodeM49 = areaType.equalsIgnoreCase("AreaCodeM49");
            boolean isAreaName = areaType.equalsIgnoreCase("AreaName");

            boolean isItemCode = itemType.equalsIgnoreCase("ItemCode");
            boolean isItemCodeCPC = itemType.equalsIgnoreCase("ItemCodeCPC");
            boolean isItemName = itemType.equalsIgnoreCase("ItemName");

            boolean isElementCode = elementType.equalsIgnoreCase("ElementCode");
            boolean isElementName = elementType.equalsIgnoreCase("ElementName");

            if ((isAreaCode || isAreaCodeM49 || isAreaName) && (isItemCode || isItemCodeCPC || isItemName) && (isElementCode || isElementName)) {
                Area area = null;
                AVL<Item> itemAVL;
                Item item = null;
                AVL<Element> elementAVL;
                Element element = null;
                Year year;
                Value value;
                AVL<ProductionYear> productionYearAVL;

                AreaCode currentAreaCode;
                AreaCodeM49 currentAreaCodeM49;
                AreaName currentAreaName;

                ItemCode currentItemCode;
                ItemCodeCPC currentItemCodeCPC;
                ItemName currentItemName;

                ElementCode currentElementCode;
                ElementName currentElementName;
                ProductionYear currentProductionYear;

                //Skip the header line
                reader.nextLine();
                line = reader.nextLine();
                String split = ",";

                if (line.contains("\",\"")) {
                    split = "\",\"";
                }

                while (line != null) {
                    String[] data;
                    data = line.split(split);

                    for (int j = 0; j < data.length; j++) {
                        data[j] = data[j].replaceAll("\"", "");
                    }

                    if (data.length == 13 && verifyIfEmptyOrNull(data)) {
                        data[11] = data[11].replaceAll("\\.", "");
                        try {
                            Integer parseAreaCode = Integer.parseInt(data[0]);
                            Integer parseItemCode = Integer.parseInt(data[3]);
                            Integer parseElementCode = Integer.parseInt(data[6]);
                            Integer parseYear = Integer.parseInt(data[9]);
                            Long parseValue = Long.parseLong(data[11]);


                            if (isAreaCode) {
                                currentAreaCode = new AreaCode(parseAreaCode, data[1], data[2]);
                                BST.Node<Area> anode = areaAVL.find(currentAreaCode);
                                area = (anode != null ? anode.getElement() : null);
                                if (area == null) {
                                    areaAVL.insert(currentAreaCode);
                                    area = currentAreaCode;
                                }
                            }

                            if (isAreaCodeM49) {
                                currentAreaCodeM49 = new AreaCodeM49(parseAreaCode, data[1], data[2]);
                                BST.Node<Area> anode = areaAVL.find(currentAreaCodeM49);
                                area = (anode != null ? anode.getElement() : null);
                                if (area == null) {
                                    areaAVL.insert(currentAreaCodeM49);
                                    area = currentAreaCodeM49;
                                }
                            }

                            if (isAreaName) {
                                currentAreaName = new AreaName(parseAreaCode, data[1], data[2]);
                                BST.Node<Area> anode = areaAVL.find(currentAreaName);
                                area = (anode != null ? anode.getElement() : null);
                                if (area == null) {
                                    areaAVL.insert(currentAreaName);
                                    area = currentAreaName;
                                }

                            }


                            itemAVL = area.getItemList();

                            if (isItemCode) {
                                currentItemCode = new ItemCode(parseItemCode, data[4], data[5]);
                                BST.Node<Item> anode = itemAVL.find(currentItemCode);
                                item = (anode != null ? anode.getElement() : null);
                                if (item == null) {
                                    itemAVL.insert(currentItemCode);
                                    item = currentItemCode;
                                }
                            }

                            if (isItemCodeCPC) {
                                currentItemCodeCPC = new ItemCodeCPC(parseItemCode, data[4], data[5]);
                                BST.Node<Item> anode = itemAVL.find(currentItemCodeCPC);
                                item = (anode != null ? anode.getElement() : null);
                                if (item == null) {
                                    itemAVL.insert(currentItemCodeCPC);
                                    item = currentItemCodeCPC;
                                }
                            }

                            if (isItemName) {
                                currentItemName = new ItemName(parseItemCode, data[4], data[5]);
                                BST.Node<Item> anode = itemAVL.find(currentItemName);
                                item = (anode != null ? anode.getElement() : null);
                                if (item == null) {
                                    itemAVL.insert(currentItemName);
                                    item = currentItemName;
                                }
                            }


                            elementAVL = item.getElementList();

                            if (isElementCode) {
                                currentElementCode = new ElementCode(parseElementCode, data[7]);
                                BST.Node<Element> anode = elementAVL.find(currentElementCode);
                                element = (anode != null ? anode.getElement() : null);
                                if (element == null) {
                                    elementAVL.insert(currentElementCode);
                                    element = currentElementCode;
                                }
                            }

                            if (isElementName) {
                                currentElementName = new ElementName(parseElementCode, data[7]);
                                BST.Node<Element> anode = elementAVL.find(currentElementName);
                                element = (anode != null ? anode.getElement() : null);
                                if (element == null) {
                                    elementAVL.insert(currentElementName);
                                    element = currentElementName;
                                }
                            }


                            year = new Year(parseYear);
                            value = new Value(data[10], parseValue, data[12].toLowerCase(Locale.ROOT).charAt(0), flagIndexes.get(data[12].toLowerCase(Locale.ROOT)));
                            currentProductionYear = new ProductionYear(year, value);
                            productionYearAVL = element.getProductionYearList();
                            BST.Node<ProductionYear> anode = productionYearAVL.find(currentProductionYear);

                            if (anode == null) {
                                productionYearAVL.insert(currentProductionYear);
                            }


                        } catch (NumberFormatException e) {
                            System.out.println("Some data is wrong country: " + data[2]);
                        }
                    }
                    line = reader.nextLine();
                }
            } else {
                System.out.println("At least one of the given types is wrong, the data will not be stored.");
            }
        } catch (IOException e) {
            System.out.println("The file does not exist");
        }

        return areaAVL;
    }


    /**
     * Method to verify if an array has an empty element or with null data
     * @param data the array to verify
     * @return true if all indexes have data or false if there is some null or empty index
     */
    private static boolean verifyIfEmptyOrNull(String[] data){
        boolean is = true;
        int i = 0;
        while (is == true && i < data.length){
            if (data[i] == null || data[i] == "") {
                is = false;
            }
            i++;
        }
        return is;
    }
}
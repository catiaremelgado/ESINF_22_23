package Projeto_2_ESINF;
import Projeto_2_ESINF.customExceptions.AreaNotFoundException;
import Projeto_2_ESINF.dataStructures.*;
import Projeto_2_ESINF.fileReaderUtils.ReadDataFromFiles;
import Projeto_2_ESINF.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductionMethods {

    /**
     * Method that searches for the nearest area in the avl that corresponds to specific parameters
     *
     * @param areaAVL AVL with all the areas
     * @param longitude part of the coordinate
     * @param latitude part of the coordinate
     * @param item name of the item that we want to search for
     * @param element name of the element that we want to search for
     * @param year year that we want to search for
     * @param fileName name of the coordinates file
     *
     * @return object area that corresponds to the closest area to the input coordinates
     * @throws AreaNotFoundException
     */
    public static Area nearestAreaToCertainData(AVL<Area> areaAVL, float longitude, float latitude, String item, String element, Integer year, String fileName) throws AreaNotFoundException {
        if (item == null || element == null || year == 0 ) {
            throw new NullPointerException("At least one of the given parameters does not exist in the data base or may be misspelled, please try again.\n\n");

        } else {
            AVL<Area> matchingAreas = new AVL<>();
            List<Three<Area, Float, Float>> listMatchingCoordinates;
            Area closestArea;


            for (Area area : areaAVL.inOrder()) {
                AVL<Item> items = area.getItemList();

                for (Item item1 : items.inOrder()) {
                    String testItem = item1.getItemName();
                    if (testItem.equals(item)) {
                        AVL<Element> elements = item1.getElementList();

                        for (Element element1 : elements.inOrder()) {
                            String testElement = element1.getElementName();
                            if (testElement.equals(element)) {
                                AVL<ProductionYear> years = element1.getProductionYearList();

                                for (ProductionYear production1 : years.inOrder()) {
                                    Year year1 = production1.getYear();
                                    Integer yearTest = year1.getYear();
                                    if (yearTest.equals(year)) {
                                        matchingAreas.insert(area);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            listMatchingCoordinates = ReadDataFromFiles.storeDataFromFileCoordinates(fileName, matchingAreas);
            TwoDTree<Area> matchingCoordinates = new TwoDTree<>(listMatchingCoordinates);
            closestArea = matchingCoordinates.nearestNeighbourSearch(longitude, latitude);

            if (closestArea == null) {
                throw new AreaNotFoundException("It was impossible to find an area that matches the requirements.\n\n");
            } else {
                return closestArea;
            }
        }
    }

    /**
     * method that sums the values of production in a certain square area of a certain item code, of a certain elementcode, of a certain year.
     *
     * @param areaAVL     tree of areas to search
     * @param twoDTree    twod tree with coordinates to search
     * @param itemCode    item code to filter
     * @param elementCode element code to filter
     * @param yearCode    year code to filter
     * @param inicialX    inicial x coordinate of square area
     * @param finalX      final x coordinate of square area
     * @param inicialY    inicial y coordinate of square area
     * @param finalY      final y coordinate of square area
     * @return long value that represents the sum
     */
    public static Long sumValuesInAreaOfProduction(AVL<Area> areaAVL, TwoDTree<Area> twoDTree, int itemCode, int elementCode, int yearCode, Float inicialX, Float finalX, Float inicialY, Float finalY) {

        Long sum = 0L;

        if (itemCode != 0 && elementCode != 0 && yearCode != 0) {

            List<Area> areasInSearch = new ArrayList<>();
            twoDTree.searchInArea(areasInSearch, inicialX, finalX, inicialY, finalY);//search areas in requested coordinates

            for (Area area : areasInSearch) {

                BST.Node<Area> areaNode = areaAVL.find(area);
                Area areaFound = (areaNode == null ? null : areaNode.getElement());

                if (areaFound != null) {

                    BST.Node<Item> itemNode = areaFound.getItemList().find(new ItemCode(itemCode));
                    Item itemFound = (itemNode == null ? null : itemNode.getElement());

                    if (itemFound != null) {

                        BST.Node<Element> elementNode = itemFound.getElementList().find(new ElementCode(elementCode));
                        Element elementFound = (elementNode == null ? null : elementNode.getElement());

                        if (elementFound != null) {

                            BST.Node<ProductionYear> yearNode = elementFound.getProductionYearList().find(new ProductionYear(new Year(yearCode)));
                            ProductionYear productionYear = (yearNode == null ? null : yearNode.getElement());

                            if (productionYear != null) { //found item,element and year in this area, so we can add to sum

                                sum += productionYear.getValue().getValue();

                            }
                        }

                    }
                }

            }
        }
        return sum;
    }


    /**
     *
     * @param areaAVL tree of areas to search
     * @param areaInput the area to search
     * @param year1 The first year in the interval
     * @param year2 The second year in the interval
     * @return list Avg of values order by Item and Element between year1 and year2
     * @throws Exception throws exception if the data is wrong
     */
    public static List AvgValueByItemElement(AVL<Area> areaAVL, Area areaInput, Year year1, Year year2) throws Exception {

        List<ItemElementAgregateValue> result = new ArrayList<>(); //return list

        if (areaInput == null || year1.getYear().intValue() == 0 || year2.getYear().intValue() == 0) {
            throw new NullPointerException("At least one of the given parameters does not exist in the data base or may be misspelled, please try again.\n\n");
        }

        if (year1.getYear().compareTo(year2.getYear()) >= 0) {
            throw new Exception("The first year should be smaller than the second!");
        }

        for (Area area : areaAVL.inOrder()) {    // search areas in areaAVL
            if (area.getAreaName().equals(areaInput.getAreaName())) {  //check if areaInput is the same area found
                for (Item item : area.getItemList().inOrder()) { //search items in AVL item
                    for (Element element : item.getElementList().inOrder()) { //search items in AVL element
                        ItemElementAgregateValue row = new ItemElementAgregateValue(year1, year2, item, element); // Op

                        for (ProductionYear product : element.getProductionYearList().inOrder()) { //search ProductionsYears in AVL ProductionYear
                            if (product.getYear().getYear() >= year1.getYear() && product.getYear().getYear() <= year2.getYear()) {
                                row.addValue(product.getValue());
                            }
                        }
                        if (!row.isEmpty()) {
                            result.add(row);
                        }
                    }
                }
                result.sort(new Comparator<ItemElementAgregateValue>() {     // Dec order sort
                    @Override
                    public int compare(ItemElementAgregateValue o1, ItemElementAgregateValue o2) {
                        return  - Double.compare(o1.avg(),o2.avg());
                    }
                });
                return result;
            }
        }
        return null;
    }

    /**
     * @param areaAVL
     * @param item    to filter
     * @param element to filter
     * @param n       number of top Areas for that item and element
     * @return
     */

    public static ArrayList<String> topNAreas(AVL<Area> areaAVL, String item, String element, Integer n) {
        AVL<ProductionYear> year = new AVL<>();
        ArrayList<Pair<String, Long>> topArea = new ArrayList<>();
        ArrayList<String> finalArray = new ArrayList<>();
        if (item == null || element == null || n == 0) {
            throw new NullPointerException("At least one of the given parameters does not exist in the data base or may be misspelled, please try again. \n");
        } else {
            AVL<Area> areaSearch = new AVL<>();
            for (Area area : areaAVL.inOrder()) {
                AVL<Item> itemAVL = area.getItemList();
                for (Item item1 : itemAVL.inOrder()) {
                    String testItem = item1.getItemName();
                    if (testItem.equals(item)) {
                        AVL<Element> elementAVL = item1.getElementList();
                        for (Element element1 : elementAVL.inOrder()) {
                            String testElement = element1.getElementName();
                            if (testElement.equals(element)) {
                                AVL<ProductionYear> years = element1.getProductionYearList();
                                for (ProductionYear production1 : years.inOrder()) {

                                    ProductionYear maximumYear = years.biggestElement();
                                    topArea.add(new Pair<>(area.getAreaName(), maximumYear.getValue().getValue()));

                                }
                            }
                        }
                    }
                }
            }
            Collections.sort(topArea);
            if (!topArea.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    finalArray.add(topArea.get(i).getLeft());
                }
            }
        }
        return finalArray;
    }
}



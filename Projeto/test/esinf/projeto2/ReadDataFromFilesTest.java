package esinf.projeto2;

import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.Three;
import Projeto_2_ESINF.fileReaderUtils.ReadDataFromFiles;
import Projeto_2_ESINF.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReadDataFromFilesTest extends AVL {

    @Test
    public void storeDataFromFileCoordinates() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countriesExpected = new ArrayList<>();
        countriesExpected.add(pol);
        countriesExpected.add(den);
        countriesExpected.add(swe);
        countriesExpected.add(it);

        AVL<Area> areaAVLReal = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaName", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> countriesReal = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini.csv", areaAVLReal);

        assertEquals(true, countriesExpected.equals(countriesReal));
    }

    @Test
    public void testStoreDataFromFileProductionFileWithOnlyOneCountry() {

        AreaCode denmark = new AreaCode(54, "'208", "Denmark");

        ItemCode apples = new ItemCode(515, "'01341", "Apples");
        ItemCode blueberries = new ItemCode(552, "'01355.01", "Blueberries");
        ItemCode raspberries = new ItemCode(547, "'01353.01", "Raspberries");

        ElementCode area_harvested = new ElementCode(5312, "Area harvested");
        ElementCode production1 = new ElementCode(5510, "Production");
        ElementCode production2 = new ElementCode(5510, "Production");
        ElementCode yield1 = new ElementCode(5419, "Yield");
        ElementCode yield2 = new ElementCode(5419, "Yield");

        ProductionYear pYDenmark1 = new ProductionYear(new Year(1994), new Value("ha", 31000000L, 'I', "Imputed value"));
        ProductionYear pYDenmark2 = new ProductionYear(new Year(1995), new Value("ha", 34000000L, 'I', "Imputed value"));
        ProductionYear pYDenmark3 = new ProductionYear(new Year(1990), new Value("tonnes", 70000000000L, 'T', "Unofficial figure"));
        ProductionYear pYDenmark4 = new ProductionYear(new Year(2011), new Value("tonnes", 54000000L, 'A', "Official figure"));
        ProductionYear pYDenmark5 = new ProductionYear(new Year(2013), new Value("hg/ha", 225807000000L, 'E', "Estimated value"));
        ProductionYear pYDenmark6 = new ProductionYear(new Year(1989), new Value("hg/ha", 50000000000L, 'E', "Estimated value"));
        ProductionYear pYDenmark7 = new ProductionYear(new Year(1990), new Value("hg/ha", 67073000000L, 'I', "Imputed value"));

        AVL<Area> areaAVLExpected2 = new AVL<>();

        areaAVLExpected2.insert(denmark);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(apples);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().insert(yield1);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().find(yield1).getElement().getProductionYearList().insert(pYDenmark5);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(raspberries);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().insert(yield2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(yield2).getElement().getProductionYearList().insert(pYDenmark6);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().insert(area_harvested);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(area_harvested).getElement().getProductionYearList().insert(pYDenmark2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(yield2).getElement().getProductionYearList().insert(pYDenmark7);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().insert(production1);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().find(production1).getElement().getProductionYearList().insert(pYDenmark3);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(area_harvested).getElement().getProductionYearList().insert(pYDenmark1);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(blueberries);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(blueberries).getElement().getElementList().insert(production2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(blueberries).getElement().getElementList().find(production2).getElement().getProductionYearList().insert(pYDenmark4);


        AVL<Area> areaAVlReal = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProductionDenmark.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");


        // verifying if all avl's are equal

        assertTrue(areaAVLExpected2.equals(areaAVlReal));

        AVL<Item> itemAVLDenmarkExpected = areaAVLExpected2.find(denmark).getElement().getItemList();
        AVL<Item> itemAVLDenmarkReal = areaAVlReal.find(denmark).getElement().getItemList();

        assertTrue(itemAVLDenmarkExpected.equals(itemAVLDenmarkReal));


        // testing apples denmark
        AVL<Element> elementAVLApplesDenmarkExpected = itemAVLDenmarkExpected.find(apples).getElement().getElementList();
        AVL<Element> elementAVLApplesDenmarkReal = itemAVLDenmarkReal.find(apples).getElement().getElementList();

        assertTrue(elementAVLApplesDenmarkExpected.equals(elementAVLApplesDenmarkReal));

        AVL<ProductionYear> elementAVLYieldApplesDenmarkExpected = elementAVLApplesDenmarkExpected.find(yield1).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldApplesDenmarkReal = elementAVLApplesDenmarkReal.find(yield1).getElement().getProductionYearList();

        assertTrue(elementAVLYieldApplesDenmarkExpected.equals(elementAVLYieldApplesDenmarkReal));

        AVL<ProductionYear> elementAVLProductionApplesDenmarkExpected = elementAVLApplesDenmarkExpected.find(production1).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionApplesDenmarkReal = elementAVLApplesDenmarkReal.find(production1).getElement().getProductionYearList();

        assertTrue(elementAVLProductionApplesDenmarkExpected.equals(elementAVLProductionApplesDenmarkReal));


        // testing raspberries denmark
        AVL<Element> elementAVLRaspberriesDenmarkExpected = itemAVLDenmarkExpected.find(raspberries).getElement().getElementList();
        AVL<Element> elementAVLRaspberriesDenmarkReal = itemAVLDenmarkReal.find(raspberries).getElement().getElementList();

        assertTrue(elementAVLRaspberriesDenmarkExpected.equals(elementAVLRaspberriesDenmarkReal));

        AVL<ProductionYear> elementAVLYieldRaspberriesDenmarkExpected = elementAVLRaspberriesDenmarkExpected.find(yield2).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldRaspberriesDenmarkReal = elementAVLRaspberriesDenmarkReal.find(yield2).getElement().getProductionYearList();

        assertTrue(elementAVLYieldRaspberriesDenmarkExpected.equals(elementAVLYieldRaspberriesDenmarkReal));

        AVL<ProductionYear> elementAVLAreaRaspberriesDenmarkExpected = elementAVLRaspberriesDenmarkExpected.find(area_harvested).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaRaspberriesDenmarkReal = elementAVLRaspberriesDenmarkReal.find(area_harvested).getElement().getProductionYearList();

        assertTrue(elementAVLAreaRaspberriesDenmarkExpected.equals(elementAVLAreaRaspberriesDenmarkReal));


        // testing blueberries denmark
        AVL<Element> elementAVLBlueberriesDenmarkExpected = itemAVLDenmarkExpected.find(blueberries).getElement().getElementList();
        AVL<Element> elementAVLBlueberriesDenmarkReal = itemAVLDenmarkReal.find(blueberries).getElement().getElementList();

        assertTrue(elementAVLBlueberriesDenmarkExpected.equals(elementAVLBlueberriesDenmarkReal));

        AVL<ProductionYear> elementAVLProductionBlueberriesDenmarkExpected = elementAVLBlueberriesDenmarkExpected.find(production2).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionBlueberriesDenmarkReal = elementAVLBlueberriesDenmarkReal.find(production2).getElement().getProductionYearList();

        assertTrue(elementAVLProductionBlueberriesDenmarkExpected.equals(elementAVLProductionBlueberriesDenmarkReal));
    }

    @Test
    public void testStoreDataFromFileProductionFileWithFourCountries() {

        AreaCode sweden = new AreaCode(210, "'752", "Sweden");
        AreaCode poland = new AreaCode(173, "'616", "Poland");
        AreaCode italy = new AreaCode(106, "'380", "Italy");
        AreaCode denmark = new AreaCode(54, "'208", "Denmark");

        ItemCode apples = new ItemCode(515, "'01341", "Apples");
        ItemCode applesP = new ItemCode(515, "'01341", "Apples");
        ItemCode applesS = new ItemCode(515, "'01341", "Apples");
        ItemCode blueberries = new ItemCode(552, "'01355.01", "Blueberries");
        ItemCode blueberriesI = new ItemCode(552, "'01355.01", "Blueberries");
        ItemCode blueberriesP = new ItemCode(552, "'01355.01", "Blueberries");
        ItemCode blueberriesS = new ItemCode(552, "'01355.01", "Blueberries");
        ItemCode gooseberries = new ItemCode(549, "'01355.02", "Gosseberries");
        ItemCode raspberries = new ItemCode(547, "'01353.01", "Raspberries");
        ItemCode raspberriesI = new ItemCode(547, "'01353.01", "Raspberries");
        ItemCode raspberriesP = new ItemCode(547, "'01353.01", "Raspberries");
        ItemCode raspberriesS = new ItemCode(547, "'01353.01", "Raspberries");
        ItemCode citrus = new ItemCode(495, "'01324", "Tangerines, mandarins, clementines");

        ElementCode area_harvested1 = new ElementCode(5312, "Area harvested");
        ElementCode area_harvested2 = new ElementCode(5312, "Area harvested");
        ElementCode area_harvested7 = new ElementCode(5312, "Area harvested");
        ElementCode area_harvested3 = new ElementCode(5312, "Area harvested");
        ElementCode area_harvested4 = new ElementCode(5312, "Area harvested");
        ElementCode area_harvested5 = new ElementCode(5312, "Area harvested");
        ElementCode area_harvested6 = new ElementCode(5312, "Area harvested");

        ElementCode production1 = new ElementCode(5510, "Production");
        ElementCode production2 = new ElementCode(5510, "Production");
        ElementCode production3 = new ElementCode(5510, "Production");
        ElementCode production4 = new ElementCode(5510, "Production");
        ElementCode production5 = new ElementCode(5510, "Production");
        ElementCode production6 = new ElementCode(5510, "Production");
        ElementCode production7 = new ElementCode(5510, "Production");
        ElementCode production8 = new ElementCode(5510, "Production");
        ElementCode production9 = new ElementCode(5510, "Production");
        ElementCode production10 = new ElementCode(5510, "Production");


        ElementCode yield1 = new ElementCode(5419, "Yield");
        ElementCode yield2 = new ElementCode(5419, "Yield");
        ElementCode yield3 = new ElementCode(5419, "Yield");
        ElementCode yield4 = new ElementCode(5419, "Yield");
        ElementCode yield5 = new ElementCode(5419, "Yield");
        ElementCode yield6 = new ElementCode(5419, "Yield");
        ElementCode yield7 = new ElementCode(5419, "Yield");
        ElementCode yield8 = new ElementCode(5419, "Yield");
        ElementCode yield9 = new ElementCode(5419, "Yield");
        ElementCode yield10 = new ElementCode(5419, "Yield");
        ElementCode yield11 = new ElementCode(5419, "Yield");


        ProductionYear pYItaly1 = new ProductionYear(new Year(2009), new Value("ha", 38640000000L, 'A', "Official figure"));
        ProductionYear pYItaly2 = new ProductionYear(new Year(1991), new Value("tonnes", 428200000000L, 'A', "Official figure"));
        ProductionYear pYItaly3 = new ProductionYear(new Year(2005), new Value("tonnes", 617037000000L, 'A', "Official figure"));
        ProductionYear pYItaly4 = new ProductionYear(new Year(1999), new Value("hg/ha", 96882000000L, 'E', "Estimated value"));
        ProductionYear pYItaly5 = new ProductionYear(new Year(2006), new Value("hg/ha", 85000000000L, 'E', "Estimated value"));
        ProductionYear pYItaly6 = new ProductionYear(new Year(1970), new Value("hg/ha", 129112000000L, 'T', "Unofficial figure"));
        ProductionYear pYItaly7 = new ProductionYear(new Year(1974), new Value("hg/ha", 140905000000L, 'T', "Unofficial figure"));
        ProductionYear pYItaly8 = new ProductionYear(new Year(1996), new Value("hg/ha", 140148000000L, 'E', "Estimated value"));

        ProductionYear pYDenmark1 = new ProductionYear(new Year(1994), new Value("ha", 31000000L, 'I', "Imputed value"));
        ProductionYear pYDenmark2 = new ProductionYear(new Year(1995), new Value("ha", 34000000L, 'I', "Imputed value"));
        ProductionYear pYDenmark3 = new ProductionYear(new Year(1990), new Value("tonnes", 70000000000L, 'T', "Unofficial figure"));
        ProductionYear pYDenmark4 = new ProductionYear(new Year(2011), new Value("tonnes", 54000000L, 'A', "Official figure"));
        ProductionYear pYDenmark5 = new ProductionYear(new Year(2013), new Value("hg/ha", 225807000000L, 'E', "Estimated value"));
        ProductionYear pYDenmark6 = new ProductionYear(new Year(1989), new Value("hg/ha", 50000000000L, 'E', "Estimated value"));
        ProductionYear pYDenmark7 = new ProductionYear(new Year(1990), new Value("hg/ha", 67073000000L, 'I', "Imputed value"));


        ProductionYear pYPoland1 = new ProductionYear(new Year(1995), new Value("ha", 2550000000L, 'E', "Estimated value"));
        ProductionYear pYPoland2 = new ProductionYear(new Year(1991), new Value("tonnes", 1145544000000L, 'A', "Official figure"));
        ProductionYear pYPoland3 = new ProductionYear(new Year(2016), new Value("hg/ha", 52361000000L, 'A', "Official figure"));
        ProductionYear pYPoland4 = new ProductionYear(new Year(2004), new Value("tonnes", 56835000000L, 'A', "Official figure"));
        ProductionYear pYPoland5 = new ProductionYear(new Year(1980), new Value("tonnes", 19507000000L, 'A', "Official figure"));
        ProductionYear pYPoland6 = new ProductionYear(new Year(2016), new Value("tonnes", 129063000000L, 'A', "Official figure"));
        ProductionYear pYPoland7 = new ProductionYear(new Year(1995), new Value("hg/ha", 44706000000L, 'E', "Estimated value"));
        ProductionYear pYPoland8 = new ProductionYear(new Year(1991), new Value("hg/ha", 43210000000L, 'E', "Estimated value"));
        ProductionYear pYPoland9 = new ProductionYear(new Year(1996), new Value("ha", 5800000000L, 'E', "Estimated value"));
        ProductionYear pYPoland10 = new ProductionYear(new Year(2008), new Value("tonnes", 16156000000L, 'A', "Official figure"));
        ProductionYear pYPoland11 = new ProductionYear(new Year(2012), new Value("tonnes", 127055000000L, 'A', "Official figure"));
        ProductionYear pYPoland12 = new ProductionYear(new Year(1991), new Value("ha", 5424000000L, 'I', ""));
        ProductionYear pYPoland13 = new ProductionYear(new Year(1961), new Value("tonnes", 285000000000L, 'A', "Official figure"));
        ProductionYear pYPoland14 = new ProductionYear(new Year(1971), new Value("tonnes", 563200000000L, 'A', "Official figure"));
        ProductionYear pYPoland15 = new ProductionYear(new Year(1992), new Value("hg/ha", 46721000000L, 'E', "Estimated value"));
        ProductionYear pYPoland16 = new ProductionYear(new Year(2008), new Value("tonnes", 7857000000L, 'A', "Official figure"));


        ProductionYear pYSweden1 = new ProductionYear(new Year(2004), new Value("ha", 1380000000L, 'A', "Official figure"));
        ProductionYear pYSweden2 = new ProductionYear(new Year(1996), new Value("ha", 10000000L, 'I', "Imputed value"));
        ProductionYear pYSweden3 = new ProductionYear(new Year(2005), new Value("ha", 129000000L, 'I', "Imputed value"));
        ProductionYear pYSweden4 = new ProductionYear(new Year(2019), new Value("ha", 130000000L, 'A', "Official figure"));
        ProductionYear pYSweden5 = new ProductionYear(new Year(1999), new Value("tonnes", 18000000000L, 'A', "Official figure"));
        ProductionYear pYSweden6 = new ProductionYear(new Year(2019), new Value("tonnes", 90000000L, 'A', "Official figure"));
        ProductionYear pYSweden7 = new ProductionYear(new Year(2019), new Value("tonnes", 480000000L, 'A', "Official figure"));
        ProductionYear pYSweden8 = new ProductionYear(new Year(1998), new Value("hg/ha", 80000000000L, 'E', "Estimated value"));
        ProductionYear pYSweden9 = new ProductionYear(new Year(2007), new Value("hg/ha", 20000000000L, 'E', "Estimated value"));
        ProductionYear pYSweden10 = new ProductionYear(new Year(2015), new Value("hg/ha", 30000000000L, 'E', "Estimated value"));
        ProductionYear pYSweden11 = new ProductionYear(new Year(1999), new Value("hg/ha", 10085000000L, 'E', "Estimated value"));
        ProductionYear pYSweden12 = new ProductionYear(new Year(2001), new Value("hg/ha", 12500000000L, 'E', "Estimated value"));
        ProductionYear pYSweden13 = new ProductionYear(new Year(2020), new Value("hg/ha", 31667000000L, 'E', "Estimated value"));


        AVL<Area> areaAVLExpected2 = new AVL<>();

        areaAVLExpected2.insert(denmark);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(apples);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().insert(yield1);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().find(yield1).getElement().getProductionYearList().insert(pYDenmark5);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(raspberries);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().insert(yield2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(yield2).getElement().getProductionYearList().insert(pYDenmark6);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().insert(area_harvested1);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(area_harvested1).getElement().getProductionYearList().insert(pYDenmark2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(yield2).getElement().getProductionYearList().insert(pYDenmark7);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().insert(production1);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().find(production1).getElement().getProductionYearList().insert(pYDenmark3);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(area_harvested1).getElement().getProductionYearList().insert(pYDenmark1);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(blueberries);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(blueberries).getElement().getElementList().insert(production2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(blueberries).getElement().getElementList().find(production2).getElement().getProductionYearList().insert(pYDenmark4);

        areaAVLExpected2.insert(italy);
        areaAVLExpected2.find(italy).getElement().getItemList().insert(citrus);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().insert(yield3);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().find(yield3).getElement().getProductionYearList().insert(pYItaly6);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().find(yield3).getElement().getProductionYearList().insert(pYItaly8);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().insert(area_harvested2);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().find(area_harvested2).getElement().getProductionYearList().insert(pYItaly1);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().find(yield3).getElement().getProductionYearList().insert(pYItaly7);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().insert(production3);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().find(production3).getElement().getProductionYearList().insert(pYItaly2);
        areaAVLExpected2.find(italy).getElement().getItemList().insert(raspberriesI);
        areaAVLExpected2.find(italy).getElement().getItemList().find(raspberriesI).getElement().getElementList().insert(yield4);
        areaAVLExpected2.find(italy).getElement().getItemList().find(raspberriesI).getElement().getElementList().find(yield4).getElement().getProductionYearList().insert(pYItaly5);
        areaAVLExpected2.find(italy).getElement().getItemList().insert(blueberriesI);
        areaAVLExpected2.find(italy).getElement().getItemList().find(blueberriesI).getElement().getElementList().insert(yield5);
        areaAVLExpected2.find(italy).getElement().getItemList().find(blueberriesI).getElement().getElementList().find(yield5).getElement().getProductionYearList().insert(pYItaly4);
        areaAVLExpected2.find(italy).getElement().getItemList().find(citrus).getElement().getElementList().find(production3).getElement().getProductionYearList().insert(pYItaly3);


        areaAVLExpected2.insert(poland);
        areaAVLExpected2.find(poland).getElement().getItemList().insert(blueberriesP);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().insert(area_harvested7);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().find(area_harvested7).getElement().getProductionYearList().insert(pYPoland1);
        areaAVLExpected2.find(poland).getElement().getItemList().insert(applesP);
        areaAVLExpected2.find(poland).getElement().getItemList().find(applesP).getElement().getElementList().insert(production4);
        areaAVLExpected2.find(poland).getElement().getItemList().find(applesP).getElement().getElementList().find(production4).getElement().getProductionYearList().insert(pYPoland2);
        areaAVLExpected2.find(poland).getElement().getItemList().insert(gooseberries);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().insert(yield6);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().find(yield6).getElement().getProductionYearList().insert(pYPoland3);
        areaAVLExpected2.find(poland).getElement().getItemList().insert(raspberriesP);
        areaAVLExpected2.find(poland).getElement().getItemList().find(raspberriesP).getElement().getElementList().insert(production5);
        areaAVLExpected2.find(poland).getElement().getItemList().find(raspberriesP).getElement().getElementList().find(production5).getElement().getProductionYearList().insert(pYPoland4);
        areaAVLExpected2.find(poland).getElement().getItemList().find(raspberriesP).getElement().getElementList().find(production5).getElement().getProductionYearList().insert(pYPoland5);
        areaAVLExpected2.find(poland).getElement().getItemList().find(raspberriesP).getElement().getElementList().find(production5).getElement().getProductionYearList().insert(pYPoland6);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().insert(yield7);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().find(yield7).getElement().getProductionYearList().insert(pYPoland7);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().find(yield7).getElement().getProductionYearList().insert(pYPoland8);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().insert(area_harvested3);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().find(area_harvested3).getElement().getProductionYearList().insert(pYPoland9);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().insert(production10);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().find(production10).getElement().getProductionYearList().insert(pYPoland10);
        areaAVLExpected2.find(poland).getElement().getItemList().insert(raspberriesP);
        areaAVLExpected2.find(poland).getElement().getItemList().find(raspberriesP).getElement().getElementList().insert(production5);
        areaAVLExpected2.find(poland).getElement().getItemList().find(raspberriesP).getElement().getElementList().find(production5).getElement().getProductionYearList().insert(pYPoland11);
        areaAVLExpected2.find(poland).getElement().getItemList().find(gooseberries).getElement().getElementList().find(area_harvested3).getElement().getProductionYearList().insert(pYPoland12);
        areaAVLExpected2.find(poland).getElement().getItemList().find(applesP).getElement().getElementList().find(production4).getElement().getProductionYearList().insert(pYPoland13);
        areaAVLExpected2.find(poland).getElement().getItemList().find(applesP).getElement().getElementList().find(production4).getElement().getProductionYearList().insert(pYPoland14);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().insert(yield7);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().find(yield7).getElement().getProductionYearList().insert(pYPoland15);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().insert(production6);
        areaAVLExpected2.find(poland).getElement().getItemList().find(blueberriesP).getElement().getElementList().find(production6).getElement().getProductionYearList().insert(pYPoland16);


        areaAVLExpected2.insert(sweden);
        areaAVLExpected2.find(sweden).getElement().getItemList().insert(blueberriesS);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().insert(yield9);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().find(yield9).getElement().getProductionYearList().insert(pYSweden9);
        areaAVLExpected2.find(sweden).getElement().getItemList().insert(applesS);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(applesS).getElement().getElementList().insert(yield10);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(applesS).getElement().getElementList().find(yield10).getElement().getProductionYearList().insert(pYSweden8);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(applesS).getElement().getElementList().insert(area_harvested4);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(applesS).getElement().getElementList().find(area_harvested4).getElement().getProductionYearList().insert(pYSweden1);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().find(yield9).getElement().getProductionYearList().insert(pYSweden10);
        areaAVLExpected2.find(sweden).getElement().getItemList().insert(raspberriesS);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().insert(area_harvested5);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().find(area_harvested5).getElement().getProductionYearList().insert(pYSweden4);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().insert(yield11);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().find(yield11).getElement().getProductionYearList().insert(pYSweden13);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().find(area_harvested5).getElement().getProductionYearList().insert(pYSweden3);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().insert(production7);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().find(production7).getElement().getProductionYearList().insert(pYSweden7);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().find(yield11).getElement().getProductionYearList().insert(pYSweden11);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().insert(area_harvested6);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().find(area_harvested6).getElement().getProductionYearList().insert(pYSweden2);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(raspberriesS).getElement().getElementList().find(area_harvested5).getElement().getProductionYearList().insert(pYSweden12);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().insert(production8);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(blueberriesS).getElement().getElementList().find(production8).getElement().getProductionYearList().insert(pYSweden6);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(applesS).getElement().getElementList().insert(production9);
        areaAVLExpected2.find(sweden).getElement().getItemList().find(applesS).getElement().getElementList().find(production9).getElement().getProductionYearList().insert(pYSweden5);


        AVL<Area> areaAVlReal = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");

        assertTrue(areaAVLExpected2.equals(areaAVlReal));


        //Denmark was already tested in the previous test

        //ITALY
        AVL<Item> itemAVLItalyExpected = areaAVLExpected2.find(italy).getElement().getItemList();
        AVL<Item> itemAVLItalyReal = areaAVlReal.find(italy).getElement().getItemList();

        assertTrue(itemAVLItalyExpected.equals(itemAVLItalyReal));


        // testing citrus italy
        AVL<Element> elementAVLCitrusItalyExpected = itemAVLItalyExpected.find(citrus).getElement().getElementList();
        AVL<Element> elementAVLCitrusItalyReal = itemAVLItalyReal.find(citrus).getElement().getElementList();

        assertTrue(elementAVLCitrusItalyExpected.equals(elementAVLCitrusItalyReal));

        AVL<ProductionYear> elementAVLYieldCitrusItalyExpected = elementAVLCitrusItalyExpected.find(yield3).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldCitrusItalyReal = elementAVLCitrusItalyReal.find(yield3).getElement().getProductionYearList();

        assertTrue(elementAVLYieldCitrusItalyExpected.equals(elementAVLYieldCitrusItalyReal));

        AVL<ProductionYear> elementAVLAreaCitrusItalyExpected = elementAVLCitrusItalyExpected.find(area_harvested2).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaCitrusItalyReal = elementAVLCitrusItalyReal.find(area_harvested2).getElement().getProductionYearList();

        assertTrue(elementAVLAreaCitrusItalyExpected.equals(elementAVLAreaCitrusItalyReal));

        AVL<ProductionYear> elementAVLProductionCitrusItalyExpected = elementAVLCitrusItalyExpected.find(production3).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionCitrusItalyReal = elementAVLCitrusItalyReal.find(production3).getElement().getProductionYearList();

        assertTrue(elementAVLProductionCitrusItalyExpected.equals(elementAVLProductionCitrusItalyReal));


        // testing raspberries italy
        AVL<Element> elementAVLRaspberriesItalyExpected = itemAVLItalyExpected.find(raspberriesI).getElement().getElementList();
        AVL<Element> elementAVLRaspberriesItalyReal = itemAVLItalyReal.find(raspberriesI).getElement().getElementList();

        assertTrue(elementAVLRaspberriesItalyExpected.equals(elementAVLRaspberriesItalyReal));

        AVL<ProductionYear> elementAVLYieldRaspberriesItalyExpected = elementAVLRaspberriesItalyExpected.find(yield4).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldRaspberriesItalyReal = elementAVLRaspberriesItalyReal.find(yield4).getElement().getProductionYearList();

        assertTrue(elementAVLYieldRaspberriesItalyExpected.equals(elementAVLYieldRaspberriesItalyReal));


        // testing blueberries italy
        AVL<Element> elementAVLBlueberriesItalyExpected = itemAVLItalyExpected.find(blueberriesI).getElement().getElementList();
        AVL<Element> elementAVLBlueberriesItalyReal = itemAVLItalyReal.find(blueberriesI).getElement().getElementList();

        assertTrue(elementAVLBlueberriesItalyExpected.equals(elementAVLBlueberriesItalyReal));

        AVL<ProductionYear> elementAVLYieldBlueberriesItalyExpected = elementAVLBlueberriesItalyExpected.find(yield5).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldBlueberriesItalyReal = elementAVLBlueberriesItalyReal.find(yield5).getElement().getProductionYearList();

        assertTrue(elementAVLYieldBlueberriesItalyExpected.equals(elementAVLYieldBlueberriesItalyReal));


        //POLAND
        AVL<Item> itemAVLPolandExpected = areaAVLExpected2.find(poland).getElement().getItemList();
        AVL<Item> itemAVLPolandReal = areaAVlReal.find(poland).getElement().getItemList();

        assertTrue(itemAVLItalyExpected.equals(itemAVLItalyReal));


        // testing blueberries poland
        AVL<Element> itemAVLBlueberriesPolandExpected = itemAVLPolandExpected.find(blueberriesP).getElement().getElementList();
        AVL<Element> itemAVLBlueberriesPolandReal = itemAVLPolandReal.find(blueberriesP).getElement().getElementList();

        assertTrue(itemAVLBlueberriesPolandExpected.equals(itemAVLBlueberriesPolandReal));

        AVL<ProductionYear> elementAVLAreaBlueberriesPolandExpected = itemAVLBlueberriesPolandExpected.find(area_harvested2).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaBlueberriesPolandReal = itemAVLBlueberriesPolandReal.find(area_harvested2).getElement().getProductionYearList();

        assertTrue(elementAVLAreaBlueberriesPolandExpected.equals(elementAVLAreaBlueberriesPolandReal));


        AVL<ProductionYear> elementAVLYieldBlueberriesPolandExpected = itemAVLBlueberriesPolandExpected.find(yield7).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldBlueberriesPolandReal = itemAVLBlueberriesPolandReal.find(yield7).getElement().getProductionYearList();

        assertTrue(elementAVLYieldBlueberriesPolandExpected.equals(elementAVLYieldBlueberriesPolandReal));


        AVL<ProductionYear> elementAVLProductionBlueberriesPolandExpected = itemAVLBlueberriesPolandExpected.find(production6).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionBlueberriesPolandReal = itemAVLBlueberriesPolandReal.find(production6).getElement().getProductionYearList();

        assertTrue(elementAVLProductionBlueberriesPolandExpected.equals(elementAVLProductionBlueberriesPolandReal));


        // testing apples poland
        AVL<Element> itemAVLApplesPolandExpected = itemAVLPolandExpected.find(applesP).getElement().getElementList();
        AVL<Element> itemAVLApplesPolandReal = itemAVLPolandReal.find(applesP).getElement().getElementList();

        assertTrue(itemAVLApplesPolandExpected.equals(itemAVLApplesPolandReal));

        AVL<ProductionYear> elementAVLProductionApplesPolandExpected = itemAVLApplesPolandExpected.find(production4).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionApplesPolandReal = itemAVLApplesPolandReal.find(production4).getElement().getProductionYearList();

        assertTrue(elementAVLProductionApplesPolandExpected.equals(elementAVLProductionApplesPolandReal));


        // testing gooseberries poland
        AVL<Element> itemAVLGooseberriesPolandExpected = itemAVLPolandExpected.find(gooseberries).getElement().getElementList();
        AVL<Element> itemAVLGooseberriesPolandReal = itemAVLPolandReal.find(gooseberries).getElement().getElementList();

        assertTrue(itemAVLGooseberriesPolandExpected.equals(itemAVLGooseberriesPolandReal));

        AVL<ProductionYear> elementAVLYieldGooseberriesPolandExpected = itemAVLGooseberriesPolandExpected.find(yield6).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldGooseberriesPolandReal = itemAVLGooseberriesPolandReal.find(yield6).getElement().getProductionYearList();

        assertTrue(elementAVLYieldGooseberriesPolandExpected.equals(elementAVLYieldGooseberriesPolandReal));


        AVL<ProductionYear> elementAVLAreaGooseberriesPolandExpected = itemAVLGooseberriesPolandExpected.find(area_harvested3).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaGooseberriesPolandReal = itemAVLGooseberriesPolandReal.find(area_harvested3).getElement().getProductionYearList();

        assertTrue(elementAVLAreaGooseberriesPolandExpected.equals(elementAVLAreaGooseberriesPolandReal));


        AVL<ProductionYear> elementAVLProductionGooseberriesPolandExpected = itemAVLGooseberriesPolandExpected.find(production10).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionGooseberriesPolandReal = itemAVLGooseberriesPolandReal.find(production10).getElement().getProductionYearList();

        assertTrue(elementAVLProductionGooseberriesPolandExpected.equals(elementAVLProductionGooseberriesPolandReal));


        // testing raspberries poland
        AVL<Element> itemAVLRaspberriesPolandExpected = itemAVLPolandExpected.find(applesP).getElement().getElementList();
        AVL<Element> itemAVLRaspberriesPolandReal = itemAVLPolandReal.find(applesP).getElement().getElementList();

        assertTrue(itemAVLRaspberriesPolandExpected.equals(itemAVLRaspberriesPolandReal));

        AVL<ProductionYear> elementAVLProductionRaspberriesPolandExpected = itemAVLRaspberriesPolandExpected.find(production4).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionRaspberriesPolandReal = itemAVLRaspberriesPolandReal.find(production4).getElement().getProductionYearList();

        assertTrue(elementAVLProductionRaspberriesPolandExpected.equals(elementAVLProductionRaspberriesPolandReal));


        //SWEDEN
        AVL<Item> itemAVLSwedenExpected = areaAVLExpected2.find(sweden).getElement().getItemList();
        AVL<Item> itemAVLSwedenReal = areaAVlReal.find(sweden).getElement().getItemList();

        assertTrue(itemAVLSwedenExpected.equals(itemAVLSwedenReal));

        // testing blueberries sweden
        AVL<Element> itemAVLBlueberriesSwedenExpected = itemAVLSwedenExpected.find(blueberriesS).getElement().getElementList();
        AVL<Element> itemAVLSwedenSwedenReal = itemAVLSwedenReal.find(blueberriesS).getElement().getElementList();

        assertTrue(itemAVLBlueberriesSwedenExpected.equals(itemAVLSwedenSwedenReal));

        AVL<ProductionYear> elementAVLYieldBlueberriesSwedenExpected = itemAVLBlueberriesSwedenExpected.find(yield9).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldBlueberriesSwedenReal = itemAVLSwedenSwedenReal.find(yield9).getElement().getProductionYearList();

        assertTrue(elementAVLYieldBlueberriesSwedenExpected.equals(elementAVLYieldBlueberriesSwedenReal));


        AVL<ProductionYear> elementAVLAreaBlueberriesSwedenExpected = itemAVLBlueberriesSwedenExpected.find(area_harvested6).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaBlueberriesSwedenReal = itemAVLSwedenSwedenReal.find(area_harvested6).getElement().getProductionYearList();

        assertTrue(elementAVLAreaBlueberriesSwedenExpected.equals(elementAVLAreaBlueberriesSwedenReal));


        AVL<ProductionYear> elementAVLProductionBlueberriesSwedenExpected = itemAVLBlueberriesSwedenExpected.find(production8).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionBlueberriesSwedenReal = itemAVLSwedenSwedenReal.find(production8).getElement().getProductionYearList();

        assertTrue(elementAVLProductionBlueberriesSwedenExpected.equals(elementAVLProductionBlueberriesSwedenReal));


        // testing apples sweden
        AVL<Element> itemAVLApplesSwedenExpected = itemAVLSwedenExpected.find(applesS).getElement().getElementList();
        AVL<Element> itemAVLApplesSwedenReal = itemAVLSwedenReal.find(applesS).getElement().getElementList();

        assertTrue(itemAVLApplesSwedenExpected.equals(itemAVLApplesSwedenReal));

        AVL<ProductionYear> elementAVLYieldApplesSwedenExpected = itemAVLApplesSwedenExpected.find(yield9).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldApplesSwedenReal = itemAVLApplesSwedenReal.find(yield9).getElement().getProductionYearList();

        assertTrue(elementAVLYieldApplesSwedenExpected.equals(elementAVLYieldApplesSwedenReal));


        AVL<ProductionYear> elementAVLAreaApplesSwedenExpected = itemAVLApplesSwedenExpected.find(area_harvested6).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaApplesSwedenReal = itemAVLApplesSwedenReal.find(area_harvested6).getElement().getProductionYearList();

        assertTrue(elementAVLAreaApplesSwedenExpected.equals(elementAVLAreaApplesSwedenReal));


        AVL<ProductionYear> elementAVLProductionApplesSwedenExpected = itemAVLApplesSwedenExpected.find(production8).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionApplesSwedenReal = itemAVLApplesSwedenReal.find(production8).getElement().getProductionYearList();

        assertTrue(elementAVLProductionApplesSwedenExpected.equals(elementAVLProductionApplesSwedenReal));


        // testing raspberries sweden
        AVL<Element> itemAVLRaspberriesSwedenExpected = itemAVLSwedenExpected.find(applesS).getElement().getElementList();
        AVL<Element> itemAVLRaspberriesSwedenReal = itemAVLSwedenReal.find(applesS).getElement().getElementList();

        assertTrue(itemAVLRaspberriesSwedenExpected.equals(itemAVLRaspberriesSwedenReal));

        AVL<ProductionYear> elementAVLAreaRaspberriesSwedenExpected = itemAVLRaspberriesSwedenExpected.find(area_harvested5).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaARaspberriesSwedenReal = itemAVLRaspberriesSwedenReal.find(area_harvested5).getElement().getProductionYearList();

        assertTrue(elementAVLAreaRaspberriesSwedenExpected.equals(elementAVLAreaARaspberriesSwedenReal));


        AVL<ProductionYear> elementAVLYieldRaspberriesSwedenExpected = itemAVLRaspberriesSwedenExpected.find(yield11).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldRaspberriesSwedenReal = itemAVLRaspberriesSwedenReal.find(yield11).getElement().getProductionYearList();

        assertTrue(elementAVLYieldRaspberriesSwedenExpected.equals(elementAVLYieldRaspberriesSwedenReal));


        AVL<ProductionYear> elementAVLProductionRaspberriesSwedenExpected = itemAVLRaspberriesSwedenExpected.find(production7).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionRaspberriesSwedenReal = itemAVLRaspberriesSwedenReal.find(production7).getElement().getProductionYearList();

        assertTrue(elementAVLProductionRaspberriesSwedenExpected.equals(elementAVLProductionRaspberriesSwedenReal));
    }


    @Test
    public void testStoreDataFromFileProductionFileWithOnlyOneCountryDataMissing() {

        AreaCode denmark = new AreaCode(54, "'208", "Denmark");

        ItemCode apples = new ItemCode(515, "'01341", "Apples");
        ItemCode raspberries = new ItemCode(547, "'01353.01", "Raspberries");

        ElementCode area_harvested = new ElementCode(5312, "Area harvested");
        ElementCode production1 = new ElementCode(5510, "Production");
        ElementCode yield2 = new ElementCode(5419, "Yield");

        ProductionYear pYDenmark1 = new ProductionYear(new Year(1994), new Value("ha", 31000000L, 'I', "Imputed value"));
        ProductionYear pYDenmark2 = new ProductionYear(new Year(1995), new Value("ha", 34000000L, 'I', "Imputed value"));
        ProductionYear pYDenmark3 = new ProductionYear(new Year(1990), new Value("tonnes", 70000000000L, 'T', "Unofficial figure"));
        ProductionYear pYDenmark6 = new ProductionYear(new Year(1989), new Value("hg/ha", 50000000000L, 'E', "Estimated value"));
        ProductionYear pYDenmark7 = new ProductionYear(new Year(1990), new Value("hg/ha", 67073000000L, 'I', "Imputed value"));

        AVL<Area> areaAVLExpected2 = new AVL<>();

        areaAVLExpected2.insert(denmark);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(raspberries);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().insert(yield2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(yield2).getElement().getProductionYearList().insert(pYDenmark6);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().insert(area_harvested);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(area_harvested).getElement().getProductionYearList().insert(pYDenmark2);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(yield2).getElement().getProductionYearList().insert(pYDenmark7);
        areaAVLExpected2.find(denmark).getElement().getItemList().insert(apples);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().insert(production1);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(apples).getElement().getElementList().find(production1).getElement().getProductionYearList().insert(pYDenmark3);
        areaAVLExpected2.find(denmark).getElement().getItemList().find(raspberries).getElement().getElementList().find(area_harvested).getElement().getProductionYearList().insert(pYDenmark1);

        AVL<Area> areaAVlReal = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProductionDenmarkDataMissing.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");


        // verifying if all avl's are equal

        assertTrue(areaAVLExpected2.equals(areaAVlReal));

        AVL<Item> itemAVLDenmarkExpected = areaAVLExpected2.find(denmark).getElement().getItemList();
        AVL<Item> itemAVLDenmarkReal = areaAVlReal.find(denmark).getElement().getItemList();

        assertTrue(itemAVLDenmarkExpected.equals(itemAVLDenmarkReal));


        // testing apples denmark
        AVL<Element> elementAVLApplesDenmarkExpected = itemAVLDenmarkExpected.find(apples).getElement().getElementList();
        AVL<Element> elementAVLApplesDenmarkReal = itemAVLDenmarkReal.find(apples).getElement().getElementList();

        assertTrue(elementAVLApplesDenmarkExpected.equals(elementAVLApplesDenmarkReal));


        AVL<ProductionYear> elementAVLProductionApplesDenmarkExpected = elementAVLApplesDenmarkExpected.find(production1).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLProductionApplesDenmarkReal = elementAVLApplesDenmarkReal.find(production1).getElement().getProductionYearList();

        assertTrue(elementAVLProductionApplesDenmarkExpected.equals(elementAVLProductionApplesDenmarkReal));


        // testing raspberries denmark
        AVL<Element> elementAVLRaspberriesDenmarkExpected = itemAVLDenmarkExpected.find(raspberries).getElement().getElementList();
        AVL<Element> elementAVLRaspberriesDenmarkReal = itemAVLDenmarkReal.find(raspberries).getElement().getElementList();

        assertTrue(elementAVLRaspberriesDenmarkExpected.equals(elementAVLRaspberriesDenmarkReal));

        AVL<ProductionYear> elementAVLYieldRaspberriesDenmarkExpected = elementAVLRaspberriesDenmarkExpected.find(yield2).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLYieldRaspberriesDenmarkReal = elementAVLRaspberriesDenmarkReal.find(yield2).getElement().getProductionYearList();

        assertTrue(elementAVLYieldRaspberriesDenmarkExpected.equals(elementAVLYieldRaspberriesDenmarkReal));

        AVL<ProductionYear> elementAVLAreaRaspberriesDenmarkExpected = elementAVLRaspberriesDenmarkExpected.find(area_harvested).getElement().getProductionYearList();
        AVL<ProductionYear> elementAVLAreaRaspberriesDenmarkReal = elementAVLRaspberriesDenmarkReal.find(area_harvested).getElement().getProductionYearList();

        assertTrue(elementAVLAreaRaspberriesDenmarkExpected.equals(elementAVLAreaRaspberriesDenmarkReal));
    }


    @Test
    public void testTimeExecutionStoreDataFromFileProduction() {

        double startTime = System.nanoTime();
        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of largest file of production world: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        AVL<Area> areaAVl2 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_medium.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of medium file of production world: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        AVL<Area> areaAVl3 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of small file of production world: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        List<Three<Area, Float, Float>> countries = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading and inserting into list three from AVL large world file: " + elapsedTime / 1000000 + " ms" + "\n\n");


        startTime = System.nanoTime();
        AVL<Area> areaAVl4 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_FR_GER_IT_PT_SP_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of large file of production fr: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        AVL<Area> areaAVl5 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_FR_GER_IT_PT_SP_shuffle_medium.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of medium file of production fr: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        AVL<Area> areaAVl6 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_FR_GER_IT_PT_SP_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of small file of production fr: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        List<Three<Area, Float, Float>> countries2 = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl4);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading and inserting into list three from AVL large fr file: " + elapsedTime / 1000000 + " ms" + "\n\n");


        startTime = System.nanoTime();
        AVL<Area> areaAVl7 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of large file of production eu: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        AVL<Area> areaAVl8 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_medium.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of medium file of production eu: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        AVL<Area> areaAVl9 = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaCode", "itemcode", "elementcode");
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading of small file of production eu: " + elapsedTime / 1000000 + " ms");

        startTime = System.nanoTime();
        List<Three<Area, Float, Float>> countries3 = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl7);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;
        System.out.println("Execution time of reading and inserting into list three from AVL large eu file: " + elapsedTime / 1000000 + " ms" + "\n\n");
    }
}
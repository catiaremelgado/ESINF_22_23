package esinf.projeto2;

import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.Three;
import Projeto_2_ESINF.dataStructures.TwoDTree;
import Projeto_2_ESINF.fileReaderUtils.ReadDataFromFiles;
import Projeto_2_ESINF.model.Area;
import Projeto_2_ESINF.model.AreaCode;
import Projeto_2_ESINF.model.AreaName;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class TwoDTreeTest {

    @Test
    public void exactSearchTest() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);

        countryCoordinates.remove(sweden, 60.128161F, 18.643501F);

        Area countryFound = countryCoordinates.exactSearch(60.128161f, 18.643501f);

        assertEquals(sweden, countryFound);

    }

    @Test
    public void exactSearchTestNotFound() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        //countryCoordinates.fillTree(countries);

        Area countryFound = countryCoordinates.exactSearch(60f, 18f);

        assertNull(countryFound);

    }

    @Test
    public void exactSearchTestExactX() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");

        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        //countryCoordinates.fillTree(countries);

        Area countryFound = countryCoordinates.exactSearch(60.128161f, 18f);

        assertNull(countryFound);

    }

    @Test
    public void exactSearchTestExactY() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        //countryCoordinates.fillTree(countries);

        Area countryFound = countryCoordinates.exactSearch(60f, 18.643501f);

        assertNull(countryFound);

    }

    @Test
    public void searchInAreaTest() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        List<Area> expectedCountriesInArea = new ArrayList<>();
        expectedCountriesInArea.add(sweden);
        expectedCountriesInArea.add(poland);
        List<Area> countriesInArea = new ArrayList<>();
        countriesInArea = countryCoordinates.searchInArea(countriesInArea, 50f, 61f, 18f, 20f);

        for (int i = 0; i < expectedCountriesInArea.size(); i++) {
            assertEquals(expectedCountriesInArea.get(i), countriesInArea.get(i));
        }
    }

    @Test
    public void searchInAreaTestNotFound() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        List<Area> expectedCountriesInArea = new ArrayList<>();
        expectedCountriesInArea.add(sweden);
        expectedCountriesInArea.add(poland);
        List<Area> countriesInArea = new ArrayList<>();
        countriesInArea = countryCoordinates.searchInArea(countriesInArea, 1f, 2f, 3f, 4f);

        assertNull(countriesInArea);
    }

    @Test
    public void searchInAreaTestInX() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        List<Area> expectedCountriesInArea = new ArrayList<>();
        expectedCountriesInArea.add(sweden);
        expectedCountriesInArea.add(poland);
        List<Area> countriesInArea = new ArrayList<>();
        countriesInArea = countryCoordinates.searchInArea(countriesInArea, 50f, 61f, 3f, 4f);

        assertNull(countriesInArea);
    }

    @Test
    public void searchInAreaTestInY() {

        Area sweden = new AreaName(210, "'752", "Sweden");
        Area poland = new AreaName(173, "'616", "Poland");
        Area italy = new AreaName(106, "'380", "Italy");
        Area denmark = new AreaName(54, "'208", "Denmark");


        Three<Area, Float, Float> swe = new Three<>(sweden, 60.128161F, 18.643501F);
        Three<Area, Float, Float> pol = new Three<>(poland, 51.919438F, 19.145136F);
        Three<Area, Float, Float> it = new Three<>(italy, 41.87194F, 12.56738F);
        Three<Area, Float, Float> den = new Three<>(denmark, 56.26392F, 9.501785F);

        List<Three<Area, Float, Float>> countries = new ArrayList<>();
        countries.add(swe);
        countries.add(pol);
        countries.add(it);
        countries.add(den);

        TwoDTree<Area> countryCoordinates = new TwoDTree<Area>(countries);
        List<Area> expectedCountriesInArea = new ArrayList<>();
        expectedCountriesInArea.add(sweden);
        expectedCountriesInArea.add(poland);
        List<Area> countriesInArea = new ArrayList<>();
        countriesInArea = countryCoordinates.searchInArea(countriesInArea, 1f, 2f, 19f, 20f);

        assertNull(countriesInArea);
    }

    @Test
    public void searchInAreaWorldLargeAreaFoundNegativeTest() {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        List<Area> countriesInArea = new ArrayList<>();
        double startTime = System.nanoTime();
        //-14.235004,-51.92528
        countriesInArea = two.searchInArea(countriesInArea, -15f, -14f, -52f, -51f);
        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertEquals(0,countriesInArea.get(0).getAreaName().compareTo("Brazil"));

        System.out.println("Execution time of large file of World production: " + elapsedTime / 1000000 + " ms");

    }

    @Test
    public void searchInAreaWorldLargeAreaManyFoundTest() {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        List<Area> countriesInArea = new ArrayList<>();
        double startTime = System.nanoTime();
        countriesInArea = two.searchInArea(countriesInArea, 41f, 47f, 2f, 13f);
        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        assertEquals(0,countriesInArea.get(0).getAreaName().compareTo("France"));
        assertEquals(0,countriesInArea.get(1).getAreaName().compareTo("Switzerland"));
        assertEquals(0,countriesInArea.get(2).getAreaName().compareTo("Italy"));

        System.out.println("Execution time of large file of World production: " + elapsedTime / 1000000 + " ms");

    }

    @Test
    public void searchInAreaWorldLargeNoAreaFoundTest() {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        List<Area> countriesInArea = new ArrayList<>();
        double startTime = System.nanoTime();
        countriesInArea = two.searchInArea(countriesInArea, 0f, 0.1f, 0f, 0.1f);
        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertNull(countriesInArea);

        System.out.println("Execution time of large file of World production: " + elapsedTime / 1000000 + " ms");

    }

    /*
    Test to see if the selected nearest neighbour is being selected correctly
    */
    @Test
    public void nearestNeighbourSearchTest(){

        Area result;
        List<Three<Area, Float, Float>> coordinates = new ArrayList<>();

        Area sweden = new AreaCode(210, "'752", "Sweden");
        Area poland = new AreaCode(173, "'616", "Poland");
        Area italy = new AreaCode(106, "'380", "Italy");
        Area denmark = new AreaCode(54, "'208", "Denmark");

        coordinates.add (new Three<>(sweden, 60.128161f, 18.643501f));
        coordinates.add (new Three<>(poland, 51.919438f, 19.145136f));
        coordinates.add (new Three<>(italy, 41.87194f, 12.56738f));
        coordinates.add (new Three<>(denmark, 56.26392f, 9.501785f));

        TwoDTree<Area> matchingCoordinates= new TwoDTree<>(coordinates);
        result = matchingCoordinates.nearestNeighbourSearch(69.567876f, 17.8758690f);

        Area expected = sweden;

        assertEquals(0, expected.compareTo(result));
    }



    /*
    Test to see if the method returns null if it receives null parameters
    */
    @Test
    public void nearestNeighbourSearchNullTest(){

        Area result;
        List<Three<Area, Float, Float>> coordinates = new ArrayList<>();

        Area sweden = new AreaCode(210, "'752", "Sweden");
        Area poland = new AreaCode(173, "'616", "Poland");
        Area italy = new AreaCode(106, "'380", "Italy");
        Area denmark = new AreaCode(54, "'208", "Denmark");

        coordinates.add (new Three<>(sweden, 60.128161f, 18.643501f));
        coordinates.add (new Three<>(poland, 51.919438f, 19.145136f));
        coordinates.add (new Three<>(italy, 41.87194f, 12.56738f));
        coordinates.add (new Three<>(denmark, 56.26392f, 9.501785f));

        TwoDTree<Area> matchingCoordinates= new TwoDTree<>(coordinates);
        result = matchingCoordinates.nearestNeighbourSearch(null, null);

        assertNull(result);
    }

    /*
    Test to see if the method returns null if the 2DTree is empty
     */
    @Test
    public void nearestNeighbourSearchNullTreeTest(){

        Area result;
        List<Three<Area, Float, Float>> coordinates = new ArrayList<>();

        TwoDTree<Area> matchingCoordinates= new TwoDTree<>(coordinates);
        result = matchingCoordinates.nearestNeighbourSearch(12.9887887f, 34.847547f);

        assertNull(result);
    }

    /*
    Test to see if the selected area in case the x is exactly equal in 2 areas the method selects considering the y
     */
    @Test
    public void nearestNeighbourSearchExactXTest(){

        Area result;
        List<Three<Area, Float, Float>> coordinates = new ArrayList<>();

        Area sweden = new AreaCode(210, "'752", "Sweden");
        Area poland = new AreaCode(173, "'616", "Poland");
        Area italy = new AreaCode(106, "'380", "Italy");
        Area denmark = new AreaCode(54, "'208", "Denmark");

        coordinates.add (new Three<>(sweden, 51.919438f, 18.643501f));
        coordinates.add (new Three<>(poland, 51.919438f, 19.145136f));
        coordinates.add (new Three<>(italy, 41.87194f, 12.56738f));
        coordinates.add (new Three<>(denmark, 56.26392f, 9.501785f));

        TwoDTree<Area> matchingCoordinates= new TwoDTree<>(coordinates);
        result = matchingCoordinates.nearestNeighbourSearch(50.567876f, 18.9758690f);

        Area expected = poland;

        assertEquals(0, expected.compareTo(result));
    }

    /*
    Test to see if the selected area in case the Y is exactly equal in 2 areas the method selects considering the X
     */
    @Test
    public void nearestNeighbourSearchExactYTest(){

        Area result;
        List<Three<Area, Float, Float>> coordinates = new ArrayList<>();

        Area sweden = new AreaCode(210, "'752", "Sweden");
        Area poland = new AreaCode(173, "'616", "Poland");
        Area italy = new AreaCode(106, "'380", "Italy");
        Area denmark = new AreaCode(54, "'208", "Denmark");

        coordinates.add (new Three<>(sweden, 60.128161f, 18.643501f));
        coordinates.add (new Three<>(poland, 49.87194f, 19.145136f));
        coordinates.add (new Three<>(italy, 51.919438f, 19.145136f));
        coordinates.add (new Three<>(denmark, 56.26392f, 9.501785f));

        TwoDTree<Area> matchingCoordinates= new TwoDTree<>(coordinates);
        result = matchingCoordinates.nearestNeighbourSearch(50.9876545f, 20.5646546f);

        Area expected = italy;

        assertEquals(0, expected.compareTo(result));
    }
}


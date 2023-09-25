package esinf.projeto1;

import Projeto_1_ESINF.MinimumNumberOfCountriesForQ;
import Projeto_1_ESINF.Store_Data;
import Projeto_1_ESINF.info.CompletedInfo;
import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.TreeMap;

public class MinimumNumberOfCountriesForQTest extends TestCase {


    public void testMinimumNumberOfCountriesForQForMediumFile() throws FileNotFoundException {
        String in = "FicheiroMEDIUM.csv";
        Store_Data instance = new Store_Data();
        CompletedInfo allinfo = instance.storeInfo(in);

        /* when quantity is lower than the country with the higher production */
        /* for quantity 8959000-1 */
        Integer expected1 = 1;
        BigInteger quantity1 = new BigInteger("8959000").subtract(BigInteger.ONE);
        Integer real1 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(allinfo, quantity1);

        assertEquals(expected1, real1);

        /* when quantity it's one value bigger than the country with the biggest production
        (and there are still values in the list) */
        /* for quantity 8959000+1 */
        Integer expected2 = 2;
        BigInteger quantity2 = new BigInteger("8959000").add(BigInteger.ONE);
        Integer real2 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(allinfo, quantity2);

        assertEquals(expected2, real2);

        /* when quantity it's equal to a sum of the productions of the list (but there are still values
        it is expected the number of needed countries to increment one more time */
        /* for quantity 21465968 */
        Integer expected3 = 5;
        BigInteger quantity3 = new BigInteger("21465968");
        Integer real3 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(allinfo, quantity3);

        assertEquals(expected3, real3);

        /* when quantity is less one than the sum of all the production from all countries
        it is expected to have 10 (all countries) */
        /* for quantity 23383579 */
        Integer expected4 = 10;
        BigInteger quantity4 = new BigInteger("23383579");
        Integer real4 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(allinfo, quantity4);

        assertEquals(expected4, real4);

        /* when quantity is equal to the sum of all the production from all countries
        it is expected to have 0 */
        /* for quantity 23383581 */
        Integer expected5 = 0;
        BigInteger quantity5 = new BigInteger("23383581");
        Integer real5 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(allinfo, quantity5);

        assertEquals(expected5, real5);

        /* when quantity is bigger than the sum of all the production from all countries
        it is expected to have 0 */
        /* for quantity 23383582 */
        Integer expected6 = 0;
        BigInteger quantity6 = new BigInteger("23383582");
        Integer real6 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(allinfo, quantity6);

        assertEquals(expected6, real6);


    }

    public void testMinimumNumberOfCountriesForQWhenQuantityIsZeroOrLess() throws FileNotFoundException {
        String in = "Livro2.csv";
        Store_Data instance = new Store_Data();
        CompletedInfo example = instance.storeInfo(in);

        /* when there are productions in the file bigger than 0 it is expected 1 */
        /* for quantity 0 */
        Integer expected = 1;
        Integer real = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(example, BigInteger.ZERO);

        assertEquals(expected, real);


        String in2 = "Production0.csv";
        Store_Data instance2 = new Store_Data();
        CompletedInfo example2 = instance2.storeInfo(in2);

        /* when there are only 0 productions in the file it is expected 0 */
        /* for quantity 0 */
        Integer expected2 = 0;
        Integer real2 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(example2, BigInteger.ZERO);

        assertEquals(expected2, real2);


        /* when quantity is less than 0
        it is expected to obtain null because it's impossible to have an quantity less than 0 */
        /* for quantity -1 */
        String in3 = "FicheiroMEDIUM.csv";
        Store_Data instance3 = new Store_Data();
        CompletedInfo example3 = instance3.storeInfo(in3);
        Integer expected3 = null;
        Integer real3 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(example3, BigInteger.ZERO.subtract(BigInteger.ONE));

        assertEquals(expected3, real3);


    }

    public void testMinimumNumberOfCountriesForQWhenCompletedInfoIsEmptyOrNonExistent() throws FileNotFoundException {
        CompletedInfo example = new CompletedInfo();
        Integer real = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(example, new BigInteger("10"));

        String in = "abcdefg";
        Store_Data instance2 = new Store_Data();
        CompletedInfo example2 = instance2.storeInfo(in);

        Integer real2 = MinimumNumberOfCountriesForQ.MinimumNumberOfCountriesForQ(example2, new BigInteger("20"));

        /* test with empty */
        assertNull(real);
        /* test when file doesn't exist */
        assertNull(real2);
    }

    /* Test with small file */
    public void testProcessCompletedInfoForExercise3ForSmallFile() throws FileNotFoundException {
        String in = "Livro2.csv";
        Store_Data instance = new Store_Data();
        CompletedInfo example = instance.storeInfo(in);

        TreeMap<String, BigInteger> expected = new TreeMap<>();
        expected.put("Portugal", new BigInteger("273721").add(new BigInteger("258363")));
        expected.put("Spain", new BigInteger("917409"));

        TreeMap<String, BigInteger> real = MinimumNumberOfCountriesForQ.ProcessCompletedInfoForExercise3(example);

        assertEquals(expected, real);
    }

    /* Test with big file */
    public void testProcessCompletedInfoForExercise3ForBigFile() throws FileNotFoundException {

        String in = "FicheiroBIG.csv";
        Store_Data instance = new Store_Data();
        CompletedInfo example = instance.storeInfo(in);

        TreeMap<String, BigInteger> expected = new TreeMap<>();
        expected.put("Afghanistan", new BigInteger("2776780"));
        expected.put("Albania", new BigInteger("2004570"));
        expected.put("Algeria", new BigInteger("8436654"));
        expected.put("Angola", new BigInteger("57791205"));
        expected.put("Antigua and Barbuda", new BigInteger("8484"));
        expected.put("Argentina", new BigInteger("59280514"));
        expected.put("Armenia", new BigInteger("2415064"));
        expected.put("Australia", new BigInteger("31695382"));
        expected.put("Austria", new BigInteger("22112518"));
        expected.put("Azerbaijan", new BigInteger("5483201"));
        expected.put("Bahamas", new BigInteger("359232"));
        expected.put("Bahrain", new BigInteger("36731"));
        expected.put("Bangladesh", new BigInteger("41027854"));
        expected.put("Barbados", new BigInteger("54751"));
        expected.put("Belarus", new BigInteger("10120744"));
        expected.put("Belgium", new BigInteger("6314702"));
        expected.put("Belgium-Luxembourg", new BigInteger("11399812"));
        expected.put("Belize", new BigInteger("2372997"));
        expected.put("Benin", new BigInteger("811271"));
        expected.put("Bhutan", new BigInteger("343799"));
        expected.put("Bolivia (Plurinational State of)", new BigInteger("13345757"));
        expected.put("Bosnia and Herzegovina", new BigInteger("1684102"));
        expected.put("Brazil", new BigInteger("362525333"));
        expected.put("Brunei Darussalam", new BigInteger("41468"));
        expected.put("Bulgaria", new BigInteger("16014283"));
        expected.put("Burundi", new BigInteger("82519686"));
        expected.put("Cabo Verde", new BigInteger("379545"));
        expected.put("Cambodia", new BigInteger("7289203"));
        expected.put("Cameroon", new BigInteger("42005161"));
        expected.put("Canada", new BigInteger("30464277"));
        expected.put("Central African Republic", new BigInteger("5561230"));
        expected.put("Chile", new BigInteger("48859756"));
        expected.put("China, mainland", new BigInteger("1079675323"));
        expected.put("China, Taiwan Province of", new BigInteger("16437549"));
        expected.put("Colombia", new BigInteger("93374700"));
        expected.put("Comoros", new BigInteger("2583901"));
        expected.put("Congo", new BigInteger("2796468"));
        expected.put("Cook Islands", new BigInteger("39250"));
        expected.put("Costa Rica", new BigInteger("96760809"));
        expected.put("Côte d'Ivoire", new BigInteger("14265140"));
        expected.put("Croatia", new BigInteger("2013172"));
        expected.put("Cuba", new BigInteger("10974756"));
        expected.put("Cyprus", new BigInteger("881023"));
        expected.put("Czechia", new BigInteger("5042800"));
        expected.put("Czechoslovakia", new BigInteger("9224079"));
        expected.put("Democratic People's Republic of Korea", new BigInteger("31054028"));
        expected.put("Democratic Republic of the Congo", new BigInteger("22325591"));
        expected.put("Denmark", new BigInteger("4092592"));
        expected.put("Dominica", new BigInteger("2196035"));
        expected.put("Dominican Republic", new BigInteger("29651362"));
        expected.put("Ecuador", new BigInteger("256965391"));
        expected.put("Egypt", new BigInteger("47681164"));
        expected.put("El Salvador", new BigInteger("591161"));
        expected.put("Equatorial Guinea", new BigInteger("1081356"));
        expected.put("Estonia", new BigInteger("279178"));
        expected.put("Eswatini", new BigInteger("132278"));
        expected.put("Ethiopia", new BigInteger("7484846"));
        expected.put("Ethiopia PDR", new BigInteger("1985000"));
        expected.put("Fiji", new BigInteger("307275"));
        expected.put("Finland", new BigInteger("709916"));
        expected.put("France", new BigInteger("145709967"));
        expected.put("French Guyana", new BigInteger("86521"));
        expected.put("French Polynesia", new BigInteger("30549"));
        expected.put("Gabon", new BigInteger("636380"));
        expected.put("Gambia", new BigInteger("0")); //saved because in the file there's an actual 0
        expected.put("Georgia", new BigInteger("3100886"));
        expected.put("Germany", new BigInteger("102853021"));
        expected.put("Ghana", new BigInteger("1622385"));
        expected.put("Greece", new BigInteger("18795208"));
        expected.put("Grenada", new BigInteger("734075"));
        expected.put("Guadeloupe", new BigInteger("6147464"));
        expected.put("Guatemala", new BigInteger("79403798"));
        expected.put("Guinea", new BigInteger("8022141"));
        expected.put("Guinea-Bissau", new BigInteger("180985"));
        expected.put("Guyana", new BigInteger("619066"));
        expected.put("Haiti", new BigInteger("13693780"));
        expected.put("Honduras", new BigInteger("57271896"));
        expected.put("Hungary", new BigInteger("42967351"));
        expected.put("India", new BigInteger("806503621"));
        expected.put("Indonesia", new BigInteger("203218761"));
        expected.put("Iran (Islamic Republic of)", new BigInteger("94379216"));
        expected.put("Iraq", new BigInteger("3675833"));
        expected.put("Ireland", new BigInteger("884161"));
        expected.put("Israel", new BigInteger("11260895"));
        expected.put("Italy", new BigInteger("136954969"));
        expected.put("Jamaica", new BigInteger("7767204"));
        expected.put("Japan", new BigInteger("55827846"));
        expected.put("Jordan", new BigInteger("2480126"));
        expected.put("Kazakhstan", new BigInteger("3686817"));
        expected.put("Kenya", new BigInteger("52185667"));
        expected.put("Kiribati", new BigInteger("266580"));
        expected.put("Kuwait", new BigInteger("6"));
        expected.put("Kyrgyzstan", new BigInteger("3181195"));
        expected.put("Lao People's Democratic Republic", new BigInteger("8375975"));
        expected.put("Latvia", new BigInteger("810944"));
        expected.put("Lebanon", new BigInteger("13767823"));
        expected.put("Liberia", new BigInteger("5343426"));
        expected.put("Libya", new BigInteger("702197"));
        expected.put("Lithuania", new BigInteger("2565767"));
        expected.put("Luxembourg", new BigInteger("86441"));
        expected.put("Madagascar", new BigInteger("17027066"));
        expected.put("Malawi", new BigInteger("10799063"));
        expected.put("Malaysia", new BigInteger("24840900"));
        expected.put("Maldives", new BigInteger("134467"));
        expected.put("Mali", new BigInteger("4384445"));
        expected.put("Malta", new BigInteger("12107"));
        expected.put("Martinique", new BigInteger("10404162"));
        expected.put("Mauritius", new BigInteger("492322"));
        expected.put("Mexico", new BigInteger("128461563"));
        expected.put("Micronesia (Federated States of)", new BigInteger("52946"));
        expected.put("Montenegro", new BigInteger("61948"));
        expected.put("Morocco", new BigInteger("21587984"));
        expected.put("Mozambique", new BigInteger("10240973"));
        expected.put("Nepal", new BigInteger("4379393"));
        expected.put("Netherlands", new BigInteger("24064805"));
        expected.put("New Caledonia", new BigInteger("113597"));
        expected.put("New Zealand", new BigInteger("20570198"));
        expected.put("Nicaragua", new BigInteger("4892519"));
        expected.put("Niue", new BigInteger("15811"));
        expected.put("North Macedonia", new BigInteger("2851755"));
        expected.put("Norway", new BigInteger("2126959"));
        expected.put("Oman", new BigInteger("1366494"));
        expected.put("Pakistan", new BigInteger("24410887"));
        expected.put("Palestine", new BigInteger("274882"));
        expected.put("Panama", new BigInteger("44006240"));
        expected.put("Papua New Guinea", new BigInteger("41423885"));
        expected.put("Paraguay", new BigInteger("7598342"));
        expected.put("Peru", new BigInteger("30816097"));
        expected.put("Philippines", new BigInteger("250406901"));
        expected.put("Poland", new BigInteger("99392552"));
        expected.put("Portugal", new BigInteger("14615316"));
        expected.put("Puerto Rico", new BigInteger("4929410"));
        expected.put("Republic of Korea", new BigInteger("25759021"));
        expected.put("Republic of Moldova", new BigInteger("11240762"));
        expected.put("Réunion", new BigInteger("236424"));
        expected.put("Romania", new BigInteger("33747150"));
        expected.put("Russian Federation", new BigInteger("48433350"));
        expected.put("Rwanda", new BigInteger("67837680"));
        expected.put("Saint Lucia", new BigInteger("4223783"));
        expected.put("Saint Vincent and the Grenadines", new BigInteger("2731287"));
        expected.put("Samoa", new BigInteger("1321196"));
        expected.put("Sao Tome and Principe", new BigInteger("784491"));
        expected.put("Senegal", new BigInteger("854697"));
        expected.put("Serbia", new BigInteger("5552051"));
        expected.put("Serbia and Montenegro", new BigInteger("3084063"));
        expected.put("Seychelles", new BigInteger("91796"));
        expected.put("Singapore", new BigInteger("18627"));
        expected.put("Slovakia", new BigInteger("1460932"));
        expected.put("Slovenia", new BigInteger("2888211"));
        expected.put("Solomon Islands", new BigInteger("14507"));
        expected.put("Somalia", new BigInteger("4329748"));
        expected.put("South Africa", new BigInteger("43802614"));
        //expected.put("South Sudan", new BigInteger("0")); //not saved because all it's quantities are null
        expected.put("Spain", new BigInteger("72904615"));
        expected.put("Sudan", new BigInteger("7920627"));
        expected.put("Sudan (former)", new BigInteger("8449996"));
        expected.put("Suriname", new BigInteger("2768851"));
        expected.put("Sweden", new BigInteger("4835199"));
        expected.put("Switzerland", new BigInteger("20033614"));
        expected.put("Syrian Arab Republic", new BigInteger("13605269"));
        expected.put("Tajikistan", new BigInteger("4092613"));
        expected.put("Thailand", new BigInteger("86365271"));
        expected.put("Timor-Leste", new BigInteger("459977"));
        expected.put("Togo", new BigInteger("976888"));
        expected.put("Tokelau", new BigInteger("855"));
        expected.put("Tonga", new BigInteger("186830"));
        expected.put("Trinidad and Tobago", new BigInteger("327400"));
        expected.put("Tunisia", new BigInteger("3641144"));
        expected.put("Türkiye", new BigInteger("129856222"));
        expected.put("Turkmenistan", new BigInteger("1310455"));
        expected.put("Tuvalu", new BigInteger("11865"));
        expected.put("Uganda", new BigInteger("10299000"));
        expected.put("Ukraine", new BigInteger("29578969"));
        expected.put("United Arab Emirates", new BigInteger("8327"));
        expected.put("United Kingdom of Great Britain and Northern Ireland", new BigInteger("21470170"));
        expected.put("United Republic of Tanzania", new BigInteger("63063277"));
        expected.put("United States of America", new BigInteger("254898106"));
        expected.put("Uruguay", new BigInteger("2463629"));
        expected.put("USSR", new BigInteger("165021000"));
        expected.put("Uzbekistan", new BigInteger("20106443"));
        expected.put("Vanuatu", new BigInteger("569793"));
        expected.put("Venezuela (Bolivarian Republic of)", new BigInteger("48620528"));
        expected.put("Viet Nam", new BigInteger("67063365"));
        expected.put("Yemen", new BigInteger("4097603"));
        expected.put("Yugoslav SFR", new BigInteger("14165557"));
        expected.put("Zambia", new BigInteger("44854"));
        expected.put("Zimbabwe", new BigInteger("5111844"));
        TreeMap<String, BigInteger> real = MinimumNumberOfCountriesForQ.ProcessCompletedInfoForExercise3(example);

        assertEquals(expected, real);
    }
}
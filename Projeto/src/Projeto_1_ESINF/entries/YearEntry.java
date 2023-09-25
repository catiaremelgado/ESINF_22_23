package Projeto_1_ESINF.entries;

/**
 * entry of a list of years
 */
public class YearEntry {
    /**
     * year of entry
     */
    private int year;

    /**
     * quantity produced in that year
     */
    private int quantity;

    /**
     * ocnstructor for YearEntry, year number and quantity produced
     * @param year year of the entry
     * @param quantity quantity produced in the year
     */
    public YearEntry(int year, int quantity) {
        this.year = year;
        this.quantity = quantity;
    }

    /**
     * obtain value of the year
     * @return int that represents the year in the entry
     */
    public int getYear() {
        return year;
    }

    /**
     * alters the year in the entry
     * @param year year of the entry
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * obtain quantity of fruit produced in that year
     * @return int that represents quantity of fruit produced in that year
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * alters quantity of fruit produced in that year
     * @param quantity quanitty of fruit produced in that year
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

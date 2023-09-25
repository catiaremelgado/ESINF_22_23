package Projeto_2_ESINF.model;

/* production file */
public class Year implements Comparable<Year> {

    private Integer year;

    /**
     * Constructor for Year
     *
     * @param year the year of Year
     */
    public Year(Integer year) {
        this.year = year;
    }

    /**
     * Getter for the year of Year
     *
     * @return year of the current Year object
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Method to compare two Years using their year (Integers)
     *
     * @param o another object Year
     * @return 1 if the current Year is bigger than another year, 0 if they are equal or -1 otherwise
     */
    @Override
    public int compareTo(Year o) {
        return this.year.compareTo(o.year);
    }
}

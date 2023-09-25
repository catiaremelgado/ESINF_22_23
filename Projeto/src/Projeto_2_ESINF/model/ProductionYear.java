package Projeto_2_ESINF.model;

public class ProductionYear implements Comparable<ProductionYear> {

    private Year year;
    private Value value;

    /**
     * Constructor of ProductionYear with all attributes
     *
     * @param year  the year of ProductionYear
     * @param value the value of ProductionYear
     */
    public ProductionYear(Year year, Value value) {
        this.year = year;
        this.value = value;
    }

    /**
     * Constructor of ProductionYear given just the Year
     *
     * @param year the given Year to ProductionYear
     */
    public ProductionYear(Year year) {
        this.year = year;
    }

    /**
     * Constructor of ProductionYear given another ProductionYear
     *
     * @param productionYear the given ProductionYear
     */
    public ProductionYear(ProductionYear productionYear) {
        this.year = productionYear.getYear();
        this.value = productionYear.getValue();
    }

    /**
     * Getter to get the Year of the current ProductionYear
     *
     * @return the Year of the current ProductionYear
     */
    public Year getYear() {
        return year;
    }

    /**
     * Getter to get the Value of the current ProductionYear
     *
     * @return the Value of the current ProductionYear
     */
    public Value getValue() {
        return value;
    }

    /**
     * Compares the ProductionYears (this is, the years)
     *
     * @param o another ProductionYear
     * @return 1 if the current ProductionYear is bigger than the another ProductionYear, 0 if both are equal, -1 otherwise
     */
    @Override
    public int compareTo(ProductionYear o) {
        return this.year.compareTo(o.getYear());
    }
}

package Projeto_2_ESINF.model;

/* production and flag files */
public class Value implements Comparable<Value> {

    /**
     * Note: Value is the same as Production
     */
    private String unit;
    private Long value;
    private char flag;
    private String flagDescription;

    /**
     * Constructor with all Value's attributes
     *
     * @param unit            the unit of the Value
     * @param value           the value of the Value
     * @param flag            the flag of the Value
     * @param flagDescription the description of the flag of Value
     */
    public Value(String unit, Long value, char flag, String flagDescription) {
        this.unit = unit;
        this.value = value;
        this.flag = flag;
        this.flagDescription = flagDescription;
    }

    /**
     * Getter for the unit of Value
     *
     * @return the unit of the current Value object
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Getter for the value of Value
     *
     * @return the value of the current Value object
     */
    public Long getValue() {
        return value;
    }

    /**
     * Getter for the flag of Value
     *
     * @return the flag of the current Value object
     */
    public char getFlag() {
        return flag;
    }

    /**
     * Getter for the flag description of Value
     *
     * @return the flag description of the current Value object
     */
    public String getFlagDescription() {
        return flagDescription;
    }

    /**
     * Method to compare two Values using their values (Integers)
     *
     * @param o another object Value
     * @return 1 if the current Value is bigger than another value, 0 if they are equal or -1 otherwise
     */
    @Override
    public int compareTo(Value o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return "Value{" +
                "unit='" + unit + '\'' +
                ", value=" + value +
                ", flag=" + flag +
                ", flagDescription='" + flagDescription + '\'' +
                '}';
    }
}

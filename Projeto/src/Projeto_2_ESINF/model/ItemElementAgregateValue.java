package Projeto_2_ESINF.model;

import Projeto_2_ESINF.model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @author João Miguel
 */
public class ItemElementAgregateValue {

    private Year min, max;
    private Item item;
    private Element element;
    private List<Value> values;

    public ItemElementAgregateValue(Year min, Year max, Item item, Element element) {
        this.min = min;
        this.max = max;
        this.item = item;
        this.element = element;
        this.values = new ArrayList<>();
    }

    public Year getMin() {
        return min;
    }

    public Year getMax() {
        return max;
    }

    public Item getItem() {
        return item;
    }

    public Element getElement() {
        return element;
    }

    /**
     * Method to add a value to values List
     * @param value The list of values
     */
    public void addValue(Value value) {
        this.values.add(value);
    }

    /**
     * Method to calculate the avg
     * @return average of the values
     */
    public double avg() {
        long sum = 0;

        for (Value v : this.values) {
            sum += v.getValue();
        }

        return (double)sum / this.values.size();
    }

    /**
     * Method to check if the values List is empty
     * @return returns true/false (depends if the list is empty or not)
     */
    public boolean isEmpty() {
        return this.values.size() == 0;
    }

}

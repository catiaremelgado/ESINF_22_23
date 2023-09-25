package Projeto_2_ESINF.model;

import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.DataTypeInterface;

/* production file */
public abstract class Item implements DataTypeInterface<Item> {
    private Integer itemCode;
    private String itemCodeCPC;
    private String itemName;

    private AVL<Element> elementList;

    /**
     * Constructor with all Item's attributes
     *
     * @param itemCode    the item's code
     * @param itemCodeCPC the item's code CPC
     * @param itemName    the item's name
     */
    public Item(Integer itemCode, String itemCodeCPC, String itemName) {
        this.itemCode = itemCode;
        this.itemCodeCPC = itemCodeCPC;
        this.itemName = itemName;
        this.elementList = new AVL<>();
    }

    /**
     * Constructor with just the item code of the Item
     *
     * @param itemCode item's code
     */
    public Item(Integer itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * Getter for the item code
     *
     * @return item's code
     */
    public Integer getItemCode() {
        return itemCode;
    }

    /**
     * Getter for the item's CPC code
     *
     * @return item's CPC code
     */
    public String getItemCodeCPC() {
        return itemCodeCPC;
    }

    /**
     * Getter for item's name
     *
     * @return item's name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Getter for the AVL of the object Item
     *
     * @return the AVL of the object Item
     */
    public AVL<Element> getElementList() {
        return elementList;
    }

    /**
     * Method to compare two items using their item codes
     *
     * @param o another object Item
     * @return 1 if the current Item is bigger than another item, 0 if they are equal or -1 otherwise
     */
    @Override
    public int compareTo(Item o) {
        if (this.itemCode > o.itemCode) {
            return 1;
        } else if (this.itemCode < o.itemCode) {
            return -1;
        } else {
            return 0;
        }
    }
}

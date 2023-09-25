package Projeto_2_ESINF.model;

import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.DataTypeInterface;

/* production file */
public abstract class Element implements DataTypeInterface<Element> {

    private Integer elementCode;
    private String elementName;

    private AVL<ProductionYear> productionYearList;

    /**
     * Constructor of Element with all attributes
     *
     * @param elementCode the code of the element
     * @param elementName the element's name
     */
    public Element(Integer elementCode, String elementName) {
        this.elementCode = elementCode;
        this.elementName = elementName;
        this.productionYearList = new AVL<>();
    }

    /**
     * Constructor of an Element with just the element code
     *
     * @param elementCode the code of the element
     */
    public Element(Integer elementCode) {
        this.elementCode = elementCode;
    }

    /**
     * Getter for the element's code
     *
     * @return the element's code
     */
    public Integer getElementCode() {
        return elementCode;
    }

    /**
     * Getter for the element's name
     *
     * @return the element's name
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Getter for the AVL of the object Element
     *
     * @return the AVL of the object Element
     */
    public AVL<ProductionYear> getProductionYearList() {
        return productionYearList;
    }

    /**
     * Method to compare two elements using their element codes
     *
     * @param o another object Element
     * @return 1 if the current Element is bigger than another element, 0 if they are equal or -1 otherwise
     */
    @Override
    public int compareTo(Element o) {
        if (this.elementCode > o.elementCode) {
            return 1;
        } else if (this.elementCode < o.elementCode) {
            return -1;
        } else {
            return 0;
        }
    }
}

package Projeto_2_ESINF.model;

import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.DataTypeInterface;

/* production file and coordinates file */
public abstract class Area implements DataTypeInterface<Area> {

    private Integer areaCode;
    private String codeM49;
    private String areaName;

   private AVL<Item> itemList;

    /**
     * Constructor for Area with all attributes
     *
     * @param areaCode the code of the area
     * @param codeM49  the code m49 of the area
     * @param areaName the name of the area
     */
    public Area(Integer areaCode, String codeM49, String areaName) {
        this.areaCode = areaCode;
        this.codeM49 = codeM49;
        this.areaName = areaName;
        this.itemList = new AVL<>();
    }

    /**
     * Constructor of Area just with the area's code
     *
     * @param areaCode the code of the area
     */
    public Area(Integer areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * Constructor of Area just with the area's name
     *
     * @param areaName the name of the area
     */
    public Area(String areaName) {
        this.areaName = areaName;
    }

    /**
     * constructor to copy area values to another area object
     *
     * @param area area to copy
     */
    public Area(Area area) {
        this.areaCode = area.getAreaCode();
        this.codeM49 = area.getCodeM49();
        this.areaName = area.getAreaName();
    }

    /**
     * Getter for the area code of the object Area
     *
     * @return the area code of the object Area
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**
     * Getter for the code M49 of the object Area
     *
     * @return the code M49 of the object Area
     */
    public String getCodeM49() {
        return codeM49;
    }

    /**
     * Getter for the area name of the object Area
     *
     * @return the area name of the object Area
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Getter for the AVL of the object Area
     *
     * @return the AVL of the object Area
     */
    public AVL<Item> getItemList() {
        return itemList;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public void setCodeM49(String codeM49) {
        this.codeM49 = codeM49;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public abstract int compareTo(Area o);
}

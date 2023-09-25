package Projeto_2_ESINF.model;

public class AreaName extends Area {

    /**
     * Constructor for Area with all attributes
     *
     * @param areaCode the code of the area
     * @param codeM49  the code m49 of the area
     * @param areaName the name of the area
     */
    public AreaName(Integer areaCode, String codeM49, String areaName) {
        super(areaCode, codeM49, areaName);
    }

    @Override
    public int compareTo(Area o) {
        if (super.getAreaName().contains(o.getAreaName()) || o.getAreaName().contains(super.getAreaName())) {
            return 0;
        }
        return super.getAreaName().compareTo(o.getAreaName());
    }

    @Override
    public String getDataName() {
        return super.getAreaName();
    }

    @Override
    public Integer getDataCode() {
        return super.getAreaCode();
    }

    @Override
    public String getDataAlternativeCode() {
        return super.getCodeM49();
    }

    @Override
    public Area getDataType() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AreaName) {
            AreaName aN = (AreaName) obj;
            return this.getAreaName().equals(aN.getDataName());
        }
        return false;
    }
}

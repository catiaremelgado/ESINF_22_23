package Projeto_2_ESINF.model;

public class AreaCode extends Area {


    /**
     * Constructor for Area with all attributes
     *
     * @param areaCode the code of the area
     * @param codeM49  the code m49 of the area
     * @param areaName the name of the area
     */
    public AreaCode(Integer areaCode, String codeM49, String areaName) {
        super(areaCode, codeM49, areaName);
    }

    @Override
    public int compareTo(Area o) {
        return super.getAreaCode().compareTo(o.getAreaCode());
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
        if(obj instanceof AreaCodeM49) {
            AreaCode a = (AreaCode) obj;
            return this.getAreaCode().equals(a.getDataCode());
        }
        return false;
    }
}

package Projeto_2_ESINF.model;

public class AreaCodeM49 extends Area {


    /**
     * Constructor for Area with all attributes
     *
     * @param areaCode the code of the area
     * @param codeM49  the code m49 of the area
     * @param areaName the name of the area
     */
    public AreaCodeM49(Integer areaCode, String codeM49, String areaName) {
        super(areaCode, codeM49, areaName);
    }

    @Override
    public int compareTo(Area o) {
        return super.getCodeM49().compareTo(o.getCodeM49());
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
            AreaCodeM49 a49 = (AreaCodeM49) obj;
            return this.getCodeM49().equals(a49.getDataAlternativeCode());
        }
        return false;
    }
}

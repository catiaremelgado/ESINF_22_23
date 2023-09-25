package Projeto_2_ESINF.model;

public class ElementCode extends Element {

    public ElementCode(Integer elementCode, String elementName) {
        super(elementCode, elementName);
    }

    public ElementCode(int elementCode) {
        super(elementCode);
    }

    @Override
    public int compareTo(Element o) {
        return super.getElementCode().compareTo(o.getElementCode());
    }

    @Override
    public String getDataName() {
        return super.getElementName();
    }

    @Override
    public Integer getDataCode() {
        return super.getElementCode();
    }

    @Override
    public String getDataAlternativeCode() {
        return null;
    }

    @Override
    public Element getDataType() {
        return this;
    }
}


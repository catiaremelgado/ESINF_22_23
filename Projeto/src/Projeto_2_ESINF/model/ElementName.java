package Projeto_2_ESINF.model;

public class ElementName extends Element {

    public ElementName(Integer elementCode, String elementName) {
        super(elementCode, elementName);
    }

    @Override
    public int compareTo(Element o) {
        return super.getElementName().compareTo(o.getElementName());
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


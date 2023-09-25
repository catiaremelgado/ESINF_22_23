package Projeto_2_ESINF.model;

public class ItemName extends Item {

    public ItemName(Integer itemCode, String itemCodeCPC, String itemName) {
        super(itemCode, itemCodeCPC, itemName);
    }

    @Override
    public int compareTo(Item o) {
        return super.getItemName().compareTo(o.getItemName());
    }

    @Override
    public String getDataName() {
        return super.getItemName();
    }

    @Override
    public Integer getDataCode() { return super.getItemCode(); }

    @Override
    public String getDataAlternativeCode() { return super.getItemCodeCPC(); }

    @Override
    public Item getDataType() {
        return this;
    }
}

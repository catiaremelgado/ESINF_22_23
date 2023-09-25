package Projeto_2_ESINF.model;

public class ItemCode extends Item {

    public ItemCode(Integer itemCode, String itemCodeCPC, String itemName) {
        super(itemCode, itemCodeCPC, itemName);
    }

    public ItemCode(int itemCode) {
        super(itemCode);
    }

    @Override
    public int compareTo(Item o) {
        return super.getItemCode().compareTo(o.getItemCode());
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


package Projeto_2_ESINF.model;

public class ItemCodeCPC extends Item {


    public ItemCodeCPC(Integer itemCode, String itemCodeCPC, String itemName) {
        super(itemCode, itemCodeCPC, itemName);
    }

    @Override
    public int compareTo(Item o) {
        return super.getItemCodeCPC().compareTo(o.getItemCodeCPC());
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

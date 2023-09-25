package Projeto_2_ESINF.dataStructures;

public interface DataTypeInterface<E> extends Comparable<E> {

    public String getDataName();

    public Integer getDataCode();

    public String getDataAlternativeCode();

    public E getDataType();

}

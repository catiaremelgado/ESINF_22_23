package Projeto_1_ESINF.iterators;


import Projeto_1_ESINF.entries.FruitEntry;
import Projeto_1_ESINF.info.CountryInfo;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author alexandraipatova
 * Iterator of fruit info list
 * @param <T> Iterated item
 */
public class FruitInfoIterator<T> implements Iterator<T> {
    /**
     * current map entry
     */
    Map.Entry<String, CountryInfo> current;

    /**
     * list ot be iterated
     */
    TreeMap<String, CountryInfo> list;

    /**
     * constructor which receives a list with specific key and value type
     * @param list list to be iterated
     */
    public FruitInfoIterator(TreeMap<String, CountryInfo> list) {
        this.list=list;
        current=list.firstEntry();
    }

    /**
     * check if there is a next element in the list
     * @return false if there is no next element, otherwise return true
     */
    public boolean hasNext()
    {
        return current != null;
    }

    /**
     * returns the next element and advances the pointer
     * @return T type value (which must be able to be constructed from FruitEntry)
     */
    public T next()
    {

        FruitEntry ce=new FruitEntry(current.getKey(), current.getValue());

        T data =(T) ce;
        current = list.higherEntry(current.getKey());
        return data;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}



package Projeto_1_ESINF.iterators;

import Projeto_1_ESINF.entries.YearEntry;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author alexandraipatova
 * Iterator of year info list
 * @param <T> Iterated item
 */
public class YearInfoIterator<T> implements Iterator<T> {
    /**
     * current map entry
     */
    Map.Entry<Integer, Integer> current;
    /**
     * list to iterate
     */
    TreeMap<Integer,Integer> list;

    /**
     * constructor which receives a list with specific key and value type
     * @param list list to be iterated
     */
    public YearInfoIterator(TreeMap<Integer,Integer> list) {
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
     * @return T type value (which must be able to be constructed from YearEntry)
     */
    public T next()
    {
        YearEntry ye=new YearEntry(current.getKey(),current.getValue());
        T data =(T) ye;
        current = list.higherEntry(current.getKey());
        return data;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}

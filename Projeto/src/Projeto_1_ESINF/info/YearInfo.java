package Projeto_1_ESINF.info;

import Projeto_1_ESINF.entries.YearEntry;
import Projeto_1_ESINF.iterators.YearInfoIterator;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;

public class YearInfo implements Iterable<YearEntry>{ //implements Iterable<FruitEntry>{
    private TreeMap<Integer, Integer > yearInfo;

    public YearInfo() {
        this.yearInfo = new TreeMap<>();
    }


    public void add(int year, int quantity) {
        yearInfo.put(year, quantity);
    }


    public TreeMap<Integer, Integer> getYearInfo() {
        return yearInfo;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      if(!(o instanceof YearInfo)) return false;
      YearInfo yearInfo1 = (YearInfo) o;
      return Objects.equals(yearInfo, yearInfo1.yearInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearInfo);
    }

    public Iterator<YearEntry> iterator() {
        return new YearInfoIterator<>(this.yearInfo);
    }
}

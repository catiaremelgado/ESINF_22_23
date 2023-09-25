package Projeto_2_ESINF.dataStructures;

import Projeto_2_ESINF.model.AreaName;

import java.util.Comparator;

/**
 * @author alexandraipatova
 */
public class Three<T, U extends Comparable<U>, S extends Comparable<S>> {

    private T left;
    private U middle;
    private S right;


    public Three(T left, U middle, S right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public S getRight() {
        return right;
    }

    public void setRight(S right) {
        this.right = right;
    }

    public U getMiddle() {
        return middle;
    }

    public void setMiddle(U middle) {
        this.middle = middle;
    }

    /**
     * Comparator for the left element, where the right or middle element don't matter or taken into account
     *
     * @param <E> Left element of Three set
     */
    static class LeftComparator<E extends Comparable<E>> implements Comparator<Three<E, ?, ?>> {
        @Override
        public int compare(Three<E, ?, ?> o1, Three<E, ?, ?> o2) {
            return o1.getLeft().compareTo(o2.getLeft());
        }
    }

    /**
     * Comparator for the middle element, where the left or right element don't matter or taken into account
     *
     * @param <E> middle element of Three set
     */
    static class MiddleComparator<E extends Comparable<E>> implements Comparator<Three<?, E, ?>> {
        @Override
        public int compare(Three<?, E, ?> o1, Three<?, E, ?> o2) {
            return o1.getMiddle().compareTo(o2.getMiddle());
        }
    }

    /**
     * Comparator for the right element, where the left or middle element don't matter or taken into account
     *
     * @param <E> right element of Three set
     */
    static class RightComparator<E extends Comparable<E>> implements Comparator<Three<?, ?, E>> {

        @Override
        public int compare(Three<?, ?, E> o1, Three<?, ?, E> o2) {
            return o1.getRight().compareTo(o2.getRight());
        }
    }


    @Override
    public boolean equals(Object obj) {
        Three<T, U, S> newo = (Three<T, U, S>) obj;
        if (newo.left instanceof AreaName && this.left instanceof AreaName) {
            AreaName left = (AreaName) this.left;
            AreaName leftNewo = (AreaName) newo.left;
            return left.equals(leftNewo) && this.middle.equals(newo.middle) && this.right.equals(newo.right);
        }
        return this.left.equals(newo.left) && this.middle.equals(newo.middle) && this.right.equals(newo.right);
    }
}

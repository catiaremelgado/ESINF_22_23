package Projeto_2_ESINF.dataStructures;

import java.lang.Comparable;

public class Pair<T , U extends Comparable> implements Comparable<Pair<T, U>> {

    private T left;
    private U right;

    public Pair(T left, U right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public U getRight() {
        return right;
    }

    public void setRight(U right) {
        this.right = right;
    }

    @Override
    public int compareTo(Pair<T, U> o) {
        return o.getRight().compareTo(right);
    }
}

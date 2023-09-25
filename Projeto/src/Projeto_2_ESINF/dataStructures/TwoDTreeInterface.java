package Projeto_2_ESINF.dataStructures;

import java.util.List;

public interface TwoDTreeInterface<E> {


    void remove(E data, Float x, Float y);


    int size(TwoDTree.TwoDNode<E> node);

    int height(TwoDTree.TwoDNode<E> node);

    E exactSearch(Float x, Float y);

    List<E> searchInArea(List<E> listInArea, Float inicialX, Float finalX, Float inicialY, Float finalY);
    E nearestNeighbourSearch(Float x, Float y);


}

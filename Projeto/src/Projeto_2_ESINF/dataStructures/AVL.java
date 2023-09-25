/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_2_ESINF.dataStructures;


/**
 * @param <E>
 * @author DEI-ESINF
 */
public class AVL<E extends Comparable<E>> extends BST<E> {
    /**
     * https://www.happycoders.eu/algorithms/avl-tree-java/
     * https://www.javatpoint.com/avl-tree
     * o fator de balanceamento tem como fim ver
     * para que lado a árvore está desiquilibrada
     *
     * @param node
     * @return diferença entre o lado direito e o lado esquerdo da árvore
     */
    private int balanceFactor(Node<E> node) {
        if (node == null) return 0;
        return height(node.getRight()) - height(node.getLeft());
    }

    /**
     * Quando a rotação é simples, ocorre sempre na direção oposta
     * se a árvore está desiquilibrada á esquerda, então roda para a direita e vice-versa
     *
     * @param node
     * @return left
     */
    private Node<E> rightRotation(Node<E> node) {
        Node<E> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param node
     * @return right
     */
    private Node<E> leftRotation(Node<E> node) {
        Node<E> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;

    }

    /**
     * As duas ocorrem da seguinte forma:
     * a primeira rotação é a tender para o desiquilibrio
     * e depois faz mais uma rotação que é a tender para o equilibrio.
     *
     * @param node
     * @return node
     */
    //para balancear o node ele tem de verificar se o balance factor da direita é maior que o da esquerda
    private Node<E> twoRotations(Node<E> node) {
        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else if (balanceFactor(node) > 0) {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;
    }

    /**
     * Deixa sempre a arvore balanceada com a inserção ou remoção de elementos.
     *
     * @param node
     * @return node
     */
    private Node<E> balanceNode(Node<E> node) {
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) < 0)
                node = twoRotations(node);
            else
                node = leftRotation(node);
        } else if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) > 0)
                node = twoRotations(node);
            else
                node = rightRotation(node);
        }
        return node;
    }

    /**
     * @param element
     */
    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    /**
     * insere um elemento e equilibra automaticamente
     *
     * @param element
     * @param node
     * @return node
     */
    private Node<E> insert(E element, Node<E> node) {
        if (node == null) return new Node(element, null, null);

        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
            node = balanceNode(node);
        } else if (node.getElement().compareTo(element) < 0) {
            node.setRight(insert(element, node.getRight()));
            node = balanceNode(node);
        }

        return node;
    }

    /**
     * @param element
     */
    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    /**
     * Remove elementos e equilibra logo a árvore
     *
     * @param element
     * @param node
     * @return node
     */
    private Node<E> remove(E element, Node<E> node) {
        if (node == null) return null;

        if (node.getElement() == element) {
            if (node.getLeft() == null && node.getRight() == null) return null;
            if (node.getLeft() == null) return null;
            if (node.getRight() == null) return null;

            E smallElem = smallestElement(node.getRight());
            node.setElement(smallElem);
            node.setRight(remove(smallElem, node.getRight()));
            node = balanceNode(node);
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    /**
     * @param otherObj
     * @return
     */
    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        AVL<E> second = (AVL<E>) otherObj;
        return equals(root, second.root);
    }

    /**
     * @param root1
     * @param root2
     * @return
     */
    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else
                return false;
        } else return false;
    }

    /**
     * Returns the biggest element within the tree.
     *
     * @return the biggest element within the tree
     */
    public E biggestElement() {
        return this.biggestElement(root);
    }

    public E biggestElement(Node<E> node) {

        if (node == null) {
            return null;
        }

        if (node.getRight() != null) {
            return this.biggestElement(node.getRight());
        } else {
            return node.getElement();
        }
    }

}
package Projeto_2_ESINF.dataStructures;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.List;

/**
 * @author alexandraipatova
 * @author diana cardoso
 */
public class TwoDTree<E extends Comparable<E>> implements TwoDTreeInterface<E> {

    protected static class TwoDNode<E> {
        private Point2D.Float coords;
        protected E info;
        protected TwoDNode<E> left;
        protected TwoDNode<E> right;

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public TwoDNode(E e, TwoDNode<E> leftChild, TwoDNode<E> rightChild, Float x, Float y) {
            info = e;
            left = leftChild;
            right = rightChild;
            coords = new Point2D.Float(x, y);
        }

        /**
         * constructs node with only coordinates
         *
         * @param x coordinate in x
         * @param y coordinate in y
         */
        public TwoDNode(Float x, Float y) {
            coords = new Point2D.Float(x, y);
        }

        /**
         * constructs a node with no associations
         *
         * @param element information about this coordinate
         * @param x       coordinate in x
         * @param y       coordinate in y
         */
        public TwoDNode(E element, Float x, Float y) {
            this.info = element;
            coords = new Point2D.Float(x, y);
        }

        /**
         * Copies the attributes of an object
         *
         * @param node (object that has the attributes that will be copied)
         */
        protected void setObject(TwoDNode<E> node) {
            info = node.getElement();
            left = node.getLeft();
            right = node.getRight();
            coords = new Point2D.Float(node.getX(), node.getY());
        }


        // accessor methods
        public E getElement() {
            return info;
        }

        public TwoDNode<E> getLeft() {
            return left;
        }

        public TwoDNode<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(E e) {
            info = e;
        }

        public void setLeft(TwoDNode<E> leftChild) {
            left = leftChild;
        }

        public void setRight(TwoDNode<E> rightChild) {
            right = rightChild;
        }

        public Float getX() {
            return (float) coords.getX();
        }

        public Float getY() {
            return (float) coords.getY();
        }

        public E getCountry() {
            return info;
        }
    }

    protected TwoDNode<E> root;     // root of the tree


    /**
     * Constructs a binary search tree.
     */
    public TwoDTree(List<Three<E, Float, Float>> data) {
        root = insert(data);
    }

    /**
     * constructing a 2d tree
     *
     * @param root root node
     */
    public TwoDTree(TwoDNode<E> root) {
        this.root = root;
    }

    /**
     * @return root Node of the tree (or null if tree is empty)
     */
    protected TwoDNode<E> root() {
        return root;
    }

    private final Comparator<TwoDNode<E>> cmpX = new Comparator<TwoDNode<E>>() {
        @Override
        public int compare(TwoDNode<E> p1, TwoDNode<E> p2) {
            int result = Float.compare(p1.getX(), p2.getX());

            if (result == 0) {
                return Float.compare(p1.getY(), p2.getY());
            }

            return result;
        }
    };
    private final Comparator<TwoDNode<E>> cmpY = new Comparator<TwoDNode<E>>() {
        @Override
        public int compare(TwoDNode<E> p1, TwoDNode<E> p2) {
            int result = Float.compare(p1.getY(), p2.getY());

            if (result == 0) {
                return Float.compare(p1.getX(), p2.getX());
            }
            return result;
        }
    };

    /**
     * Method to check if the 2d tree is empty
     *
     * @return true if empty, fals eif not empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Wrapper method for insert
     *
     * @param data list of coordinates to insert in the 2d tree
     * @return return the root node of the insertion
     */
    private TwoDNode<E> insert(List<Three<E, Float, Float>> data) {
        return insert(data, true);
    }

    /**
     * Method that creates a balanced 2d tree with the coordinates extracted from the file
     *
     * @param data list of the coordinates read from the file
     * @param divX marks if it his meant to sort by x(true) or y(false)
     */
    private TwoDNode<E> insert(List<Three<E, Float, Float>> data, boolean divX) {

        if (data.isEmpty()) return null;

        if (divX) {
            data.sort(new Three.MiddleComparator<>());

        } else {
            data.sort(new Three.RightComparator<>());

        }

        int median = data.size() / 2;

        Three<E, Float, Float> three = data.get(median);
        TwoDNode<E> node = new TwoDNode<>(three.getMiddle(), three.getRight());

        node.setElement(three.getLeft());

        node.setLeft(insert(data.subList(0, median), !divX));

        //tests if there is another element after the median and if there is puts him on the right subtree of the node
        if (median + 1 < data.size()) {
            node.setRight(insert(data.subList(median + 1, data.size()), !divX));

        }
        return node;
    }


    public void remove(E element, Float x, Float y) {
        TwoDNode<E> searchElement = new TwoDNode<>(element, x, y);
        remove(searchElement, root, 0);
    }

    /**
     * removes element and readjusts 2d tree
     *
     * @param searchElement element to be removed (of type area)
     * @param node          current node being analyzed
     * @param depth         how far down the tree we are
     * @return node that it accessed last, null if operations is concluded
     */
    public TwoDNode<E> remove(TwoDNode<E> searchElement, TwoDNode<E> node, int depth) {

        if (node == null) return null;

        if (node.getElement().equals(searchElement)) { //area was found
            if (node.getLeft() == null && node.getRight() == null) {//is leaf -> instant deletion
                node = null;
                return null;
            }
            if (node.getRight() != null) {
                //if the right subtree isn't null, find the smallest x or y element in this subtree and replace it recursively
                TwoDNode<E> min = smallestElement(node.getRight(), depth % 2 == 0);
                node = min;
                node.setRight(remove(node.getRight(), min, depth + 1));
            } else if (node.getLeft() != null) {
                TwoDNode<E> min = smallestElement(node.getLeft(), depth % 2 == 0);
                node = min;
                node.setRight(remove(node.getLeft(), min, depth + 1));
            }


        } else {
            //continue searching depending on if x or y related and the value of x or y of the searchElement
            if (depth % 2 == 0) {
                if (searchElement.getX() < node.getX())
                    node.setLeft(remove(searchElement, node.getLeft(), depth + 1));
                else
                    node.setRight(remove(searchElement, node.getRight(), depth + 1));
            } else {
                if (searchElement.getY() < node.getY())
                    node.setLeft(remove(searchElement, node.getLeft(), depth + 1));
                else
                    node.setRight(remove(searchElement, node.getRight(), depth + 1));
            }

        }
        return node;

    }

    protected TwoDNode<E> smallestElement(TwoDNode<E> node, boolean divX) {
        return smallestElement(node, divX, 0);
    }

    /**
     * returns the smallest element of the tree
     *
     * @param node the node currently being analyzed
     * @return the left most element of the tree
     */
    protected TwoDNode<E> smallestElement(TwoDNode<E> node, boolean divX, int depth) {

        if (node == null) {
            return null;
        }

        if ((depth % 2 == 0 && divX) || (depth % 2 != 0 && !divX)) {
            if (node.getLeft() == null)
                return node;
        }

        //find the minimum between the search of the current node and its right and left subtrees
        return minNode(node, smallestElement(node.getLeft(), divX, depth + 1), smallestElement(node.getRight(), divX, depth + 1), divX);

    }

    public TwoDNode<E> minNode(TwoDNode<E> x, TwoDNode<E> y, TwoDNode<E> z, boolean divX) {
        TwoDNode<E> res = x;
        if (divX) {
            if (y != null && y.getX() < res.getX())
                res = y;
            if (z != null && z.getX() < res.getX())
                res = z;
        } else {
            if (y != null && y.getY() < res.getY())
                res = y;
            if (z != null && z.getY() < res.getY())
                res = z;
        }
        return res;
    }

    /**
     * Method that returns the size of the tree
     *
     * @return (number of nodes)
     */
    @Override
    public int size(TwoDNode<E> node) {

        if (node == null) {
            return 0;

        } else {
            return (size(node.left) + 1 + size(node.right));

        }
    }

    /**
     * Method that returns the height of the tree
     *
     * @return height
     */
    @Override
    public int height(TwoDNode<E> node) {

        if (node == null) {
            return -1;

        } else {
            int heightL = height(node.getLeft());

            int heightR = height(node.getRight());

            if (heightL > heightR) {
                return (heightL + 1);

            } else {
                return (heightR + 1);

            }
        }
    }


    protected TwoDNode<E> findElement(TwoDNode<E> node, E element) {
        if ((element == null) || (node == null)) return null;
        int cmp = element.compareTo(node.getElement());
        if (cmp == 0) return node;
        findElement(node.getLeft(), element);
        findElement(node.getRight(), element);
        return null;
    }

    public E nearestNeighbourSearch(Float x, Float y) {
        if(x != null && y != null) {
            return nearest_neighbour_search(root, x, y, root, true);
        }else{
            return null;
        }
    }

    /**
     * Method that finds the nearest area in the tree by coordinates
     *
     * @param node node that is being tested (its distance to the desired coordinate)
     * @param x longitude
     * @param y latitude
     * @param nearestNode the closest node found so far
     * @param divX if the coordinate is going to be compared by x(true) or y(false)
     *
     * @return Object are that meets the criteria (having the nearest coordinate to the desired coordinate)
     */
    private E nearest_neighbour_search(TwoDNode<E> node, Float x, Float y, TwoDNode<E> nearestNode, boolean divX) {
        TwoDNode<E> node1;
        TwoDNode<E> node2;

        double delta;

        if (node == null)
            return null;

        double d = Point2D.distanceSq(node.coords.x, node.coords.y, x, y);

        double closestDist = Point2D.distanceSq(nearestNode.coords.x, nearestNode.coords.y, x, y);

        if (closestDist > d)
            nearestNode.setObject(node);

        if (divX) {
            delta = x - node.coords.x;

        } else {
            delta = y - node.coords.y;

        }

        double delta2 = delta * delta;

        if (delta < 0) {
            node1 = node.left;
            node2 = node.right;

        } else {
            node1 = node.right;
            node2 = node.left;

        }

        nearest_neighbour_search(node1, x, y, nearestNode, !divX);

        if (delta2 < closestDist) {
            nearest_neighbour_search(node2, x, y, nearestNode, !divX);

        }

        return nearestNode.info;
    }

    public List<E> searchInArea(List<E> listInArea, Float inicialX, Float finalX, Float inicialY, Float finalY) {
        List<E> list = searchInArea(listInArea, root, inicialX, finalX, inicialY, finalY, true);
        return (list.isEmpty() ? null : list);
    }

    /**
     * searches the list of elements that are contained in a certain square area in the binary search tree
     *
     * @param listInArea the list of elements that are in the area
     * @param node       the current node being analyzed
     * @param inicialX   the starting x coordinate of the square area
     * @param finalX     the final x coordinate of the square area
     * @param inicialY   the starting y coordinate of the square area
     * @param finalY     the final y coordinate of the square area
     * @param divX       boolean that indicates whether x is being taken into account or y
     * @return list of elements inside the area
     */
    public List<E> searchInArea(List<E> listInArea, TwoDNode<E> node, Float inicialX, Float finalX, Float inicialY, Float finalY, boolean divX) {


        if (node == null) return listInArea;


        boolean inX = node.getX() > inicialX && node.getX() < finalX;
        boolean inY = node.getY() > inicialY && node.getY() < finalY;

        if (inX && inY)//node is found
        {
            listInArea.add(node.getElement());
        }

        if (node.getLeft() == null && node.getRight() == null) {

            return listInArea;

        } else if (divX) { //if current level is x related
            if (inX) { //if the coordinate is in the correct x area, then we have to search both sides of the tree, since we don't know which side might be another element
                return searchInArea( //split node
                        searchInArea(listInArea, node.getRight(), inicialX, finalX, inicialY, finalY, !divX)
                        , node.getLeft(), inicialX, finalX, inicialY, finalY, !divX);

            } else if (node.getX() < inicialX) { //get left node if x is smaller
                return searchInArea(listInArea, node.getRight(), inicialX, finalX, inicialY, finalY, !divX);

            } else if (node.getX() > finalX) { //get right node if x is bigger

                return searchInArea(listInArea, node.getLeft(), inicialX, finalX, inicialY, finalY, !divX);
            }
        } else {
            if (inY) {//split node
                return searchInArea(
                        searchInArea(listInArea, node.getLeft(), inicialX, finalX, inicialY, finalY, !divX)
                        , node.getRight(), inicialX, finalX, inicialY, finalY, !divX);

            } else if (node.getY() < inicialY) { //get left node if x is smaller

                return searchInArea(listInArea, node.getRight(), inicialX, finalX, inicialY, finalY, !divX);

            } else if (node.getY() > finalY) { //get right node if x is bigger

                return searchInArea(listInArea, node.getLeft(), inicialX, finalX, inicialY, finalY, !divX);

            }
        }

        return listInArea;
    }

    @Override
    public E exactSearch(Float x, Float y) {
        TwoDNode<E> searchNode = new TwoDNode<>(x, y);
        TwoDNode<E> result = exactSearch(root, searchNode, true);
        return (result == null ? null : result.getElement());
    }

    /**
     * does an exact search for a coordinate in the 2D Tree
     *
     * @param node current node being anayzed
     * @param divX indicates if the current level is x or y related
     * @return returns the exact node if found, if not returns null
     */
    public TwoDNode<E> exactSearch(TwoDNode<E> node, TwoDNode<E> searchNode, boolean divX) {


        if (node == null || (node.info == null) && node.getLeft() == null && node.getRight() == null) return null;

        if (cmpX.compare(searchNode, node) == 0 && cmpY.compare(searchNode, node) == 0)//node is found
        {
            return node;
        }

        if (divX) { //if current level is x related

            if (cmpX.compare(searchNode, node) < 0) { //get left node if x is smaller
                return exactSearch(node.getLeft(), searchNode, !divX);
            } else { //get right node if x is bigger
                return exactSearch(node.getRight(), searchNode, !divX);
            }
        } else {

            if (cmpY.compare(searchNode, node) < 0) {//same for y

                return exactSearch(node.getLeft(), searchNode, !divX);

            }
            return exactSearch(node.getRight(), searchNode, !divX);
        }
    }
}

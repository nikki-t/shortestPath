/**
 * The Node class represents a node in a graph. A node has an element instance
 * variable that represents a vertex and a map of edges to represent
 * connected , adjacent nodes. Each edge map contains a node as its key and
 * the edge weight as its value. A Node object also has a instance variable
 * that represents the nodes direct distance to a destination node.
 *
 * @author Nikki Tebaldi
 * @version 1.0
 * @since 2020-04-04
 */

import java.util.HashMap;
import java.util.Map.Entry;

public class Node<E> {

    // Instance variables
    private E element;
    private int distanceToDest;
    private HashMap<Node<E>, Integer> edges = new HashMap<>();

    // Constructs an empty node
    public Node() { }

    // Constructs a node with an initialized element value
    public Node(E e) { element = e; }

    // Constructs a node with an initialized element and direct distance values
    public Node(E e, int dd) {
        element = e;
        distanceToDest = dd;
    }

    // Accessor Methods
    public E getElement() {
        return element;
    }
    public int getDistanceToDest() { return distanceToDest; }
    public HashMap<Node<E>, Integer> getEdges() {
        return edges;
    }

    // Mutator Methods
    public void setElement(E e) {
        element = e;
    }
    public void setDistanceToDest(int distance) { distanceToDest = distance; }
    public void setEdge(Node<E> vertex, int weight) { edges.put(vertex, weight); }

    /**
     *  Print the current state of the node object.
     */
    public void printNode() {
        System.out.println("Element: " + element
                + "\nDistance To Destination: " + distanceToDest);

        System.out.println("Edges: ");
        // Loop through the node's HashMap of edges
        for(Entry<Node<E>, Integer> entry : edges.entrySet()) {
            System.out.println("\tEdge: " + entry.getKey().element
                    + "\n\tWeight: " + entry.getValue()
                    + "\n\tDD: " + entry.getKey().distanceToDest);
        }
    }

}

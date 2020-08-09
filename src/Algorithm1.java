/**
 * The Algorithm1 class represents an algorithm that finds the shortest path
 * through the graph from a specific node to the destination node using the
 * node's direct distance to the destination node.
 *
 * This class is a concrete implementation (subclass) of the Algorithm class.
 *
 * @author Nikki Tebaldi
 * @version 1.0
 * @since 2020-04-24
 */

import java.util.HashMap;

public class Algorithm1 extends Algorithm {

    // Constructor - Initialized via parent constructor
    public Algorithm1() { super(); }

    /**
     * Finds the shortest edge connected to a node object taking into account
     * the sequence of visited nodes and any dead ends.
     *
     * Determines shortest node based on the edge node's direct distance to
     * destination node.
     *
     * Returns null if an edge cannot be determined.
     *
     * @param currentNode Node object used to determine the next closest node
     * @return Node object with the smallest distance to destination node
     */
    @Override
    public Node<Character> findShortest(Node<Character> currentNode) {
        // Get node's edge list
        HashMap<Node<Character>, Integer> edgeList = currentNode.getEdges();

        // Initialize variables to hold the smallest edge node and edge
        // weight values
        int smallestDDValue = Integer.MAX_VALUE;
        Node<Character> smallestDDNode = null;

        // Loop through the node's HashMap of edges' keys
        for(Node<Character> edgeNode : edgeList.keySet()) {

            // Test if node has already been visited
            boolean foundNode = getSequencePath().search(edgeNode) > -1;

            // If the edge node has not been visited
            if (!foundNode) {
                // Get the edge node's distance to destination node
                int edgeDD = edgeNode.getDistanceToDest();
                // Compare edge node with current smallest edge
                if (edgeDD < smallestDDValue) {
                    smallestDDValue = edgeDD;
                    smallestDDNode = edgeNode;
                }
            } // End outer if to determine if dead end or visited
        } // End for loop

        // Return node with the smallest direct distance to destination node
        return smallestDDNode;
    }
}

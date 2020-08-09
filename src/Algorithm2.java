/**
 * The Algorithm2 class represents an algorithm that finds the shortest path
 * through the graph from a specific node to the destination node using the
 * sum of the node's direct distance to the destination node and the edge
 * weight between the node and its adjacent nodes.
 *
 * This class is a concrete implementation (subclass) of the Algorithm class.
 *
 * @author Nikki Tebaldi
 * @version 1.0
 * @since 2020-04-24
 */

import java.util.HashMap;
import java.util.Map;

public class Algorithm2 extends Algorithm {

    // Constructor - Initialized via parent constructor
    public Algorithm2() { super(); }

    /**
     * Finds the shortest edge connected to a node object taking into account
     * the sequence of visited nodes and any dead ends.
     *
     * Determines shortest node based on the edge node's direct distance to
     * destination node plus the edge node's weight.
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

        // Initialize variables to hold the smallest edge node and sum values
        Node<Character> smallestNode = null;
        int smallestSum = Integer.MAX_VALUE;

        // Loop through the node's HashMap of edges entries (key, value)
        for(Map.Entry<Node<Character>, Integer> edgeNode : edgeList.entrySet()) {

            // Test if node has already been visited
           boolean foundNode = getSequencePath().search(edgeNode.getKey()) > -1;

            // If the edge node has not been visited
            if (!foundNode) {
                // Calculate the sum of the edge weight and edge node's distance
                // to destination node
                int sumDDWeight = edgeNode.getValue()
                        + edgeNode.getKey().getDistanceToDest();
                // Determine if the sum is smaller than previous sums
                if (sumDDWeight < smallestSum) {
                    smallestSum = sumDDWeight;
                    smallestNode = edgeNode.getKey();
                }
            }

        }

        // Return node with smallest sum of edge weight and distance to
        // destination
        return smallestNode;

    }

}

/**
 * The Algorithm class represents an algorithm that finds the shortest path
 * through the graph from a specific node to the destination node.
 *
 * This class is abstract and contains an abstract method called findShortest
 * which determines the shortest distance in a node's edge list. findShortest
 * is determined by the concrete implementation of the Algorithm class.
 *
 * @author Nikki Tebaldi
 * @version 1.0
 * @since 2020-04-24
 */

import java.util.Stack;

public abstract class Algorithm {

    // Instance variables
    Stack<Node<Character>> sequencePath;
    Stack<Node<Character>> shortestPath;

    // Constructor - initializes all values to null or 0
    public Algorithm(){
        sequencePath = new Stack<>();
        shortestPath = new Stack<>();
    }

    // Accessor methods
    public Stack<Node<Character>> getSequencePath() { return sequencePath; }
    public Stack<Node<Character>> getShortestPath() { return shortestPath; }

    // Abstract methods

    /**
     * Abstract method that finds the shortest edge connected to a node object
     * taking into account the sequence of visited nodes and any dead ends.
     */
    abstract Node<Character> findShortest(Node<Character> currentNode);

    // Concrete methods

    /**
     * Finds the shortest path from the input node to the destination node.
     * @param inputNode Character Node object
     */
    public void findShortestPath(Node<Character> inputNode,
                                 Node<Character> destinationNode) {

        // Set current node to the inputNode
        Node<Character> currentNode = inputNode;

        // Keep moving through the graph as long as the currentNode is not the
        // destination node
        while (currentNode.getElement() != destinationNode.getElement()) {

            // Add node to paths to track traversal
            sequencePath.push(currentNode);
            shortestPath.push(currentNode);

            // Find the shortest node that does not include dead ends or nodes
            // that have already been visited
            Node<Character> shortestNode = findShortest(currentNode);

            // If a node could not be found; All nodes have been visited and
            // have encountered a dead end
            if (shortestNode == null) {
                // Backtrack from the current node as it is a dead end; Remove
                // from shortest path
                shortestPath.pop();
                // Move forward with previously visited node
                currentNode = shortestPath.pop();
            } else {
                // Shortest node was found; Move forward with the shortest node
                currentNode = shortestNode;
            }
        } // End while; Shortest path to destination has been found

        // Add destination node to the sequence and shortest path stacks
        sequencePath.push(currentNode);
        shortestPath.push(currentNode);
    }

    /**
     * Calculates the shortest path length by summing the edge weights of the
     * Node object's present in the shortest path.
     *
     * Suppresses warning for unchecked cast of Object type to  Node<Character>
     * type as method is always dealing with Node<Character> objects in
     * generation of array that contains the shortest path Nodes.
     *
     * @return Integer that is the sum of all edge weights in the shortest path
     */
    @SuppressWarnings("unchecked")
    public int getShortestPathLength() {
        // Convert the stack to an array
        Object[] shortestPathArray = shortestPath.toArray();

        // Loop through array an sum edge weights of the shortest path
        int length = shortestPathArray.length;
        int totalPathLength = 0;

        for (int i = 0; i < length - 1; i++) {
            // Cast array elements to Node objects
            Node<Character> currentNode = (Node<Character>) shortestPathArray[i];
            Node<Character> nextNode = (Node<Character>) shortestPathArray[i + 1];

            // Sum edge weight between current node and next node in list
            totalPathLength += currentNode.getEdges().get(nextNode);

        }

        return totalPathLength;
    }

    /**
     * Private class method to print the contents of an array that contains
     * Node objects.
     *
     * Suppresses compiler warning for unchecked cast of Object type to
     * Node<Character> type as method is always dealing with Node<Character>
     * objects in private method.
     *
     * @param array Object array
     */
    @SuppressWarnings("unchecked")
    private static void printNodeArray(Object[] array) {
        // Print out each element in the array
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            // Cast array elements to Node object
            Node<Character> node = (Node<Character>) array[i];
            // Print node object's element
            System.out.print(node.getElement() + " -> ");
        }
        // Print last element without an arrow
        Node<Character> node = (Node<Character>) array[length - 1];
        System.out.print(node.getElement());
        System.out.println();
    }

    /**
     * Prints contents of sequence path stack.
     */
    public void printSequencePath() {

        // Convert the stack to an array
        Object[] sequencePathArray = sequencePath.toArray();

        System.out.print("Sequence of all nodes: ");

        // Print the contents of the array
        printNodeArray(sequencePathArray);
    }

    /**
     * Prints contents of shortest path stack.
     */
    public void printShortestPath() {

        // Convert the stack to an array
        Object[] shortestPathArray = shortestPath.toArray();

        System.out.print("Shortest path: ");

        // Print the contents of the array
        printNodeArray(shortestPathArray);
    }

}

/**
 * The Graph class represents a graph of connected vertices by keeping an
 * array of Node class objects. The array is populated by the files
 * referenced by the instance variables graphFile and DDFile.
 *
 * The Graph class also has a destination node instance variable to track the
 * destination of a graph traversal.
 *
 * The graph instance variable is an ArrayList that represents the vertices
 * and their edges in a graph.
 *
 * @author Nikki Tebaldi
 * @version 1.0
 * @since 2020-04-04
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph {

    // Instance variable
    private String graphFile;
    private String DDFile;
    private Node<Character> destinationNode;
    private ArrayList<Node<Character>> graph = new ArrayList<>();

    // Constructor which initializes an empty array list
    public Graph() { }

    // Accessor methods
    public String getGraphFile() { return graphFile; }
    public String getDDFile() { return DDFile; }
    public Node<Character> getDestinationNode() { return destinationNode; }

    // Mutator methods
    public void setGraphFile(String filename) {
        graphFile = filename;
    }
    public void setDDFile(String filename) {
        DDFile = filename;
    }
    public void setDestinationNode(Node<Character> dNode) { destinationNode = dNode; }

    // Graph methods
    /**
     * Tries to create and open a file for reading as a Scanner object.
     * If an error occurs program prints an error message and exits.
     * @param filename String value of a file to try to read
     * @return Scanner object that contains file data
     */
    private static Scanner getFileScanner(String filename) {
        // Try to read the input file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(filename));

        } catch (FileNotFoundException e) {
            // File cannot be found; Print error message and exit program
            System.out.println("File not found: " + filename
                    + ". Exiting program...");
            System.exit(0);
        }

        return fileScanner;
    }

    /**
     * Creates a Character array of Node elements from String parameter.
     *
     * Suppress warning in place as cast from Node object to Node<Character>
     * object is always safe as Node objects are created and inserted into
     * array by private method.
     *
     * @param nodeNames String array of node names.
     * @return Character array of Node elements.
     */
    @SuppressWarnings("unchecked")
    private Node<Character>[] createNodeElementArray(String[] nodeNames) {

        int numNodes = nodeNames.length;
        Node<Character>[] nodeElements = new Node[numNodes];

        // Loop through node names and create Node objects
        for (int i = 0; i < numNodes; i++) {
            // Convert String to a character
            char element = nodeNames[i].charAt(0);
            // Create a Node object from the element making sure it is uppercase
            nodeElements[i] =  new Node<>(Character.toUpperCase(element));
        }

        return nodeElements;
    }

    /**
     * Populates graph list of Nodes as vertices by parsing the file stored
     * in the instance variable graphFile.
     */
    private void createGraphVertices() {
        // Read the input file
        Scanner graphScanner = getFileScanner(graphFile);

        // Retrieve the first line of the file as column names
        String[] nodeNames = graphScanner.nextLine().trim().split("\\s+");

        // Convert first line into character array of Node elements
        Node<Character>[] nodeElements = createNodeElementArray(nodeNames);

        // Initialize a variable to serve as index to node elements list
        int nodeIndex = 0;

        // Read through the file and populate graph with nodes
        while (graphScanner.hasNext()) {

            // Get next line in the file
            String[] row = graphScanner.nextLine().trim().split("\\s+");

            // Store reference to node in elements list for comparison with row
            Node<Character> node = nodeElements[nodeIndex];

            // Loop through each value in the row to create a Node with
            // appropriate edges
            for (int i = 1; i < row.length; i++) {
                // Test if row element is an edge of the node
                if (row[i].charAt(0) != '0') {
                    // Convert row element to an integer
                    int value = Integer.parseInt(row[i]);
                    // Set edge for Node object retrieved from elements list
                    // and row data
                    node.setEdge(nodeElements[i - 1], value);
                }
            }

            // Add node to graph list
            graph.add(node);

            // Increment nodeIndex to point to next node in elements list
            nodeIndex++;
        }
    }

    /**
     * Using the file stored by the instance variable DDFile set each node
     * object's direct distance to destinationNode instance variable.
     */
    private void setDirectDistances() {

        // Read the input file
        Scanner ddScanner = getFileScanner(DDFile);

        // Read through the file and populate the graph's node elements
        // distance to destination variable
        while (ddScanner.hasNext()) {

            // Get the next line in the file
            String[] row = ddScanner.nextLine().trim().split("\\s+");

            // Match the character in file with a node in the graph
            Node<Character> node = findNode(row[0].charAt(0));

            // If a node was found set it's distanceToDest variable value
            if (node != null) {
                node.setDistanceToDest(Integer.parseInt(row[1]));
            }

        }

    }

    public void populateGraph() {
        // Populate graph with nodes
        createGraphVertices();

        // Set each node's distance to the destination node
        setDirectDistances();

    }

    /**
     * If it exists, return the node that contains an element with a value
     * that matches parameter c.
     * @param c Character object to search for
     * @return Node object or null.
     */
    public Node<Character> findNode(Character c) {

        Node<Character> foundNode = null;

        for (Node<Character> node : graph) {
            if (node.getElement() == c ) {
                foundNode = node;
                break;
            }
        }

        return foundNode;
    }

    /**
     * Prints the graph as 2 X 2 matrix to the screen.
     */
    public void printGraph() {
        // Print out column names
        System.out.print("      ");
        for (Node<Character> node : graph) {
            System.out.printf("%-6s", node.getElement());
        }
        System.out.println();

        // Print each row's edges
        for (Node<Character> node1 : graph) {

            System.out.printf("%-6s", node1.getElement());

            for (Node<Character> node2 : graph) {

                if (node1.getEdges().containsKey(node2)) {
                    System.out.printf("%-6s", node1.getEdges().get(node2));
                } else {
                    System.out.print("0     ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints nodes and their direct distances.
     */
    public void printDirectDistances() {
        // Loop through each node in the graph and print its direct distance
        // to destination node
        for (Node<Character> node : graph) {
            System.out.println(node.getElement() + ": "
                    + node.getDistanceToDest());
        }
    }

    /**
     * Prints nodes in graph.
     */
    public void printNodes() {
        // Loop through each node in the graph and print its direct distance to
        // destination node
        int size = graph.size();
        for (int i = 0; i < size - 2; i++) {
            System.out.print(graph.get(i).getElement() + ", ");
        }

        // Print last element without a comma
        System.out.print(graph.get(size- 1).getElement() + "\n");

    }

}

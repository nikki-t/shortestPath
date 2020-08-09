/**
 * The project program finds the shortest path in a graph from a user-defined
 * start node to a destination node using two different algorithms.
 *
 * The project program uses the following classes to implement the traversal
 * of the graph in search of the shortest path: Node, Graph, Algorithm,
 * Algorithm1, Algorithm2.
 *
 * The input files and destination node are set as static class variables
 * at the beginning of the class definition for project.
 *
 * @author Nikki Tebaldi
 * @version 1.0
 * @since 2020-04-24
 */

import java.util.Scanner;

public class project {

    // INPUT FILE NAMES
    private static final String GRAPH_FILE = "graph_input.txt";
    private static final String DD_FILE = "direct_distance.txt";

    // DESTINATION NODE NAME
    private static final Character DESTINATION_NODE = 'Z';

    /**
     * Validate user input: Prompt for input until user enters only one
     * character.
     * @param input String array of user input
     * @param keyboard Scanner object that represents keyboard input
     * @return Character that is valid user input
     */
    private static Character validateInputLength(String[] input,
                                                Scanner keyboard) {

        // Validation tests
        boolean validLength = input.length == 1;
        boolean validLetter = Character.isLetter(input[0].charAt(0));

        // Test if user input is greater than one AND if the first element
        // of user input is a character
        while (!validLength | !validLetter) {

            // Keep prompting for input
            System.out.println("\nPlease enter only one letter.");
            System.out.println("And make sure to enter a valid letter.");
            System.out.println("\tPlease enter \"one\" start node letter: ");

            input = keyboard.nextLine().trim().split("");

            // Validation tests after new input
            validLength = input.length == 1;
            validLetter = Character.isLetter(input[0].charAt(0));
        }

        // Return valid uppercase character input
        return Character.toUpperCase(input[0].charAt(0));
    }

    private static Node<Character> getStartNodeInput(Graph graph) {

        // Create a new scanner object
        Scanner keyboard = new Scanner(System.in);

        // Create a new node to store result of search on input character
        Node<Character> inputNode = null;

        while (inputNode == null) {
            // Prompt user for input
            System.out.print("Graph nodes: ");
            graph.printNodes();
            System.out.println("\tPlease enter a start node letter: ");
            String[] userInput = keyboard.nextLine().trim().split("");

            // Validate user input
            Character elementChar = validateInputLength(userInput, keyboard);

            // Search for an element match in graph node vertices
            inputNode = graph.findNode(elementChar);

            // Print message if no node was found; Keep looping until valid
            // node is found
            if (inputNode == null) {
                System.out.println("Could not find a matching node."
                        + "\n\tPlease enter a node from the list of Graph nodes.");
            }
        }

        // Return character element
        return inputNode;

    }

    public static void main(String[] args) {

        // Create new Graph object
        Graph graph = new Graph();

        // Set input files
        graph.setGraphFile(GRAPH_FILE);
        graph.setDDFile(DD_FILE);

        // Populate graph with data
        graph.populateGraph();

        // Get input node
        Node<Character> inputNode = getStartNodeInput(graph);

        // Set destination node
        Node<Character> destNode = graph.findNode(DESTINATION_NODE);
        graph.setDestinationNode(destNode);

        // Print out start and destination nodes
        System.out.println("\nStart Node " + inputNode.getElement());
        System.out.println("Destination node "
                + graph.getDestinationNode().getElement() + "\n");

        // Create Algorithm objects
        Algorithm1 alg1 = new Algorithm1();
        Algorithm2 alg2 = new Algorithm2();

        // Run Algorithm 1
        alg1.findShortestPath(inputNode, destNode);
        System.out.println("Algorithm 1:");
        System.out.println();
        alg1.printSequencePath();
        alg1.printShortestPath();
        System.out.println("Shortest path length: " + alg1.getShortestPathLength());

        System.out.println();

        // Run Algorithm 2
        alg2.findShortestPath(inputNode, destNode);
        System.out.println("Algorithm 2:");
        System.out.println();
        alg2.printSequencePath();
        alg2.printShortestPath();
        System.out.println("Shortest path length: " + alg2.getShortestPathLength());

    }
}

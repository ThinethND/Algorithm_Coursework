/**
 * Student Name: Diluksha Nimshan Munasinghe
 * Student ID UoW: w2052877
 * Student ID IIT: 20230020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String filename = getFileNameFromUser(); // calling filename method


        boolean printPaths = false; // set to false if you don't want to print augmenting paths

        try { // open and read the text file
            Scanner scanner = new Scanner(new File(filename));
            int numNodes = scanner.nextInt();
            FlowNetwork network = new FlowNetwork(numNodes);

            while (scanner.hasNextInt()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int capacity = scanner.nextInt();
                network.addEdge(from, to, capacity);
            }

            int source = 0;
            int sink = numNodes - 1;
            FordFulkerson ff = new FordFulkerson(network);
            int maxFlow = ff.maxFlow(source, sink, printPaths); //  Pass the boolean

            System.out.println("The maximum possible flow is: " + maxFlow);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found.");
        }
    }

    public static String getFileNameFromUser() { // Function to get file name or path from the user
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter the full file path or filename (e.g., bridge_1.txt): ");
        return inputScanner.nextLine();
    }
}

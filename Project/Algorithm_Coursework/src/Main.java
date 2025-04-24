import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "network.txt"; // Place your test file here
        try {
            Scanner scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            FlowNetwork network = new FlowNetwork(n);

            while (scanner.hasNextInt()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int capacity = scanner.nextInt();
                network.addEdge(from, to, capacity);
            }

            network.printNetwork();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}

import java.util.*;

public class FlowNetwork {
    int numNodes;
    List<Edge>[] adjList;

    public FlowNetwork(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int capacity) {
        Edge edge = new Edge(from, to, capacity);
        adjList[from].add(edge);
    }

    public void printNetwork() {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + ":");
            for (Edge e : adjList[i]) {
                System.out.println("  " + e);
            }
        }
    }
}

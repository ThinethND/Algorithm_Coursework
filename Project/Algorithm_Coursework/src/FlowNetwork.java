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
        Edge forwardEdge = new Edge(from, to, capacity);
        Edge backwardEdge = new Edge(to, from, 0); // Residual edge
        adjList[from].add(forwardEdge);
        adjList[to].add(backwardEdge);
    }

    public List<Edge> getAdjEdges(int node) {
        return adjList[node];
    }
}

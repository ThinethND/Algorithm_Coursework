/**
 * Student Name: Diluksha Nimshan Munasinghe
 * Student ID UoW: w2052877
 * Student ID IIT: 20230020
 */

import java.util.*;

public class FlowNetwork {
    int numNodes;
    List<Edge>[] adjList;

    public FlowNetwork(int numNodes) { // Constructor to initialize the graph
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int capacity) { // Adds an edge to the flow network
        Edge forwardEdge = new Edge(from, to, capacity);
        Edge backwardEdge = new Edge(to, from, 0); // Residual edge
        adjList[from].add(forwardEdge);
        adjList[to].add(backwardEdge);
    }

    public List<Edge> getAdjEdges(int node) { // Returns the list of edges connected to a given node
        return adjList[node];
    }
}

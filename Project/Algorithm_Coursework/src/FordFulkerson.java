/**
 * Student Name: Diluksha Nimshan Munasinghe
 * Student ID UoW: w2052877
 * Student ID IIT: 20230020
 */

import java.util.*;

public class FordFulkerson {
    private boolean[] visited;
    private Edge[] parent;
    private FlowNetwork network;

    public FordFulkerson(FlowNetwork network) { // constructor for initializes the network
        this.network = network;
    }

    private boolean dfs(int source, int sink) {  // Find augmenting paths from using DFS algorithms
        visited = new boolean[network.numNodes];
        parent = new Edge[network.numNodes];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (Edge edge : network.getAdjEdges(current)) {
                if (!visited[edge.to] && edge.residualCapacity() > 0) { // only visit unvisited nodes
                    visited[edge.to] = true;
                    parent[edge.to] = edge;
                    stack.push(edge.to);
                    if (edge.to == sink) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public int maxFlow(int source, int sink, boolean printPath) { // Find the maximum flow using Ford Fulkerson algorithms
        int flow = 0;
        int augmentingPathCount = 0;

        while (dfs(source, sink)) {
            int pathFlow = Integer.MAX_VALUE;  // Find the bottleneck capacity
            for (int v = sink; v != source; v = parent[v].from) {
                pathFlow = Math.min(pathFlow, parent[v].residualCapacity());
            }

            if (printPath) {
                List<Integer> path = new ArrayList<>();
                for (int v = sink; v != source; v = parent[v].from) {
                    path.add(v);
                }
                path.add(source);
                Collections.reverse(path);
                System.out.print("Augmented path: ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i != path.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println(" | Flow added: " + pathFlow);
            } else {
                System.out.println("Flow added: " + pathFlow);
            }

            for (int v = sink; v != source; v = parent[v].from) { // Update forward and backward edges with new flow
                Edge e = parent[v];
                e.flow += pathFlow;
                for (Edge back : network.getAdjEdges(e.to)) {
                    if (back.to == e.from) {
                        back.flow -= pathFlow;
                        break;
                    }
                }
            }

            flow += pathFlow;
            augmentingPathCount++;
        }
        // print the  total number of augmenting paths
        System.out.println("\nTotal augmenting paths found: " + augmentingPathCount);
        return flow;
    }
}

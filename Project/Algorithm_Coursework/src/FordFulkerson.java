import java.util.*;

public class FordFulkerson {
    private boolean[] visited;
    private Edge[] parent;
    private FlowNetwork network;

    public FordFulkerson(FlowNetwork network) {
        this.network = network;
    }

    // Depth First Search to find an augmenting path
    private boolean dfs(int source, int sink) {
        visited = new boolean[network.numNodes];
        parent = new Edge[network.numNodes];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (Edge edge : network.getAdjEdges(current)) {
                if (!visited[edge.to] && edge.residualCapacity() > 0) {
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

    public int maxFlow(int source, int sink) {
        int flow = 0;
        while (dfs(source, sink)) {
            // Find bottleneck (minimum residual capacity)
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v].from) {
                pathFlow = Math.min(pathFlow, parent[v].residualCapacity());
            }

            // Print the augmenting path
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

            // Update flows along the path
            for (int v = sink; v != source; v = parent[v].from) {
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
        }
        return flow;
    }
}

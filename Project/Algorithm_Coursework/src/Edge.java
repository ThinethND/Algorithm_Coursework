/**
 * Student Name: Diluksha Nimshan Munasinghe
 * Student ID UoW: w2052877
 * Student ID IIT: 20230020
 */

public class Edge {
    int from, to;
    int capacity;
    int flow;

    public Edge(int from, int to, int capacity) { // Constructor to create a new edge
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int residualCapacity() { // Method to calculate and return the residual capacity of the edge
        return capacity - flow;
    }
}

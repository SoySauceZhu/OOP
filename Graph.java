import java.util.*;

public class Graph {
    private final Map<Integer, List<Edge>> adjList = new HashMap<>();

    public void addEdge(int u, int v, int weight) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, weight));
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, weight)); // For undirected graph
    }

    public List<Edge> getNeighbors(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Integer> getNodes() {
        return adjList.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            sb.append("NodeID: ").append(entry.getKey()).append(": ");
            for (Edge edge : entry.getValue()) {
                sb.append(edge.target).append("(").append(edge.weight).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 1);

        System.out.println(graph);
    }
}

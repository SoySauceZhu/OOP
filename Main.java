import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {


        /**
         * HOW TO RUN:
         * Note that this is Dijkstra implementation for start to end **WITHOUT** waypoint!
         * You need think of how to implement the waypoint part
         */

        GraphConstructor.Result graphResult = GraphConstructor.readGraphFromCSV("CW3_Data_Files/roads.csv");
        Graph graph = graphResult.graph;
        Map<Integer, String> nodeNames = graphResult.nodeNames;


        Dijkstra.Result dijkstraResult = Dijkstra.shortestPaths(graph, 0);
        Map<Integer, Integer> dist = dijkstraResult.dist;
        Map<Integer, Integer> prev = dijkstraResult.prev;


        System.out.println("Shortest paths from node 0:");

        for (Map.Entry<Integer, Integer> entry : dist.entrySet()) {
            int node = entry.getKey();
            int distance = entry.getValue();
            System.out.println("Node " + nodeNames.get(node) + " (ID: " + node + ") - Distance: " + distance);
        }


        System.out.println("Shortest path from node 0 to node 4:");

        List<Integer> path = Dijkstra.reconstructPath(0, 4, prev);
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.print("Path: ");
            for (int i = 0; i < path.size(); i++) {
                int node = path.get(i);
                System.out.print(nodeNames.get(node) + " (ID: " + node + ")");
                if (i < path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }

    }
}

import java.util.*;

public class Dijkstra {
    public static class Result {
        public final Map<Integer, Integer> dist;
        public final Map<Integer, Integer> prev;

        public Result(Map<Integer, Integer> dist, Map<Integer, Integer> prev) {
            this.dist = dist;
            this.prev = prev;
        }
    }

    public static Result shortestPaths(Graph graph, int start) {
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        for (int node : graph.getNodes()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited.contains(current.id)) continue;
            visited.add(current.id);

            for (Edge edge : graph.getNeighbors(current.id)) {
                int alt = dist.get(current.id) + edge.weight;
                if (alt < dist.get(edge.target)) {
                    dist.put(edge.target, alt);
                    prev.put(edge.target, current.id);
                    pq.add(new Node(edge.target, alt));
                }
            }
        }

        return new Result(dist, prev);
    }

    public static List<Integer> reconstructPath(int start, int end, Map<Integer, Integer> prev) {
        List<Integer> path = new ArrayList<>();
        for (Integer at = end; at != null; at = prev.get(at)) {
            path.add(at);
            if (at == start) break;
        }
        Collections.reverse(path);
        if (path.getFirst() != start) return new ArrayList<>(); // unreachable
        return path;
    }


    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 1);

        int start = 1;
        Result result = shortestPaths(graph, start);

        System.out.println("Shortest distances from node " + start + ": " + result.dist);
        System.out.println("Previous nodes: " + result.prev);

        int end = 4;
        List<Integer> path = reconstructPath(start, end, result.prev);
        System.out.println("Path from " + start + " to " + end + ": " + path);
    }
}

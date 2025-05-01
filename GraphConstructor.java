import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphConstructor {

    public static class Result {
        public final Graph graph;
        public final Map<Integer, String> nodeNames;

        public Result(Graph graph, Map<Integer, String> nodeNames) {
            this.graph = graph;
            this.nodeNames = nodeNames;
        }

    }

    public static Result readGraphFromCSV(String filePath) throws IOException {
        Graph graph = new Graph();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        Map<String, Integer> nodeIds = new HashMap<>();
        Map<Integer, String> nodeNames = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();

            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid line format: " + line);
            }


            String source = parts[0].trim().replace("\"", "");
            String target = parts[1].trim().replace("\"", "");
            int weight = Integer.parseInt(parts[2].trim());

            // If the keys are not present in the map, add them with a new ID
            int sourceId = nodeIds.computeIfAbsent(source, key -> nodeIds.size());
            nodeNames.put(sourceId, source);
            int targetId = nodeIds.computeIfAbsent(target, key -> nodeIds.size());
            nodeNames.put(targetId, target);

            graph.addEdge(sourceId, targetId, weight);
        }

        return new Result(graph, nodeNames);
    }
}
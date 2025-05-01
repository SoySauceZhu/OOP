public class Node implements Comparable<Node> {
    int id;
    int distance;   // The distance from the source node

    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Node && ((Node) o).id == this.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

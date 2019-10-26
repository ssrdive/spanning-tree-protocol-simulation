import java.util.*;

public class Node implements Comparator<Node> {
    String name;
    String MAC;
    String type;
    int cost;
    Node from;
    List<Edge> edgeList;

    public Node() {}

    public Node(String name, String MAC) {
        this.name = name;
        this.MAC = MAC;
        this.type = "NON-ROOT";
        this.from = null;
        edgeList = new ArrayList<>();
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.cost < node2.cost)
            return -1;
        else
            return 1;
    }
}

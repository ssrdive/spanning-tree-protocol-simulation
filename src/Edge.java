public class Edge {
    int cost;
    Node destination;
    Node source;
    String portType;

    public Edge(int cost, Node destination, Node source) {
        this.cost = cost;
        this.destination = destination;
        this.source = source;
        this.portType = "UN";
    }
}
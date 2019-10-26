public class STPDemo {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode("A", "11");
        graph.addNode("B", "22");
        graph.addNode("C", "33");
        graph.addNode("D", "44");
        graph.addNode("E", "55");
        graph.addNode("F", "66");
        graph.addNode("G", "77");
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 5);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "D", 2);
        graph.addEdge("C", "F", 8);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "F", 6);
        graph.addEdge("E", "G", 1);
        graph.addEdge("F", "G", 10);
        graph.runSTP();
        graph.printGraph();
    }
}
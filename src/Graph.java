import java.util.*;

public class Graph {
    List<Node> nodeList;
    PriorityQueue<Node> pq;
    Set<Node> settled;
    Node root;
    private List<Node> pathList;

    Graph() {
        nodeList = new ArrayList<>();
    }

    public void addNode(String name, String MAC) {
        nodeList.add(new Node(name, MAC));
    }

    public void addEdge(String srcName, String destName, int cost) {
        Node srcNode = null, destNode = null;

        for(Node temp : nodeList) {
            if(temp.name.equals(srcName))
                srcNode = temp;
            else if(temp.name.equals(destName))
                destNode = temp;
        }

        if(srcNode == null || destNode == null) {
            System.out.println("Source or destination node doesn't exist");
            return;
        }

        Edge edge = new Edge(cost, destNode, srcNode);
        srcNode.edgeList.add(edge);

        edge = new Edge(cost, srcNode, destNode);
        destNode.edgeList.add(edge);
    }

    public void printGraph() {
        for(Node temp : nodeList) {
            System.out.println(temp.name + "(" + temp.type + ")");
            for(Edge tempEdge : temp.edgeList) {
                System.out.print("--> " + tempEdge.destination.name + "[" + tempEdge.portType + "]" + "(" + tempEdge.cost + ")");
            }
            System.out.println("\n");
        }
    }

    public void dijkstra(String srcName) {
        Node srcNode = null;

        for(Node temp : nodeList) {
            if (temp.name == srcName)
                srcNode = temp;
            temp.cost = Integer.MAX_VALUE;
        }

        if(srcNode == null) {
            System.out.println("Source node doesn't exist");
            return;
        }
        pq = new PriorityQueue<Node>(nodeList.size(), new Node());
        settled = new HashSet<Node>();

        pq.add(srcNode);
        srcNode.cost = 0;
        srcNode.from = srcNode;

        while(settled.size() != nodeList.size()) {
            srcNode = pq.remove();
            settled.add(srcNode);

            visitNeighbors(srcNode);
        }

    }

    private void visitNeighbors(Node srcNode) {
        int edgeDistance = -1;
        int newDistance = -1;

        for(Edge tempEdge : srcNode.edgeList ) {
            if(!settled.contains(tempEdge.destination)) {
                edgeDistance = tempEdge.cost;
                newDistance = srcNode.cost + edgeDistance;

                if(newDistance < tempEdge.destination.cost) {
                    tempEdge.destination.cost = newDistance;
                    tempEdge.destination.from = srcNode;
                }

                pq.add(tempEdge.destination);
            }
        }
    }

    public void runSTP() {
        Node highestMAC = nodeList.get(0);
        for(Node temp : nodeList) {
            if(Integer.parseInt(temp.MAC) > Integer.parseInt(highestMAC.MAC)) {
                highestMAC = temp;
            }
        }

        root = highestMAC;
        highestMAC.type = "ROOT";

        for(Edge temp : root.edgeList) {
            temp.portType = "DP";
        }

        for(Node currentNode : nodeList)
            if(currentNode != root) {
                dijkstra(currentNode.name);
                Node path = root;
                while(path.from.name != currentNode.name) {
                    path = path.from;
                }
                Edge rootEdge = currentNode.edgeList.get(0);
                for(Edge tempEdge : currentNode.edgeList) {
                    if(tempEdge.destination == path) {
                        rootEdge = tempEdge;
                        break;
                    }
                }
                rootEdge.portType = "RP";
                for (Edge tempEdge : currentNode.edgeList) {
                    if (tempEdge.portType.equals("RP")) {
                        for (Edge tempEdge2 : tempEdge.destination.edgeList)
                            if (tempEdge2.destination == currentNode)
                                tempEdge2.portType = "DP";
                    } else if (tempEdge.portType.equals("UN")) {
                        for (Edge tempEdge3 : tempEdge.destination.edgeList)
                            if (tempEdge3.destination == currentNode)
                                if (tempEdge3.portType.equals("UN")) {
                                    int node1Cost, node2Cost;
                                    dijkstra(currentNode.name);
                                    node1Cost = root.cost;
                                    dijkstra(tempEdge.destination.name);
                                    node2Cost = root.cost;
                                    if (node1Cost < node2Cost)
                                        tempEdge.portType = "DP";
                                    else
                                        tempEdge3.portType = "DP";
                                }
                    }
                }
            }

        for(Node currentNode : nodeList)
            if(currentNode != root)
                for(Edge tempEdge : currentNode.edgeList)
                    if(tempEdge.portType.equals("UN"))
                        tempEdge.portType = "BP";
    }
}
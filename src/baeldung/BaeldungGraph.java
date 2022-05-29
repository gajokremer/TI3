package baeldung;

import java.util.*;

public class BaeldungGraph {

    public static class Node {

        private int index;
        private Integer distance = Integer.MAX_VALUE;
        private List<Node> shortestPath = new LinkedList<>();
        private final Map<Node, Integer> adjacentNodes = new HashMap<>();

        public Node(int index) {
            this.index = index;
        }

        public void addDestination(Node destination, int distance) {

            adjacentNodes.put(destination, distance);
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }
    }

    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {

        if (!nodes.contains(node)) {

            nodes.add(node);
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
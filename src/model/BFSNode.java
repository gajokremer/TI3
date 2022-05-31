package model;

import java.util.LinkedList;
import java.util.List;

public class BFSNode<T> {

    private final T data;
    private boolean visited;
    private List<BFSNode<T>> neighbours;

    BFSNode(T data) {
        this.data = data;
        visited = false;
        neighbours = new LinkedList<>();
    }

    public T getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<BFSNode<T>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<BFSNode<T>> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public String toString() {
        return "BFSNode{" +
                "data=" + data +
                ", visited=" + visited +
//                ", neighbours=" + neighbours +
                '}';
    }
}
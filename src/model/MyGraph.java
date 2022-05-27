package model;

import java.util.LinkedList;
import java.util.List;

public class MyGraph<T> implements Graph<T> {

   private List<T> vertexes;
   private List<Edge<T>> edges;

    public MyGraph() {
        vertexes = new LinkedList<>();
        edges = new LinkedList<>();
    }

    public List<T> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<T> vertexes) {
        this.vertexes = vertexes;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    @Override
    public void addVertex(T data) {

        if (!vertexes.contains(data)) {

            vertexes.add(data);
        }
    }

    @Override
    public void addEdge(T origin, T end, boolean bidirectional) {

        if (!vertexes.contains(origin)) {

            vertexes.add(origin);
        }

        if (!vertexes.contains(end)) {

            vertexes.add(end);
        }

        edges.add(new Edge<>(origin, end));

        if (bidirectional) {

            edges.add(new Edge<>(end, origin));
        }
    }

    @Override
    public int getVertexCount() {
        return 0;
    }

    @Override
    public int getEdgesCount(boolean bidirectional) {
        return 0;
    }

    @Override
    public boolean hasVertex(T data) {
        return false;
    }

    @Override
    public boolean hasEdge(T origin, T end) {
        return false;
    }
}
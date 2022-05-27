package model;

public interface Graph<T> {

    void addVertex(T data);

    void addEdge(T origin, T end, boolean bidirectional);

    int getVertexCount();

    int getEdgesCount(boolean bidirectional);

    boolean hasVertex(T data);

    boolean hasEdge(T origin, T end);
}
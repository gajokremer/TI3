package extras;

public class Edge<T> {

    private T origin;
    private T end;

    public Edge(T origin, T end) {
        this.origin = origin;
        this.end = end;
    }

    public T getOrigin() {
        return origin;
    }

    public void setOrigin(T origin) {
        this.origin = origin;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return origin + " -> " + end;
    }
}
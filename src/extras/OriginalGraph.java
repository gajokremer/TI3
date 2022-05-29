package extras;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OriginalGraph<T> {

    private final HashMap<T, List<T>> map;

    public OriginalGraph() {
        map = new HashMap<>();
    }

    public void addVertex(T data) {
        map.put(data, new LinkedList<>());
    }

    public void addEdge(T origin, T end, boolean bidirectional) {

        if (!map.containsKey(origin)) {

            addVertex(origin);
        }

        if (!map.containsKey(end)) {

            addVertex(end);
        }

        map.get(origin).add(end);

        if (bidirectional) {

            map.get(end).add(origin);
        }
    }

    public int getVertexCount() {

        return map.keySet().size();
    }

    public int getEdgesCount(boolean bidirectional) {

        int count = 0;

        for (T v : map.keySet()) {

            count += map.get(v).size();
        }

        if (bidirectional) {

            count = count / 2;
        }

        return count;
    }

    public boolean hasVertex(T data) {

        return map.containsKey(data);
    }

    public boolean hasEdge(T origin, T end) {

        return map.get(origin).contains(end);
    }

    public void dijkstra() {

    }

//    public void breathFirstSearch(T start) {
//
//        Queue<T> queue = new LinkedList<>();
//
//        queue.add(start);
//
//        while (!queue.isEmpty()) {
//
//            T current = queue.poll();
//
//            if (current.isVisited()) {
//
//                current.setVisited(true);
//
//                System.out.println(current);
//
//                queue.addAll(current.getNeighbours());
//            }
//        }
//    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {

            builder.append(v.toString()).append(": ");

            for (T w : map.get(v)) {

                builder.append(w.toString()).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
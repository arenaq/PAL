package cv1.grafy3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Petr Kuška
 */
public class DruhaMocninaGrafu {

    public static void main(String[] args) throws IOException {
        HashMap<Integer, Vertex> graph = new HashMap<>();
        HashMap<Integer, Vertex> graph_squared = new HashMap<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // creation of graph G
        int pocet_hran = Integer.parseInt(in.readLine());
        for (int i = 0; i < pocet_hran; i++) {
            Edge edge = getTuple(in.readLine());
            if (graph.get(edge.a) == null) {
                graph.put(edge.a, new Vertex(edge.a));
            }
            if (graph.get(edge.b) == null) {
                graph.put(edge.b, new Vertex(edge.b));
            }
            graph.get(edge.a).list.add(graph.get(edge.b));
            graph.get(edge.b).list.add(graph.get(edge.a));
        }

        // creation of G^2
        for (Entry<Integer, Vertex> entry : graph.entrySet()) {
            for (Vertex p : entry.getValue().list) {
                for (Vertex t : p.list) {
                    if (entry.getValue().id != t.id) {
                        if (graph_squared.get(entry.getValue().id) == null) {
                            graph_squared.put(entry.getValue().id, new Vertex(entry.getValue().id));
                        }
                        if (graph_squared.get(t.id) == null) {
                            graph_squared.put(t.id, new Vertex(t.id));
                        }
                        graph_squared.get(entry.getValue().id).list.add(graph_squared.get(t.id));
                        graph_squared.get(t.id).list.add(graph_squared.get(entry.getValue().id));
                    }
                }
            }
        }

        // printing graph G^2 to stdout
        StringBuilder sb = new StringBuilder();
        sb.append("-------------\n");
        // auxiliary list to evidence already added edges
        List<Edge> edges = new LinkedList<>();
        for (Entry<Integer, Vertex> entry : graph_squared.entrySet()) {
            Vertex A = entry.getValue();
            for (Vertex B : entry.getValue().list) {
                boolean add = true;
                for (int i = 0; i < edges.size(); i++) {
                    Edge e = edges.get(i);
                    // if edge (a, b) or (b, a) is contained, nothing will happen
                    if ((e.a == A.id && e.b == B.id) || (e.a == B.id && e.b == A.id)) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    edges.add(new Edge(A.id, B.id));
                    sb.append(A.id).append(" ").append(B.id).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static class Vertex {

        int id;
        Set<Vertex> list;

        Vertex(int id) {
            this.id = id;
            list = new HashSet<Vertex>();
        }
    }

    static class Edge {

        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            if (this.a == e.a && this.b == e.b) {
                return true;
            }
            return false;
        }

    }

    /* optimizing input parsing */
    static final int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    static Edge getTuple(String line) {
        int x = 0;
        int y = 0;

        int len = line.length();
        int index = len - 1;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            y += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index >= 0) {
            char c = line.charAt(index);
            x += (c - '0') * pow[len - index - 1];
            index--;
        }
        // ošetření pořadí v rámci hrany
        if (y < x) {
            return new Edge(y, x);
        } else {
            return new Edge(x, y);
        }
    }
}

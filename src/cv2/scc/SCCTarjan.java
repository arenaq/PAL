package cv2.scc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// optimized version of http://en.wikipedia.org/wiki/Tarjan's_strongly_connected_components_algorithm
public class SCCTarjan {

    List<Integer>[] graph;
    boolean[] visited;
    Stack<Integer> stack;
    int time;
    int[] lowlink;
    List<List<Integer>> components;

    public List<List<Integer>> scc(List<Integer>[] graph) {
        int n = graph.length;
        this.graph = graph;
        visited = new boolean[n];
        stack = new Stack<>();
        time = 0;
        lowlink = new int[n];
        components = new ArrayList<>();

        for (int u = 0; u < n; u++) {
            if (!visited[u]) {
                dfs(u);
            }
        }

        return components;
    }

    void dfs(int u) {
        lowlink[u] = time++;
        visited[u] = true;
        stack.add(u);
        boolean isComponentRoot = true;

        for (int v : graph[u]) {
            if (!visited[v]) {
                dfs(v);
            }
            if (lowlink[u] > lowlink[v]) {
                lowlink[u] = lowlink[v];
                isComponentRoot = false;
            }
        }

        if (isComponentRoot) {
            List<Integer> component = new ArrayList<>();
            while (true) {
                int x = stack.pop();
                component.add(x);
                lowlink[x] = Integer.MAX_VALUE;
                if (x == u) {
                    break;
                }
            }
            components.add(component);
        }
    }

    // Usage example
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src/cv2/scc/input.txt")));
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // ziskani poctu hran z první řádky
        int pocet_vrcholu = 0;
        int pocet_hran = 0;
        try {
            Tuple tuple = getTuple(in.readLine());
            pocet_vrcholu = tuple.a;
            pocet_hran = tuple.b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        List<Integer>[] g = new List[pocet_vrcholu];
        
        for (int i = 0; i < g.length; i++)
            g[i] = new ArrayList<>();

        // sestrojeni grafu G
        for (int i = 0; i < pocet_hran; i++) {
            Tuple tuple = getTuple(in.readLine());
            g[tuple.a-1].add(tuple.b-1);
        }
        
        List<List<Integer>> components = new SCCTarjan().scc(g);
        System.out.println(components.size());
    }

    private static class Tuple {

        int a, b;

        Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static final int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    private static final Tuple getTuple(String line) {
        int A = 0;
        int B = 0;
        int C = 0;

        int len = line.length();
        int index = len - 1;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            C += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            B += (c - '0') * pow[len - index - 1];
            index--;
        }
        
        len = index;
        index--;
        while (index >= 0) {
            char c = line.charAt(index);
            A += (c - '0') * pow[len - index - 1];
            index--;
        }
        return new Tuple(A, B);
    }
}

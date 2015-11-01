package du1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class Main {

    protected int[] vertices;
    protected int N, M, C1, C2, minSpanTreeSum;
    protected ArrayList<Edge> edges = new ArrayList<>();
    protected ArrayList<Edge> output = new ArrayList<>();

    /*int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    private void parseFirstLine(String line) {
        int len = line.length();
        int index = len - 1;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            C2 += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            C1 += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            M += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index >= 0) {
            char c = line.charAt(index);
            N += (c - '0') * pow[len - index - 1];
            index--;
        }
    }

    private Edge getEdge(String line) {
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
        return new Edge(A, B, C);
    }*/

    public static void main(String[] args) throws IOException {
        int nextFreeGroupId = 3;
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        main.N = Integer.parseInt(st.nextToken());
        main.M = Integer.parseInt(st.nextToken());
        main.C1 = Integer.parseInt(st.nextToken());
        main.C2 = Integer.parseInt(st.nextToken());

        main.vertices = new int[main.N];

        for (int i = 0; i < main.M; i++) {
            st = new StringTokenizer(br.readLine());
            main.edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Comparator<Edge> costComparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge first, Edge second) {
                return first.C < second.C ? -1 : 1;
            }
        };
        Collections.sort(main.edges, costComparator);

        // go througth all edges
        for (int i = 0; i < main.edges.size(); i++) {
            if (i == 0) { // if first edge (the least), increment sum and reserve first and second ID to those vertices
                main.minSpanTreeSum += main.edges.get(i).C;
                main.vertices[main.edges.get(i).A] = 1;
                main.vertices[main.edges.get(i).B] = 2;
                continue;
            }

            // if both nodes are not in a group, lets add them to new group, increment total sum and finish the loop
            if (main.vertices[main.edges.get(i).A] == 0 && main.vertices[main.edges.get(i).B] == 0) {
                main.vertices[main.edges.get(i).A] = nextFreeGroupId;
                main.vertices[main.edges.get(i).B] = nextFreeGroupId;
                nextFreeGroupId++;
                main.minSpanTreeSum += main.edges.get(i).C;
                continue;
            }

            // if there is no cycle (2 vertices is not in the same group)
            if (main.vertices[main.edges.get(i).A] != main.vertices[main.edges.get(i).B]) {
                // if one of them is unassigned => so add it to the same group, increment total sum
                if (main.vertices[main.edges.get(i).A] == 0 || main.vertices[main.edges.get(i).B] == 0) {
                    if (main.vertices[main.edges.get(i).A] == 0) {
                        main.vertices[main.edges.get(i).A] = main.vertices[main.edges.get(i).B];
                    } else {
                        main.vertices[main.edges.get(i).B] = main.vertices[main.edges.get(i).A];
                    }
                    main.minSpanTreeSum += main.edges.get(i).C;
                    // if both are assigned, it means, that they are connecting two groups
                } else {
                    // if the edge connects first two groups, it might be one of the solutions
                    if ((main.vertices[main.edges.get(i).A] == 1 && main.vertices[main.edges.get(i).B] == 2) || (main.vertices[main.edges.get(i).A] == 2 && main.vertices[main.edges.get(i).B] == 1)) {
                        if (main.edges.get(i).C >= main.C1 && main.edges.get(i).C <= main.C2) {
                            if (main.edges.get(i).A < main.edges.get(i).B) {
                                main.output.add(new Edge(main.edges.get(i).A, main.edges.get(i).B, main.edges.get(i).C));
                            } else {
                                main.output.add(new Edge(main.edges.get(i).B, main.edges.get(i).A, main.edges.get(i).C));
                            }
                        }
                    } else {
                        // lets merge groups -> group with lower number will receive the higher number
                        if (main.vertices[main.edges.get(i).A] < main.vertices[main.edges.get(i).B]) {
                            int from = main.vertices[main.edges.get(i).B];
                            int to = main.vertices[main.edges.get(i).A];
                            for (int j = 0; j < main.vertices.length; j++) {
                                if (main.vertices[j] == from) {
                                    main.vertices[j] = to;
                                }
                            }
                        } else {
                            int from = main.vertices[main.edges.get(i).A];
                            int to = main.vertices[main.edges.get(i).B];
                            for (int j = 0; j < main.vertices.length; j++) {
                                if (main.vertices[j] == from) {
                                    main.vertices[j] = to;
                                }
                            }
                        }
                        main.minSpanTreeSum += main.edges.get(i).C;
                    }
                }
            }
        }

        Comparator<Edge> edgeComparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge first, Edge second) {
                if (first.A < second.A) {
                    return -1;
                } else if (first.A > second.A) {
                    return 1;
                } else if (first.B < second.B) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Collections.sort(main.output, edgeComparator);

        StringBuilder sb = new StringBuilder();
        sb.append(main.minSpanTreeSum);

        for (int i = 0; i < main.output.size(); i++) {
            sb.append("\n");
            sb.append(main.output.get(i).A);
            sb.append(" ");
            sb.append(main.output.get(i).B);
            sb.append(" ");
            sb.append(main.output.get(i).C);
        }

        System.out.println(sb);
    }
}

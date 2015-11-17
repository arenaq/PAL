package du3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class TreeIsomorphism {

    static boolean[][] A_matrix, B_matrix; // {{vertex, vertex}, does they have an edge} - only upper half from diagonal is filled
    static short[][] B_edges;
    static short[] A_degrees, B_degrees; // {vertex, number of edges}
    static short[] A_degree_classification; // {degree, count} - example {1, 4} means that there are 4 vertices with degree 1

    static short[] getDegreesClassification(short[] edge_to_degree) {
        short[] degreesClassification = new short[edge_to_degree.length];
        for (int i = 0; i < edge_to_degree.length; i++) {
            degreesClassification[edge_to_degree[i]]++;
        }
        degreesClassification[0] = 0; // hack
        return degreesClassification;
    }

    static boolean[] getCertificate(short[][] tree) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    static boolean compare(short[] A, short[] B) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] != B[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean compare(boolean[] A, boolean[] B) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] != B[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        short N = Short.parseShort(in.readLine()); // number of nodes of a tree A, max 1200

        A_matrix = new boolean[N][N];
        A_degrees = new short[N];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            short v1 = Short.parseShort(st.nextToken());
            short v2 = Short.parseShort(st.nextToken());
            A_matrix[v1][v2] = true;
            A_matrix[v2][v1] = true;
            A_degrees[v1]++;
            A_degrees[v2]++;
        }

        A_degree_classification = getDegreesClassification(A_degrees);

        B_matrix = new boolean[N + 1][N + 1];
        B_edges = new short[N + 1][N + 1];
        B_degrees = new short[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            short v1 = Short.parseShort(st.nextToken());
            short v2 = Short.parseShort(st.nextToken());
            B_matrix[v1][v2] = true;
            B_matrix[v2][v1] = true;
            B_edges[v1][B_degrees[v1]++] = v2;
            B_edges[v2][B_degrees[v2]++] = v1;
        }

        StringBuilder sb = new StringBuilder();
        String prefix = "";
        //System.out.println("Graph B:");
        //printGraph(B_matrix, B_degrees);
        for (int i = 0; i < B_degrees.length; i++) {
            if (B_degrees[i] == 1) {
                B_matrix[i][B_edges[i][0]] = false;
                B_matrix[B_edges[i][0]][i] = false;
                B_degrees[i]--;
                B_degrees[B_edges[i][0]]--;
                //System.out.println("Vertex " + i + ":");
                //printGraph(B_matrix, B_degrees);
                //System.out.println("-----------------");
                short[] d = getDegreesClassification(B_degrees);
                if (compare(A_degree_classification, d)) {
                    sb.append(prefix).append(i);
                    prefix = " ";
                }
                B_matrix[i][B_edges[i][0]] = true;
                B_matrix[B_edges[i][0]][i] = true;
                B_degrees[i]++;
                B_degrees[B_edges[i][0]]++;
            }
        }
        System.out.println("-");
        System.out.println(sb.toString());
    }

    static void printGraph(boolean[][] graph, short[] number_of_edges) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < graph.length; i++) {
            for (int j = i; j < graph[i].length; j++) {
                if (graph[i][j] == true) {
                    sb.append(i).append(" ").append(j).append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    static void print1dArray(short[] array) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < array.length; i++) {
            sb.append(i).append(":").append(array[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}

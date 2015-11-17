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
    static short[] A_degrees, B_degrees; // {vertex, number of edges}
    static short[] A_degree_classification, B_degree_classification; // {degree, count} - example {1, 4} means that there are 4 vertices with degree 1

    static short[] getDegreesClassification(short[] edge_to_degree) {
        short[] degreesClassification = new short[edge_to_degree.length];
        for (int i = 0; i < edge_to_degree.length; i++) {
            degreesClassification[edge_to_degree[i]]++;
        }
        return degreesClassification;
    }

    static boolean[] getCertificate(short[][] tree) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    static void copyGraphExceptVertex(boolean[][] out_graph, short[] out_degrees, int vertex) {
        for (int i = 0; i < out_graph.length; i++) {
            for (int j = i; j < out_graph[i].length; j++) { // limited to diagonal - only upper half of diagonal is filled
                if (i != vertex && j != vertex) {
                    out_graph[i][j] = B_matrix[i][j];
                    out_degrees[i]++;
                    out_degrees[j]++;
                }
            }
        }
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
            if (v1 < v2) {
                A_matrix[v1][v2] = true;
            } else {
                A_matrix[v2][v1] = true;
            }
            A_degrees[v1]++;
            A_degrees[v2]++;
        }

        B_matrix = new boolean[N + 1][N + 1];
        B_degrees = new short[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            short v1 = Short.parseShort(st.nextToken());
            short v2 = Short.parseShort(st.nextToken());
            if (v1 < v2) {
                B_matrix[v1][v2] = true;
            } else {
                B_matrix[v2][v1] = true;
            }
            B_degrees[v1]++;
            B_degrees[v2]++;
        }

        System.out.println("Graph B:");
        printGraph(B_matrix, B_degrees);
        for (int i = 0; i < B_degrees.length; i++) {
            if (B_degrees[i] == 1) {
                System.out.println("Vertex "+i+":");
                for (int x = 0; x < B_matrix.length; x++) {
                    for (int y = x; y < B_matrix[x].length; y++) { // limited to diagonal - only upper half of diagonal is filled
                        if (x != i && y != i) {
                            if (B_matrix[x][y] == true) System.out.println(x+" "+y);
                        }
                    }
                }
                System.out.println("-----------------");
            }
        }
        // TODO
        /* pro vsechny vrcholy X stupne 1 z grafu B:
         pokud podgraf G grafu B vytvoreny odebranim hrany vrcholu X ma stejne schema stupnu vrcholu,   tak vytvorime z grafu G certifikat a porovname jej s grafem A
         */
    }

    static void printGraph(boolean[][] graph, short[] number_of_edges) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < graph.length; i++) {
            for (int j = i; j < graph[i].length; j++) {
                if (graph[i][j] == true) {
                    sb.append(i);
                    sb.append(" ");
                    sb.append(j);
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    static void print1dArray(short[] array) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < array.length; i++) {
            sb.append(i);
            sb.append(":");
            sb.append(array[i]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

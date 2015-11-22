package du3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    static String getCertificate(boolean[][] tree, short[] degrees, int skip) {
        short[] degrees_copy = new short[degrees.length];
        boolean[][] tree_copy = new boolean[tree.length][tree[0].length];
        System.arraycopy(degrees, 0, degrees_copy, 0, degrees.length);
        for (int i = 0; i < tree_copy.length; i++) {
            System.arraycopy(tree[i], 0, tree_copy[i], 0, tree[i].length);
        }
        String[] label = new String[tree_copy.length];
        Arrays.fill(label, "01");
        boolean[] sort = new boolean[tree_copy.length];
        String[][] parts = new String[tree_copy.length][tree_copy[0].length];
        short[] i_parts = new short[tree_copy[0].length];

        int number_of_vertices = tree_copy.length;
        if (skip > -1) {
            label[skip] = null;
            number_of_vertices--;
        }
        while (number_of_vertices > 2) {
            for (int i = 0; i < degrees_copy.length; i++) {
                if (degrees_copy[i] == 1) { // find leaf
                    short adjacent = 0;
                    for (short j = 0; j < tree_copy[i].length; j++) { // get adjacent vertex to the leaf
                        if (tree_copy[i][j] == true) {
                            adjacent = j;
                            tree_copy[i][j] = false;
                            tree_copy[j][i] = false;
                            break;
                        }
                    }
                    sort[adjacent] = true; // set adjacent vertex for after processing (sorting of labels)
                    parts[adjacent][i_parts[adjacent]++] = label[i]; // add label to set of labels which are to be sorted in after processing
                    label[i] = null; // remove label of i (we should never need it again)
                    degrees_copy[i]--; // lower degree of a leaf from 1 to 0
                    number_of_vertices--;
                }
            }
            // after processing
            for (int j = 0; j < sort.length; j++) {
                if (sort[j] == true) {
                    String[] to_sort = new String[i_parts[j] + 1];
                    System.arraycopy(parts[j], 0, to_sort, 0, i_parts[j]);

                    if (label[j].length() > 2) {
                        to_sort[to_sort.length - 1] = label[j].substring(1, label[j].length() - 1);
                    } else {
                        to_sort[to_sort.length - 1] = "";
                    }

                    Arrays.sort(to_sort);
                    label[j] = "";
                    for (int k = 0; k < to_sort.length; k++) {
                        label[j] += to_sort[k];
                    }
                    label[j] = "0" + label[j] + "1";
                    // clean
                    degrees_copy[j] -= i_parts[j];
                    sort[j] = false;
                    i_parts[j] = 0;
                }
            }
        }
        String[] certificate = new String[2];
        short tmp = 0;
        for (int i = 0; i < label.length; i++) {
            if (label[i] != null) {
                certificate[tmp++] = label[i];
            }
        }
        if (number_of_vertices == 1) {
            return certificate[0];
        } else {
            Arrays.sort(certificate);
            return certificate[0] + certificate[1];
        }
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

        String A_certificate = getCertificate(A_matrix, A_degrees, -1);

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
                    String B_certificate = getCertificate(B_matrix, B_degrees, i);
                    //System.out.println(i + ": " + A_certificate + " ? " + B_certificate);
                    if (A_certificate.compareTo(B_certificate) == 0) {
                        sb.append(prefix).append(i);
                        prefix = " ";
                    }
                }
                B_matrix[i][B_edges[i][0]] = true;
                B_matrix[B_edges[i][0]][i] = true;
                B_degrees[i]++;
                B_degrees[B_edges[i][0]]++;
            }
        }
        //System.out.println("-");
        System.out.println(sb.toString());
        /////////////////////////////////////////
        //System.out.println(getCertificate(B_matrix, B_edges, B_degrees));
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

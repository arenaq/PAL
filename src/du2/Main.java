package du2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class Main {

    static int N, M; // number of nodes, number of edges
    static int[] degree, weight, number_of_edges, index_of_node_in_topo;
    static int[][] edges;
    static boolean[][] nasl, pred;

    static void topologicalSort() {
        int[] queue = new int[N];
        int queue_size = 0;
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                queue[queue_size] = i;
                index_of_node_in_topo[i] = queue_size++;
            }
        }
        for (int i = 0; i < queue_size; i++) {
            int A = queue[i];
            for (int j = 0; j < number_of_edges[A]; j++) {
                int B = edges[A][j];
                pred[B][A] = true; // creating prededecesors sets
                for (int k = 0; k < N; k++) {
                    if (pred[A][k] == true) pred[B][k] = true;
                }
                if (--degree[B] == 0) {
                    queue[queue_size] = B;
                    index_of_node_in_topo[B] = queue_size++;
                }
            }
        }
    }
    
    static void createNasl() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pred[i][j] == true) {
                    nasl[j][i] = true;
                }
            }
        }
    }
    
//    static boolean isPathBetween(int A, int B) {
//        for (int i = 0; i < edges[A].length; i++) {
//            if (edges[A][i] == B) return true;
//            if (isPathBetween(edges[A][i], B)) return true;
//        }
//        return false;
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N];
        weight = new int[N];
        index_of_node_in_topo = new int[N];
        number_of_edges = new int[N];
        edges = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            edges[node1][number_of_edges[node1]] = node2;
            number_of_edges[node1]++;
            degree[node2]++;
        }
        
        nasl = new boolean[N][N];
        pred = new boolean[N][N];
        
        topologicalSort();
        createNasl();
        
        int[] result = new int[3];
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < number_of_edges[i]; j++) {
                int A = i;
                int B = edges[i][j];
                int weight_sum = 0;
                for (int k = 0; k < N; k++) {
                    if (nasl[A][k] == true && pred[B][k] == true) {
                        weight_sum += weight[k];
                    }
                }
                if (weight_sum == 0) continue;
                weight_sum += weight[A]+weight[B];
                if (weight_sum > result[2]) {
                    result[0] = A;
                    result[1] = B;
                    result[2] = weight_sum;
                }
            }
        }
        System.out.println(result[0]+" "+result[1]+" "+result[2]);
    }

}

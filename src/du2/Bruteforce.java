package du2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 *
 * @author Petr Kuška
 */
public class Bruteforce {

    static int N, M; // number of nodes, number of edges
    static int[] degree, weight, number_of_edges, index_of_node_in_topo;
    static int[][] edges;
    static Set<Integer>[] nasl, pred;

    static int[] topologicalSort() {
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
                pred[B].add(A);
                if (--degree[B] == 0) {
                    queue[queue_size] = B;
                    index_of_node_in_topo[B] = queue_size++;
                }
            }
        }
        return queue;
    }
    
    static boolean isPathBetween(int A, int B) {
        for (int i = 0; i < edges[A].length; i++) {
            if (edges[A][i] == B) return true;
            if (isPathBetween(edges[A][i], B)) return true;
        }
        return false;
    }

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
        
        nasl = new TreeSet[N];
        pred = new TreeSet[N];
        for (int i = 0; i < N; i++) {
            nasl[i] = new TreeSet();
            pred[i] = new TreeSet();
        }     
        int[] topo = topologicalSort();
        
        for (int i = 0; i < N; i++) {
            System.out.print(topo[i] + " ");
        }
        System.out.println();
        
        int[] result = new int[3];
        for (int i = 0; i < N; i++) {
            int A = topo[i];
            int max_index_in_topo = index_of_node_in_topo[A];
            for (int j = 0; j < edges[A].length; j++) {
                int B = edges[A][j];
                // constrain - if B belongs to (NASL(A) & PRED(A))
                if (!isPathBetween(A, B)) continue;
                if (index_of_node_in_topo[B] > max_index_in_topo) max_index_in_topo = index_of_node_in_topo[B];
            }
            int weight_sum = 0;
            for (int j = i; j <= max_index_in_topo; j++) {
                weight_sum += weight[topo[j]];
            }
            if (weight_sum > result[2]) {
                result[0] = topo[i];
                result[1] = topo[max_index_in_topo];
                result[2] = weight_sum;
            }
        }
        System.out.println(result[0]+" "+result[1]+" "+result[2]);
    }

}

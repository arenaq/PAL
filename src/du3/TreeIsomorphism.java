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
    static short N; // number of nodes of a tree A, max 1200
    static short[][] A_edges, B_edges;
    static short[] A_number_of_edges, B_number_of_edges;
    static short[] A_edge_to_degree, B_edge_to_degree;
    
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
    
    static void copyGraphExceptVertex(short[][] out_graph, short[] out_number_of_edges, int vertex) {
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < B_number_of_edges[i]; j++) {
                short val = B_edges[i][j];
                if (val != vertex) {
                    out_graph[i][out_number_of_edges[i]++] = val;
                }
            }
        }
        for (int i = vertex+1; i < B_edges.length; i++) {
            for (int j = 0; j < B_number_of_edges[i]; j++) {
                short val = B_edges[i][j];
                if (val != vertex) {
                    out_graph[i-1][out_number_of_edges[i-1]++] = val;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Short.parseShort(in.readLine());
        
        A_edges = new short[N][N];
        A_number_of_edges = new short[N];
        A_edge_to_degree = new short[N];
        for (int i = 0; i < N-1; i++) {
             StringTokenizer st = new StringTokenizer(in.readLine());
             short v1 = Short.parseShort(st.nextToken());
             short v2 = Short.parseShort(st.nextToken());
             A_edges[v1][A_number_of_edges[v1]++] = v2;
             A_edges[v2][A_number_of_edges[v2]++] = v1;
             A_edge_to_degree[v1]++;
             A_edge_to_degree[v2]++;
        }
        
        B_edges = new short[N+1][N+1];
        B_number_of_edges = new short[N+1];
        B_edge_to_degree = new short[N+1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
             short v1 = Short.parseShort(st.nextToken());
             short v2 = Short.parseShort(st.nextToken());
             B_edges[v1][B_number_of_edges[v1]++] = v2;
             B_edges[v2][B_number_of_edges[v2]++] = v1;
             B_edge_to_degree[v1]++;
             B_edge_to_degree[v2]++;
        }
        
        System.out.println("Graph B:");
        printGraph(B_edges, B_number_of_edges);
        for (int i = 0; i < B_edge_to_degree.length; i++) {
            short[][] graph_candidate = new short[N][N];
            short[] number_of_edges = new short[N];
            if (B_edge_to_degree[i] == 1) {
                copyGraphExceptVertex(graph_candidate, number_of_edges, i);
                // VYPIS
                System.out.println("Vertex "+i+":");
                printGraph(graph_candidate, number_of_edges);
            }
        }
        // TODO
        /* pro vsechny vrcholy X stupne 1 z grafu B:
        pokud podgraf G grafu B vytvoreny odebranim hrany vrcholu X ma stejne schema stupnu vrcholu,   tak vytvorime z grafu G certifikat a porovname jej s grafem A
        */
    }
    
    static void printGraph(short[][] graph, short[] number_of_edges) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < number_of_edges[i]; j++) {
                sb.append(i+" "+graph[i][j]+"\n");
            }
        }
        System.out.println(sb.toString());
    }
    
    static void print1dArray(short[] array) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < array.length; i++) {
            sb.append(i+":"+array[i]+"\n");
        }
        System.out.println(sb.toString());
    }
}

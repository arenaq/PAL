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
    static short[] A_degree, B_degree;
    
    static short[] getCertificate(short[][] tree) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Short.parseShort(in.readLine());
        
        A_edges = new short[N][N]; // TODO rozměry pole?
        A_number_of_edges = new short[N];
        A_degree = new short[N];
        for (int i = 0; i < N-1; i++) {
             StringTokenizer st = new StringTokenizer(in.readLine());
             short v1 = Short.parseShort(st.nextToken());
             short v2 = Short.parseShort(st.nextToken());
             A_edges[v1][A_number_of_edges[v1]++] = v2;
             A_edges[v2][A_number_of_edges[v2]++] = v1;
             A_degree[v1]++;
             A_degree[v2]++;
        }
        
        B_edges = new short[N+1][N+1];
        B_number_of_edges = new short[N+1];
        B_degree = new short[N+1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
             short v1 = Short.parseShort(st.nextToken());
             short v2 = Short.parseShort(st.nextToken());
             B_edges[v1][B_number_of_edges[v1]++] = v2;
             B_edges[v2][B_number_of_edges[v2]++] = v1;
             B_degree[v1]++;
             B_degree[v2]++;
        }
        // TODO
        /* pro vsechny vrcholy X stupne 1 z grafu B:
        pokud podgraf G grafu B vytvoreny odebranim hrany vrcholu X ma stejne schema stupnu vrcholu,   tak vytvorime z grafu G certifikat a porovname jej s grafem A
        */
    }
    
    static void printEdges() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < A_number_of_edges[i]; j++) {
                sb.append(i+" "+A_edges[i][j]+"\n");
            }
        }
        sb.append("-\n");
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < B_number_of_edges[i]; j++) {
                sb.append(i+" "+B_edges[i][j]+"\n");
            }
        }
        System.out.println(sb.toString());
    }
    
    static void printDegrees() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < N; i++) {
            sb.append(i+":"+A_degree[i]+"\n");
        }
        sb.append("-\n");
        for (int i = 0; i < N+1; i++) {
            sb.append(i+":"+B_degree[i]+"\n");
        }
        System.out.println(sb.toString());
    }
}

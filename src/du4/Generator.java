package du4;

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
public class Generator {

    static int pseudoRandom(int seed, int A, int C, int M) {
        return (A * seed + C) % M;
    }
    
    static int numberOfDistinctFactors(int n) {
        Set<Integer> s = new TreeSet();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                s.add(i);
                n = n / i;
            }
        }
        if (n > 1) {
            s.add(n);
        }
        return s.size();
    }

    public static void main(String[] args) throws IOException {
        int A, C, M, K, N;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int mcn;
        int seed = 0;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb_tmp = new StringBuilder();
        int index = 0;
        StringBuilder sb_index = new StringBuilder();
        do {
            sb_index.append(String.valueOf(index++)+"\t");
            sb.append(seed+"\t");
            sb_tmp.append(numberOfDistinctFactors(seed)+"\t");
            seed = pseudoRandom(seed, A, C, M);
        } while (seed != 0);
        System.out.println(sb_index.toString());
        System.out.println(sb.toString());
        System.out.println(sb_tmp.toString());
    }

}

package du4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
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
                if (s.contains(i)) return -1;
                s.add(i);
                n = n / i;
            }
        }
        if (n > 1) {
            if (s.contains(n)) return -1;
            s.add(n);
        }
        return s.size();
    }
    
    static boolean hasKDistinctFactors(BitSet sieve, int n, int K) {
        int dist_fact = 0;
        int tmp = n;
        for (int i = sieve.nextSetBit(0); i <= n; i = sieve.nextSetBit(i+1)) {
            if (tmp % i == 0) {
                dist_fact++;
                tmp = tmp / i;
                if (dist_fact == K) break;
                if (tmp % i == 0) return false;
            }
        }
        return dist_fact==K && tmp == 1;
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
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        BitSet sieve = new BitSet(M);
        sieve.set(2, sieve.size(), true);
        
        for (int i = 2; i*i <= sieve.size(); i++) {
            if (sieve.get(i)) {
                int j = i;
                int sqrt = i * j;
                while (sqrt >= 0) {
                    if (sqrt > sieve.size()) break;
                    sieve.set(sqrt, false);
                    j++;
                    sqrt = i * j;
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////
        int[] seeds = new int[M];
        boolean[] challenging = new boolean[M];
        int best_seed = 0, best_count = 0;
        int seed = pseudoRandom(0, A, C, M);
        int count = 0;
        int index = 1;
        for (; index < N; index++) {
            if (hasKDistinctFactors(sieve, seed, K)) {
                count++;
                challenging[index] = true;
            }
            seeds[index] = seed;
            seed = pseudoRandom(seed, A, C, M);
        }
        
        best_count = count;
        
        while (seed != 0) {
            if (hasKDistinctFactors(sieve, seed, K)) {
                count++;
                challenging[index] = true;
            }
            seeds[index] = seed;
            seed = pseudoRandom(seed, A, C, M);
            if (challenging[index - N]) {
                count--;
            } else {
                if (count > best_count) {
                    best_seed = seeds[index - N + 1];
                    best_count = count;
                }
            }
            index++;
        }
        System.out.println(best_seed+" "+best_count);
    }

}

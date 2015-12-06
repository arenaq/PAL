package du4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class Generator {

    static int pseudoRandom(int seed, int A, int C, int M) {
        return (A * seed + C) % M;
    }
    
    static boolean hasKDistinctFactors(BitSet sieve, int n, int K) {
        int dist_fact = 0;
        int tmp = n;
        for (int i = sieve.nextSetBit(0); i <= tmp; i = sieve.nextSetBit(i+1)) {
            if (n % i == 0) {
                dist_fact++;
                n = n / i;
                if (dist_fact == K) break;
                if (n % i == 0) return false;
            }
        }
        return dist_fact==K && n == 1;
    }

    public static void main(String[] args) throws IOException {
        int A, C, M, K, N;
        // parse arguments
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        // creation of Eratosthenesrat sieve
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
        // OK, now let's have some fun
        int[] seeds = new int[M]; // array of all seeds (will be usefull in the end)
        boolean[] challenging = new boolean[M]; // array of challenging numbers
        int best_seed = 0, best_count; // best seed to start from and best count of challenging numbers in the set
        int seed = pseudoRandom(0, A, C, M); // let's start from 0 (its given in the assignment)
        int count = 0; // number of challenging numbers in the set
        // now lets initiate the first set from 1 to N
        int index = 1;
        for (; index < N; index++) {
            if (hasKDistinctFactors(sieve, seed, K)) {
                count++;
                challenging[index] = true;
            }
            seeds[index] = seed;
            seed = pseudoRandom(seed, A, C, M);
        }
        
        best_count = count; // the best we have so far is our only one...
        
        // ok, now... lets not do the whole set again, instead just move to the right in the sequence of pseudo random numbers 
        // and decrease counter if the first index of our set (the one that we are leaving behind) was challenging number or not
        // and increase counter if the new number (the one that is added to our set) is challenging number or not
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

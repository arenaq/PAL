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
        long lA = A;
        long lC = C;
        long lM = M;
        long muhehehe = (lA * seed + lC) % lM;
        return (int)muhehehe;
    }
    
    static BitSet getChallengingNumbers(BitSet primes, int K, int M) {
        BitSet challenging = new BitSet(M);
        switch (K) {
            case 1:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    challenging.set((int)n1);
                }
            break;
            case 2:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        challenging.set((int)n2);
                    }
                }
            break;
            case 3:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            challenging.set((int)n3);
                        }
                    }
                }
            break;
            case 4:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                challenging.set((int)n4);
                            }
                        }
                    }
                }
            break;
            case 5:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                for (int i5 = primes.nextSetBit(i4 + 1);; i5 = primes.nextSetBit(i5 + 1)) {
                                    long n5 = n4 * (int)i5;
                                    if (n5 <= 0 || n5 >= M) break;
                                    challenging.set((int)n5);
                                }
                            }
                        }
                    }
                }
            break;
            case 6:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                for (int i5 = primes.nextSetBit(i4 + 1);; i5 = primes.nextSetBit(i5 + 1)) {
                                    long n5 = n4 * (int)i5;
                                    if (n5 <= 0 || n5 >= M) break;
                                    for (int i6 = primes.nextSetBit(i5 + 1);; i6 = primes.nextSetBit(i6 + 1)) {
                                        long n6 = n5 * (int)i6;
                                        if (n6 <= 0 || n6 >= M) break;
                                        challenging.set((int)n6);
                                    }
                                }
                            }
                        }
                    }
                }
            break;
            case 7:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                for (int i5 = primes.nextSetBit(i4 + 1);; i5 = primes.nextSetBit(i5 + 1)) {
                                    long n5 = n4 * (int)i5;
                                    if (n5 <= 0 || n5 >= M) break;
                                    for (int i6 = primes.nextSetBit(i5 + 1);; i6 = primes.nextSetBit(i6 + 1)) {
                                        long n6 = n5 * (int)i6;
                                        if (n6 <= 0 || n6 >= M) break;
                                        for (int i7 = primes.nextSetBit(i6 + 1);; i7 = primes.nextSetBit(i7 + 1)) {
                                            long n7 = n6 * (int)i7;
                                            if (n7 <= 0 || n7 >= M) break;
                                            challenging.set((int)n7);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            break;
            case 8:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                for (int i5 = primes.nextSetBit(i4 + 1);; i5 = primes.nextSetBit(i5 + 1)) {
                                    long n5 = n4 * (int)i5;
                                    if (n5 <= 0 || n5 >= M) break;
                                    for (int i6 = primes.nextSetBit(i5 + 1);; i6 = primes.nextSetBit(i6 + 1)) {
                                        long n6 = n5 * (int)i6;
                                        if (n6 <= 0 || n6 >= M) break;
                                        for (int i7 = primes.nextSetBit(i6 + 1);; i7 = primes.nextSetBit(i7 + 1)) {
                                            long n7 = n6 * (int)i7;
                                            if (n7 <= 0 || n7 >= M) break;
                                            for (int i8 = primes.nextSetBit(i7 + 1);; i8 = primes.nextSetBit(i8 + 1)) {
                                                long n8 = n7 * (int)i8;
                                                if (n8 <= 0 || n8 >= M) break;
                                                challenging.set((int)n8);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            break;
            case 9:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                for (int i5 = primes.nextSetBit(i4 + 1);; i5 = primes.nextSetBit(i5 + 1)) {
                                    long n5 = n4 * (int)i5;
                                    if (n5 <= 0 || n5 >= M) break;
                                    for (int i6 = primes.nextSetBit(i5 + 1);; i6 = primes.nextSetBit(i6 + 1)) {
                                        long n6 = n5 * (int)i6;
                                        if (n6 <= 0 || n6 >= M) break;
                                        for (int i7 = primes.nextSetBit(i6 + 1);; i7 = primes.nextSetBit(i7 + 1)) {
                                            long n7 = n6 * (int)i7;
                                            if (n7 <= 0 || n7 >= M) break;
                                            for (int i8 = primes.nextSetBit(i7 + 1);; i8 = primes.nextSetBit(i8 + 1)) {
                                                long n8 = n7 * (int)i8;
                                                if (n8 <= 0 || n8 >= M) break;
                                                for (int i9 = primes.nextSetBit(i8 + 1);; i9 = primes.nextSetBit(i9 + 1)) {
                                                    long n9 = n8 * (int)i9;
                                                    if (n9 <= 0 || n9 > M) break;
                                                    challenging.set((int)n9);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            break;
            case 10:
                for (int i1 = primes.nextSetBit(0);; i1 = primes.nextSetBit(i1 + 1)) {
                    long n1 = (int)i1;
                    if (n1 <= 0 || n1 >= M) break;
                    for (int i2 = primes.nextSetBit(i1 + 1);; i2 = primes.nextSetBit(i2 + 1)) {
                        long n2 = n1 * (int)i2;
                        if (n2 <= 0 || n2 >= M) break;
                        for (int i3 = primes.nextSetBit(i2 + 1);; i3 = primes.nextSetBit(i3 + 1)) {
                            long n3 = n2 * (int)i3;
                            if (n3 <= 0 || n3 >= M) break;
                            for (int i4 = primes.nextSetBit(i3 + 1);; i4 = primes.nextSetBit(i4 + 1)) {
                                long n4 = n3 * (int)i4;
                                if (n4 <= 0 || n4 >= M) break;
                                for (int i5 = primes.nextSetBit(i4 + 1);; i5 = primes.nextSetBit(i5 + 1)) {
                                    long n5 = n4 * (int)i5;
                                    if (n5 <= 0 || n5 >= M) break;
                                    for (int i6 = primes.nextSetBit(i5 + 1);; i6 = primes.nextSetBit(i6 + 1)) {
                                        long n6 = n5 * (int)i6;
                                        if (n6 <= 0 || n6 >= M) break;
                                        for (int i7 = primes.nextSetBit(i6 + 1);; i7 = primes.nextSetBit(i7 + 1)) {
                                            long n7 = n6 * (int)i7;
                                            if (n7 <= 0 || n7 >= M) break;
                                            for (int i8 = primes.nextSetBit(i7 + 1);; i8 = primes.nextSetBit(i8 + 1)) {
                                                long n8 = n7 * (int)i8;
                                                if (n8 <= 0 || n8 >= M) break;
                                                for (int i9 = primes.nextSetBit(i8 + 1);; i9 = primes.nextSetBit(i9 + 1)) {
                                                    long n9 = n8 * (int)i9;
                                                    if (n9 <= 0 || n9 > M) break;
                                                    for (int i10 = primes.nextSetBit(i9 + 1);; i10 = primes.nextSetBit(i10 + 1)) {
                                                        long n10 = n9 * (int)i10;
                                                        if (n9 <= 0 || n9 > M) break;
                                                        challenging.set((int)n10);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            break;
        }
        return challenging;
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
        sieve.set(2, M, true);
        
        for (int i = 2; i*i <= M; i++) {
            if (sieve.get(i)) {
                int j = i;
                int sqrt = i * j;
                while (sqrt >= 0) {
                    if (sqrt > M) break;
                    sieve.set(sqrt, false);
                    j++;
                    sqrt = i * j;
                }
            }
        }
        // OK, now let's have some fun
        int[] seeds = new int[M]; // array of all seeds (will be usefull in the end)
        BitSet challenging = getChallengingNumbers(sieve, K, M); // array of challenging numbers
        int best_seed = 0, best_count; // best seed to start from and best count of challenging numbers in the set
        int seed = pseudoRandom(0, A, C, M); // let's start from 0 (its given in the assignment)
        int count = 0; // number of challenging numbers in the set
        // now lets initiate the first set from 1 to N
        int index;
        for (index = 1; index < N; index++) {
            if (challenging.get(seed)) {
                count++;
            }
            seeds[index] = seed;
            seed = pseudoRandom(seed, A, C, M);
        }
        
        best_count = count; // the best we have so far is our only one...
        
        // ok, now... lets not do the whole set again, instead just move to the right in the sequence of pseudo random numbers 
        // and decrease counter if the first index of our set (the one that we are leaving behind) was challenging number or not
        // and increase counter if the new number (the one that is added to our set) is challenging number or not
        while (seed != 0) {
            if (challenging.get(seed)) {
                count++;
            }
            seeds[index] = seed;
            seed = pseudoRandom(seed, A, C, M);
            if (challenging.get(seeds[index - N])) {
                count--;
            } else {
                if (count > best_count) {
                    best_seed = seeds[index - N + 1];
                    best_count = count;
                }
            }
            index++;
        }
        for (int i = 1; i < N; i++) {
            if (challenging.get(seed)) {
                count++;
            }
            seed = seeds[i];
            if (challenging.get(seeds[M-N+i-1])) {
                count--;
            } else {
                if (count > best_count) {
                    best_seed = seeds[M-N+i];
                    best_count = count;
                }
            }
        }
        System.out.println(best_seed+" "+best_count);
    }

}

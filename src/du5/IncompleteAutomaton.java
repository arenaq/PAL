package du5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class IncompleteAutomaton {
    static short S; // number of states (indices 0,...,S-1), S <= 300
    static byte A; // size of the input alphabet (first A characters from ('a',...,'z'), 2 <= A <= 26
    static byte F; // number of final states, F <= 30
    static short P; // number of positive examples, P <= 2000
    static short N; // number of negative examples, N <= 5000
    static short L; // length of each example, L <= 30000
    
    /**
     * returns index of the letter in English alphabet (26 letters)
     * @param letter
     * @return index
     */
    static short getIndexInAlphabet(char letter) {
        return (short)(letter-97);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        S = Short.parseShort(st.nextToken());
        A = Byte.parseByte(st.nextToken());
        F = Byte.parseByte(st.nextToken());
        P = Short.parseShort(st.nextToken());
        N = Short.parseShort(st.nextToken());
        L = Short.parseShort(st.nextToken());
    }
    
}

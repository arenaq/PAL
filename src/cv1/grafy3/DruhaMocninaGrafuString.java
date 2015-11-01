package cv1.grafy3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class DruhaMocninaGrafuString {

    static long pocet_hran;
    static Tuple[] G;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // ziskani poctu hran
        try {
            pocet_hran = Long.parseLong(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // sestrojeni grafu G
        for (int i = 0; i < pocet_hran; i++) {
            G[i] = getTuple(in.readLine());
        }
        
        // vypis grafu G2
        StringBuilder sb = new StringBuilder();
        
    }

    private static class Tuple<A, B> {

        A a;
        B b;

        Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    public static final Tuple getTuple(String line) {
        int index = 0;
        while (line.charAt(index) != ' ') {
            index++;
        }
        return new Tuple<Object, Object>(line.substring(0, index), line.substring(index + 1));
    }

}

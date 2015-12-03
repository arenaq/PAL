package du4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Petr Kuška
 */
public class NumberOfDistinctFactors {

    static final String filename = "factors.txt";
    static final int n = 300000000;

    static int numberOfDistinctFactors(int n) {
        Set<Integer> s = new TreeSet();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                if (s.contains(i)) {
                    return -1;
                }
                s.add(i);
                n = n / i;
            }
        }
        if (n > 1) {
            if (s.contains(n)) {
                return -1;
            }
            s.add(n);
        }
        return s.size();
    }

    public static void main(String[] args) throws IOException {
        File file = new File(filename);

        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        
        int m = n / 100;
        for (int i = 0; i < n; i++) {
            if (i % m == 0) System.out.println(i / m + " %");
            int factors = numberOfDistinctFactors(i);
            if (factors == 2) {
                //bw.write(i);
                //bw.write(":");
                bw.write(factors);
                bw.write(" ");
            }
        }

        bw.close();
    }
}

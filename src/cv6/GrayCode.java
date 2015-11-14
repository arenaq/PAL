package cv6;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Petr Kuška
 */
public class GrayCode {

    static char[] set = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("subsets.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < (1 << set.length); i++) { // (1 << set.length) = 2^(set.length)
            int gray = i ^ (i >> 1); // binary to gray code
            sb.delete(0, sb.length());
            int power = 0;
            while (gray > 0) {
                if ((gray & 1) == 1) {
//                    if (sb.length() > 0) sb.append(" "); // takes ~10 sekund
                    sb.append(set[power]);
                }
                power++;
                gray = gray >> 1;
            }
            pw.println(sb.toString());
        }
        pw.close();
    }

}

package cv6;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Petr Kuška
 */
public class GrayCode {

//    static String[] set = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static final String[] set = {"A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("subsets.txt");
        //TODO - gray code increment function http://stackoverflow.com/questions/17490431/gray-code-increment-function
        for (int i = 1; i < (1 << set.length); i++) { // (1 << set.length) = 2^(set.length)
            int gray = i ^ (i >> 1); // binary to gray code
            String s = "";
            int power = 0;
            while (gray > 0) {
                if ((gray & 1) == 1) {
                    s = set[power++] + " " + s;
                    gray = gray >> 1;
                }
            }
            pw.println(s);
        }
        pw.close();
    }

}

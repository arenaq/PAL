package cv7;

import java.math.BigInteger;

/**
 *
 * @author Petr Kuška
 */
public class PrimalityTest {

    /*public static boolean FermatTest(BigInteger n, int k) {
        Random r = new Random();
        BigInteger one = new BigInteger("1");
        for (int i = 0; i < k; i++) {
            int a = r.nextInt(n. - 3) + 2; // <2, n-2>
            BigInteger aN = new BigInteger(String.valueOf(a));
            if (aN.pow(n).mod(_n).compareTo(one) != 0) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BigInteger act = new BigInteger("1000000000");
        BigInteger to = new BigInteger("1200000000");
        
        while(act.compareTo(to) < 0) {
            if ()
        }
    }*/
    public static void main(String[] args) {
        BigInteger act = new BigInteger("1000000000"); // n
        BigInteger to = new BigInteger("1200000000");
        BigInteger one = new BigInteger("1");
        
        while(act.compareTo(to) < 0) {
            if (act.isProbablePrime(1)) {
                System.out.println(act.toString());
            }
            act = act.add(one);
        }
    }

}

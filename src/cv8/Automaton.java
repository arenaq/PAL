package cv8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Petr Kuška
 */
public class Automaton {

    private static class UnexpectedStateException extends Exception {

        public UnexpectedStateException(String string) {
        }
    }

    // (ac* + bb)*a
    public static boolean test(String s) throws UnexpectedStateException {
        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (state) {
                case 0:
                    if (s.charAt(i) == 'a') {
                        state = 1;
                    } else if (s.charAt(i) == 'b') {
                        state = 2;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if (s.charAt(i) == 'c') {
                        state = 3;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    if (s.charAt(i) == 'b') {
                        state = 4;
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    if (s.charAt(i) == 'a') {
                        state = 1;
                    } else if (s.charAt(i) == 'b') {
                        state = 2;
                    } else {
                        return false;
                    }
                    break;
                case 4:
                    if (s.charAt(i) == 'a') {
                        state = 1;
                    } else if (s.charAt(i) == 'b') {
                        state = 2;
                    } else {
                        return false;
                    }
                    break;
                default:
                    throw new UnexpectedStateException("State " + state + " is not defined.");
            }
        }
        return state == 1;
    }

    public static void main(String[] args) throws IOException, UnexpectedStateException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        while (input.compareTo("") != 0) {
            System.out.println(input + ":" + (test(input) ? "ACCEPTED" : "NOT ACCEPTED"));
            input = in.readLine();
        }
    }

}

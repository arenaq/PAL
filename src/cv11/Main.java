package cv11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Petr Kuška
 */
public class Main {

    static SkipList<Integer> list;

    static void readFromStdIn() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer key = 0;
        while (key >= 0) {
            key = Integer.valueOf(in.readLine());
            list.insert(key, key);
            System.out.println(list.toString());
        }
    }

    static void exercise1() {
        list.insert(3, 16, 16);
        list.insert(2, 23, 23);
        list.insert(2, 18, 18);
        list.insert(2, 5, 5);
        list.insert(1, 15, 15);
        list.insert(1, 19, 19);
        list.insert(1, 33, 33);
        list.insert(1, 23, 23);
        list.insert(1, 16, 16);
        list.insert(2, 15, 15);
        list.insert(2, 21, 21);
        list.insert(1, 4, 4);
        list.insert(2, 22, 22);
        list.insert(1, 6, 6);
        list.insert(2, 6, 6);
        list.insert(4, 17, 17);
    }

    static void exercise1_delete() {
        list.delete(16);
        list.delete(23);
        list.delete(18);
        list.delete(5);
        list.delete(15);
        list.delete(19);
        list.delete(33);
        list.delete(23);
        list.delete(16);
        list.delete(15);
        list.delete(21);
        list.delete(4);
        list.delete(22);
        list.delete(6);
        list.delete(6);
        list.delete(17);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        list = new SkipList(5);
        exercise1();
        System.out.println("Level: "+list.level);
        System.out.println(list.toString());
        exercise1_delete();
        System.out.println("Level: "+list.level);
        System.out.println(list.toString());
    }

}

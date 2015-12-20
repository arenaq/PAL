package cv11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class SkipList {
    static List<Integer> list_head;
    static final double p = 0.25;
    static int maxLevel = 1;

    private static class List<T> {
        T value;
        List<T>[] pointers;
    }
    
    static int randomLevel() { //random() returns a random value in [0..1)
        int newLevel = 1;
        while (new Random().nextDouble() < p) // no MaxLevel check
            newLevel++;
        return Math.min(newLevel, maxLevel); // efficiency!
    }

    public static void main(String[] args) throws IOException {
        list_head = new List();
        list_head.pointers = new List[1];
        list_head.value = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        while (st.hasMoreElements()) {
            List<Integer>[] update = new List[maxLevel+1];
            int key = Integer.parseInt(st.nextToken());
            // insert
            List<Integer> node = list_head;
            for (int i = node.pointers.length; i >= 1; i--) {
                 while (node.pointers[i-1].value < key) node = node.pointers[i-1];
                update[i-1] = node;
            }
            
            node = node.pointers[0];
            if (node.value == key) {
                node.value = key;
            } else {
                int level = randomLevel();
                if (level > maxLevel) {
                    level = ++maxLevel;
                    update[level-1] = list_head;
                }
                
                node = new List();
                node.pointers = new List[level];
                node.value = key;
                for (int i = 0; i < level; i++) {
                    node.pointers[i] = update[i].pointers[i];
                    update[i].pointers[i] = node;
                }
            }
            
        }
    }

}

package cv11;

import java.util.Random;

/**
 *
 * @author Petr Kuška
 */
public class SkipList<Data> {
    
    static final double p = 0.25;
    private final int maxLevel;
    public Node<Data> header;
    public int level;
    
    public class Node<T> {
        public Node[] forward;
        public Integer key;
        public T value;
        
        public Node(int level, Integer key, T data) {
            this.forward = new Node[level];
            this.key = key;
            this.value = data;
        }
    }
    
    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        level = 1;
        //Node<Data> tail = new Node(maxLevel, Integer.MAX_VALUE, null);
        header = new Node(maxLevel, 0/*Integer.MIN_VALUE*/, null);
        //header.forward[0] = tail;
    }
    
    public Data search(int searchKey) {
        Node<Data> x = header;
        for (int i = level; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].key < searchKey) {
                x = x.forward[i];
            }
        }
        x = x.forward[0];
        if (x != null && x.key == searchKey) {
            return x.value;
        } else {
            return null;
        }
    }
    
    public void insert(int newLevel, int searchKey, Data newValue) {
        Node[] update = new Node[level+1];
        Node<Data> x = this.header;
        for (int i = this.level - 1; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].key < searchKey) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        
        x = x.forward[0];
        if (x != null && x.key == searchKey) {
            x.value = newValue;
        } else {
            if (newLevel > this.level) {
                newLevel = ++this.level;
                update[newLevel-1] = this.header;
            }
            
            x = new Node(newLevel, searchKey, newValue);
            for (int i = 0; i < newLevel; i++) {
                x.forward[i] = update[i].forward[i];
                update[i].forward[i] = x;
            }
        }
    }
    
    public void insert(int searchKey, Data newValue) {
        Node<Data>[] update = new Node[level+1];
        Node<Data> x = this.header;
        for (int i = this.level - 1; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].key < searchKey) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        
        x = x.forward[0];
        if (x != null && x.key == searchKey) {
            x.value = newValue;
        } else {
            int newLevel = randomLevel();
            
            if (newLevel > this.level) {
                newLevel = ++this.level;
                update[newLevel-1] = this.header;
            }
            
            x = new Node(newLevel, searchKey, newValue);
            for (int i = 0; i < newLevel; i++) {
                x.forward[i] = update[i].forward[i];
                update[i].forward[i] = x;
            }
        }
    }
    
    public void delete(int searchKey) {
        Node[] update = new Node[level];
        Node x = this.header;
        for (int i = this.level-1; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].key < searchKey) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = x.forward[0];
        if (x != null && x.key == searchKey) {
            for (int i = 0; i < level; i++) {
                if (update[i].forward[i] != x) break;
                update[i].forward[i] = x.forward[i];
            }
            while (this.level > 1 && this.header.forward[this.level-1] == null) this.level--;
        }
    }
    
    private int randomLevel() { //random() returns a random value in [0..1)
        int newLevel = 1;
        while (new Random().nextDouble() < p) // no MaxLevel check
            newLevel++;
        return Math.min(newLevel, maxLevel); // efficiency!
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            Node x = header.forward[i];
            while (x != null) {
                sb.append(x.key+" ");
                x = x.forward[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
}

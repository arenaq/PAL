package cv3.binaryheap;

import java.util.Arrays;

/**
 *
 * @author Petr Kuška
 */
public class BinaryHeap {
    private static final int DEFAULT_CAPACITY = 10;
    protected int[] array;
    protected int size;
    
    @SuppressWarnings("unchecked")
    public BinaryHeap () {
        array = new int[DEFAULT_CAPACITY];  
        size = 0;
    }

    /**
     * Adds a new key x to the heap.
     */
    public void insert(int x) {
        if (size >= array.length - 1) {
            array = this.resize();
        }
        
        size++;
        array[size] = x;
        
        bubbleUp();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the minimum item of the heap.
     */
    public int accessMin() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return array[1];
    }

    /**
     * Removes and returns the minimum node of the heap.
     */
    public int deleteMin() {
    	int result = accessMin();

        removeElement(1);
    	bubbleDown();
    	
    	return result;
    }
    
    /**
     * Removes and returns the element on index in the heap.
     */
    public int remove(int x) {
        int result = 0;
        if(x <= size) {
            result = array[x];
        } else {
            throw new IllegalStateException();
        }

        removeElement(x);
    	bubbleDown(x);
    	
    	return result;
    }
    
    /**
     * Removes element from array
     */
    private void removeElement(int x) {
        array[x] = array[size];
    	array[size] = Integer.MIN_VALUE;
    	size--;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    protected void bubbleDown() {
        int index = 1;

        while (hasLeftChild(index)) {
            int smallerChild = leftChildIndex(index);
            
            if (hasRightChild(index) && array[leftChildIndex(index)] > (array[rightChildIndex(index)])) {
                smallerChild = rightChildIndex(index);
            } 
            
            if (array[index] > (array[smallerChild])) {
                swap(index, smallerChild);
            } else {
                break;
            }
            
            index = smallerChild;
        }        
    }

    protected void bubbleDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChild = leftChildIndex(index);
            
            if (hasRightChild(index) && array[leftChildIndex(index)] > (array[rightChildIndex(index)])) {
                smallerChild = rightChildIndex(index);
            } 
            
            if (array[index] > (array[smallerChild])) {
                swap(index, smallerChild);
            } else {
                break;
            }
            
            index = smallerChild;
        }        
    }

    protected void bubbleUp() {
        int index = this.size;
        
        while (hasParent(index) && (parent(index) > (array[index]))) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }        
    }

    protected boolean hasParent(int i) {
        return i > 1;
    }

    protected int leftChildIndex(int i) {
        return i * 2;
    }

    protected int rightChildIndex(int i) {
        return i * 2 + 1;
    }

    protected boolean hasLeftChild(int i) {
        return leftChildIndex(i) <= size;
    }

    protected boolean hasRightChild(int i) {
        return rightChildIndex(i) <= size;
    }

    protected int parent(int i) {
        return array[parentIndex(i)];
    }

    protected int parentIndex(int i) {
        return i / 2;
    }
    
    protected int[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }
    
    protected void swap(int x1, int x2) {
        int tmp = array[x1];
        array[x1] = array[x2];
        array[x2] = tmp;        
    }
    
    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap();
        
        for(int i = 0; i <= 25; i++ ) {
            heap.insert(i);
        }
        
        heap.remove(4);
       
        System.out.println(heap.toString());
    }
}

package com.gk.algods;

/**
 * Implementation of MinHeap using an array. For simplicity, we do not use resizable
 * array in this program.
 *
 * Min Heap Property: Each node in the Heap has a value no larger than its child nodes.
 * Therefore, the top element (root node) has the smallest value in the Heap.
 *
 * For a node at position 'i' :-
 *  -   Parent = i/2
 *  -   Left Child = 2*i
 *  -   Right Child = 2*i+1
 *
 *                      Time        Space
 * Construct a Heap     O(N)        O(N)
 * Add                  O(logN)     O(1)
 * Pop                  O(logN)     O(1)
 * Peek                 O(1)        O(1)
 */
public class MinHeap {

    int[] minHeap; // Flattened tree structure
    int capacity; // Maximum number of items the heap can hold
    int heapSize; // Number of valid heap items

    /**
     * Constructor
     * @param capacity heap capacity
     */
    public MinHeap(int capacity){
        this.capacity = capacity;
        this.minHeap = new int[capacity+1];
        this.heapSize = 0;

        // We use indices 1-n to better track Binary tree implementation
        // Index 0 can be treated as dummy node
        this.minHeap[0] = 0;
    }

    /**
     * Add 'value' to min heap
     * TC: O(logN), N = heapSize
     * @param value input integer value
     */
    public void add(int value){

        heapSize++; // Update size
        // 1. Check if heap is at capacity: print error message and return
        if(heapSize > capacity){
            System.out.println("Addition exceeds capacity of min-heap, no action taken.");
            heapSize--;
            return;
        }
        // 2. Insert new value at last position in minHeap array
        minHeap[heapSize] = value;
        // 3. Check and Fix the heap invariant at this index
        heapifyUp(heapSize);
    }

    /**
     * Verify if the heap property holds (bottom-up) and if not, fix it
     */
    private void heapifyUp(int index) {
        // Get parent of given index
        int parent = index/2;

        // Move value at index upward until the right position is found
        // i.e. minHeap[index] >= minHeap[parent
        while(minHeap[index] < minHeap[parent] && index > 1){
            swap(index, parent);
            index = parent;
            parent = index/2;
        }
    }

    /**
     * Remove and return minimum value from the heap
     * TC: O(logN), N = heapSize
     * @return Min value
     */
    public int pop(){
        // 1. Check if heap is empty
        if(heapSize < 1){
            System.out.println("Heap is Empty!");
            return Integer.MIN_VALUE;
        }
        // 2. Remove and store the top value
        int min = minHeap[1];
        // 3. Move last value in the heap to top/root
        minHeap[1] = minHeap[heapSize];
        heapSize--;
        // 4. Check and Fix the heap invariant for top
        heapifyDown(1);
        // 5. Return removed value
        return min;
    }

    /**
     * Verify if the heap property holds (top-down) and if not, fix it
     */
    private void heapifyDown(int index) {
        // While index has children i.e. it is not a leaf node
        while(index <= heapSize/2){
            int leftChild = index * 2;
            int rightChild = index * 2 + 1;
            // If value at index is greater than both children
            if(minHeap[index] > minHeap[leftChild] || minHeap[index] > minHeap[rightChild]){
                // Swap with smaller of the two children
                int swapId = minHeap[leftChild] < minHeap[rightChild] ? leftChild : rightChild;
                swap(index, swapId);
                index = swapId;
            }else { // No action needed
                break;
            }
        }
    }

    /**
     * Peek and return the top of heap
     * @return  Min value
     */
    public int peek(){
        return minHeap[1];
    }

    /**
     * Return size of heap
     * @return size
     */
    public int size(){
        return heapSize;
    }

    /**
     * Swap items at positions 'index' and 'parent'
     * @param index position1
     * @param parent position2
     */
    private void swap(int index, int parent) {
        int temp = minHeap[parent];
        minHeap[parent] = minHeap[index];
        minHeap[index] = temp;
    }

    /**
     * @Override toString method from Object class to allow printing this class object in desired format
     */
    @Override
    public String toString(){
        if(heapSize == 0)
            return "Heap is empty!";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 1; i <= heapSize; i++){
            sb.append(minHeap[i]);
            if(i < heapSize)
                sb.append(", ");
        }
        sb.append(']');

        return sb.toString();
    }
}

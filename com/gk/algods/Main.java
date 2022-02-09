package com.gk.algods;
import java.util.*;

/**
 * Driver class to test implementations of various algorithms and datastructures
 */
public class Main {
    public static void main(String[] args){

        /**
         * PART I: Testing Sorting algorithms for input array
         */
        int[] input = new int[]{8,3,5,1,4,2,5,2,7,2,9,4,3,7,1};
        int n = input.length;
        int[] result = new int[n];

//        System.out.print("Input Array:          ");
//        printArray(input);

//        System.out.print("Insertion Sort:       ");
//        com.gk.algods.SortingAlgorithms.insertionSort(input);
//        printArray(input);

//        System.out.print("Top-Down Merge Sort:  ");
//        result = com.gk.algods.SortingAlgorithms.mergeSortTopDown(input);
//        printArray(result);

//        System.out.print("Bottom-Up Merge Sort: ");
//        result = com.gk.algods.SortingAlgorithms.mergeSortBottomUp(input);
//        printArray(result);

//        System.out.print("Quick Sort:           ");
//        SortingAlgorithms.quickSort(input, 0, n-1);

//        System.out.print("Counting Sort:        ");
//        result = SortingAlgorithms.countingSort(input, 0, 9);
//        printArray(result);

        /**
         * PART II. Test Search algorithms for array
         */


        /**
         * Implement custom list iterator
         */
//        System.out.print("Custom List Iterator:        ");
//        Integer[] arr = new Integer[]{8,3,5,1,4,2,5,2,7,2,9,4,3,7,1};
//        List<Integer> list = new LinkedList<>();
//        Collections.addAll(list, arr);
//        MyListIterator iter = new MyListIterator(list);
//        while(iter.hasNext()){
//            System.out.println(iter.next());
//        }

        /**
         * PART III. Datastructures
         */

        /**
         * MinHeap
         */

        System.out.print("Min Heap:        ");
        MinHeap minHeap = new MinHeap(3);
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(2);
        // [1,3,2]
        System.out.println(minHeap);
        // 1
        System.out.println(minHeap.peek());
        // 1
        System.out.println(minHeap.pop());
        // [2, 3]
        System.out.println(minHeap);
        minHeap.add(4);
        // Add too many elements
        minHeap.add(5);
        // [2,3,4]
        System.out.println(minHeap);

        System.out.println(minHeap.pop());
        // [3, 4]
        System.out.println(minHeap.pop());
        // [4]

        System.out.println(minHeap.pop());
        // Remove from empty
        System.out.println(minHeap.pop());

        /**
         * Max-Heap
         */
        System.out.print("Max Heap:        ");
        MaxHeap maxheap = new MaxHeap(5);
        maxheap.add(1);
        maxheap.add(2);
        maxheap.add(3);
        // [3,1,2]
        System.out.println(maxheap);
        // 3
        System.out.println(maxheap.peek());
        // 3
        System.out.println(maxheap.pop());
        System.out.println(maxheap.pop());
        System.out.println(maxheap.pop());
        // No element
        System.out.println(maxheap);
        maxheap.add(4);
        // Add too many elements
        maxheap.add(5);
        // [4,1,2]
        System.out.println(maxheap);
    }

    private static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            if(i == arr.length-1)
                System.out.print("\n\n");
            else
                System.out.print(", ");
        }
    }
}

package com.gk.algods;

/**
 * Driver class to test implementations of various algorithms and datastructures
 */
public class Main {
    public static void main(String[] args){
        int[] input = new int[]{8,3,5,1,4,2,5,2,7,2,9,4,3,7,1};
        int n = input.length;
        int[] result = new int[n];

        System.out.print("Input Array:          ");
        printArray(input);

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

        System.out.print("Counting Sort:        ");
        result = SortingAlgorithms.countingSort(input, 0, 9);
        printArray(result);

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

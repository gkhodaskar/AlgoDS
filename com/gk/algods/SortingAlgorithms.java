package com.gk.algods;

/**
 * Implementations for different Sorting Algorithms (mainly for Arrays)
 * References:-
 * 1. https://www.freecodecamp.org/news/sorting-algorithms-explained-with-examples-in-python-java-and-c/
 * 2. https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/
 * 3. https://en.wikipedia.org/wiki/Merge_sort
 */
public class SortingAlgorithms {

    /********************************************************************************************/
    /**                                  INSERTION SORT                                        **/
    /********************************************************************************************/
    /**
     * Space Complexity: O(1)
     * Time Complexity: O(n), O(n*n), O(n*n) for Best, Average, Worst cases respectively.
     * Best Case: array is already sorted
     * Average Case: array is randomly sorted
     * Worst Case: array is reverse sorted.
     * Sorting In Place: Yes
     * Stable: Yes */

    /**
     * Insertion Sort implementation
     * @param inputArray unsorted array input
     */
    public static void insertionSort(int[] inputArray){
        if(inputArray.length <= 1)
            return;
        int n = inputArray.length;

        for (int right = 1; right < n; right++) {
            int key = inputArray[right];
            int left = right - 1;
            while (left >= 0 && inputArray[left] > key) {
                inputArray[left+1] = inputArray[left];
                left -= 1;
            }
            inputArray[left+1] = key;
        }

    }

    /********************************************************************************************/
    /**                                      MERGE SORT                                        **/
    /********************************************************************************************/

    /**
     * Space Complexity: O(n)
     * Time Complexity: O(n*log(n)) -> Derived from recurrence : T(n) = 2 * T(n / 2) + O(n)
     * Sorting In Place: No in a typical implementation
     * Stable: Yes
     * Parallelizable: Yes (Several parallel variants are discussed in the third edition of Cormen,
     * Leiserson, Rivest, and Stein's Introduction to Algorithms.) */

    /**
     * Recursive (Top-Down) implementation of Merge Sort
     * @param inputArray unsorted array input
     * @return mergedArray obtained from MergeHalves()
     */
    public static int[] mergeSortTopDown(int [] inputArray){
        if(inputArray.length <= 1) {
            return inputArray;
        }
        int arrLen = inputArray.length;
        int pivot = arrLen/2;

        // In-built Arrays.copyOfRange() method
//        int[] leftHalf = mergeSortTopDown(Arrays.copyOfRange(inputArray, 0, pivot));
//        int[] rightHalf = mergeSortTopDown(Arrays.copyOfRange(inputArray, pivot, arrLen));

        // Custom copyArray() method: creates a new array and copies items
        int[] leftHalf = mergeSortTopDown(copyArray(inputArray, 0, pivot));
        int[] rightHalf = mergeSortTopDown(copyArray(inputArray, pivot, arrLen));

        return mergeHalvesTopDown(leftHalf, rightHalf);
    }

    /**
     * Merge given sorted arrays into a single array
     * @param leftHalf array 1
     * @param rightHalf array 2
     * @return merged array formed by leftHalf and rightHalf
     */
    private static int[] mergeHalvesTopDown(int[] leftHalf, int[] rightHalf) {
        // Array to store merged result
        int[] mergedArray = new int[leftHalf.length + rightHalf.length];

        // Iterators for all three arrays
        int leftPtr = 0, rightPtr = 0;
        int mergePtr = 0;

        // While both left and right pointers are smaller than respective
        // array lengths
        while(leftPtr < leftHalf.length && rightPtr < rightHalf.length){
            // Copy smaller item to mergedArray
            if(leftHalf[leftPtr] <= rightHalf[rightPtr]){
                mergedArray[mergePtr++] = leftHalf[leftPtr++];
            } else {
                mergedArray[mergePtr++] = rightHalf[rightPtr++];
            }
        }

        // Copy remaining items from leftHalf, if any
        while(leftPtr < leftHalf.length){
            mergedArray[mergePtr++] = leftHalf[leftPtr++];
        }

        // Copy remaining items from righttHalf, if any
        while (rightPtr < rightHalf.length){
            mergedArray[mergePtr++] = rightHalf[rightPtr++];
        }

        return mergedArray;
    }

    /**
     * Iterative (Bottom up) implementation of Merge Sort
     * In the bottom up approach, we divide the list into sublists of a single element
     * at the beginning. Each of the sublists is then sorted already. Then from this
     * point on, we merge the sublists two at a time until a single list remains.
     * @param inputArray unsorted input array
     * @return sorted input array
     */
    public static int[] mergeSortBottomUp(int[] inputArray){
        int n = inputArray.length;
        int[] workArray = new int[n];

        // Each 1-element sub-array in A is already "sorted".
        // Sort successively longer sub-arrays of length 2, 4, 8, 16...
        // until the whole array is sorted.
        for(int width = 1; width < n; width *= 2){
            // Traverse through i sub-arrays for each width
            for(int i = 0; i < n; i = i+2*width){
                // Merge two neighbouring sub-arrays of this width
                mergeHalvesBottomUp(inputArray, i, Math.min(i+width, n), Math.min(i+2*width, n), workArray);
            }
            // Copy to inputArray
            copyArray(workArray, 0 , n, inputArray, 0); // Note: copyArray() creates a new copy
        }
        return inputArray;
    }

    /**
     * Merge halves for Bottom up implementation
     * @param inputArray array input
     * @param left  Pointer to head of left sub-array
     * @param right Pointer to head of right sub-array
     * @param end   Pointer to end of inputArray
     * @param workArray temp array to help move items around
     */
    private static void mergeHalvesBottomUp(int[] inputArray, int left, int right, int end, int[] workArray) {
        int i = left, j = right;
        // While there are elements in the left or right sub-arrays...
        for (int k = left; k < end; k++) {
            // If left sub-array head exists and is <= existing right sub-array head.
            if (i < right && (j >= end || inputArray[i] <= inputArray[j])) {
                // copy left head
                workArray[k] = inputArray[i];
                i = i + 1;
            } else { // copy right head
                workArray[k] = inputArray[j];
                j = j + 1;
            }
        }
    }

    /********************************************************************************************/
    /**                                      QUICK SORT                                        **/
    /********************************************************************************************/

    /**
     * Space Complexity: O(n)
     * Time Complexity: O(n*log(n)),O(n*log(n)), O(n^2) for Best, Average, Worst cases respectively.
     * Sorting In Place: Yes
     * Stable: No
     */

    /**
     * Divide and Conquer implementation of Quick Sort
     * @param input unsorted array
     * @param low start of subarray
     * @param high end of subarray
     */
    public static void quickSort(int input[], int low, int high) {
        if (low < high)
        {
            int pivot = partition(input, low, high);

            quickSort(input, low, pivot - 1);
            quickSort(input, pivot + 1, high);
        }
//        return input;
    }

    /**
     * Partition given range of input array using a pivot at high
     * @param input input array
     * @param low start of subarray
     * @param high end of subarray
     * @return position of pivot value
     */
    private static int partition (int input[], int low, int high) {
        int pivot = input[high];
        int loPtr = low;

        for (int hiPtr = low; hiPtr < high; hiPtr++)
        {
            // If item at hiPtr is smaller than pivot, move it to the left
            if (input[hiPtr] <= pivot)
            {
                swapInArray(input, loPtr ,hiPtr);
                loPtr++;
            }
        }
        // Put pivot in loPtr position,
        // values smaller than pivot are in positions low to i-1
        // values higher than pivot are in positions i+1 to high
        swapInArray(input, loPtr, high);
        return (loPtr);
    }

    /********************************************************************************************/
    /**                                    COUNTING SORT                                       **/
    /********************************************************************************************/

    /**
     * Space complexity: O(K)
     * Best case performance: O(n+K)
     * Average case performance: O(n+K)
     * Worst case performance: O(n+K)
     * Stable: Yes (K is the number of distinct elements in the array)
     */

    /**
     * Counting Sort is a sorting technique based on keys between a specific range. It works by
     * counting the number of objects having distinct key values (kind of hashing). Then doing
     * some arithmetic to calculate the position of each object in the output sequence.
     * @param inputArray unsorted input
     * @return sorted output (new array)
     */
    public static int[] countingSort(int[] inputArray, int lo, int hi){
        int n = inputArray.length;
        int[] result = new int[n];
        int span = hi-lo+1;
        int[] counts = new int[span];

        for(int val : inputArray){
            counts[val-lo]++;
        }

        for(int ptr = 1; ptr < span; ptr++){
            counts[ptr] += counts[ptr-1];
        }

        for(int ptr2 = 0; ptr2 < n; ptr2++){
            int currVal = inputArray[ptr2];
            int idx = counts[currVal-lo];
            counts[currVal-lo]--;
            result[idx-1] = currVal;
        }

        return result;
    }


    /********************************************************************************************/
    /**                                     UTILITY METHODS                                    **/
    /********************************************************************************************/

    /**
     * Swap i'th and j'th item in input array
     * @param input input array
     * @param i swap position 1
     * @param j swap position 2
     */
    private static void swapInArray(int[] input, int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * Copy range [start, end) from input array to output starting at index idx
     * @param input input array
     * @param start start of copy range
     * @param end end of copy range
     * @param output array to be copied to
     * @param idx start index of output array, copy to and following this index up to idx+(end-start)
     */
    private static void copyArray(int[] input, int start, int end, int[] output, int idx){
        for(; idx < output.length && start < end; idx++){
            output[idx] = input[start++];
        }
    }

    /**
     * Creates a new copy of input array from start to end-1
     * @param input input array
     * @param start start of copy range
     * @param end end of copy range
     * @return copy of input
     */
    private static int[] copyArray(int[] input, int start, int end){
        int[] result = new int[end-start];
        int resPtr = 0;
        for(; start < end; start++){
            result[resPtr++] = input[start];
        }
        return result;
    }

    /**
     * Print array
     * @param input input array
     */
    private static void printArray(int[] input){
        for(int i = 0; i < input.length; i++){
            System.out.print(input[i]);
            if(i == input.length-1)
                System.out.println();
            else
                System.out.print(", ");
        }
    }
}

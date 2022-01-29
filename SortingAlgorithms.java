import java.util.Arrays;

/**
 * Implementations for different Sorting Algorithms (mainly for Arrays)
 * References:-
 * 1. https://www.freecodecamp.org/news/sorting-algorithms-explained-with-examples-in-python-java-and-c/
 * 2. https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/
 * 3. https://en.wikipedia.org/wiki/Merge_sort
 */
public class SortingAlgorithms {

/********************************************************************************************/
/*                                   INSERTION SORT                                         */
/********************************************************************************************/
    /**
     * Space Complexity: O(1)
     * Time Complexity: O(n), O(n*n), O(n*n) for Best, Average, Worst cases respectively.
     * Best Case: array is already sorted
     * Average Case: array is randomly sorted
     * Worst Case: array is reverse sorted.
     * Sorting In Place: Yes
     * Stable: Yes
     * @param inputArray
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
/*                                       MERGE SORT                                         */
/********************************************************************************************/
    /**
     * Space Complexity: O(n)
     * Time Complexity: O(n*log(n)) -> Derived from recurrence relation: T(n) = 2 * T(n / 2) + O(n)
     * Sorting In Place: No in a typical implementation
     * Stable: Yes
     * Parallelizable: Yes (Several parallel variants are discussed in the third edition of Cormen,
     * Leiserson, Rivest, and Stein's Introduction to Algorithms.)
     * @param inputArray
     * @return mergedArray
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

        return mergeHalves(leftHalf, rightHalf);
    }

    private static int[] mergeHalves(int[] leftHalf, int[] rightHalf) {
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
     * Iterative (Bottom up) implementation of merge sort
     * In the bottom up approach, we divide the list into sublists of a single element
     * at the beginning. Each of the sublists is then sorted already. Then from this
     * point on, we merge the sublists two at a time until a single list remains.
     * @param inputArray
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
     * @param inputArray
     * @param left  Pointer to head of left sub-array
     * @param right Pointer to head of right sub-array
     * @param end   Pointer to end of inputArray
     * @param workArray
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
/*                                      UTILITY METHODS                                     */
/********************************************************************************************/

    /**
     * Swap i'th and j'th item in input array
     * @param input
     * @param i
     * @param j
     */
    private static void swap(int[] input, int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * Copies range [start, end) from input array to output starting at index idx
     * @param input
     * @param start
     * @param end
     * @param output
     * @param idx
     */
    private static void copyArray(int[] input, int start, int end, int[] output, int idx){
        for(; idx < output.length && start < end; idx++){
            output[idx] = input[start++];
        }
    }
    /**
     * Creates a new copy of input array from start to end-1
     * @param input
     * @param start
     * @param end
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
     * Print input array
     * @param input
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

public class Main {
    public static void main(String[] args){
        int[] input = new int[]{8,3,5,1,4,2};

//        SortingAlgorithms.insertionSort(arr);

        System.out.println("Top-Down Merge Sort");
        int[] result = SortingAlgorithms.mergeSortTopDown(input);
        printArray(result);

        System.out.println("Bottom-Up Merge Sort");
        SortingAlgorithms.mergeSortBottomUp(input);
        printArray(result);
    }

    private static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            if(i == arr.length-1)
                System.out.println();
            else
                System.out.print(", ");
        }
    }

}

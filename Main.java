public class Main {
    public static void main(String[] args){
        int[] input = new int[]{8,3,5,1,4,2};
        int n = input.length;
        int[] result = new int[n];

//        System.out.println("Insertion Sort");
//        SortingAlgorithms.insertionSort(input);
//        printArray(input);

//        System.out.println("Top-Down Merge Sort");
//        result = SortingAlgorithms.mergeSortTopDown(input);
//        printArray(result);

        System.out.println("Bottom-Up Merge Sort");
        result = SortingAlgorithms.mergeSortBottomUp(input);
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

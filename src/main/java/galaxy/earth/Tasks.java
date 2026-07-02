package galaxy.earth;

import java.util.Arrays;

public class Tasks {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 0, 5, 0, 3, 0, 2};
        int[] arr2 = new int[arr.length];
        int counter = 0;
        Arrays.parallelSort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (!(arr[i] == 0)) {
                arr2[arr2.length-i-1] = arr[i];
            } else {
                arr2[arr2.length-i-1] = 0;
            }
            System.out.println(Arrays.toString(arr2));
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i]==0){
//                counter++;
//
//            }
//        }
        }
    }
}

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class MathOperations {

    MathOperations(){
        fillArrayWithRandNumbers(smallArr);
        fillArrayWithRandNumbers(mediumArr);
        //fillArrayWithRandNumbers(largeArr);
        sortArray(smallArr);
        sortArray(mediumArr);
        //sortArray(largeArr);
        soutArray(smallArr);
        soutArray(mediumArr);
        //soutArray(largeArr);
    }

    private Integer[] smallArr=new Integer[1000];
    private Integer[] mediumArr=new Integer[10000];
    Integer[] largeArr=new Integer[100000];

    private void fillArrayWithRandNumbers(Integer[] arr){
        for(int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*2000-1000);
        }
    }

    private void sortArray(Integer[] arr){
        Arrays.sort(arr);
    }

    private void soutArray(Integer[] arr){
        int j=0;
        for(Integer el: arr){
            if(j>=1000){
                System.out.println("\n");
                j=0;
            }
            System.out.print(el+", ");
            j++;
        }
        System.out.println("\n\n");
    }

}

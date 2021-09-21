import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

class MathOperations {

    private TimeTracer timeTracer = new TimeTracer();

    MathOperations(){
        Integer[] smallArr=new Integer[10000];
        Integer[] mediumArr=new Integer[100000];
        Integer[] largeArr=new Integer[1000000];
        Integer[] giantArr=new Integer[10000000];

        fillArrayWithRandNumbers(smallArr);
        fillArrayWithRandNumbers(mediumArr);
        fillArrayWithRandNumbers(largeArr);
        fillArrayWithRandNumbers(giantArr);

        sortArray(smallArr);
        sortArray(mediumArr);
        sortArray(largeArr);
        sortArray(giantArr);

        //soutArray(smallArr);
        //soutArray(mediumArr);
        //soutArray(largeArr);
        //soutArray(giantArr);
    }

    private void fillArrayWithRandNumbers(Integer[] arr){
        timeTracer.startTrace();
        for(int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*2000-1000);
        }
        timeTracer.stopTrace();
        System.out.println(timeTracer.getTraceResult().getTraceResult());
    }

    private void sortArray(Integer[] arr){
        timeTracer.startTrace();
        Arrays.sort(arr);
        timeTracer.stopTrace();
        System.out.println(timeTracer.getTraceResult().getTraceResult());
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

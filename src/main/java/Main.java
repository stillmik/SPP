
public class Main {

    static int add(int a,int b){
        return a+b;
    }

    public static void main(String[] args) {
        System.out.println(Main.add(5,3));
        System.out.println(Main.add(4,8));
        MathOperations mathOperations = new MathOperations();
        System.out.println("method: "+ Thread.currentThread().getStackTrace()[1].getMethodName()+ "\nclass: "+ Thread.currentThread().getStackTrace()[1].getClassName());
    }
}

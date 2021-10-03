package tree;

import java.util.ArrayList;

public class Node {

    public String time;
    public String address;//~MathOperations.sleep()~MathOperations.<init>()~Main.main()~main
    public String name;//~MathOperations.sleep()
    public ArrayList<Node> children;

    public void printChildren(){
        System.out.println("\n----------------------------------------------");
        System.out.println(" parent: "+ name);
        for(Node el: children){
            System.out.print("\tchild: "+ el.name);
        }
    }
}

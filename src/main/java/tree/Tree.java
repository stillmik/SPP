package tree;

import timeTracer.Type;

import java.util.ArrayList;

public class Tree {

    private Node root = new Node();

    public Tree() {
        root.address = "";
        root.name ="root";
        root.children = new ArrayList<>();
    }

    public void setTime(Node current,Node pathNode){

        String nextStepAddress = getThisStepAddress(pathNode, current.address);
        for (int i = 0; i < current.children.size(); i++) {
            if (nextStepAddress.equals(current.children.get(i).address)) {//вглубь
                setTime(current.children.get(i), pathNode);
                return;
            }
        }
        current.time=pathNode.time-current.time;
    }

    public void appendChild(Node current, Node append) {

        String nextStepAddress = getThisStepAddress(append, current.address);
        for (int i = 0; i < current.children.size(); i++) {
            if (nextStepAddress.equals(current.children.get(i).address)) {//вглубь
                appendChild(current.children.get(i), append);
                return;
            }
        }
        if (append.address.equals(nextStepAddress)) {
            createNode(nextStepAddress, current, append);
        } else {
            appendChild(createNode(nextStepAddress, current, append), append);
        }
    }

    private Type getTypeOfNode(Node node) {
        if (node.name.equals("root")) {
            return Type.ROOT;
        }
        if (node.name.endsWith("()")) {
            return Type.METHOD;
        } else {
            return Type.THREAD;
        }
    }

    private Node createNode(String thisStepAddress, Node current, Node append) {
        if(append.address.equals(thisStepAddress)){
            append.name = getName(thisStepAddress);
            current.children.add(append);
            System.out.println("NEW APPENDNODE:"+ "nodeType: " +getTypeOfNode(append) +" nodeAddress: " + append.address + " currentAddress: " + current.address + " append: " + append.address);
            return append;
        }else {
            Node newNode = new Node();
            newNode.children = new ArrayList<>();
            newNode.address = thisStepAddress;
            newNode.name = getName(thisStepAddress);
            newNode.id = append.id;
            current.children.add(newNode);
            System.out.println("NEW NODE:"+ "nodeType: " +getTypeOfNode(newNode) + " nodeAddress: " + newNode.address + " currentAddress: " + current.address + " append: " + append.address);
            return newNode;
        }
    }

    private String getName(String thisStepAddress) {
        int i = 1;
        while (thisStepAddress.charAt(i) != '~') {
            i++;
            if (i == thisStepAddress.length()) {
                break;
            }
        }
        return thisStepAddress.substring(0, i);
    }

    private String getThisStepAddress(Node append, String prevStepAddress) {
        String nextStepAddress = "";
        String nextStepPiece = append.address;
        nextStepPiece = nextStepPiece.replace(prevStepAddress, "");
        int i = nextStepPiece.length() - 1;
        while (i >= 0) {
            if (nextStepPiece.charAt(i) == '~') {
                nextStepAddress = nextStepPiece.substring(i + 1);
                break;
            }
            i--;
        }
        nextStepAddress = "~".concat(nextStepAddress.concat(prevStepAddress));
        return nextStepAddress;
    }

    public Node getRoot() {
        return root;
    }
}
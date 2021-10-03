package tree;

import java.util.ArrayList;

public class Tree {

    private Node root = new Node();

    public Tree() {
        root.address = "";
        root.children = new ArrayList<>();
    }

    public void appendChild(Node current, Node append) {
        System.out.println("\nBEGIN:"+ " current:"  + current.address + " append: " + append.address);

        String thisStepAddress = getThisStepAddress(append, current.address);

        for (int i = 0; i < current.children.size(); i++) {
            System.out.println("parent: " + current.address + " child: " + current.children.get(i).address);
            if (thisStepAddress.equals(current.children.get(i).address)) {//вглубь
                System.out.println("идем к ребенку: " + current.children.get(i).address);
                appendChild(current.children.get(i), append);
                return;
            }
        }
        if (append.address.equals(thisStepAddress)) {
            System.out.println("создаем ноду: " + thisStepAddress);
            createNode(thisStepAddress, current, append);
        } else {
            System.out.println("создаем путь: " + thisStepAddress);
            appendChild(createNode(thisStepAddress, current, append), append);
        }
    }

    private Node createNode(String thisStepAddress, Node current, Node append) {
        Node node = new Node();
        node.children = new ArrayList<>();
        node.address = thisStepAddress;
        node.name = getName(thisStepAddress);
        current.children.add(node);
        System.out.println("NEW NODE: nodeAddress: " + node.address + " currentAddress: " + current.address + " append: " + append.address);
        return node;
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
        if (append.address.equals(prevStepAddress))
            return append.name.concat(prevStepAddress);
        String thisStepAddress = "";
        String thisStepPiece = append.address;
        thisStepPiece = thisStepPiece.replace(prevStepAddress, "");
        int i = thisStepPiece.length() - 1;
        while (i >= 0) {
            if (thisStepPiece.charAt(i) == '~') {
                thisStepAddress = thisStepPiece.substring(i + 1);
                break;
            }
            i--;
        }
        thisStepAddress = "~".concat(thisStepAddress.concat(prevStepAddress));
        System.out.println("thisStep: " + thisStepAddress);
        return thisStepAddress;
    }

    public Node getRoot() {
        return root;
    }
}
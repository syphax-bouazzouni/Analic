package dep.tree;

import app.function.Function;

public class Node {
    private String value;
    private Node leftSon, rightSon;

    public Node(String value) {
        this.value = value;
        this.leftSon = null;
        this.rightSon = null;
    }

    public Node(String value, Node leftSon, Node rightSon) {
        this.value = value;
        this.leftSon = leftSon;
        this.rightSon = rightSon;
    }

    public static boolean exist(Node n) {
        return (n != null);
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (!value.equals("") && !value.equals(" ")) this.value = value;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }

    public Node getRightSon() {
        return rightSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public boolean isLeaf() {
        return (this.leftSon == null) && (this.rightSon == null);
    }

    @Override
    public String toString() {
        String str = "";
        if (Node.exist(this.leftSon)) {
            if (!this.leftSon.isLeaf()) str += "(" + this.leftSon + ")";
            else str += this.leftSon;
        }
        if (isNumeric(this.value)) {
            if (Integer.parseInt(this.value) < 0)
                str += "(" + this.value + ")";
            else str += this.value;

        } else str += this.value;

        if (Node.exist(this.rightSon)) {
            if (!this.rightSon.isLeaf() || Function.isComposeFunction(this.value) != -1)
                str += "(" + this.rightSon + ")";
            else str += this.rightSon;
        }

        return str;
    }

    public void addLeftLeaf(Node n) {
        if (n != null) {
            if (this.leftSon == null) {
                this.setLeftSon(n);
            } else this.leftSon.setLeftSon(n);
        }

    }

    public void addRightLeaf(Node n) {
        if (n != null) {

            if (this.rightSon == null) {
                this.setRightSon(n);
            } else this.rightSon.setRightSon(n);
        }

    }

    public Node addLeftLeaf(String str) {
        Node n = new Node(str);

        if (this.leftSon == null) {
            this.setLeftSon(n);
        } else this.leftSon.setLeftSon(n);
        return n;
    }

    public Node addRightLeaf(String str) {

        Node n = new Node(str);

        if (this.rightSon == null) {
            this.setRightSon(n);
        } else this.leftSon.setRightSon(n);
        return n;
    }

}

package dep.tree;

public class TestTree {
    public static void main(String[] args) {
        String str = "xsin(x)+1";
        Tree t1, t2, t3, t4, t5;
        Node n;
        t1 = new Tree("*");
        t1.addRightNode("sin(x)");
        t1.addLeftNode("x");

        Tree.print(t1);
        t2 = new Tree("+");
        t2.addLeftTree(t1);
        t2.addRightNode("1");
        Tree.print(t2);
    }
}

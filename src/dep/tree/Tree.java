package dep.tree;

public class Tree {
    private Node root;

    public Tree() {
        this.root = null;
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Tree(String rootValue) {
        this.root = new Node(rootValue);
    }

    public static void print(Tree t) {
        System.out.println(t.toString());
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(String str) {
        if (!str.equals("")) this.root = new Node(str);
    }

    public void setRoot(Node newRoot) {
        this.root = newRoot;
    }

    @Override
    public String toString() {
        return "Tree{ root = " + this.root + " }";

    }

    public void addLeftNode(Node n) {
        this.root.addLeftLeaf(n);
    }

    public void addRightNode(Node n) {
        this.root.addRightLeaf(n);
    }

    public Node addLeftNode(String str) {
        Node n = new Node(str);
        this.root.addLeftLeaf(n);
        return n;
    }

    public Node addRightNode(String str) {
        Node n = new Node(str);
        this.root.addRightLeaf(n);
        return n;
    }

    public void addLeftTree(Tree t) {
        if (t != null) this.addLeftNode(t.getRoot());
    }

    public void addRightTree(Tree t) {
        if (t != null) this.addRightNode(t.getRoot());
    }

}

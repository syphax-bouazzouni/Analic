package app.function;

public class TestFunction {
    public static void main(String[] args) {

        Function f = new Function("(-1)/26");
        System.out.println(f.getTreeFunc());
        System.out.println(f.calc(7));


    }

}

package app.rootFinding;

import app.function.Function;

public class TestRootFinding {


    public static void main(String[] args) {
        Function f = new Function("x^3-(6*(x^2))+(11*x)-6");
        Function df = new Function("1");
        System.out.println("the root of" + f + "\n\t is : " + RootFinding.findRootWithBisection(f, -20, 0, 1000));
        System.out.println("the root of" + f + "\n\t is : " + RootFinding.findRootWithNewtonRaphson(f, df, -20, 0, 1000));
        System.out.println("the root of" + f + "\n\t is : " + RootFinding.findRootWithSecant(f, 3, -2, 100));
    }
}

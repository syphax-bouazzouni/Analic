package app.rootFinding;

import app.function.Function;

/**
 * RootFinding is a class that contain different  methods to find roots of function : <br>
 * - Bisection <br>
 * - Newton Raphson<br>
 * - Secant<br>
 *
 * @author Bouazzouni syphax
 * @see Function
 */
public class RootFinding {
    private final static double EPSILON = 0.000001; // the tolerance
    private final static double ERR_VAL = 0.010101; // a constant that represent an error

    /**
     * A static method that use the Bisection algorithm to find <b>1 root in a range [a,b] </b><br>
     * Conditions of work :<br>
     * - the function have to be monotone  and continuous in the range [a,b]
     *
     * @param f       the function to resolve (of @type Function)
     * @param a       the lower edge
     * @param b       the higher range
     * @param MaxIter the maximum try to find the root (&gt;=100)
     * @return the root (a @type double)
     */
    public static double findRootWithBisection(Function f, double a, double b, int MaxIter) {
        double dx, xMid, fMid, edge;

        if (f.calc(a) * f.calc(b) >= 0) {
            System.out.println("ERROR IN THE INTERVAL");
            return ERR_VAL;
        }

        if (b <= 0) {
            dx = a - b;
            edge = b;
        } else {
            dx = b - a;
            edge = a;
        }
        for (int i = 0; i < MaxIter; i++) {
            dx /= 2;
            xMid = edge + dx;
            fMid = f.calc(xMid);
            System.out.println(i);
            //System.out.println(xMid);
            if ((fMid < 0 && b > 0) || (fMid > 0 && b <= 0)) {
                edge = xMid;
            }
            if (Math.abs(dx) < EPSILON || fMid == 0) return xMid;
        }
        System.out.println("Didn't found the root with" + MaxIter + "iteration");
        return ERR_VAL;
    }

    /**
     * A static method that use the Newton Raphson algorithm to find <b>1 root in a range [a,b] </b><br>
     * Conditions of work :<br>
     * - the function have to be continuous in the range [a,b]<br>
     * - the function can be derived
     *
     * @param f       the function to resolve (of @type Function)
     * @param df      the derived of the function to resolve
     * @param a       the lower edge
     * @param b       the higher edge
     * @param MaxIter the maximum try to find the root (&gt;=100)
     * @return the root (a @type double)
     */
    public static double findRootWithNewtonRaphson(Function f, Function df, double a, double b, int MaxIter) {
        double guess = (a + b) / 2;
        double fVal;
        double dfVal;
        double dx;
        for (int i = 0; i < MaxIter; i++) {
            fVal = f.calc(guess);
            dfVal = df.calc(guess);
            if (dfVal != 0) {
                dx = fVal / dfVal;
                guess -= dx;
            } else return RootFinding.findRootWithBisection(f, guess, b, i);
            if ((a - guess) * (guess - b) < 0) {
                System.out.println("OUT OF RANGE");
                return ERR_VAL;
            }

            if (Math.abs(dx) < EPSILON) return guess;

        }
        System.out.println("Didn't found the root with" + MaxIter + "iteration");
        return ERR_VAL;


    }

    /**
     * A static method that use the Scant algorithm to find <b>1 root </b><br>
     * Conditions of work :<br>
     * - We have to give it <b>2 point like approximation of the root</b>
     *
     * @param f       f the function to resolve (@type Function)
     * @param x0      an approximation of the root
     * @param x1      an approximation of the root
     * @param MaxIter the maximum try to find the root (&gt;=100)
     * @return the root (a @type double)
     */
    public static double findRootWithSecant(Function f, double x0, double x1, int MaxIter) {
        double f0 = f.calc(x0);
        double f1 = f.calc(x1);
        double x2;
        for (int i = 0; i < MaxIter; i++) {
            x2 = x1 - (((x1 - x0) * f1) / (f1 - f0));
            x0 = x1;
            x1 = x2;
            f0 = f1;
            f1 = f.calc(x2);
            if (Math.abs(f1) < EPSILON) return x2;

        }
        System.out.println("Didn't found the root with" + MaxIter + "iteration");
        return ERR_VAL;
    }
}

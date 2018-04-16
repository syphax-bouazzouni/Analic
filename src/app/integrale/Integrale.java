package app.integrale;

import app.function.Function;

/**
 * Integrale class is used to calculate integrales of  functions between [a,b]
 *
 * @author Ibrahim Benadidou
 * @see app.function.Function
 */
public class Integrale extends Function {

    /**
     * Construc an Integrale wich is an extension of Function
     *
     * @param strFunc the representaion of the function to intigrate
     */
    public Integrale(String strFunc) {
        super(strFunc);
    }

    /**
     * Integrate The function from a to b using the trapezoidal rule (a lower then b).
     *
     * @param a the low edje
     * @param b the up edje
     * @param N Increase N for more precision (minimum of N is 1)
     * @return the reuslt of the integration
     */
    public double integrateTrapez(double a, double b, int N) {
        double sum = 0;
        double h = (b - a) / N;              // step size

        sum = 0.5 * (this.calc(a) + this.calc(b));     // the first "bourn" and the last one are multiplied by 1/2
        for (int i = 1; i < N; i++) {
            sum += this.calc(a + h * i);
        }

        return sum * h; //the result will be = h*(f(a)*0.5 + f(a+h) + f(a+2h) +.....+f(b)*0.5)
    }


    /**
     * Integrate The function from a to b using the Simpson rule (a lower then b).
     *
     * @param a the low edje
     * @param b the up edje
     * @param N Increase N for more precision (minimum of N is 1)
     * @return the reuslt of the integration
     */
    public double integrateSimpson(double a, double b, int N) {
        int cpt = 0;
        double sum = 0;
        double h = (b - a) / N;
        /* h is the step size for exemple if h=2
         then the steps are f(a) f(a+2) f(a+4) ... f(a+h=b) untill it reach b ((h doesnt neccescerly = b-a ))
         */
        sum = (this.calc(a) + this.calc(b));     // the first "bourne" and the last one are multiplied by 1/3 // read the rule for more understanding !!
        for (int i = 1; i < N; i++) {
            if (i % 2 == 0) sum += (2 * this.calc(a + h * i));
            else sum += (4 * this.calc(a + h * i));
        }

        //the result will be = h * (f(a)/3 + f(a+h)*4/3 + f(a+h)*2/3 + ......+f(b)/3)
        return sum * (h / 3);
    }
}

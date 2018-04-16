package dep.fraction;

/**
 * This class repsent Fractions Like 1/2 or 15/4 ... <br>
 * And Implement it's diferente operation like addition ,subtraction ... <br>
 * It simplfate fraction automaticly  for example if you write new Fraction(3/6) it will represent (1/2) <br>
 *
 * @author Kader taburi
 */
public class Fraction {

    /**
     * Represent the top of the fraction
     */
    private int a;
    /**
     * Represent the bottom of the fraction
     */
    private int b;

    /**
     * Create a null fraction (0).
     */
    public Fraction() {
        this.a = 0;
        this.b = 1;
    }

    /**
     * Create a fraction with the bottom equals to 1
     *
     * @param a the top of the fraction
     */
    public Fraction(int a) {
        this.a = a;
        this.b = 1;
    }

    /**
     * Create a fraction
     *
     * @param a the top of the fraction
     * @param b the bottom of the fraction
     */
    public Fraction(int a, int b) {
        this.setFraction(a, b);
    }

    /**
     * Do the pgcd bettwen two numbers
     *
     * @param a a number
     * @param b a number
     * @return PGCD(a, b)
     */
    private static int PGCD(int a, int b) {
        int PGCD = 1;

        if (a % b != 0) {
            PGCD = PGCD(b, a % b);
        } else PGCD = b;
        return PGCD;
    }

    /**
     * Do addition between two fractions <br>
     * Example of use :Fraction.add(f1,f2)<br>
     *
     * @param fraction1 the first fraction
     * @param fraction2 the second fraction
     * @return fraction1 + fraction2
     */
    public static Fraction add(Fraction fraction1, Fraction fraction2) {

        Fraction addFraction = new Fraction();
        int a = fraction1.getA(), b = fraction1.getB();
        int c = fraction2.getA(), d = fraction2.getB();

        addFraction.setFraction((a * d) + (b * c), (d * b));

        return addFraction;
    }

    /**
     * Do subtraction between two fractions <br>
     * Example of use :Fraction.sub(f1,f2)<br>
     *
     * @param fraction1 the first fraction
     * @param fraction2 the second fraction
     * @return fraction1 - fraction2
     */
    public static Fraction sub(Fraction fraction1, Fraction fraction2) {

        Fraction subFraction = new Fraction();

        int a = fraction1.getA(), b = fraction1.getB();
        int c = fraction2.getA(), d = fraction2.getB();

        subFraction.setFraction((a * d) - (b * c), (d * b));

        return subFraction;

    }

    /**
     * Do the multiplication of two fractions <br>
     * Example of use :Fraction.mul(f1,f2)<br>
     *
     * @param fraction1 the first fraction
     * @param fraction2 the second fraction
     * @return fraction1 * fraction2
     */
    public static Fraction mul(Fraction fraction1, Fraction fraction2) {
        Fraction mulFraction = new Fraction();

        int a = fraction1.getA(), b = fraction1.getB();
        int c = fraction2.getA(), d = fraction2.getB();

        mulFraction.setFraction((a * c), (d * b));

        return mulFraction;

    }

    /**
     * Do the division of two fractions <br>
     * Example of use :Fraction.div(f1,f2)<br>
     *
     * @param fraction1 the first fraction
     * @param fraction2 the second fraction
     * @return fraction1 / fraction2
     */
    public static Fraction div(Fraction fraction1, Fraction fraction2) {
        Fraction divFraction = new Fraction();

        int a = fraction1.getA(), b = fraction1.getB();
        int c = fraction2.getA(), d = fraction2.getB();

        divFraction.setFraction((a * d), (c * b));

        return divFraction;

    }

    public static Fraction pow(Fraction fraction, int i) {
        return new Fraction((int) Math.pow(fraction.a, i), (int) Math.pow(fraction.b, i));
    }

    /**
     * Says if a fraction is defirent of + infini or - infini
     *
     * @return result
     */
    public boolean isFinite() {
        return b != 0;
    }

    /**
     * Says if a fraction is equal to 0
     *
     * @return the result
     */
    public boolean isNul() {
        return this.a == 0;
    }

    /**
     * Says if a fraction is negative
     *
     * @return the result
     */
    public boolean isNegative() {
        return (this.a < 0 && this.b > 0) || (this.b < 0 && this.a > 0);
    }

    /**
     * Gives the top of the fraction
     *
     * @return the top of the fraction
     */
    public int getA() {
        return a;
    }

    private void setA(int a) {
        this.a = a;
    }

    /**
     * Gives the bottom of the fraction
     *
     * @return the bottom of the fraction
     */
    public int getB() {
        return b;
    }

    private void setB(int b) {
        this.b = b;
    }

    /**
     * Set(change) a fraction
     *
     * @param a the top of the fraaction
     * @param b the bottom of the fraction
     */
    public void setFraction(int a, int b) {
        this.setA(a);
        this.setB(b);
        this.simplifateFraction();
        this.omitSign();
    }

    /**
     * It gives the double result of the fraction
     *
     * @return a double or 0 if it's infinite
     */
    public double getresultFraction() {
        if (this.isFinite()) {
            return a / b;
        } else {
            return 0;
        }
    }

    /**
     * Simplifate a fraction  <br>
     * Example 3/6 gives 1/2
     */
    private void simplifateFraction() {
        if (this.isFinite()) {
            int pgcd = PGCD(this.a, this.b);
            this.a = (this.a / pgcd);
            this.b = (this.b / pgcd);
        }
    }

    /**
     * change the place of the minus signe <br>
     * Example 1/-6 it gives -1/6
     * it makes the bottom always positive
     */
    private void omitSign() {
        if ((isNegative() && this.a > 0) || (!isNegative() && this.a < 0)) {
            setA(-this.a);
            setB(-this.b);
        }
    }

    public void setNul() {
        this.a = 0;
    }

    /**
     * Do the addition between a fraction and a constant
     *
     * @param c the constant to add
     * @return this(fraction) + c
     */
    public Fraction add(int c) {
        if (this.isFinite()) {
            this.setFraction(a + (b * c), b);
        }
        return this;
    }

    /**
     * Do the subtraction between a fraction and a constant
     *
     * @param c the constant to sub
     * @return this(fraction) - c
     */
    public Fraction sub(int c) {

        if (this.isFinite()) {
            this.setFraction(a - (b * c), b);
        }
        return this;

    }

    /**
     * Do the multiplication between a fraction and a constant
     *
     * @param c the constant to mul
     * @return this(fraction) * c
     */
    public Fraction mul(int c) {
        if (this.isFinite()) {
            this.setFraction(a * c, b);
        }
        return this;

    }

    /**
     * Do the division between a fraction and a constant
     *
     * @param c the constant to div
     * @return this(fraction) / c
     */
    public Fraction div(int c) {
        if (this.isFinite()) {
            this.setFraction(a, b * c);
        }
        return this;
    }


    /**
     * Give a string that represent  the fraction <br>
     * Example : (1/2) , 1 ,0 ,+ infinite , - infinite , (-1/2)
     *
     * @return a string
     */
    public String toString() {

        if (this.isFinite()) {
            if (this.a < 0) {
                if (this.b == 1) return "(" + this.a + ")";
                else return "((" + this.a + ")/" + this.b + ")";
            } else {
                if (this.b == 1) return this.a + "";
                else return "(" + this.a + "/" + this.b + ")";
            }
        } else if (this.a > 0) return " + Infinite";
        else if (this.a < 0) return " - Infinite";
        else return "NOT EXIST";

    }

    /**
     * Clone a fraction with the same attribut and diferente reference
     *
     * @return the new fraction
     */
    public Fraction clone() {
        return new Fraction(this.a, this.b);
    }
}


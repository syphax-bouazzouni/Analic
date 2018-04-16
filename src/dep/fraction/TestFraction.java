package dep.fraction;

public class TestFraction {

    public static void main(String[] args) {

        Fraction f1 = new Fraction(6, 9);
        Fraction f2 = new Fraction(144, 81);
        Fraction f3 = new Fraction(12, 0);
        Fraction f4 = new Fraction(-12, 0);
        Fraction f5 = new Fraction(0, 0);
        Fraction f6 = new Fraction(0, 12);

        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);

        System.out.println(Fraction.add(f1, f2));
        System.out.println(Fraction.sub(f1, f2));
        System.out.println(Fraction.mul(f1, f2));
        System.out.println(Fraction.div(f1, f3));
        System.out.println(Fraction.div(f1, f6));
    }


}

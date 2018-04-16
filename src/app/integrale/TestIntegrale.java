package app.integrale;

public class TestIntegrale {
    public static void main(String[] args) {
        Integrale i = new Integrale("x^2");

        System.out.println(i.integrateTrapez(2, 4, 100000));
        System.out.println(i.integrateSimpson(2, 4, 100000));
    }
}

package app.curveFetting;

public class TestCurveFetting {

    public static void main(String[] args) {


        try {
            /* Polynomial fitting */
            //Create a random vector x and y with 4 point
            // And we luck for 2 degree polynomial
            CurveFetting c = new CurveFettingPoly(3, 3);
            System.out.println("Vector X:" + c.getvectorX());
            System.out.println("Vector Y:" + c.getvectorY());
            System.out.println("SigmaX:" + c.getSigmaVectorX());
            System.out.println("SigmaY:" + c.getSigmaVectorY());
            System.out.println("normal Matrix:" + c.getNormalMatrix());
            System.out.println(c.getFettingFunction());
            /* Linear feting */

            CurveFetting b = new CurveFittingLinear(3);
            System.out.println("Vector X:" + b.getvectorX());
            System.out.println("Vector Y:" + b.getvectorY());
            System.out.println("SigmaX:" + b.getSigmaVectorX());
            System.out.println("SigmaY:" + b.getSigmaVectorY());
            System.out.println("normal Matrix:" + b.getNormalMatrix());
            System.out.println(b.getFettingFunction());

            /* Exponoial fetting */
            CurveFettingExpo d = new CurveFettingExpo(3);
            System.out.println(d.getFettingFunction());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}

package app.matrice;

public class TestMatrix {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);
        //Matrix m2 = new Matrix(3,1);
        //System.out.println(Matrix.operation(m1,m2,'-'));
        //m1.det();
        System.out.println("Oringinal :");
        System.out.println(m1);
        System.out.println("det= " + m1.det());
        System.out.println("rank= " + m1.rank());
        System.out.println("Inverse :");
        System.out.println(m1.inverse());
        System.out.println("Echelonner :");
        System.out.println(m1.echelonner());
        System.out.println("Echelonner inferieur :");
        System.out.println(m1.lowerEchelonner());
        System.out.println("Echelonner reduite :");
        System.out.println(m1.echelonnerReduced());


    }
}

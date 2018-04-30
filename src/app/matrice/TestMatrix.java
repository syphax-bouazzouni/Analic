package app.matrice;

public class TestMatrix {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);// Create a null matrix of 3 rows and 3 column
        Matrix m2 = Matrix.random(3,5);//Create a random matrix with user insertions

        try {
            //Operation with one matrix
            System.out.println("Original :");
            System.out.println(m1);
            System.out.println("determinant= " + m1.det());
            System.out.println("rank= " + m1.rank());
            System.out.println("Co Matrix: ");
            System.out.println(m1.coMat());
            System.out.println("Inverse :");
            System.out.println(m1.inverse());
            System.out.println("Echelonner :");
            System.out.println(m1.echelonner());
            System.out.println("Echelonner inferieur :");
            System.out.println(m1.lowerEchelonner());
            System.out.println("Echelonner reduite :");
            System.out.println(m1.echelonnerReduced());
            System.out.println("Transpose :");
            System.out.println(Matrix.transpose(m1));

            //Operation between two matrix
            System.out.println("m1 = \n"+m1);
            System.out.println("m2 = \n"+m2);
            System.out.println("Addition m1+m2 \n"+Matrix.operation(m1,m2,'+'));
            System.out.println("Sustraction m1-m2 \n"+Matrix.operation(m1,m2,'-'));
            System.out.println("Multiplication m1*m2 \n"+Matrix.mul(m1,m2));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

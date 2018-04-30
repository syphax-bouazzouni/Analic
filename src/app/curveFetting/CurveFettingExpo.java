package app.curveFetting;

import app.function.Function;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;

public class CurveFettingExpo extends CurveFittingLinear {

    public CurveFettingExpo(int nbPoint) {
        super(nbPoint);
    }

    @Override
    public Function getFettingFunction() throws NotSquareMatrixException, MatrixNullException, MatrixMultiplicationException, NotInversibleMatrixException {

        Function function = super.getFettingFunction(); // function = B+(A*X)
        System.out.println("Function :" + function);
        if (!function.getStrFunc().isEmpty()) {

            Function B = new Function(function.getTreeFunc().getRoot().getLeftSon().toString()); // get B
            Function A = new Function(function.getTreeFunc().getRoot().getRightSon().getLeftSon().toString()); // get A
            return new Function("e((" + B.getStrFunc() + "))*e((" + A.getStrFunc() + ")*x)");
        } else return new Function("");
    }

}

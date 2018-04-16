package app.curveFetting;

import app.function.Function;
import app.matrice.Matrix;
import dep.fraction.Fraction;

public class CurveFettingPoly extends CurveFetting {

    /**
     * the Degree of the fitted polynomial
     */
    private int deg;

    /**
     * contain [sum(Xi^0),sum(Xi^1),..,sum(Xi^(2*deg))]
     */
    private Matrix sigmaVectorX;
    /**
     * contain [sum(Yi*(Xi^0)),sum(Yi*(Xi^1)),..,sum(Yi*(Xi^(deg))]
     */
    private Matrix sigmaVectorY;
    /**
     * Contain Aij = sigmaVector(i+j) with (0<=i<=deg) and (0<=j<=deg)
     */
    private Matrix normalMatrix;

    /**
     * Construct sigmaVectorX ,sigmaVectorY and the normalMatrix <br>
     * With asking the user for the number of point
     *
     * @param deg the degree of the fitted polynomial
     */
    public CurveFettingPoly(int deg) {
        super();
        this.deg = deg;

        this.constructSigmaVectorX();
        this.constructSigmaVectorY();
        this.constructNormalMatrix();
    }

    /**
     * Construct sigmaVectorX ,sigmaVectorY and the normalMatrix <br>
     *
     * @param nbPoint the number of point to use to creat the fitted polynomial
     * @param deg     the degree of the fitted polynomial
     */
    public CurveFettingPoly(int nbPoint, int deg) {
        super(nbPoint);
        this.deg = deg;
        this.constructSigmaVectorX();
        this.constructSigmaVectorY();
        this.constructNormalMatrix();
    }

    /**
     * create sigmaVectorX = [sum(Xi^0),sum(Xi^1),..,sum(Xi^(2*deg))]
     */
    private void constructSigmaVectorX() {
        this.sigmaVectorX = new Matrix(1, (2 * this.deg));
        Fraction sum = new Fraction(0);

        for (int i = 0; i < sigmaVectorX.getColNb(); i++) {
            for (int j = 0; j < vectorX.getColNb(); j++) {
                sum = Fraction.add(sum, Fraction.pow(vectorX.getElem(0, j), i));
                // sum = sum + (Xj ^ i);
            }
            sigmaVectorX.setElem(0, i, sum.clone()); // sigmaVectorXi = sum(Xj ^ i)
            sum.setNul();// sum = 0;
        }
    }

    /**
     * create sigmaVectorY = [sum(Yi*(Xi^0)),sum(Yi*(Xi^1)),..,sum(Yi*(Xi^(deg))]
     */
    private void constructSigmaVectorY() {

        this.sigmaVectorY = new Matrix(1, this.deg);

        Fraction sum = new Fraction(0);
        Fraction a;

        for (int i = 0; i < sigmaVectorY.getColNb(); i++) {
            for (int j = 0; j < vectorY.getColNb(); j++) {

                a = Fraction.pow(vectorX.getElem(0, j), i); // a = (Xj ^ i)
                sum = Fraction.add(sum, Fraction.mul(vectorY.getElem(0, j), a));
                //sum = sum + (Yj*a)
            }
            sigmaVectorY.setElem(0, i, sum.clone()); // sigmaVctorYi = sum(Yj*(Xj^i))
            sum.setNul();// sum = 0;
        }
    }

    /**
     * Contain Aij = sigmaVector(i+j) with (0<=i<=deg) and (0<=j<=deg)
     */
    private void constructNormalMatrix() {
        this.normalMatrix = new Matrix(this.deg, this.deg);
        for (int i = 0; i < normalMatrix.getColNb(); i++) {
            for (int j = 0; j < normalMatrix.getColNb(); j++) {
                normalMatrix.setElem(i, j, sigmaVectorX.getElem(0, i + j));// Aij = sigmaVectorX(i+j)
            }
        }
    }


    public Matrix getSigmaVectorX() {
        return sigmaVectorX;
    }

    public Matrix getSigmaVectorY() {
        return sigmaVectorY;
    }

    public Matrix getNormalMatrix() {
        return normalMatrix;
    }


    /**
     * Gives the the fitted polynomial
     *
     * @return a function
     */
    @Override
    public Function getFettingFunction() {
        Matrix resultCof = Matrix.mul(this.normalMatrix.inverse(), Matrix.transpose(this.sigmaVectorY));
        // reslutCof is the result ceoficient
        // reslutCof = INVERSE(normalmatrix) * sigmaVectorY
        String strFunc = "";
        resultCof = Matrix.transpose(resultCof);

        if (resultCof == null) {
            System.out.println("Impossible to get fitted polynomial");
        } else {
            if (!resultCof.getElem(0, 0).isNul())
                strFunc += resultCof.getElem(0, 0);
            for (int i = 1; i < this.deg; i++)
                if (!resultCof.getElem(0, 0).isNul())
                    strFunc += "+" + "(" + resultCof.getElem(0, i) + "*(x^" + i + "))";

            strFunc = "(" + strFunc + ")";
        }
        // the fitted polynomial is y = sum(resultCof(i) *(x^i))
        return new Function(strFunc);
    }
}

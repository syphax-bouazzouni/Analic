package app.curveFetting;

import app.function.Function;
import app.matrice.Matrix;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;

/**
 * Not commented yet
 */
public abstract class CurveFetting {
    protected int nbPoint;
    protected Matrix vectorX, vectorY;

    /**
     * contain [sum(Xi^0),sum(Xi^1),..,sum(Xi^(2*deg))]
     */
    protected Matrix sigmaVectorX;
    /**
     * contain [sum(Yi*(Xi^0)),sum(Yi*(Xi^1)),..,sum(Yi*(Xi^(deg))]
     */
    protected Matrix sigmaVectorY;
    /**
     * Contain Aij = sigmaVector(i+j) with (0 &lt;= i &lt;= deg) and (0 &lt;= j &lt;= deg)
     */
    protected Matrix normalMatrix;



    public CurveFetting(Matrix matrix1 ,Matrix matrix2) {
        this.vectorX = matrix1;
        this.vectorY = matrix2;
        this.nbPoint = vectorX.getColNb();
    }

    public CurveFetting(int nbPoint) {
        this.vectorY = new Matrix(1, nbPoint);
        this.vectorX = new Matrix(1, nbPoint);
        this.nbPoint = nbPoint;
    }

    public Matrix getvectorX() {
        return vectorX;
    }
    public Matrix getvectorY() {
        return vectorY;
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

    public void setVectorX(Matrix vectorX) {
        this.vectorX = vectorX;
    }
    public void setVectorY(Matrix vectorY) {
        this.vectorY = vectorY;
    }


    public abstract Function getFettingFunction() throws NotSquareMatrixException, NotInversibleMatrixException, MatrixNullException, MatrixMultiplicationException;
}



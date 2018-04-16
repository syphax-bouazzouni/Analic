package app.curveFetting;

import app.function.Function;
import app.matrice.Matrix;

/**
 * Not commented yet
 */
abstract class CurveFetting {
    protected int nbPoint;
    protected Matrix vectorX, vectorY;


    public CurveFetting() {
        this.vectorX = new Matrix();
        this.vectorY = new Matrix();
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

    public abstract Function getFettingFunction();
}



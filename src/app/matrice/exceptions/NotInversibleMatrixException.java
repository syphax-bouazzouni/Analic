package app.matrice.exceptions;

public class NotInversibleMatrixException extends Exception {


    public NotInversibleMatrixException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Impossible to inverse (det=0)";
    }
}

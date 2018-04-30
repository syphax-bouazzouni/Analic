package app.matrice.exceptions;

public class NotSquareMatrixException extends Exception{

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NotSquareMatrixException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Matrix isn't a square ";
    }
}

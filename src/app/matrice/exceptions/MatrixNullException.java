package app.matrice.exceptions;

public class MatrixNullException extends Exception {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public MatrixNullException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Matrix is null";
    }
}

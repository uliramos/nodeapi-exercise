package com.tradeshift.exercise.nodeapi.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotSetParentOfRootException extends RuntimeException {
    /**
     * Default @{@link CannotSetParentOfRootException} constructor
     */
    public CannotSetParentOfRootException() {
        super();
    }

    /**
     * Constructs a new @{@link CannotSetParentOfRootException} with the specified detail message.
     */
    public CannotSetParentOfRootException(String message) {
        super(message);
    }

    /**
     * Constructs a new @{@link CannotSetParentOfRootException} with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     */
    public CannotSetParentOfRootException(String message, Throwable cause) {
        super(message, cause);
    }
}

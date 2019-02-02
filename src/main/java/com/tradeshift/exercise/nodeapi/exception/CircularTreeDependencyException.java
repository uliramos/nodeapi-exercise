package com.tradeshift.exercise.nodeapi.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CircularTreeDependencyException extends RuntimeException {

    /**
     * Default @{@link CircularTreeDependencyException} constructor
     */
    public CircularTreeDependencyException() {
        super();
    }

    /**
     * Constructs a new @{@link CircularTreeDependencyException} with the specified detail message.
     */
    public CircularTreeDependencyException(String message) {
        super(message);
    }

    /**
     * Constructs a new @{@link CircularTreeDependencyException} with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     */
    public CircularTreeDependencyException(String message, Throwable cause) {
        super(message, cause);
    }
}

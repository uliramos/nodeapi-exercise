package com.tradeshift.exercise.nodeapi;

public class TradeShiftNodeNotFoundException extends RuntimeException {

    /**
     * Default TradeShiftNodeNotFoundException constructor
     */
    public TradeShiftNodeNotFoundException() {
        super();
    }

    /**
     * Constructs a new @TradeShiftNodeNotFoundException exception with the specified detail message.
     */
    public TradeShiftNodeNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new @TradeShiftNodeNotFoundException exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     */
    public TradeShiftNodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

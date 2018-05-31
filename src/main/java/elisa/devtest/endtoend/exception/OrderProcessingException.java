package elisa.devtest.endtoend.exception;

import elisa.devtest.endtoend.util.Messages;

/**
 * A class to represent order processing exceptions
 * @author Solange U. Gasengayire
 */
public class OrderProcessingException extends RuntimeException {

    /**
     * Constructor
     * @param e wrapped exception
     */
    public OrderProcessingException(Exception e) {
        super(Messages.ORDER_PROCESSING_EXCEPTION, e);
    }

    /**
     * Constructor
     * @param message exception message
     * @param e wrapped exception
     */
    public OrderProcessingException(String message, Exception e) {
        super(message, e);
    }
}

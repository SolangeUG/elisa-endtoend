package elisa.devtest.endtoend.exception;

/**
 * A class to represent order processing exceptions
 * @author Solange U. Gasengayire
 */
public class OrderProcessingException extends RuntimeException {

    public OrderProcessingException(Exception e) {
        super("Error processing order", e);
    }
}

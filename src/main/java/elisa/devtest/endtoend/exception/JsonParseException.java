package elisa.devtest.endtoend.exception;

import elisa.devtest.endtoend.util.Messages;

/**
 * A class to represent JSON parsing exceptions
 */
public class JsonParseException extends RuntimeException {
    /**
     * Constructor
     * @param e json parser exception
     */
    public JsonParseException(Exception e) {
        super(Messages.JSON_PARSING_EXCEPTION, e);
    }
}

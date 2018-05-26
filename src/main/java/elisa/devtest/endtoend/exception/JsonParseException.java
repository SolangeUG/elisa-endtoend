package elisa.devtest.endtoend.exception;

public class JsonParseException extends RuntimeException {
    public JsonParseException(Exception e) {
        super("Error parsing json", e);
    }
}

public class InvalidFrameException extends IllegalStateException {
    public InvalidFrameException() {
    }

    public InvalidFrameException(String message) {
        super(message);
    }

    public InvalidFrameException(String message, Throwable cause) {
        super(message, cause);
    }
}

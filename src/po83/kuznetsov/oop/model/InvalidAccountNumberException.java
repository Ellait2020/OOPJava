package po83.kuznetsov.oop.model;

public class InvalidAccountNumberException extends RuntimeException {
    public InvalidAccountNumberException() {
    }

    public InvalidAccountNumberException(String message) {
        super(message);
    }
}

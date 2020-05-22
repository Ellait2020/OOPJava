package po83.kuznetsov.oop.model;

public class DuplicateAccountNumberException extends Exception {
    public DuplicateAccountNumberException() {
    }

    public DuplicateAccountNumberException(String message) {
        super(message);
    }

    public DuplicateAccountNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
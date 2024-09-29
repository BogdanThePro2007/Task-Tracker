package exception;

public class FileWriteException extends RuntimeException {

    public FileWriteException(final String message) {
        super(message);
    }
}

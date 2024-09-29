package exception;

public class FileReadException extends RuntimeException{

    public FileReadException(final String message) {
        super(message);
    }
}

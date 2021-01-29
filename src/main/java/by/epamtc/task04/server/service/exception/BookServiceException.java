package by.epamtc.task04.server.service.exception;

public class BookServiceException extends Exception {
    public BookServiceException() {
    }

    public BookServiceException(String message) {
        super(message);
    }

    public BookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookServiceException(Throwable cause) {
        super(cause);
    }

    public BookServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package by.epamtc.task04.server.service.exception;

public class BookDAOException extends Exception {
    public BookDAOException() {
    }

    public BookDAOException(String message) {
        super(message);
    }

    public BookDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDAOException(Throwable cause) {
        super(cause);
    }

    public BookDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

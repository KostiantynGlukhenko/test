package chess;

/**
 * Created by Administrator on 01.02.2015.
 */
public class InvalidGameStatusException extends RuntimeException {
    public InvalidGameStatusException() {
    }

    public InvalidGameStatusException(String message) {
        super(message);
    }

    public InvalidGameStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGameStatusException(Throwable cause) {
        super(cause);
    }

    public InvalidGameStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package chess;

/**
 * Created by Administrator on 01.02.2015.
 */
public class PositionValidationException extends RuntimeException {
    public PositionValidationException() {
    }

    public PositionValidationException(String message) {
        super(message);
    }

    public PositionValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PositionValidationException(Throwable cause) {
        super(cause);
    }

    public PositionValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

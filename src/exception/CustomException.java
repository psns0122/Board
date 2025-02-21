package exception;

import enumer.ErrorCode;
import lombok.Getter;

public class CustomException extends RuntimeException {
    @Getter
    ErrorCode error;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.error = errorCode;
    }

    public ErrorCode getErrorCode() {
        return error;
    }
}
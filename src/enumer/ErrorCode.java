package enumer;

import lombok.Getter;

public enum ErrorCode {
    INVALID_INPUT("잘못된 입력입니다."),
    OUT_OF_RANGE("인덱스가 범위를 벗어났습니다."),
    UNKNOWN_ERROR("알 수 없는 에러가 발생했습니다.");

    @Getter
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
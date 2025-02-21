package enumer;

import lombok.Getter;

public enum SelectType {
    CREAT(1), READ(2), CLEAR(3), EXIT(4), ERROR(9),
    OK(1), CANCEL(2), UPDATE(1), DELETE(2), LIST(3);

    @Getter
    private final int num;

    SelectType(int num) {
        this.num = num;
    }
}

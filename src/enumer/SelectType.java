package enumer;

public enum SelectType {
    CREAT(1), READ(2), CLEAR(3), EXIT(4), ERROR(9),
    OK(1), CANCEL(2), UPDATE(1), DELETE(2), LIST(3);

    private final int num;

    SelectType(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }
}

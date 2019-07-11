package kz.kakimzhanova.parser.exception;

public class EnumConstantNotPresent extends Exception {
    public EnumConstantNotPresent() {
    }

    public EnumConstantNotPresent(String s) {
        super(s);
    }

    public EnumConstantNotPresent(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EnumConstantNotPresent(Throwable throwable) {
        super(throwable);
    }
}

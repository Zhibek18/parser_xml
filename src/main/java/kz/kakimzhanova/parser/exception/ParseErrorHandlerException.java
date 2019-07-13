package kz.kakimzhanova.parser.exception;

public class ParseErrorHandlerException extends Exception{
    public ParseErrorHandlerException() {
    }

    public ParseErrorHandlerException(String s) {
        super(s);
    }

    public ParseErrorHandlerException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ParseErrorHandlerException(Throwable throwable) {
        super(throwable);
    }
}

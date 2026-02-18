package Exceptions;

public class CaminhoNotFoundException extends RuntimeException {
    public CaminhoNotFoundException(String message) {
        super(message);
    }
}

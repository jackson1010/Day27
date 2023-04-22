package TFIP.Day26v2.Exception;

public class GameNotFoundException extends Exception {
    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}

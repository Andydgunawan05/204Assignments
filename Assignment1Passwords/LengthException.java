// Custom exception thrown when the password is not 6 characters long
public class LengthException extends Exception {
    public LengthException(String message) {
        super(message);
    }
}


//custom exception thrown when password is weak
public class WeakPasswordException extends Exception {
	public WeakPasswordException(String message) {
		super(message);
    }
}
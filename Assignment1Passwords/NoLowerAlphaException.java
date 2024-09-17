// Custom exception thrown when password has no lower case letter
public class NoLowerAlphaException extends Exception {
	public NoLowerAlphaException(String message) {
		super(message);
	}
}
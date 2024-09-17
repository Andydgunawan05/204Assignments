//Custom Exception thrown when password has no upper case letter
public class NoUpperAlphaException extends Exception {
	public NoUpperAlphaException(String message) {
		super(message);
	}
}

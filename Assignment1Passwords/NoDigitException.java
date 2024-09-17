// Custom exception thrown when there is no number in the password
public class NoDigitException extends Exception{
	public NoDigitException(String message) {
		super(message);
	}
}
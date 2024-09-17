//Custom exception thrown when 2 characters are in a row
public class InvalidSequenceException extends Exception {
	public InvalidSequenceException(String message) {
		super(message);
	}
}
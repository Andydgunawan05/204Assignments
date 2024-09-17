//Custom exception thrown when two passwords don't match
public class UnmatchedException extends Exception {
	public UnmatchedException() {
		super("Passwords do not match");
	}
}
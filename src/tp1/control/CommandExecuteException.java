package tp1.control;

public abstract class CommandExecuteException extends Throwable{
	private String error;
	public CommandExecuteException(String string) {
		this.error = string;
	}
	public String getMessage() {
		return error;
	}

}

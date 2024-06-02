package tp1.control;

public class CommandParseException extends Throwable{
	private String error;
	public CommandParseException(String unknownCommand) {
		this.error = unknownCommand;
	}

}

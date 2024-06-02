package tp1.control.commands;

import tp1.control.CommandExecuteException;
import tp1.control.CommandParseException;
import tp1.control.ExecutionResult;
import tp1.control.GameModel;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {
	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game)  throws CommandExecuteException{
		return game.move(move);
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].equals(this.getShortcut()) || commandWords[0].equals(this.getName())) {	
			if(commandWords.length == 2) {
				if(commandWords[1].equals("l") || commandWords[1].equals("left"))
					this.move = Move.LEFT;
				else if(commandWords[1].equals("lleft"))
					this.move = Move.LLEFT;
				else if(commandWords[1].equals("r") ||commandWords[1].equals("right"))
					this.move = Move.RIGHT;
				else if(commandWords[1].equals("rright"))
					this.move = Move.RRIGHT;
				else if(commandWords[1].equals("n") || commandWords[1].equals("none"))
					this.move = Move.NONE;
				else if(commandWords[1].equals("up") || commandWords[1].equals("UP"))
					this.move = Move.UP;
				else if(commandWords[1].equals("down") || commandWords[1].equals("DOWN"))
					this.move = Move.DOWN;
				else 
					throw new IllegalArgumentException(Messages.UNKNOWN_COMMAND);
				return new MoveCommand(this.move);
			}
			else
				throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
			
		return new NoneCommand();
	}
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name);
	}
}

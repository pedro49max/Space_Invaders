package tp1.control.commands;

import tp1.control.CommandExecuteException;
import tp1.control.ExecutionResult;
import tp1.control.GameModel;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand{
	@Override
	public boolean execute(GameModel game)  throws CommandExecuteException{
		game.shootLaser(true);
		return 	true;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_SUPERLASER_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SUPERLASER_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SUPERLASER_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SUPERLASER_HELP;
	}
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name);
	}

}

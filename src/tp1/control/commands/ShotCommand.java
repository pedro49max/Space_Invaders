package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.Game;
import tp1.view.Messages;

public class ShotCommand  extends NoParamsCommand {

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOOT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOOT_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SHOOT_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SHOOT_HELP;
	}

	@Override
	public ExecutionResult execute(Game game) {// throws CommandExecuteException {
		game.shot(false);
		return new ExecutionResult(true);
	}
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name);
	}

}

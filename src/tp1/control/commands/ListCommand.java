package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.GameModel;
import tp1.view.Messages;

public class ListCommand extends NoParamsCommand {
	@Override
	protected String getName() {
		return Messages.COMMAND_LIST_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_LIST_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_LIST_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_LIST_HELP;
	}

	@Override
	public boolean execute(GameModel game) {// throws CommandExecuteException{
		//System.out.println(Messages.HELP_AVAILABLE_COMMANDS);
		System.out.println(CommandGenerator.commandList());
		return false;
	}
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name);
	}
}

package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import tp1.control.CommandExecuteException;
import tp1.control.CommandParseException;
import tp1.control.ExecutionResult;
import tp1.control.GameModel;
import tp1.control.InitialConfiguration;
import tp1.logic.Game;
import tp1.view.Messages;

public class Reset extends Command{
	InitialConfiguration iniConf;
	List<String> Conf;
	public Reset() {}

	protected Reset(InitialConfiguration iniConf) {
		this.iniConf = iniConf;
	}
	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameModel game)  throws CommandExecuteException {		
		return game.resetConfiguration(iniConf);
	}
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name);
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equals(this.getShortcut()) || commandWords[0].equals(this.getName())) {
			if(commandWords.length == 2) {
				try {
					this.iniConf = InitialConfiguration.readFromFile(commandWords[1]);
				} catch (IOException e) {
					throw new CommandParseException(Messages.FILE_NOT_FOUND);
				}
				return new Reset(iniConf);
			}
			else if(commandWords.length == 1)
				return new Reset(InitialConfiguration.NONE);
			else
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		else	
			return new Reset(InitialConfiguration.NONE);
	}
}

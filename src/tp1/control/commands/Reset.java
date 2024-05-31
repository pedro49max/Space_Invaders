package tp1.control.commands;

import java.util.List;

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
	public ExecutionResult execute(GameModel game) {// throws CommandExecuteException {
		game.resetConfiguration(iniConf);
		return new ExecutionResult(true);
	}
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name);
	}
	
	public Command parse(String[] commandWords) {// throws CommandParseException {
		if(commandWords[0].equals(this.getShortcut()) || commandWords[0].equals(this.getName())) {
			if(commandWords.length == 2) {
				if(commandWords[1].equals("CONF_1") ||commandWords[1].equals("conf_1"))
					this.iniConf = InitialConfiguration.CONF_1;
				else if(commandWords[1].equals("CONF_2") ||commandWords[1].equals("conf_2"))
					this.iniConf = InitialConfiguration.CONF_2;
				else if(commandWords[1].equals("CONF_3") ||commandWords[1].equals("conf_3"))
					this.iniConf = InitialConfiguration.CONF_3;
				else //if (commandWords[1].equals("NONE") ||commandWords[1].equals("none"))
					iniConf = InitialConfiguration.NONE;
				/*else
					throw new CommandParseException(Messages.UNKNOWN_COMMAND);*/
				//return new Reset(InitialConfiguration.readInitialConfiguration(commandWords[1] + ".txt"));
				return new Reset(iniConf);
			}
			else if(commandWords.length == 1)
				return new Reset(InitialConfiguration.NONE);
			/*else
				throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);*/
		}
		else	
			return new Reset(InitialConfiguration.NONE);
		return null;//Shoudn't end here
	}
}

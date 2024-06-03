package tp1.control.commands;

import tp1.control.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {
	ExitCommand exit;
	HelpCommand help;
	ListCommand list;
	NoneCommand none;
	ShockWaveCommand ShockWave;
	ShotCommand shot;
	SuperLaserCommand sl;
	Command command;
	
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		exit = new ExitCommand();
		help = new HelpCommand();
		list =  new ListCommand();
		none = new NoneCommand();
		ShockWave = new ShockWaveCommand();
		shot = new ShotCommand();
		sl = new SuperLaserCommand();
		if (matchCommandName(commandWords[0])) {
	 		if(commandWords.length == 1) {
	 			if(commandWords[0].equals(exit.getShortcut()) || commandWords[0].equals(exit.getName())) 
	 				return new ExitCommand();
	 			else if(commandWords[0].equals(help.getShortcut()) || commandWords[0].equals(help.getName()))
	 				return new HelpCommand();
	 			else if(commandWords[0].equals(list.getShortcut()) || commandWords[0].equals(list.getName()))
	 				return new ListCommand();
	 			else if(commandWords[0].equals(ShockWave.getShortcut()) || commandWords[0].equals(ShockWave.getName()))
	 				return new ShockWaveCommand();
	 			else if(commandWords[0].equals(shot.getShortcut()) || commandWords[0].equals(shot.getName()))
	 				return new ShotCommand();
	 			else if(commandWords[0].equals(sl.getShortcut()) || commandWords[0].equals(sl.getName()))
	 				return new SuperLaserCommand();
	 			else if(commandWords[0].equals(none.getShortcut()) || commandWords[0].equals(none.getName()) || commandWords[0].equals(""))
	 				return new NoneCommand();
	 			else
	 				throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	 		}	 			
	 		else
	 			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 	}
	 	return null;
	}
	
}

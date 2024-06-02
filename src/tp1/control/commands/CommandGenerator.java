package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.control.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new SuperLaserCommand(),
		new Reset(),
		new ExitCommand(),
		new ListCommand(),
		new ShockWaveCommand(),
		new ShotCommand(),
		new NoneCommand(),
		new MoveCommand(),
		new HelpCommand()
		//TODO fill with your code
	);

	public static Command parse(String[] commandWords) throws CommandParseException{		
		Command command = null;
		for (Command c: availableCommands) {
			if(c.matchCommandName(commandWords[0]))
				command = c.parse(commandWords);
			else if(commandWords[0].length() == 0)
				command = new NoneCommand();				
		}
		if(command == null) {
			throw new CommandParseException(Messages.UNKNOWN_COMMAND);
		}
		return command;
	}
		
	public static String commandHelp() {//I did't know how to do it better, and it works
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {			
			commands.insert(0, Messages.LINE_SEPARATOR);
			commands.insert(0, c.getHelp());
			commands.insert(0, " : ");
			commands.insert(0, c.getDetails());
			 //String.join(System.lineSeparator(), c.getHelp());
			//TODO fill with your code
		}
		return commands.toString();
	}
	public static String commandList() {
		StringBuilder commands = new StringBuilder();	
		commands.insert(0,"[U]CM Ship: damage='1', endurance='3'");
		commands.insert(0, Messages.LINE_SEPARATOR);
		commands.insert(0,"[R]egular Alien: points='5', damage='0', endurance='2'");
		commands.insert(0, Messages.LINE_SEPARATOR);
		commands.insert(0,"[D]estroyer Alien: points='10', damage='1', endurance='1'");
		commands.insert(0, Messages.LINE_SEPARATOR);
		commands.insert(0,"U[f]o: points='25', damage='0', endurance='1'");
		commands.insert(0, Messages.LINE_SEPARATOR);
		commands.insert(0,"[E]xplosive Alien: points='12', damage='0', endurance='2'");
		commands.insert(0, Messages.LINE_SEPARATOR);
			//TODO fill with your cod
		return commands.toString();
	}

}

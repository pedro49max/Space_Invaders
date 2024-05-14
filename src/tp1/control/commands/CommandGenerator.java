package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new ExitCommand(),
		new ListCommand(),
		new Reset(),
		new NoneCommand(),
		new ShockWaveCommand(),
		new ShotCommand(),
		new SuperLaserCommand()
		//TODO fill with your code
	);

	public static Command parse(String[] commandWords) throws CommandParseException {		
		Command command = null;
		for (Command c: availableCommands) {
			if(c.matchCommandName(commandWords[0]))
				command = c.parse(commandWords);
			else if(commandWords[0].length() == 0)
				command = new NoneCommand();				
		}
		return command;
	}
		
	public static String commandHelp() {//I did't know how to do it better, and it works
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {			
			commands.insert(0, Messages.LINE_SEPARATOR);
			commands.insert(0, c.getHelp());
			commands.insert(0, ": ");
			commands.insert(0, c.getDetails());
			 //String.join(System.lineSeparator(), c.getHelp());
			//TODO fill with your code
		}
		return commands.toString();
	}
	public static String commandList() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			commands.insert(0, c.getDetails());
			commands.insert(0, Messages.LINE_SEPARATOR);
			//TODO fill with your code
		}
		return commands.toString();
	}

}

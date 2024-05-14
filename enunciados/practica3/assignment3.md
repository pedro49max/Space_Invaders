# Assignment 3: Introducing exceptions and input-output in the Space Invaders application

**Submission: December 21st at 09:00 hrs**

**Objective:** Exception-handling and file-handling

**Frequently Asked Questions**: As you may have questions (this is normal) we will compile them in this [frequently asked questions document](../faq.md). To know the latest changes that have been introduced [the document's history is available here](https://github.com/informaticaucm-TPI/2324-SpaceInvaders/commits/main/enunciados/faq.md).

# Introduction

In this assignment, we extend the functionality of the game of the previous
assignment in two ways:

- *Exception handling*: errors that may occur during the execution of the application can be more effectively dealt with using the exception mechanism of the Java language. As well as making the program more robust, this mechanism enables the user to be informed about the occurrence of an error in whatever level of detail is considered appropriate, while at the same time providing a great deal of flexibility concerning where the error is handled (and the error message printed). In addition, the program improves interoperability with the user.

- *File handling*: a useful addition to the application would be the facility to save and load the initial settings of the game. In this way, the initial configurations will not be part of the program code and will not be limited to a few fixed configurations.
  
# Exception handling

In this section, we present the exceptions that should be handled by the application and give some information about their implementation, as well as providing a sample execution.

Exception handling in a language like Java is very useful to control certain situations that occur during the execution of the game: for example, to show relevant information to the user about what happened during "parsing" or the execution of a command.

You will have observed in the previous assignment, that each command invoked its corresponding method of the `GameModel` interface to perform operations on the game.
So, if you wanted to fire a laser, you had to make sure that there was no laser already on the board. If it could not be fired, the execution of the command returned an `ExecutionResult` *record with the error message to be displayed. In addition, internally `ExecutionResult` stored a `boolean` to represent that an error had occurred when executing the command so that the controller would know that the command had failed. However, in some situations, we had to print meaningless messages.

In the present assignment, we will apply the exception mechanism to perform this task, and certain methods will be able to throw and process exceptions to handle certain situations during the game. In some cases, this handling will consist only of providing a message to the user, while in other cases the handling may be more complex. In this section, we will not deal with file-related exceptions, which will be explained in the next section.

First, we are going to deal with two types of exceptions: `CommandParseException` and `CommandExecuteException`. The first one is used to handle errors that occur when `parsing` a command, i.e. errors that occur during the execution of the `parse()` method, such as unknown command, wrong number of parameters or invalid parameter type. The second is used to deal with error situations that can occur when executing the `execute()` method of a command: for example, not having enough points to launch the super laser or trying to move the ship off the board.

In addition, we will deal with exceptions thrown by the system, i.e. those not created or thrown by us. In the game, this happens with the `NumberFormatException`, which is thrown when an error occurs when converting a `String` to an `int`. It can also occur with the `IllegalArgumentException`.
`IllegalArgumentException`, thrown by the static `valueOf` method of enumerated types to convert a `string` to its corresponding enum. Later, we will also deal with those related to file handling.

In this section, we will not deal with exceptions arising in the file-handling; these will be discussed in the file-handling section of this document.

### General aspects of the implementation

Now that the exception mechanism has taken over the task of passing error messages between different parts of the program, in particular, we no longer need to use `ExecutionResult` objects to pass such messages from the commands to the controller. We will remove `ExecutionResult` and use a `boolean` to indicate if the game needs to be printed but also consider the possibility that an error has occurred so that, by controlling the flow of exceptions that may occur during parse or command execution, the user can be informed of the error. In this way, the error messages displayed to the user can be much more descriptive than in the previous assignment.

The main aspects of the implementation are as follows:

- The `CommandParseException` and `CommandExecuteException` exceptions must be defined, exceptions of these types must be thrown and handled appropriately, so that the controller can communicate to the user the problems that have occurred.

- The `parse(String[] parameters)` method header of the `Command` class becomes capable of throwing `CommandParseException` type exceptions:

	```java
    public abstract Command parse(String[] parameter) throws CommandParseException;
	```

	For example, the `parse()` method of the `NoParamsCommand` class provided in the previous assignment becomes as follows:

	```java
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length == 1)
				...
			else
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		else
			return null;	
	}
	```

- The `parse(String[] commandWords)` static method header of `CommandGenerator` becomes capable of throwing `CommandParseException` type exceptions:

	```java
	public static Command parse(String[] commandWords) throws CommandParseException
	```

	so, the method throws an exception of type

	```java
	throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	```

	if it finds an unknown command, instead of returning `null` and waiting for `Controller` to handle the case with a simple *if-then-else*.


- `IllegalArgumentException`: system exception thrown when a player-supplied element should be able to be converted to an enumerated type and it is not possible. This exception shall be caught to throw a `CommandParseException` instead.

- The `execute()` abstract method header of the `Command` class now returns a `boolean`, indicating whether to print the board after executing the command, and also to be able to throw `CommandExecuteException` exceptions:

	```java
	public abstract boolean execute(GameModel game) throws CommandExecuteException;
	```

- The controller must be able to catch, within the `run()` method, the exceptions thrown by the two previous methods

	```java
	public void run() {
		...
		while (!game.isFinished()) {
			...
			try { ... }
			catch (CommandParseException | CommandExecuteException e) {
				System.out.println(e.getMessage());
				Throwable cause = e.getCause();
				if (cause != null) 
				    System.out.println(cause.getMessage());
			}
		}
		...
	}
	```

Thus, all messages are printed from the loop of the `run()` method of the controller (except those of the `help` and `list` commands).

## Exceptions en `GameModel`

Errors when parsing commands can be detected directly in the `parse` method, usually generated by some incorrectness in the input given by the user.

However, errors when executing commands arise from the logic of the game. In previous assignment, methods invoked by error-prone commands returned a `boolean` to indicate whether their invocation was successful. For example, the `move(Move dir)` method of `GameModel` returned `false` when attempting to move the player's ship off the board. However, the error message we were able to generate merely reported that the ship had failed to move.

In general, error messages are more descriptive if they are created where the error occurred. Therefore, we will replace the return of the boolean `false` with the throwing of an exception. This will allow us to display the following error messages:

- Message when trying to launch a super laser without enough points:

	```
	[DEBUG] Executing: sl

	Super laser cannot be shot
	Not enough points: only 0 points, 5 points required
	Command > 
	```

- Message when trying to move the ship off the board:

	```
	Command > m lleft

	[DEBUG] Executing: m lleft

	Movement cannot be performed
	Cannot move in direction LLEFT from position (1, 7)
	Command > 
	```

- Message when trying to move using an incorrect direction:

	```
	Command > m up

	[DEBUG] Executing: m up

	Wrong direction: UP
	Allowed UCMShip moves: <left|lleft|right|rright>
	```
    
    Therefore, the header of the `shootSuperLaser` method of `GameModel` will look like this:
    
	```java
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughtPointsException;
	```

	That is, it is a `void` method (it returns nothing) that can throw an exception representing either the error that there is already a laser on the board or the error that there are not enough dots.

In addition, we will consider the following exceptions thrown by `GameModel` methods. All of them will inherit from a `GameModelException` class.

- `LaserInFlightException`: a specific exception thrown when trying to launch some kind of laser when there is already one on the board.

- `NotEnoughPointsException`: exception thrown when it is not possible to perform some action requested by the user, as the player does not have enough points to perform it.

- `NoShockWaveException`: Exception thrown when you try to throw a *shockwave* and you don't have that possibility.

- `OffWorldException`: Exception that occurs when attempting to improperly access an off-board position (e.g. when moving the ship).

- `NotAllowedMoveException`: This exception is thrown when an attempt is made to perform an impermissible move when moving the ship.

- `InitializationException`: Exception thrown when resetting the game, when the initial configuration format is incorrect, either because there are not enough parameters in some line of the file (see below) or because the parameters are incorrect (unknown ship or incorrect position format).

A good practice in exception handling is to catch a low-level exception and then throw a high-level exception containing another message which, although necessarily less specific than the low-level one, is also useful. For example, in the `parse` method of the `MoveCommand` command we can do the following:

```java
	} catch (IllegalArgumentException e) {
		throw new CommandParseException(Messages.DIRECTION_ERROR + direction);
	}
```

That way we catch a system exception to throw a `CommandParseException` instead. When converting a value of type `string` to its value of type `Move`, if the string does not match any value in the enum, we can either throw an `IllegalArgumentException` ourselves (or, depending on our implementation, take advantage of the one thrown by the `valueOf` method when this happens). 

In the case of runtime errors, the `execute` method of each command will call a `GameModel` method likely to throw the exceptions listed above. In that case, we shall construct the new `CommandExecuteException` exception in such a way that it will either *cover* or *decorate* they caught exception:

```java
	} catch (OffWorldException owe) {
		throw new CommandExecuteException(Messages.MOVEMENT_ERROR, owe);
	}
```

This way the cause of the error is not lost and, in particular, the message of the overridden exception can be retrieved in the controller (note the `cause.getMessage()` in the `run` method of the controller).

## Saving and loading initial configuration

The second extension is to read from the file the initial configuration at reset.

```
Command > h

[DEBUG] Executing: h

Available commands: 
.........
[r]eset [<filename>]: resets the game using the initial configuration in file <filename>. If no file is given the standard configuration is used
.........
```

As in the previous assignment, you can reset by starting with a standard configuration, using the `reset` command without parameters, or by starting with a *customised* configuration, using it with parameters. However, unlike the previous practice, the command parameter can be any `string`, which will have to correspond to the name of a file. 

To achieve this, we modify `InitialConfiguration` to be a class and not an enum. This class will contain a method:

```java
	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException, IOException 
	{...}
```

to read an initial configuration from the file. Note that this method will play the role of the static `valueOfIgnoreCase` method in the enumeration.

We will assume that the initial configuration files provide a description of an alien spacecraft on each line:

```
R 2 3
R 3 3
R 4 3
R 5 3
```

In addition, to represent the default initial configuration we define in `InitialConfiguration` a constant `NONE`:

```java
	public static final InitialConfiguration NONE = new InitialConfiguration();
```

So, from other classes like `ResetCommand` or `AlienManager` you can use the `InitialConfiguration.NONE` constant as default configuration, just like we did with the enum. For example, in `initialize` of `AlienManager` you can write:

```java
	if (conf == InitialConfiguration.NONE) {
		// standard initialization
	} else {
		// initialization using conf
	}
```

The `parse` method of `ResetCommand` shall invoke `readFromFile` to load the initial configuration, making sure that the file exists and that no error occurs while reading it.

## Exceptions during uploading

During parsing and execution of the `reset` command, it is necessary to check that each description of an alien ship (i.e., each line) is correct.

- The file must exist:

	```
	Command > r conf

	[DEBUG] Executing: r conf

	File not found: "conf"
	```

- The ships in the file are of known type:

	```
	Command > r conf_6

	[DEBUG] Executing: r conf_6

	Invalid initial configuration
	Unknown ship: "s"
	```

- Each line has three words:
	```
	Command > r conf_5

	[DEBUG] Executing: r conf_5

	Invalid initial configuration
	Incorrect entry "R 5". Insufficient parameters.
	```

- The second and third words can be converted to numbers. When the conversion from a `string` to an `int` fails, a `NumberFormatException` is generated. This exception will be caught to throw an `InitializationException` instead.

	```
	Command > r conf_2

	[DEBUG] Executing: r conf_2

	Invalid initial configuration
	Invalid position (a, 3)
	```

- And finally, the position of each alien ship given is on the board:

	```
	Command > r conf_4

	[DEBUG] Executing: r conf_4

	Invalid initial configuration
	Position (3, 8) is off board
	```

To handle all these errors, we will assume that the `initialize` method of `AlienManager` can throw exceptions of type `InitializationException`:

```java
public GameObjectContainer initialize(InitialConfiguration conf) throws InitializationException
```

These exceptions will be caught directly in the `execute` method of `ResetCommand`. Note that if the file load fails and the exception is handled, informing the user of the problem, the game should be able to continue as if the reset had not been attempted.
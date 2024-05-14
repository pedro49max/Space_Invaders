# Práctica 3: Excepciones y ficheros

**Entrega: semana del 18 de diciembre**

**Objetivos:** Manejo de excepciones y tratamiento de ficheros

**Preguntas Frecuentes**: Como es habitual que tengáis dudas (es normal) las iremos recopilando en este [documento de preguntas frecuentes](../faq.md). Para saber los últimos cambios que se han introducido [puedes consultar la historia del documento](https://github.com/informaticaucm-TPI/2324-SpaceInvaders/commits/main/enunciados/faq.md).


# Introducción

En esta práctica se ampliará la funcionalidad del juego en dos aspectos:

- Incluiremos la definición y el tratamiento de excepciones. Durante la ejecución del juego pueden presentarse estados excepcionales que deben ser tratados de forma particular. Además, cada uno de estos estados debe proporcionar al usuario información relevante de por qué se ha llegado a ellos (por ejemplo, errores producidos al procesar un determinado comando). El objetivo último es dotar al programa de mayor robustez, así como mejorar la interoperabilidad con el usuario.

- Cargaremos de ficheros las configuraciones iniciales del tablero. De esta forma las configuraciones iniciales no serán parte del código del programa ni estarán limitadas a unas pocas configuraciones fijas.
  
# Manejo de excepciones

En esta sección se enumerarán las excepciones que deben tratarse durante el juego, se explicará la forma de implementarlas y se mostrarán ejemplos de ejecución.

## Descripción

El tratamiento de excepciones en un lenguaje como Java resulta muy útil para controlar determinadas situaciones que se producen durante la ejecución del juego: por ejemplo, para mostrar información relevante al usuario sobre lo ocurrido durante el "parseo" o la ejecución de un comando. 

En las prácticas anteriores, cada comando invocaba su método correspondiente de la interfaz `GameModel` para poder llevar a cabo operaciones sobre el juego.
Así, si se quería disparar un láser, se debía asegurar que no hubiera ya un láser en el tablero. Si no podía dispararse, la ejecución del comando devolvía un *record* `ExecutionResult` con el mensaje de error que se debía mostrar. Además, internamente `ExecutionResult` almacenaba un `boolean` para representar que se había producido un error a la hora de ejecutar el comando, de modo que el controlador pudiera saber que el comando había fallado. Sin embargo, en algunas situaciones teníamos que imprimir mensajes poco significativos.

En esta práctica vamos a aplicar el mecanismo de excepciones para realizar esta tarea, y ciertos métodos van a poder lanzar y procesar excepciones para tratar determinadas situaciones durante el juego. En algunos casos este tratamiento consistirá únicamente en proporcionar un mensaje al usuario, mientras que en otros el tratamiento podrá ser más complejo. En esta sección no vamos a ocuparnos de tratar las excepciones relativas a los ficheros, que serán explicadas en la sección siguiente.

En primer lugar, vamos a tratar dos tipos de excepciones: `CommandParseException` y `CommandExecuteException`. La primera sirve para tratar los errores que tienen lugar al "parsear" un comando, es decir, aquellos producidos durante la ejecución del método `parse()`, tales como comando desconocido, número de parámetros incorrecto o tipo de parámetros no válido. La segunda se utiliza para tratar las situaciones de error que se pueden dar al ejecutar el método `execute()` de un comando: por ejemplo, no tener suficientes puntos para lanzar el súper láser o intentar mover la nave fuera del tablero.

Además, nos ocuparemos de las excepciones lanzadas por el sistema, es decir, aquellas no creadas ni lanzadas por nosotros. En el juego esto ocurre con la excepción `NumberFormatException`, que se lanza cuando se produce un error al convertir un `String` a un `int`. También puede ocurrir con la excepción
`IllegalArgumentException`, lanzada por el método estático `valueOf` de los tipos enumerados para convertir un `string` al enumerado que le corresponde. Más adelante nos ocuparemos también de las que tienen que ver con el manejo de ficheros.

## Aspectos generales de la implementación

Una de las principales modificaciones que realizaremos a la hora de incluir el manejo de excepciones en el juego consistirá en ampliar la comunicación entre los comandos y el controlador. Eliminaremos `ExecutionResult` y utilizaremos un `boolean` para indicar si es necesario pintar el juego, pero también se contemplará la posibilidad de que se haya producido un error de forma que, controlando el flujo de las excepciones que puedan producirse durante el "parseo" o la ejecución de un comando, se podrá informar del error al usuario. De esta forma, los mensajes de error mostrados al usuario podrán ser mucho más descriptivos que en la práctica anterior. 

Básicamente, los cambios que se deben realizar son los siguientes:

- Se deben definir las excepciones `CommandParseException` y `CommandExecuteException`, lanzar excepciones de estos tipos y tratarlas adecuadamente, de forma que el controlador pueda comunicar al usuario los problemas que hayan tenido lugar. 

- La cabecera del método `parse(String[] parameters)` de la clase `Command` pasa a poder lanzar excepciones de tipo `CommandParseException`:

	```java
    public abstract Command parse(String[] parameter) throws CommandParseException;
	```

	Por ejemplo, el método `parse()` de la clase `NoParamsCommand` que se proporcionó en la práctica anterior pasa a ser de la siguiente forma:

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

- La cabecera del método estático `parse(String[] commandWords)` de `CommandGenerator` pasa a poder lanzar excepciones de tipo `CommandParseException`:

	```java
	public static Command parse(String[] commandWords) throws CommandParseException
	```

	de forma que el método lanza una excepción del tipo 

	```java
	throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	```

	si se encuentra con un comando desconocido, en lugar de devolver `null` y esperar a que `Controller` trate el caso mediante un simple *if-then-else*.


- `IllegalArgumentException`: excepción del sistema lanzada cuando un elemento proporcionado por el jugador debería poder convertirse a un tipo enumerado y no es posible. Esta excepción se capturará para lanzar en su lugar una `CommandParseException`.


- La cabecera del método abstracto `execute()` de la clase `Command` pasa a devolver un `boolean`, indicando si se debe voler a pintar el tablero tras ejecutar el comando, y también a poder lanzar excepciones de tipo `CommandExecuteException`:

	```java
	public abstract boolean execute(GameModel game) throws CommandExecuteException;
	```

- El controlador debe poder capturar, dentro del método `run()`, las excepciones lanzadas por los dos métodos anteriores

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


Así, todos los mensajes se imprimen desde el bucle del método `run()` del controlador (excepto los de los comandos `help` y `list`).

## Excepciones en `GameModel`

Los errores al parsear los comandos se pueden detectar directamente en el método `parse`, habitualmente generados por alguna incorrección en la entrada dada por el usuario.

Sin embargo, los errores al ejecutar los comandos surgen de la lógica del juego. En la práctica anterior los métodos invocados por los comandos susceptibles de generar errores devolvían un `boolean` para indicar si su invocación había tenido éxito. Por ejemplo, el método `move(Move dir)` de `GameModel` devolvía `false` cuando se intentaba mover a la nave del jugador fuera del tablero. Sin embargo, el mensaje de error que podíamos generar se limitaba a informar de que la nave no había podido moverse.

En general, los mensajes de error son más descriptivos si se crean allá donde se ha producido el error. Por ello, vamos a sustituir la devolución del booleano `false` por el lanzamiento de una excepción. Esto nos permitirá mostrar los siguientes mensajes de error:

- Mensaje al intentar lanzar un súper láser sin suficientes puntos:

	```
	[DEBUG] Executing: sl

	Super laser cannot be shot
	Not enough points: only 0 points, 5 points required
	Command > 
	```

- Mensaje al intentar mover la nave fuera del tablero:

	```
	Command > m lleft

	[DEBUG] Executing: m lleft

	Movement cannot be performed
	Cannot move in direction LLEFT from position (1, 7)
	Command > 
	```

- Mensaje al intentar mover utilizando una dirección incorrecta:

	```
	Command > m up

	[DEBUG] Executing: m up

	Wrong direction: UP
	Allowed UCMShip moves: <left|lleft|right|rright>
	```


    Por lo tanto, la cabecera del método `shootSuperLaser` de `GameModel` quedará así:

	```java
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughtPointsException;
	```

	Es decir, es un método `void` (no devuelve nada) que puede lanzar una excepción que representa o bien el error de que ya hay un láser en el tablero, o bien el error de que no se tienen puntos suficientes.

Además, consideraremos las siguientes excepciones lanzadas por los métodos de `GameModel`. Todas ellas heredarán de una clase `GameModelException`.

- `LaserInFlightException`: excepción específica que se produce al intentar lanzar algún tipo de láser cuando ya hay uno en el tablero.

- `NotEnoughPointsException`: excepción lanzada cuando no es posible realizar alguna acción solicitada por el usuario, al no tener el jugador suficientes puntos para llevarla a cabo.

- `NoShockWaveException`: excepción que tiene lugar cuando se intenta lanzar un *shockwave* y no se tiene esa posibilidad.

- `OffWorldException`: excepción que se produce cuando se intenta acceder de manera indebida a una posición fuera del tablero (por ejemplo, al mover la nave).

- `NotAllowedMoveException`: esta excepción se lanza cuando se intenta realizar un movimiento no permitido al mover la nave.

- `InitializationException`: excepción lanzada al resetear la partida, cuando el formato de la configuración inicial es incorrecto, bien porque en alguna línea del fichero (ver más adelante) no hay suficientes parámetros o bien porque los parámetros son incorrectos (nave desconocida o formato de la posición incorrecto).

Una buena práctica en el tratamiento de excepciones consiste en recoger una excepción de bajo nivel para, a continuación, lanzar una de alto nivel que contiene otro mensaje que, aunque será necesariamente menos específico que el de la de bajo nivel, es también de utilidad. Por ejemplo, en el método `parse` del comando `MoveCommand` podemos hacer lo siguiente:

```java
	} catch (IllegalArgumentException e) {
		throw new CommandParseException(Messages.DIRECTION_ERROR + direction);
	}
```

De esa forma capturamos una excepción del sistema  para lanzar en su lugar una `CommandParseException`. Cuando convertimos un valor de tipo `string` a su valor de tipo `Move`, si la cadena no se corresponde con ningún valor del enumerado, podemos o bien lanzar nosotros mismos una `IllegalArgumentException` (o, dependiendo de nuestra implementación, aprovechar la que lanza el método `valueOf` cuando esto sucede). 

En el caso de los errores de ejecución, en el método `execute` de cada comando se invocará a un método de `GameModel` susceptible de lanzar las excepciones listadas más arriba. En ese caso deberemos construir la nueva excepción `CommandExecuteException` de manera que 
*recubra* o *decore* a la excepción capturada: 

```java
	} catch (OffWorldException owe) {
		throw new CommandExecuteException(Messages.MOVEMENT_ERROR, owe);
	}
```

De esta forma la causa del error no se pierde y, en particular, se puede recuperar el mensaje de la excepción recubierta en el controlador (fíjate en el `cause.getMessage()` que aparece en el método `run` del controlador).

# Carga de fichero con las configuraciones iniciales 

La segunda extensión consiste en leer de fichero la configuración inicial al resetear. 

```
Command > h

[DEBUG] Executing: h

Available commands: 
.........
[r]eset [<filename>]: resets the game using the initial configuration in file <filename>. If no file is given the standard configuration is used
.........
```

Al igual que en la práctica anterior, se puede resetar comenzando con una configuración estándar, usando el comando `reset` sin parámetros, o comenzando con una configuración *customizada*, usándolo con parámetros. Sin embargo, a diferencia de la práctica anterior, el parámetro del comando puede ser cualquier `string`, que se tendrá que corresponder con el nombre de un fichero. 

Para conseguirlo, modificamos `InitialConfiguration` para que sea una clase y no un enumerado. Dicha clase contendrá un método:

```java
	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException, IOException 
	{...}
```

para leer del fichero una configuración inicial. Fíjate en que ese método jugará el papel del método estático `valueOfIgnoreCase` del enumerado.

Supondremos que los ficheros de las configuraciones iniciales proporcionan una descripción de una nave alienígena en cada línea:

```
R 2 3
R 3 3
R 4 3
R 5 3
```

Además, para representar a la configuración inicial por defecto definimos en `InitialConfiguration` una constante `NONE`:

```java
	public static final InitialConfiguration NONE = new InitialConfiguration();
```

Así, desde otras clases como `ResetCommand` o `AlienManager` se puede usar la constante `InitialConfiguration.NONE` como configuración por defecto, al igual que hacíamos con el enumerado. Por ejemplo, en `initialize` de `AlienManager` se puede escribir:

```java
	if (conf == InitialConfiguration.NONE) {
		// inicialización estándar 
	} else {
		// inicialización usando conf
	}
```

El método `parse` de `ResetCommand` será el encargado de invocar a `readFromFile` para cargar la configuración inicial, asegurándose de que existe el fichero y de que no se produce ningún error durante su lectura.

## Excepciones durante la carga del fichero

Durante el "parseo" y ejecución del comando `reset` es necesario comprobar que cada descripción de una nave alienígena (i.e., cada línea) es correcta.

- Tiene que existir el fichero:

	```
	Command > r conf

	[DEBUG] Executing: r conf

	File not found: "conf"
	```

- Las naves que aparecen en el fichero son de tipo conocido:

	```
	Command > r conf_6

	[DEBUG] Executing: r conf_6

	Invalid initial configuration
	Unknown ship: "s"
	```

- Cada línea tiene tres palabras:

	```
	Command > r conf_5

	[DEBUG] Executing: r conf_5

	Invalid initial configuration
	Incorrect entry "R 5". Insufficient parameters.
	```

- Las palabras segunda y tercera se pueden convertir a números.
Cuando falla la conversión de un `string` a un `int` se genera una `NumberFormatException`. Esta excepción se capturará para lanzar en su lugar una `InitializationException`. 

	```
	Command > r conf_2

	[DEBUG] Executing: r conf_2

	Invalid initial configuration
	Invalid position (a, 3)
	```

- Y, por último, la posición de cada nave alienígena dada está dentro del tablero:

	```
	Command > r conf_4

	[DEBUG] Executing: r conf_4

	Invalid initial configuration
	Position (3, 8) is off board
	```

Para manejar todos estos errores supondremos que el método `initialize` de `AlienManager` puede lanzar excepciones de tipo `InitializationException`:

```java
public GameObjectContainer initialize(InitialConfiguration conf) throws InitializationException
```

Esas excepciones se capturarán directamente en el método `execute` de `ResetCommand`. Ten en cuenta que si la carga del fichero falla y se gestiona esa excepción, informando al usuario del problema, el juego debe poder continuar como si no se hubiese intentado el reseteo.
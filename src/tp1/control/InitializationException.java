package tp1.control;

import tp1.view.Messages;

public class InitializationException  extends GameModelException{

	public InitializationException(String specfificError) {//this is not
		super(Messages.INITIAL_CONFIGURATION_ERROR+ Messages.LINE_SEPARATOR + specfificError);//Messages.FILE_NOT_FOUND does not do what it hast to
		// TODO Auto-generated constructor stub
	}

}

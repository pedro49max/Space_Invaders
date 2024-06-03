package tp1.control;

import tp1.view.Messages;

public class LaserInFlightException extends GameModelException {
	public LaserInFlightException() {
		super(Messages.LASER_ALREADY_SHOT);
	}
	
}

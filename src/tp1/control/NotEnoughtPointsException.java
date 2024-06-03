package tp1.control;

import tp1.view.Messages;

public class NotEnoughtPointsException extends GameModelException {

	public NotEnoughtPointsException(int points) {
		super("Super laser cannot be shot" + Messages.LINE_SEPARATOR + "Not enough points: only "+ points +" points, 5 points required");
	}

}

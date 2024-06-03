package tp1.control;

import tp1.logic.Move;
import tp1.view.Messages;

public class NotAllowedMoveException  extends GameModelException{

	public NotAllowedMoveException(Move move) {
		super("Wrong direction: " + move + Messages.LINE_SEPARATOR + Messages.ALLOWED_MOVES_MESSAGE);
		// TODO Auto-generated constructor stub
	}

}

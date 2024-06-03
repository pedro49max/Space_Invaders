package tp1.control;

import tp1.logic.Move;
import tp1.logic.Position;

public class OffWorldException  extends GameModelException{

	public OffWorldException(Move move, Position pos) {
		super("Cannot move in direction " + move + " from position " + pos.toString());
		// TODO Auto-generated constructor stub
	}

}

package tp1.logic.gameobjects;

import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip{
	protected Move move;
	public AlienShip(GameWorld game, Position pos, int life, int points) {
		super(game, pos, life, points);
		// TODO Auto-generated constructor stub
	}

}

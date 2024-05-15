package tp1.logic.gameobjects;

import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	protected Move move;
	public Weapon(GameWorld game, Position pos, int life, boolean goodSide) {
		super(game, pos, life, goodSide);
		// TODO Auto-generated constructor stub
	}
	public int points() {
		return 0;
	}
	protected void setDir() {
		if(goodSide)
			move = Move.UP;
		else
			move = Move.DOWN;
	}
}

package tp1.logic.gameobjects;

import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
	protected int points;
	public EnemyShip(GameWorld game, Position pos, int life, int points) {
		super(game, pos, life);
		this.points = points;
		// TODO Auto-generated constructor stub
	}

}

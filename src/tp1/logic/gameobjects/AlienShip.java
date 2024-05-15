package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip{
	protected Move move;
	protected AlienManager alienmanager;
	public AlienShip(GameWorld game, Position pos, int life, int points, AlienManager alienManager) {
		super(game, pos, life, points);
		this.alienmanager = alienManager;
		// TODO Auto-generated constructor stub
	}
	public boolean border() {
		boolean onborder = false;
		final int right = Game.DIM_X-1;
		final int left = 0;
		int i = 0;
		while( i < Game.DIM_X && !onborder) {
			Position pos1= new Position(left, i);
			Position pos2= new Position(right, i);
			if(this.pos.equals(pos1) || this.pos.equals(pos2))
				onborder = true;
			else
				i++;
		}		
		return onborder;
	}
	protected void setDir() {
		move = this.alienmanager.getDir();
	}
}

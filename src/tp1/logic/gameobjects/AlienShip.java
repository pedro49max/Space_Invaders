package tp1.logic.gameobjects;

import java.util.Random;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Level;
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
		while( i < Game.DIM_Y && !onborder) {
			Position pos1= new Position(left, i);
			Position pos2= new Position(right, i);
			if(this.pos.equals(pos1) || this.pos.equals(pos2))
				onborder = true;
			else
				i++;
		}		
		return onborder;
	}
	public boolean ground() {
		boolean gro = false;
		final int botton = Game.DIM_Y-1;
		int i = 0;
		while( i < Game.DIM_X && !gro) {
			Position pos1= new Position(i, botton);
			if(this.pos.equals(pos1))
				gro = true;
			else
				i++;
		}		
		return gro;
	}
	protected void setDir() {
		move = this.alienmanager.getDir();
	}
	public boolean bomb(Level level, Random rand) {
		if(this.points==10 && rand.nextDouble() < level.getShotF())
			return true;
		else 
			return false;
	}
	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);
}

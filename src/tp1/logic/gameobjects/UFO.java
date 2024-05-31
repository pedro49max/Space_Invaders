package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UFO extends EnemyShip{
	protected Move move;
	protected boolean visible;
	protected AlienManager manager;
	public UFO(GameWorld game, AlienManager manager) {
		super(game, new Position(Game.DIM_X,0), 1, 20);
		this.manager = manager;
	}

	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
	}
	public void restartPosition() {
		this.pos= new Position(Game.DIM_X - 1,0);
	}

	@Override
	protected String getSymbol() {
		return " " +Messages.UFO_SYMBOL + "[0" + this.life + "]";
	}

	@Override
	protected int getDamage() {
		return 0;//does no damage on its own
	}

	@Override
	protected int getArmour() {
		return this.life;
	}

	@Override
	public void onDelete() {
		this.manager.deleteUFO(this);
		this.game.getShockwave(true);
		this.game.deleteObject(this);
	}

	@Override
	public void automaticMove() {
		this.pos.move(this.move);
		if(this.pos.getCol() < 0)
			this.visible = false;
		else
			this.visible = true;
	}

	@Override
	protected void setDir() {
		this.move = Move.LEFT;		
	}
	public boolean getVisible() {
		return this.visible;
	}
}

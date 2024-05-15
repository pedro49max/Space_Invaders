package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UFO extends EnemyShip{
	protected Move move;
	public UFO(GameWorld game) {
		super(game, new Position(Game.DIM_X - 1,0), 1, 20);
	}

	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
	}

	@Override
	protected String getSymbol() {
		return Messages.UFO_SYMBOL + "[" + this.life + "]";
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
		this.game.deleteObject(this);
	}

	@Override
	public void automaticMove() {
		this.pos.move(this.move);
	}

	@Override
	protected void setDir() {
		this.move = Move.LEFT;		
	}

}

package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip{

	public DestroyerAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, 1, 10, alienManager);
	}

	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
	}

	@Override
	protected String getSymbol() {
		return Messages.DESTROYER_ALIEN_SYMBOL + "[" + this.life + "]";
	}

	@Override
	protected int getDamage() {
		return 0;//does no damage
	}

	@Override
	protected int getArmour() {
		return this.life;
	}

	@Override
	public void onDelete() {
		this.alienmanager.deleteAlien(this);
		this.game.deleteObject(this);
	}

	@Override
	public void automaticMove() {
		this.pos.move(this.move);
	}

}

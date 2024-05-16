package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ShockWave extends UCMWeapon{

	public ShockWave(GameWorld game, Position pos) {
		super(game, pos, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOnPosition(Position pos) {
		return false;//Has no position
	}

	@Override
	protected String getSymbol() {
		return null;//Has no symbol to be displayed in the map
	}

	@Override
	protected int getDamage() {
		return 1;//1 of damage to every enemy ship
	}

	@Override
	protected int getArmour() {
		return 0;//can't be hit
	}

	@Override
	public void onDelete() {
		this.game.getShockwave(false);
		this.game.deleteObject(this);
	}

	@Override
	public void automaticMove() {
		// doesn't move	
	}

}

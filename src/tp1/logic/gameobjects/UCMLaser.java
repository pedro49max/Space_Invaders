package tp1.logic.gameobjects;

import tp1.view.Messages;

public class UCMLaser extends UCMLasers{
	public UCMLaser(GameWorld game, UCMShip player) {
		super(game, player, 1);
	}

	@Override
	protected String getSymbol() {
		return Messages.LASER_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return 1;//does 1 damage to 1 enemy
	}
}

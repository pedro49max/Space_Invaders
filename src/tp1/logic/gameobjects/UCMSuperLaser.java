package tp1.logic.gameobjects;

import tp1.view.Messages;

public class UCMSuperLaser extends UCMLasers{

	public UCMSuperLaser(GameWorld game, UCMShip player) {
		super(game, player, 1);
	}

	@Override
	protected String getSymbol() {
		return Messages.SUPERLASER_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return 2;
	}
}

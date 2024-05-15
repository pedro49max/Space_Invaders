package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class UCMLaser extends UCMWeapon{
	private UCMShip player;
	public UCMLaser(GameWorld game, UCMShip player) {
		super(game, player.clonePos(), 1);
		this.player = player;
	}

	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
	}

	@Override
	protected String getSymbol() {
		return Messages.LASER_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return 1;//does 1 damage to 1 enemy
	}

	@Override
	protected int getArmour() {
		return this.life;
	}

	@Override
	public void onDelete() {
		this.player.shotAgain();
		this.game.deleteObject(this);
	}

	@Override
	public void automaticMove() {
		this.pos.move(this.move);//UP
		
	}

}

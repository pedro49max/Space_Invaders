package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon{

	public Bomb(GameWorld game, Position pos) {
		super(game, pos, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
	}

	@Override
	protected String getSymbol() {
		return Messages.BOMB_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return 1;//To UCMShip or UCMWeapon
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
		this.pos.move(this.move);//down
	}

}

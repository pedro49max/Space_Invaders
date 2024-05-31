package tp1.logic.gameobjects;

import tp1.logic.Position;

public abstract class UCMLasers extends UCMWeapon{
	protected UCMShip player;
	public UCMLasers(GameWorld game, UCMShip player, int life) {
		super(game, player.clonePos(), life);
		this.player = player;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void automaticMove() {
		this.pos.move(this.move);//UP	
		if(this.pos.getRow()<0)
			this.onDelete();
	}
	
	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
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
}

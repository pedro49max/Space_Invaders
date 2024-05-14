package tp1.logic.gameobjects;

import tp1.logic.Position;

public class UFO extends EnemyShip{

	public UFO(GameWorld game, Position pos) {
		super(game, pos, 1, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOnPosition(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void automaticMove() {
		// TODO Auto-generated method stub
		
	}

}

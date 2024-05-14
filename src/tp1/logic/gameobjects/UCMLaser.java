package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public class UCMLaser extends UCMWeapon{

	public UCMLaser(Game game, Position pos) {
		super(game, pos);
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

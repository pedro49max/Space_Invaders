package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Position;

public class DestroyerAlien extends AlienShip{

	public DestroyerAlien(Game game, Position pos, AlienManager alienManager) {
		super(game, pos, 1);
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

package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends AlienShip{

	public ExplosiveAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, 2, 12, alienManager);
		// TODO Auto-generated constructor stub
	}
	public ExplosiveAlien() {
		super(null, null, 2, 12, null);
	}
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new ExplosiveAlien(game, pos, am);
	}

	@Override
	public String getShortSymbol() {
		return Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}

	@Override
	protected String getSymbol() {
		return " " +Messages.EXPLOSIVE_ALIEN_SYMBOL + "[0" + this.life + "]";
	}

	@Override
	public void onDelete() {
		this.alienmanager.deleteAlien(this);
		this.game.deleteObject(this);
	}


}

package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Position;
import tp1.view.Messages;

public class RegularAlien extends AlienShip {

	public RegularAlien(GameWorld game, Position pos, AlienManager alienManager) {
		// TODO fill with your code
		super(game, pos, 2, 5, alienManager);
	}
	public RegularAlien() {
		super(null, null, 2, 5, null);
	}
	@Override
	protected String getSymbol() {
		return " " +Messages.REGULAR_ALIEN_SYMBOL + "[0" + this.life + "]";
	}
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new RegularAlien(game, pos, am);
	}
	@Override
	public String getShortSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
	}

	@Override
	public void onDelete() {
		this.alienmanager.deleteAlien(this);
		this.game.deleteObject(this);
	}
}

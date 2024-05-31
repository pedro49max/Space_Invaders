package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip{

	public DestroyerAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, 1, 10, alienManager);
	}

	public DestroyerAlien() {
		super(null, null, 1, 10, null);
	}
	@Override
	protected String getSymbol() {
		return " " +Messages.DESTROYER_ALIEN_SYMBOL + "[0" + this.life + "]";
	}

	public Position bombPosition() {
		Position bomb = this.pos.clone();
		bomb.move(Move.DOWN);
		return bomb;
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new DestroyerAlien(game, pos, am);
	}
	@Override
	public String getShortSymbol() {
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}

	@Override
	public void onDelete() {
		this.alienmanager.deleteAlien(this);
		this.game.deleteObject(this);
	}
}

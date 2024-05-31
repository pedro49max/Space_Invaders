package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;
import tp1.logic.AlienManager;
import tp1.logic.Position;

public class ShipFactory {
	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) {		
		return parse(input, game, pos, am);
	}
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien()
	);
	private static AlienShip parse(String alienString, GameWorld game, Position pos, AlienManager am) {// throws AlienParseException {		
		AlienShip alien = null;
		for (AlienShip c: AVAILABLE_ALIEN_SHIPS) {
			if(c.getShortSymbol().equalsIgnoreCase(alienString))
				alien = c.copy(game, pos, am);				
		}
		return alien;
	}
}

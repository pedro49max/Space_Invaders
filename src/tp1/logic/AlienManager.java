package tp1.logic;

import tp1.logic.gameobjects.RegularAlien;

public class AlienManager  {
	
	private Game game;
	private int remainingAliens;
	private Level level;
	//TODO fill with your code
	public AlienManager(Game game, Level level){
		this.game = game;
		this.level = level;
	}
	public  GameObjectContainer initialize() {
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeOvni(container);
		initializeRegularAliens(container);
		initializeDestroyerAliens(container);
		
		//TODO fill with your code
		
		
		return container;
	}
	
	private void initializeOvni(GameObjectContainer container) {
		//container.add(new Ufo(game));
	}
	
	private void initializeRegularAliens (GameObjectContainer container) {

		//TODO fill with your code
		//		container.add(new RegularAlien(....));
	}
	
	private void initializeDestroyerAliens(GameObjectContainer container) {
		//TODO fill with your code
	}

	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) {
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			//AlienShip ship = ...
			container.add(ship);
			this.remainingAliens++;
		}
	}



	//TODO fill with your code


}

package tp1.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UFO;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.Bomb;
public class AlienManager  {
	
	private Game game;
	private int remainingAliens = 0;
	private Level level;
	private List<AlienShip> aliens;
	private Move dir= Move.LEFT, prevdir = Move.LEFT;
	//TODO fill with your code
	public AlienManager(Game game, Level level){
		this.game = game;
		this.level = level;
	}
	public  GameObjectContainer initialize() {
		this.remainingAliens = 0;
		aliens = new ArrayList<>();
		GameObjectContainer container = new GameObjectContainer();
		initializeOvni(container);
		initializeRegularAliens(container);
		initializeDestroyerAliens(container);
		//TODO fill with your code
		
		
		return container;
	}
	
	private void initializeOvni(GameObjectContainer container) {
		container.add(new UFO(game));
	}
	
	private void initializeRegularAliens (GameObjectContainer container) {
		this.remainingAliens += level.getNumAliens();
		for(int i = 0; i < level.getNumRowsRegularAliens(); i++){
			for (int j = 0; j < level.getNumAliens()/level.getNumRowsRegularAliens(); j++){
				Position position = new Position(j + 2, i + 1);
				RegularAlien regular = new RegularAlien(game, position,this);
				container.add(regular);				
				aliens.add((AlienShip)regular);
				this.remainingAliens++;
			}
		}
	}
	
	private void initializeDestroyerAliens(GameObjectContainer container) {
		Position position;
		this.remainingAliens += level.getNumDestroyer();
		for (int j = 0; j < level.getNumDestroyer(); j++){
			
			if(level.getNumAliens()/level.getNumRowsRegularAliens() == level.getNumDestroyer())
				position = new Position(j + 2,1 + level.getNumRowsRegularAliens());
			else
				position = new Position(j + 3,1 + level.getNumRowsRegularAliens());
			DestroyerAlien destroyer = new DestroyerAlien(game, position, this);
			container.add(destroyer);
			aliens.add(destroyer);
			this.remainingAliens++;
		}
	}
	
	public void setAlienDir() {
		if(this.timeToMove()) {
			boolean onBorder = this.onBorder();
			onBorder = this.onBorder();
			if (onBorder && (this.dir.equals(Move.LEFT)|| this.prevdir.equals(Move.LEFT))){
				if(this.dir.equals(Move.DOWN)) {
					this.dir = Move.RIGHT;
					this.prevdir = Move.DOWN;
				}			
				else if(this.dir.equals(Move.LEFT)) {
					this.dir = Move.DOWN;
					this.prevdir = Move.LEFT;				
				}
			}
			else if (onBorder && (this.dir.equals(Move.RIGHT) || this.prevdir.equals(Move.RIGHT))) {
				if(this.dir.equals(Move.DOWN)) {
					this.dir = Move.LEFT;
					this.prevdir = Move.DOWN;
				}	
				else if (this.dir.equals(Move.RIGHT)) {
					this.dir = Move.DOWN;
					this.prevdir = Move.RIGHT;
				}
			}		
			else
				this.prevdir = this.dir;
		}
	}	
	private boolean onBorder() {
		int i = 0;
		boolean border = false;
		while(i < aliens.size() && !border) {
			AlienShip alien = aliens.get(i);
			if(alien.border())
				border = true;
			else
				i++;
		}
		return border;
	}
	public boolean touchGround() {
		int i = 0;
		boolean ground = false;
		while(i < aliens.size() && !ground) {
			AlienShip alien = aliens.get(i);
			if(alien.ground())
				ground = true;
			else
				i++;
		}
		return ground;
	}
	public Move getDir() {
		if(this.timeToMove()) {
			return this.dir;
		}			
		else
			return Move.NONE;
	}
	public void deleteAlien(AlienShip alien) {
		this.aliens.remove(alien);
		this.remainingAliens--;
	}
	public int getRemainingAliens() {
		return this.remainingAliens;
	}
	private boolean timeToMove() {
		return game.getCycle()%level.getNumCycles() == 0;
	}
	public void bombs(Random rand, GameObjectContainer container) {
		for(int i = 0; i < aliens.size(); i++) {
			AlienShip alien = aliens.get(i);
			if(alien.bomb(level, rand)) {
				DestroyerAlien destroyer = (DestroyerAlien)alien;
				Bomb bomb = new Bomb(this.game, destroyer.bombPosition());
				container.add(bomb);
			}
				
		}
	}
	/*private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) {
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			//AlienShip ship = ...
			container.add(ship);
			this.remainingAliens++;
		}
	}*/
	


	//TODO fill with your code


}

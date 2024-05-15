package tp1.logic;

import java.util.List;
import java.util.Random;

import tp1.control.GameModel;
import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameWorld;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.UCMShip;


public class Game implements GameStatus , GameModel, GameWorld{

	//TODO fill with your code

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int currentCycle;
	private long seed;
	private Level level;
	private Random rand ;
	private boolean doExit;
	
	//TODO fill with your code

	public Game (Level level, long seed){
		this.level = level;
		this.seed = seed;
		initGame();
	}
		
	private void initGame () {	
		this.rand= new Random(seed);
		doExit = false;
		currentCycle = 0;
		
		alienManager = new AlienManager(this, level);
		container = alienManager.initialize();
		player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		container.add(player);
	}

	//GameModel METHODS
	
	public boolean isFinished() {
		return this.doExit;
	}

	public void exit() {
		this.doExit = true;
	}

	public void update() {
	    this.currentCycle++;
	    this.alienManager.setAlienDir();
	    this.container.computerActions();
	    this.container.automaticMoves();
	}	
	@Override
	public boolean move(Move move) {
		return this.player.move(move);
	}

	@Override
	public boolean shootLaser() {
		if(this.player.getcanShot()) {
			this.container.add(new UCMLaser(this, player));
			return true;
		}
		else
			return false;
	}
	public void shockWaveDrop() {
		this.container.shockWave(new ShockWave(this, new Position(1,1)));
	}
	@Override
	public void resetConfiguration(List<String> Conf) {
		currentCycle = 0;		
		alienManager = new AlienManager(this, level);
		container = alienManager.initialize();
		player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		container.add(player);
	}	
	//GameWorld Methods
	
	public void deleteObject(GameObject object) {
		this.container.remove(object);
	}
	public void receivePoints(int p) {
		this.player.addPoints(p);
	}
	//CALLBACK METHODS
	
	public void addObject(GameObject object) {
	    this.container.add(object);
	}

	// TODO fill with your code
	
	//VIEW METHODS
	
	public String positionToString(int col, int row) {
		return this.container.positionToString(new Position(col, row));
	}

	@Override
	public String stateToString() {
		return player.State();
	}

	@Override
	public boolean playerWin() {
		// TODO fill with your code
		return false;
	}

	@Override
	public boolean aliensWin() {
		// TODO fill with your code
		return false;
	}

	@Override
	public int getCycle() {
		return this.currentCycle;
	}

	@Override
	public int getRemainingAliens() {
		return this.alienManager.getRemainingAliens();
	}
}

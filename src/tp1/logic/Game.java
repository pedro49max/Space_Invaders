package tp1.logic;

import java.util.Random;

import tp1.control.GameModel;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameWorld;
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

	//CONTROL METHODS
	
	public boolean isFinished() {
		// TODO fill with your code
		return this.doExit;
	}

	public void exit() {
		this.doExit = true;
	}

	public void update() {
	    this.currentCycle++;
	    this.container.computerActions();
	    this.container.automaticMoves();
	}

	// TODO fill with your code

	//CALLBACK METHODS
	
	public void addObject(GameObject object) {
	    this.container.add(object);
	}

	// TODO fill with your code
	
	//VIEW METHODS
	
	public String positionToString(int col, int row) {
		return null;
		//return container.toString(new Position(col, row));
	}
	
	

	@Override
	public String infoToString() {
		// TODO fill with your code
		return null;
	}

	@Override
	public String stateToString() {
		// TODO fill with your code
		return null;
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
		// TODO fill with your code
		return 0;
	}

	@Override
	public int getRemainingAliens() {
		// TODO fill with your code
		return 0;
	}

	@Override
	public boolean move(Move move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shootLaser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}

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
import tp1.logic.gameobjects.UCMSuperLaser;


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
		container = alienManager.initialize(InitialConfiguration.NONE);
		player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		container.add(player);
	}

	//GameModel METHODS
	
	public boolean isFinished() {
		if(this.playerWin() || this.aliensWin() || this.doExit)
			return true;
		else
			return false;
	}

	public void exit() {
		this.doExit = true;
	}

	public void update() {
	    this.currentCycle++;
	    this.alienManager.setAlienDir();
	    this.container.computerActions();
	    this.container.automaticMoves();
	    this.alienManager.bombs(rand, this.container);
	    this.alienManager.resetUFO(rand, container);
	    this.container.computeractionsAfterMoving();//all damage makes sense to implement it after movement
	}	
	@Override
	public boolean move(Move move) {
		return this.player.move(move);
	}

	@Override
	public boolean shootLaser(boolean superLaser) {
		if(this.player.getcanShot()) {
			if(!superLaser)
				this.container.add(new UCMLaser(this, player));
			else if(this.player.canShotSuperLaser())
				this.container.add(new UCMSuperLaser(this, player));
			return true;
		}
		else
			return false;
	}
	public void shockWaveDrop() {
		if(player.checkSockwave()) {
			this.getShockwave(false);
			this.container.shockWave(this);			
		}
	}

	@Override
	public void resetConfiguration(InitialConfiguration Conf) {
		currentCycle = 0;		
		alienManager = new AlienManager(this, level);
		container = alienManager.initialize(Conf);
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
	@Override
	public void getShockwave(boolean get) {
		this.player.getShockwave(get);
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
		return this.alienManager.getRemainingAliens() == 0;
	}

	@Override
	public boolean aliensWin() {
		return this.alienManager.touchGround() || !this.player.isAlive();
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

package tp1.logic.gameobjects;

import tp1.control.NotAllowedMoveException;
import tp1.control.OffWorldException;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMShip extends Ship{
	private boolean canShot = true;
	private boolean shockWave = false;
	private int points = 0;
	
	public UCMShip(GameWorld game, Position pos) {
		super(game, pos, 3, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOnPosition(Position position) {
		return this.pos.equals(position);
	}

	@Override
	protected String getSymbol() {
		if(this.isAlive())
			return Messages.UCMSHIP_SYMBOL;
		else
			return Messages.UCMSHIP_DEAD_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return 0;
	}

	@Override
	protected int getArmour() {
		return this.life;
	}

	@Override
	public void onDelete() {
	}

	@Override
	public void automaticMove() {
		// No need		
	}

	@Override
	public int points() {
		return 0;//gives no points when hit by UCMWeapon, because it can't get hit by it
	}

	public boolean move(Move move)  throws OffWorldException, NotAllowedMoveException{
		if(move.equals(Move.LEFT)&& pos.getCol() == 0)
			throw new OffWorldException(move, this.pos);
		else if(move.equals(Move.LLEFT)&& pos.getCol() == 1)
			throw new OffWorldException(move, this.pos);
		else if(move.equals(Move.RRIGHT)&& pos.getCol() == Game.DIM_X-2)
			throw new OffWorldException(move, this.pos);
		else if(move.equals(Move.RIGHT)&& pos.getCol() == Game.DIM_X -1)
			throw new OffWorldException(move, this.pos);
		else if(move.equals(Move.DOWN))
			throw new NotAllowedMoveException(move);
		else if(move.equals(Move.UP))
			throw new NotAllowedMoveException(move);
		else {
			this.pos.move(move);
			return true;
		}	
	}
	public boolean getcanShot() {
		if(this.canShot) {
			this.canShot = false;
			return true;
		}		
		 return false;
	}
	public boolean canShotSuperLaser() {
		if(this.points>5) {
			points -= 5;
			return true;
		}
		else
			canShot = true;
		return false;
	}

	@Override
	protected void setDir() {//does nothing
	}
	public void addPoints(int p) {
		this.points += p;
	}
	public Position clonePos() {//only for the weapon
		return this.pos.clone();
	}
	public void shotAgain() {//when the laser gets deleted
		this.canShot = true;
	}
	public boolean checkSockwave() {
		return this.shockWave;
	}
	public void getShockwave(boolean get) {
		this.shockWave = get;
	}
	public String State() {
		String NEW_LINE = System.lineSeparator();
		String state;
		state = "Life: " + this.life +NEW_LINE;
        state+= "Points: " + this.points + NEW_LINE + "ShockWave: ";
		if(this.shockWave)
			state += "ON";
		else
			state += "OFF";
		state += NEW_LINE;
		return state;
	}
	public static String allowedMoves(String separator) {
		return "left"+separator+"lleft"+separator+"right"+separator+"rright";
	}
}

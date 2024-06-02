package tp1.control;


import tp1.logic.Move;

public interface GameModel { // everything to do with commands and game
	public boolean move(Move move) throws OffWorldException, NotAllowedMoveException;
	public void shootLaser(boolean superLaser)throws LaserInFlightException, NotEnoughtPointsException;
	public boolean resetConfiguration(InitialConfiguration Conf) throws InitializationException;
	public boolean isFinished();
	public void exit();
	public void update();
	public boolean shockWaveDrop() throws NoShockWaveException;
	//More
}

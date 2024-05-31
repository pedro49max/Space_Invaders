package tp1.control;

import tp1.logic.Move;

public interface GameModel { // everything to do with commands and game
	public boolean move(Move move);
	public boolean shootLaser();
	public void resetConfiguration(InitialConfiguration Conf);
	public boolean isFinished();
	public void exit();
	public void update();
	public void shockWaveDrop();
	//More
}

package tp1.control;

import java.util.List;

import tp1.logic.Move;

public interface GameModel { // everything to do with commands and game
	public boolean move(Move move);
	public boolean shootLaser();
	public void resetConfiguration(List<String> Conf);
	public boolean isFinished();
	public void exit();
	public void update();
	public void shockWaveDrop();
	//More
}

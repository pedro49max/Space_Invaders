package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameWorld {//for the methods in Game used in GameObjects
	public void deleteObject(GameObject object);
	public void receivePoints(int points);
	public void getShockwave(boolean get);
	public void explotion(Position pos);
}

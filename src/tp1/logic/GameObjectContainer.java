package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ShockWave;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void automaticMoves() {
		for (int i=0;i<objects.size();i++) {
				GameObject object = objects.get(i);
				object.automaticMove();
			//TODO fill with your code
		}
		//TODO fill with your code
	}

	public void computerActions() {
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.computerAction();
		}
		// TODO fill with your code
	}

	public void shockWave(ShockWave sw) {
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.receiveAttack(sw);
		//TODO fill with your code
		}
	}
	public String positionToString(Position pos) {
		String info = " ";
		int i = 0;
		boolean found = false;
		while(i < objects.size() && !found) {
			GameObject object = objects.get(i);
			if(object.isOnPosition(pos)) {
				found = true;
				info = object.toString();
			}
			else
				i++;
		}
		return info;
	}
	//TODO fill with your code
	
}

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
	}
	public void computeractionsAfterMoving() {
		for(int i = 0; i < objects.size();i++) {//just for the attacks one to one
			GameObject attacker = objects.get(i);
			int j= 0;
			boolean keepgoing = true;
			while(j< objects.size() && keepgoing) {
				if(j == i) {
					j++;
					if(j == objects.size())
						break;
				}
				GameObject asulted = objects.get(j);
				keepgoing = !attacker.performAttack(asulted);
				j++;
			}
		}
	}

	public void shockWave(Game game) {
		List<GameObject> objectives = new ArrayList<>();
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if(!object.getGoodSide()) {
				objectives.add(object);
			}				
		}
		this.massAtack(objectives, game);
	}
	public void  explotion(Position pos, Game game) {
		List<Position> explotionPositions = pos.explotionPos();
		List<GameObject> objectives = new ArrayList<>();
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			for(int j = 0; j < explotionPositions.size(); j++){
				Position position = explotionPositions.get(j);
				if(object.isOnPosition(position)) {
					objectives.add(object);
				}
			}
		}
		this.massAtack(objectives, game);
	}
	private void massAtack(List<GameObject> objectives, Game game) {
		ShockWave sw;//Use shockwave because it acts similar
		for(int i = 0; i < objectives.size(); i++) {
			GameObject object = objectives.get(i);
			sw = new ShockWave(game, object.clonePos());			
			this.objects.add(sw);//It is going to be deleted after attacking
			object.receiveAttack(sw);
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

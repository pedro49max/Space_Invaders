package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class Position {

	private int col;
	private int row;
	//TODO use your code from P1

	public Position(int x, int y){
		this.col = x;
		this.row = y;
	}
	public void move(Move move) {//Doing move here as you said in class
		this.col += move.getX(); 
		this.row += move.getY();
	}
	public int getCol() {
		return this.col;
	}
	public int getRow() {
		return this.row;
	}
	public boolean equals(Position pos) {
		if(this.row == pos.getRow() && this.col == pos.getCol())
			return true;
		else
			return false;
	}
	public Position clone() {
		return new Position(this.col, this.row);
	}
	public List<Position> explotionPos(){
		List<Position> positions = new ArrayList<>();
		for(int x = -1 ; x < 2; x++) {
			for(int y = -1; y < 2; y++) {
				if(x!=0 || y!= 0) {
					positions.add(new Position(this.col + x, this.row +y));
				}
			}
		}
		return positions;
	}
}

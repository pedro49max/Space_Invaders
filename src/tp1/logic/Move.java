package tp1.logic;

public enum Move {
	LEFT(-1,0), LLEFT(-2,0), RIGHT(1,0), RRIGHT(2,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	private int x;
	private int y;
	
	private Move(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public boolean equals(Move move) {
		if(this.x == move.getX() && this.y == move.getY())
			return true;
		else return false;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	//TODO use your code from P1
}

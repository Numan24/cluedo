package cluedo;

public final class Position {

	int row;
	int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int row(){
		return row;
	}
	
	public int col(){
		return col;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}
}

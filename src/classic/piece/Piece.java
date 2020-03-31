package classic.piece;

import classic.board.Tile;

public abstract class Piece {
	private boolean killed = false;
	private boolean white = false;

	public Piece(boolean white) {
		this.white = white;
	}

	public boolean isWhite() {
		return this.white;
	}

	public boolean isKilled() {
		return this.killed == true;
	}

	public void setKilled(boolean killed) {
		this.killed = killed;
	}
	
	public boolean isValid(Tile from, Tile to){
		if(from.getPiece().isWhite() == to.getPiece().isWhite())	
			return false;
		
		return true;
	}
	
	public abstract String pieceName();

	public abstract String pieceCode();

	public abstract boolean canMove(Tile from, Tile to);
}

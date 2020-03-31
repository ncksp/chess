package classic.piece;

import java.util.Vector;

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
		try {			
			if(from.getPiece().isWhite() == to.getPiece().isWhite())	
				return false;
		} catch (Exception e) {
		}
		
		return true;
	}
	
	public boolean isValid(int rank, int file){
		if(rank < 0 || rank > 7 || file < 0 || file > 7)
			return false;
		
		return true;
	}
	
	public abstract String pieceName();

	public abstract String pieceCode();

	public abstract Tile setMovement(int rank, int file);
	public abstract boolean canMove(Tile from, Tile to);
	
	public abstract Vector<Tile> getMoves(Tile from);
}

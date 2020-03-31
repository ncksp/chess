package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class Bishop extends Piece {

	public Bishop(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "Bishop";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "B";
	}

	@Override
	public boolean canMove(Tile from, Tile to) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Tile setMovement(int rank, int file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		// TODO Auto-generated method stub
		return null;
	}

}


// the other three states are available in this chapter’s sample code

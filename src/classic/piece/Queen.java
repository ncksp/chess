package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class Queen extends Piece {

	public Queen(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Tile from, Tile to) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "Queen";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "Q";
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

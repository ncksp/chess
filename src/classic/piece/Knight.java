package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class Knight extends Piece {

	public Knight(boolean white) {
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
		return "Knight";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "N";
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

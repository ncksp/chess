package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class King extends Piece {
	private boolean castling = false;

	public King(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	public boolean isCastling() {
		return castling;
	}

	public void setCastling(boolean castling) {
		this.castling = castling;
	}

	@Override
	public boolean canMove(Tile from, Tile to) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "King";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "K";
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

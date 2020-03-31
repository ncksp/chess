package classic.piece;

import classic.board.Tile;

public class Rook extends Piece{

	public Rook(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Tile from, Tile to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "Rook";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "R";
	}

}

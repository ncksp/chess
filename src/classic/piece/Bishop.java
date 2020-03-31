package classic.piece;

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
		return false;
	}

}

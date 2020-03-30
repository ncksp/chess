package classic.piece;

import classic.board.Board;
import classic.board.Tile;

public class Pawn extends Piece {

	public Pawn(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Board board, Tile from, Tile to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "Pawn";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "P";
	}

}

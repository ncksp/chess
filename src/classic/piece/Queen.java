package classic.piece;

import classic.board.Board;
import classic.board.Tile;

public class Queen extends Piece {

	public Queen(boolean white) {
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
		return "Queen";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "Q";
	}

}

package classic.piece;

import classic.board.Board;
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
	public boolean canMove(Board board, Tile from, Tile to) {
		// TODO Auto-generated method stub
		return false;
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

}

package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class Queen extends Piece {

	Vector<Tile> moves = new Vector<>();

	public Queen(boolean white) {
		super(white);
	}

	@Override
	public String pieceName() {
		return "Queen";
	}

	@Override
	public String pieceCode() {
		return "Q";
	}

	@Override
	public Vector<Tile> getMoves(Tile from, Tile to) {
		moves = new Vector<>();
		
		moves.addAll(new Rook(from.getPiece().isWhite()).getMoves(from, to));
		moves.addAll(new Bishop(from.getPiece().isWhite()).getMoves(from, to));

		return moves;
	}

}

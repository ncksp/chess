package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class King extends Piece {

	Vector<Tile> moves = new Vector<>();

	private boolean castling = false;

	public King(boolean white) {
		super(white);
	}

	public boolean isCastling() {
		return castling;
	}

	public void setCastling(boolean castling) {
		this.castling = castling;
	}

	@Override
	public String pieceName() {
		return "King";
	}

	@Override
	public String pieceCode() {
		return "K";
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		// N
		if (valid(from.getRank() + 1, from.getFile()) && getPiece(from.getRank() + 1, from.getFile()) == null
				|| (getPiece(from.getRank() + 1, from.getFile()) != null
						&& getPiece(from.getRank() + 1, from.getFile()).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 1, from.getFile()));

		// NE
		if (valid(from.getRank() + 1, from.getFile() + 1) && getPiece(from.getRank() + 1, from.getFile() + 1) == null
				|| (getPiece(from.getRank() + 1, from.getFile() + 1) != null
						&& getPiece(from.getRank() + 1, from.getFile() + 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 1, from.getFile() + 1));

		// E
		if (valid(from.getRank(), from.getFile() + 1) && getPiece(from.getRank(), from.getFile() + 1) == null
				|| (getPiece(from.getRank(), from.getFile() + 1) != null
						&& getPiece(from.getRank(), from.getFile() + 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank(), from.getFile() + 1));

		// SE
		if (valid(from.getRank() - 1, from.getFile() + 1) && getPiece(from.getRank() - 1, from.getFile() + 1) == null
				|| (getPiece(from.getRank() - 1, from.getFile() + 1) != null
						&& getPiece(from.getRank() - 1, from.getFile() + 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 1, from.getFile() + 1));

		// S
		if (valid(from.getRank() - 1, from.getFile()) && getPiece(from.getRank() - 1, from.getFile()) == null
				|| (getPiece(from.getRank() - 1, from.getFile()) != null
						&& getPiece(from.getRank() - 1, from.getFile()).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 1, from.getFile()));

		// SW
		if (valid(from.getRank() - 1, from.getFile() - 1) && getPiece(from.getRank() - 1, from.getFile() - 1) == null
				|| (getPiece(from.getRank() - 1, from.getFile() - 1) != null
						&& getPiece(from.getRank() - 1, from.getFile() - 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 1, from.getFile() - 1));

		// W
		if (valid(from.getRank(), from.getFile() - 1) && getPiece(from.getRank(), from.getFile() - 1) == null
				|| (getPiece(from.getRank(), from.getFile() - 1) != null
						&& getPiece(from.getRank(), from.getFile() - 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank(), from.getFile() - 1));

		// NW
		if (valid(from.getRank() + 1, from.getFile() - 1) && getPiece(from.getRank() + 1, from.getFile() - 1) == null
				|| (getPiece(from.getRank() + 1, from.getFile() - 1) != null
						&& getPiece(from.getRank() + 1, from.getFile() - 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 1, from.getFile() - 1));

		return moves;
	}

}

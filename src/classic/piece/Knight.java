package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class Knight extends Piece {

	Vector<Tile> moves = new Vector<>();

	public Knight(boolean white) {
		super(white);
	}

	@Override
	public String pieceName() {
		return "Knight";
	}

	@Override
	public String pieceCode() {
		return "N";
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		// NNE
		if (valid(from.getRank() + 2, from.getFile() + 1) && getPiece(from.getRank() + 2, from.getFile() + 1) == null
				|| (getPiece(from.getRank() + 2, from.getFile() + 1) != null
						&& getPiece(from.getRank() + 2, from.getFile() + 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 2, from.getFile() + 1));

		// ENE
		if (valid(from.getRank() + 1, from.getFile() + 2) && getPiece(from.getRank() + 1, from.getFile() + 2) == null
				|| (getPiece(from.getRank() + 1, from.getFile() + 2) != null
						&& getPiece(from.getRank() + 1, from.getFile() + 2).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 1, from.getFile() + 2));

		// ESE
		if (valid(from.getRank() - 1, from.getFile() + 2) && getPiece(from.getRank() - 1, from.getFile() + 2) == null
				|| (getPiece(from.getRank() - 1, from.getFile() + 2) != null
						&& getPiece(from.getRank() - 1, from.getFile() + 2).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 1, from.getFile() + 2));

		// SSE
		if (valid(from.getRank() - 2, from.getFile() + 1) && getPiece(from.getRank() - 2, from.getFile() + 1) == null
				|| (getPiece(from.getRank() - 2, from.getFile() + 1) != null
						&& getPiece(from.getRank() - 2, from.getFile() + 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 2, from.getFile() + 1));

		// SSW
		if (valid(from.getRank() - 2, from.getFile() - 1) && getPiece(from.getRank() - 2, from.getFile() - 1) == null
				|| (getPiece(from.getRank() - 2, from.getFile() - 1) != null
						&& getPiece(from.getRank() - 2, from.getFile() - 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 2, from.getFile() - 1));

		// WSW
		if (valid(from.getRank() - 1, from.getFile() - 2) && getPiece(from.getRank() - 1, from.getFile() - 2) == null
				|| (getPiece(from.getRank() - 1, from.getFile() - 2) != null
						&& getPiece(from.getRank() - 1, from.getFile() - 2).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() - 1, from.getFile() - 2));

		// WNW
		if (valid(from.getRank() + 1, from.getFile() - 2) && getPiece(from.getRank() + 1, from.getFile() - 2) == null
				|| (getPiece(from.getRank() + 1, from.getFile() - 2) != null
						&& getPiece(from.getRank() + 1, from.getFile() - 2).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 1, from.getFile() - 2));

		// NNW
		if (valid(from.getRank() + 2, from.getFile() - 1) && getPiece(from.getRank() + 2, from.getFile() - 1) == null
				|| (getPiece(from.getRank() + 2, from.getFile() - 1) != null
						&& getPiece(from.getRank() + 2, from.getFile() - 1).isWhite() != from.getPiece().isWhite()))
			moves.add(setMovement(from.getRank() + 2, from.getFile() - 1));

		return moves;

	}

}

package classic.piece;

import java.util.Vector;

import classic.board.BoardUtils;
import classic.board.Tile;

public class Pawn extends Piece {

	Vector<Tile> moves = new Vector<>();

	public Pawn(boolean white) {
		super(white);
	}

	@Override
	public String pieceName() {
		return "Pawn";
	}

	@Override
	public String pieceCode() {
		return "P";
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		Tile[][] board = BoardUtils.board;
		boolean white = board[from.getRank()][from.getFile()].isWhite();
		moves = new Vector<>();
		int sub = 0;
		sub = white ? -1 : 1;

		int currentPosition = from.getRank();
		int max = white ? 4 : 3;

		// System.out.println(max);
		// System.out.println(max - currentPosition);
		// System.out.println(Math.abs(max - currentPosition));

		boolean canMoveDouble = Math.abs(max - currentPosition) == 2 ? true : false;

		// System.out.println(canMoveDouble);
		if (valid(from.getRank() + sub, from.getFile()) && getPiece(from.getRank() + sub, from.getFile()) == null)
			moves.add(setMovement(from.getRank() + sub, from.getFile()));

		if (valid(from.getRank() + sub, from.getFile()) && white
				&& getPiece(from.getRank() - 1, from.getFile() - 1) != null
				&& !getPiece(from.getRank() - 1, from.getFile() - 1).isWhite())
			moves.add(setMovement(from.getRank() - 1, from.getFile() - 1));

		if (valid(from.getRank() + sub, from.getFile()) && white
				&& getPiece(from.getRank() - 1, from.getFile() + 1) != null
				&& !getPiece(from.getRank() - 1, from.getFile() + 1).isWhite())
			moves.add(setMovement(from.getRank() - 1, from.getFile() + 1));

		if (valid(from.getRank() + sub, from.getFile()) && !white
				&& getPiece(from.getRank() + 1, from.getFile() - 1) != null
				&& getPiece(from.getRank() + 1, from.getFile() - 1).isWhite())
			moves.add(setMovement(from.getRank() + 1, from.getFile() - 1));

		if (valid(from.getRank() + sub, from.getFile()) && !white
				&& getPiece(from.getRank() + 1, from.getFile() + 1) != null
				&& getPiece(from.getRank() + 1, from.getFile() + 1).isWhite())
			moves.add(setMovement(from.getRank() + 1, from.getFile() + 1));

		if (!canMoveDouble)
			return moves;

		if (valid(from.getRank() + sub, from.getFile()) && getPiece(from.getRank() + sub + sub, from.getFile()) == null)
			moves.add(setMovement(from.getRank() + sub + sub, from.getFile()));

		return moves;
	}

}

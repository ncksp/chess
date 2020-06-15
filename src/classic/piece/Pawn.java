package classic.piece;

import java.util.Vector;

import classic.board.Board;
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
		Board.notSafePosition.remove(moves);
		Tile[][] board = BoardUtils.board;
		boolean white = board[from.getRank()][from.getFile()].isWhite();
		moves = new Vector<>();
		int sub = 0;
		sub = white ? -1 : 1;

		int currentPosition = from.getRank();
		int max = white ? 4 : 3;

		boolean canMoveDouble = Math.abs(max - currentPosition) == 2 ? true : false;
		
		if (valid(from.getRank() + sub, from.getFile()) && getPiece(from.getRank() + sub, from.getFile()) == null)
			moves.add(setMovement(from.getRank() + sub, from.getFile(),from));

		if (valid(from.getRank() + sub, from.getFile()) && white
				&& getPiece(from.getRank() - 1, from.getFile() - 1) != null
				&& !getPiece(from.getRank() - 1, from.getFile() - 1).isWhite())
			moves.add(setMovement(from.getRank() - 1, from.getFile() - 1,from));

		if (valid(from.getRank() + sub, from.getFile()) && white
				&& getPiece(from.getRank() - 1, from.getFile() + 1) != null
				&& !getPiece(from.getRank() - 1, from.getFile() + 1).isWhite())
			moves.add(setMovement(from.getRank() - 1, from.getFile() + 1,from));

		if (valid(from.getRank() + sub, from.getFile()) && !white
				&& getPiece(from.getRank() + 1, from.getFile() - 1) != null
				&& getPiece(from.getRank() + 1, from.getFile() - 1).isWhite())
			moves.add(setMovement(from.getRank() + 1, from.getFile() - 1,from));

		if (valid(from.getRank() + sub, from.getFile()) && !white
				&& getPiece(from.getRank() + 1, from.getFile() + 1) != null
				&& getPiece(from.getRank() + 1, from.getFile() + 1).isWhite())
			moves.add(setMovement(from.getRank() + 1, from.getFile() + 1,from));

		if (!canMoveDouble)
			return moves;

		if (valid(from.getRank() + sub, from.getFile()) && getPiece(from.getRank() + sub + sub, from.getFile()) == null)
			moves.add(setMovement(from.getRank() + sub + sub, from.getFile(),from));

		return moves;
	}

}

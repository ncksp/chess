package classic.piece;

import java.util.Vector;

import classic.board.BoardUtils;
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
	public Vector<Tile> getMoves(Tile from) {
		Tile[][] board = BoardUtils.board;
		boolean white = board[from.getRank()][from.getFile()].isWhite();
		moves = new Vector<>();
		// up
		for (int i = from.getRank() - 1; i >= 0; i--) {
			try {
				if (board[i][from.getFile()].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
			moves.add(setMovement(i, from.getFile()));
		}

		// down
		for (int i = from.getRank() + 1; i < 8; i++) {
			try {
				if (board[i][from.getFile()].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}

			moves.add(setMovement(i, from.getFile()));
		}
		// left
		for (int i = from.getFile() - 1; i >= 0; i--) {
			try {
				if (board[from.getRank()][i].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}

			moves.add(setMovement(from.getRank(), i));
		}
		// right
		for (int i = from.getFile() + 1; i < 8; i++) {
			try {
				if (board[from.getRank()][i].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}

			moves.add(setMovement(from.getRank(), i));
		}

		// NE
		for (int i = from.getRank() - 1, j = from.getFile() + 1; i >= 0 && j < 8; i--, j++) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
			moves.add(setMovement(i, j));
		}

		// SE
		for (int i = from.getRank() + 1, j = from.getFile() + 1; i < 8 && j < 8; i++, j++) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
			moves.add(setMovement(i, j));
		}

		// SW
		for (int i = from.getRank() + 1, j = from.getFile() - 1; i < 8 && j >= 0; i++, j--) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
			moves.add(setMovement(i, j));
		}

		// NW
		for (int i = from.getRank() - 1, j = from.getFile() - 1; i >= 0 && j >= 0; i--, j--) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
				moves.add(setMovement(i, j));
			}
		}
		return moves;
	}

}

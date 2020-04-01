package classic.piece;

import java.util.Vector;

import classic.board.BoardUtils;
import classic.board.Tile;

public class Rook extends Piece {

	Vector<Tile> moves = new Vector<>();

	public Rook(boolean white) {
		super(white);
	}

	@Override
	public String pieceName() {
		return "Rook";
	}

	@Override
	public String pieceCode() {
		return "R";
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
		
	
		return moves;
	}

}

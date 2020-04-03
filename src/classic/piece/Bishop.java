package classic.piece;

import java.util.Vector;

import classic.board.Board;
import classic.board.BoardUtils;
import classic.board.Tile;

public class Bishop extends Piece {

	Vector<Tile> moves = new Vector<>();

	public Bishop(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "Bishop";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "B";
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		// TODO Auto-generated method stub
		Board.notSafePosition.remove(moves);
		Tile[][] board = BoardUtils.board;
		boolean white = board[from.getRank()][from.getFile()].isWhite();
		moves = new Vector<>();
		// NE
		for (int i = from.getRank() - 1, j = from.getFile() + 1; i >= 0 && j < 8; i--, j++) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
//			System.out.println("NE");
//			System.out.println(j + "-" + i);
			moves.add(setMovement(i, j,from));
		}

		// SE
		for (int i = from.getRank() + 1, j = from.getFile() + 1; i < 8 && j < 8; i++, j++) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
//			System.out.println("SE");
//			System.out.println(j + "-" + i);
			moves.add(setMovement(i, j,from));
		}

		// SW
		for (int i = from.getRank() + 1, j = from.getFile() - 1; i < 8 && j >= 0; i++, j--) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
//			System.out.println("SW");
//			System.out.println(j + "-" + i);
			moves.add(setMovement(i, j,from));
		}

		// NW
		for (int i = from.getRank() - 1, j = from.getFile() - 1; i >= 0 && j >= 0; i--, j--) {
			try {
				if (board[i][j].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
//			System.out.println("NW");
//			System.out.println(j + "-" + i);
			moves.add(setMovement(i, j,from));
		}
		return moves;
	}

}
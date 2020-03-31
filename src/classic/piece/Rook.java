package classic.piece;

import java.util.Vector;

import classic.board.BoardUtils;
import classic.board.Tile;

public class Rook extends Piece {

	public Rook(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String pieceName() {
		// TODO Auto-generated method stub
		return "Rook";
	}

	@Override
	public String pieceCode() {
		// TODO Auto-generated method stub
		return "R";
	}

	@Override
	public Tile setMovement(int rank, int file) {
		return new Tile(BoardUtils.board[rank][file].getPiece(), rank, file);
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		Tile[][] board = BoardUtils.board;
		Vector<Tile> moves = new Vector<>();
		boolean white = board[from.getRank()][from.getFile()].isWhite();

		// up
		for (int i = 7; i >= 0; i--) {

			if (from.getRank() < 1)
				break;

			if (!isValid(i, from.getFile()))
				break;
			try {
				if (i != from.getRank() && board[i][from.getFile()].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
			System.out.println("a");
			moves.add(setMovement(i, from.getFile()));
		}

		// down
		for (int i = 0; i < 8; i++) {
			if (from.getRank() >= 7)
				break;

			if (!isValid(i, from.getFile()))
				break;
			try {
				if (i != from.getRank() && board[i][from.getFile()].isWhite() == white) {
					break;
				}
			} catch (Exception e) {
			}
			System.out.println("b");
			moves.add(setMovement(i, from.getFile()));
		}
		// // left
		for (int i = 7; i >= 0; i--) {
			if (from.getFile() < 1)
				break;

			if (!isValid(from.getRank(), i))
				break;
			try {
				if (i != from.getFile() && board[from.getRank()][i].isWhite() == white) {
					System.out.println("D");
					break;
				}
			} catch (Exception e) {
			}
			System.out.println("c");
			moves.add(setMovement(from.getRank(), i));
		}
		// // right
		for (int i = 0; i < 8; i++) {
			if (from.getFile() >= 7)
				break;

			if (!isValid(from.getRank(), i))
				break;
			try {
				if (i != from.getFile() && board[from.getRank()][i].isWhite() == white) {
					System.out.println("D");
					break;
				}
			} catch (Exception e) {
			}
			System.out.println("c");
			moves.add(setMovement(from.getRank(), i));
		}
		return moves;
	}

	@Override
	public boolean canMove(Tile from, Tile to) {
		// TODO Auto-generated method stub
		if (!isValid(from, to))
			return false;

		Vector<Tile> moves = getMoves(from);
		if (moves.size() < 1)
			return false;
		System.out.println(moves);
		for (Tile tile : moves) {
			System.out.println(tile.getFile() + "-" + tile.getRank() + "{" + to.getFile() + "-" + to.getRank() + "}");
		}
		Tile tile = moves.stream().filter(f -> f.getFile() == to.getFile() && f.getRank() == to.getRank()).findAny()
				.orElse(null);

		if (tile != null)
			return true;

		return false;
	}

}

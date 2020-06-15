package classic.piece;

import java.util.Vector;

import classic.board.Board;
import classic.board.BoardUtils;
import classic.board.Tile;
import classic.player.Player;

public abstract class Piece {
	private boolean killed = false;
	private boolean white = false;

	public Piece(boolean white) {
		this.white = white;
	}

	public boolean isWhite() {
		return this.white;
	}

	public boolean isKilled() {
		return this.killed == true;
	}

	public void setKilled(boolean killed) {
		this.killed = killed;
	}

	public boolean isValid(Tile from, Tile to) {
		try {
			if (from.getPiece().isWhite() == to.getPiece().isWhite())
				return false;
		} catch (Exception e) {
		}

		return true;
	}

	public Piece getPiece(int rank, int file) {
		if (rank < 0 || rank > 7 || file < 0 || file > 7)
			return null;

		return BoardUtils.board[rank][file].getPiece();
	}

	public boolean valid(int rank, int file) {
		if (rank < 0 || rank > 7 || file < 0 || file > 7)
			return false;
		return true;
	}

	public Tile setMovement(int rank, int file, Tile from) {
		return new Tile(from.getPiece(), rank, file);
	}

	public boolean canMove(Player player, Tile from, Tile to) {
		if (!isValid(from, to)) {
			return false;
		}
		System.out.println("A");

		Vector<Tile> moves = getMoves(from);
		if (from.getPiece().getClass() == King.class
				&& Math.abs(from.getFile() - to.getFile()) == 2) {
			return isCanToCastling(player, from, to);
		}
		System.out.println("B");

		if (moves.size() < 1) {
			return false;
		}
		System.out.println("C");


		for (Tile tile : moves) {
			// try {
			// System.out.println(tile.getFile() + "-" + tile.getRank() + "{" +
			// to.getFile() + "-" + to.getRank() + "}"
			// + tile.getPiece().isWhite() + "-" + to.getPiece().isWhite());
			//
			// } catch (Exception e) {
			// System.out.println(
			// tile.getFile() + "-" + tile.getRank() + "{" + to.getFile() + "-"
			// + to.getRank() + "} null");
			// }
			// kalo piece kosong dan data sama
			if (tile.getFile() == to.getFile() && tile.getRank() == to.getRank() 
					&& (to.getPiece() == null || to.getPiece() != null)) {
				// System.out.println("a");
				return true;
			}

			// kalo piece gak kosong, lalu yang di cari beda warna sama yang
			// ada,
			// dan kalo file dan rank salah satu beda
			if (tile.getPiece() != null && tile.getPiece().isWhite() != from.getPiece().isWhite()
					&& (tile.getFile() != to.getFile() || tile.getRank() != to.getRank())) {
				// System.out.println("b");
				continue;
			}

			// kalo salah satu nya kosong
			if (tile.getPiece() == null || to.getPiece() == null)
				continue;

			if ((tile.getFile() != to.getFile() || tile.getRank() != to.getRank())
					&& tile.getPiece().isWhite() == to.getPiece().isWhite()) {
				// System.out.println("c");
				return false;

			} else if (tile.getFile() == to.getFile() && tile.getRank() == to.getRank()
					&& tile.getPiece().isWhite() == to.getPiece().isWhite()) {
				// System.out.println("d");
				return true;
			}

		}
		return false;
	}

	public Vector<Tile> getNextMoves(Tile to) {
		Vector<Tile> tempNotSafePosition = new Vector<>();

		tempNotSafePosition.addAll(Board.notSafePosition);
		for (Tile tile : tempNotSafePosition) {
			if (tile.getPiece().getClass() == this.getClass() && tile.getPiece().isWhite() == to.isWhite())
				Board.notSafePosition.removeElement(tile);

			if (tile.getPiece().isKilled())
				Board.notSafePosition.removeElement(tile);
		}

		tempNotSafePosition.clear();
		Board.notSafePosition.addAll(getMoves(to));
//		System.out.println("----------------------");
		return null;
	}

	public boolean isCanToCastling(Player player, Tile from, Tile to) {
		if (from.getRank() != 7 && from.getRank() != 0) {
			return false;
		}
		if (player.isCastling()) {
			return false;
		}

		return from.getFile() - to.getFile() > 0 ? queenCastling(player, from, to) : kingCastling(player, from, to);
	}

	private boolean kingCastling(Player player, Tile from, Tile to) {
		for (int i = from.getFile() + 1; i < 7; i++) {
			if (BoardUtils.board[from.getRank()][i].getPiece() != null)
				return false;
		}

		player.setCastling(true);
		BoardUtils.board[from.getRank()][5].setPiece(BoardUtils.board[from.getRank()][7].getPiece());
		BoardUtils.board[from.getRank()][7].setPiece(null);
		return true;
	}

	private boolean queenCastling(Player player, Tile from, Tile to) {
		for (int i = from.getFile() - 1; i > 0; i--) {
			if (BoardUtils.board[from.getRank()][i].getPiece() != null)
				return false;
		}

		player.setCastling(true);
		BoardUtils.board[from.getRank()][3].setPiece(BoardUtils.board[from.getRank()][0].getPiece());
		BoardUtils.board[from.getRank()][0].setPiece(null);
		return true;
	}

	public abstract String pieceName();

	public abstract String pieceCode();

	public abstract Vector<Tile> getMoves(Tile from);

}

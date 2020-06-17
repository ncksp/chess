package classic.piece;

import java.util.Vector;

import classic.board.Board;
import classic.board.BoardUtils;
import classic.board.Move;
import classic.board.Tile;
import classic.player.Castling;
import classic.player.Player;

public abstract class Piece extends Move {
	private boolean killed = false;
	private boolean white = false;
	private int everMove = 0;
	public Piece(boolean white) {
		this.white = white;
	}
	public void move(){
		this.everMove++;
	}
	public int getEverMove(){
		return this.everMove;
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

	public Piece getPiece(int rank, int file) {
		if (rank < 0 || rank > 7 || file < 0 || file > 7)
			return null;

		return BoardUtils.board[rank][file].getPiece();
	}

	public Tile setMovement(int rank, int file, Tile from) {
		return new Tile(from.getPiece(), rank, file);
	}

	public boolean canMove(Player player, Tile from, Tile to) {
		if (!isValid(from, to)) 
			return false;
		
		Vector<Tile> moves = getMoves(from, to);
		if (from.getPiece().getClass() == King.class
				&& Math.abs(from.getFile() - to.getFile()) == 2) 
			return Castling.isCanToCastling(player, from, to);

		if (moves.size() < 1) 
			return false;

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
				move();
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
				 System.out.println("c");
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
		Board.notSafePosition.addAll(getMoves(to, null));
		return null;
	}



	public abstract String pieceName();

	public abstract String pieceCode();

	public abstract Vector<Tile> getMoves(Tile from, Tile to);

}

package classic.board;

import classic.piece.Piece;
import classic.player.Player;

public class Move {
	private Player player;
	private Tile start;
	private Tile end;
	private Piece pieceMoved;
	private Piece pieceKilled;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Tile getStart() {
		return start;
	}

	public void setStart(Tile start) {
		this.start = start;
	}

	public Tile getEnd() {
		return end;
	}

	public void setEnd(Tile end) {
		this.end = end;
	}

	public Piece getPieceMoved() {
		return pieceMoved;
	}

	public void setPieceMoved(Piece pieceMoved) {
		this.pieceMoved = pieceMoved;
	}

	public Piece getPieceKilled() {
		return pieceKilled;
	}

	public void setPieceKilled(Piece pieceKilled) {
		this.pieceKilled = pieceKilled;
	}

}

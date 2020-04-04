package classic.board;

import classic.piece.Piece;
import classic.player.Player;

public class Movement {
	private Player player;
	private Tile from;
	private Tile to;
	private Piece pieceKilled;
	private static Movement movement = new Movement();

	private Movement() {

	}

	public static Movement getInstance() {
		return movement;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Tile getFrom() {
		return from;
	}

	public void setFrom(Tile from) {
		this.from = from;
	}

	public Tile getTo() {
		return to;
	}

	public void setTo(Tile to) {
		this.to = to;
	}

	public Piece getPieceKilled() {
		return pieceKilled;
	}

	public void setPieceKilled(Piece pieceKilled) {
		this.pieceKilled = pieceKilled;
	}
	
	public Piece getStartPiece(){
		return this.getFrom().getPiece();
	}
	
	public Piece getEndPiece(){
		return this.getTo().getPiece();
	}

	public boolean canMove() {

		return this.getFrom().getPiece().canMove(this.getPlayer(), this.getFrom(), this.getTo());
	}

	public boolean fromIsWhite() {
		return this.getFrom().getPiece().isWhite();
	}

	public boolean toIsWhite() {
		return this.getTo().getPiece().isWhite();
	}

	public int fromRank() {
		return this.getFrom().getRank();
	}

	public int toRank() {
		return this.getTo().getRank();
	}

	public int fromFile() {
		return this.getFrom().getFile();
	}

	public int toFile() {
		return this.getTo().getFile();
	}
	
	public void getNextMoves(){
		this.getTo().getPiece().getNextMoves(this.to);
	}
	

}

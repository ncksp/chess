package classic.board;

import classic.piece.Piece;

public class Tile {
	private Piece piece;
	private int rank;
	private int file;
	
	public Tile(Piece piece, int rank, int file) {
		super();
		this.piece = piece;
		this.rank = rank;
		this.file = file;
	}
	
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	
	public String getCode(){
		return this.getPiece().pieceCode();
	}
	
	public boolean isWhite(){
		return this.getPiece().isWhite();
	}
	
}

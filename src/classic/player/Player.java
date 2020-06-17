package classic.player;

import java.util.Vector;

import classic.board.Board;
import classic.board.Tile;

public class Player {
	protected boolean whiteSide;
	private boolean check;
	private boolean castling = false;
	private boolean stalemate = false;
	protected Tile king;
	public boolean isWhiteSide() {
		return this.whiteSide;
	}
	
	public Tile getKing() {
		return king;
	}
	public void staleMate(){
		this.stalemate = true;
	}
	
	public boolean isStalemate(){
		return this.stalemate;
	}
	public void setKing(Tile king) {
		this.king = king;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean isCheck) {
		this.check = isCheck;
	}

	public boolean isCastling() {
		return castling;
	}

	public void setCastling(boolean isCastling) {
		this.castling = isCastling;
	}
	
	public void definePlayerCheck() {
		for (Tile tile : Board.notSafePosition) {
			if (tile.getFile() == this.getKing().getFile() && tile.getRank() == this.getKing().getRank()
					&& tile.isWhite() != this.isWhiteSide())
				check=true;
			break;

		}
		check = false;
	}
	
	private boolean isExistSafePosition(Vector<Tile> kingNextMoves, Player player){
		for (Tile tile : kingNextMoves) {
			for (Tile notSafe : Board.notSafePosition) {
				if (notSafe.getFile() != tile.getFile() && notSafe.getRank() != tile.getRank() 
						&& notSafe.isWhite() != this.isWhiteSide())
					return true;
			}
		}
		return false;
	}
	
	public boolean isStalematePosition(){
		Vector<Tile> kingNextMoves = new Vector<>();
		kingNextMoves = this.king.getPiece().getMoves(this.getKing(), null);
		
		return isExistSafePosition(kingNextMoves, this);
	}
	
	public boolean isPlayerCheckMate(){
		Vector<Tile> kingNextMoves = new Vector<>();
		
		kingNextMoves = this.king.getPiece().getMoves(this.getKing(), null);
		
		if(!isExistSafePosition(kingNextMoves, this)) return true;
		
		return false;
		
	}
}

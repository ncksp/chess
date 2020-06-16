package classic.player;

import java.util.Vector;

import classic.board.Board;
import classic.board.Tile;

public class Player {
	protected boolean whiteSide;
	protected boolean isCheck;
	protected boolean isCastling = false;
	protected Tile king;
	public boolean isWhiteSide() {
		return this.whiteSide;
	}

	public Tile getKing() {
		return king;
	}

	public void setKing(Tile king) {
		this.king = king;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public boolean isCastling() {
		return isCastling;
	}

	public void setCastling(boolean isCastling) {
		this.isCastling = isCastling;
	}
	
	public void definePlayerCheck() {
		for (Tile tile : Board.notSafePosition) {
			if (tile.getFile() == this.getKing().getFile() && tile.getRank() == this.getKing().getRank()
					&& tile.isWhite() != this.isWhiteSide())
				isCheck=true;
			break;

		}
		isCheck = false;
	}
	
	public boolean isPlayerCheckMate(){
		Vector<Tile> getKingNextMoves = new Vector<>();
		
		getKingNextMoves = this.king.getPiece().getMoves(this.getKing(), null);
		
		for (Tile tile : getKingNextMoves) {
			for (Tile notSafe : Board.notSafePosition) {
				if (notSafe.getFile() != tile.getFile() && notSafe.getRank() != tile.getRank() 
						&& tile.isWhite() != this.isWhiteSide())
					return false;
			}
		}
		return true;
	}
}

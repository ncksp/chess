package classic.player;

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
}

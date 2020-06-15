package classic.player;

import classic.board.Tile;
import classic.piece.King;

public class WhitePlayer extends Player {
	public WhitePlayer() {
		this.whiteSide = true; 
		this.king = new Tile(new King(true), 7, 4);
	}
}

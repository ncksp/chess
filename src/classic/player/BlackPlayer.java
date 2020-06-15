package classic.player;

import classic.board.Tile;
import classic.piece.King;

public class BlackPlayer extends Player {
	public BlackPlayer() {
		this.whiteSide = false;
		this.king = new Tile(new King(false), 0, 4);
	}
}

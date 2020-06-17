package classic.board;

import classic.piece.Piece;
import classic.player.Player;
public class Move {

	public Move() {
		// TODO Auto-generated constructor stub
	}

	public boolean isValid(Tile from, Tile to) {
		try {
			if (from.getPiece().isWhite() == to.getPiece().isWhite())
				return false;
		} catch (Exception e) {
		}

		return true;
	}
	
	public boolean valid(int rank, int file) {
		if (rank < 0 || rank > 7 || file < 0 || file > 7)
			return false;
		return true;
	}


	protected boolean isLegalMove(Movement movement, Player player){
		Piece startPiece = movement.getStartPiece();	
		if (startPiece == null)
			return false;
		if (movement.fromIsWhite() != player.isWhiteSide())
			return false;

		if (!movement.canMove(player)) 
			return false;	
		
		return true;
	}

}

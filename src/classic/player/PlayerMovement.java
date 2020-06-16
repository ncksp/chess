package classic.player;

import classic.board.Board;
import classic.board.BoardUtils;
import classic.board.Movement;
import classic.board.Tile;
import classic.piece.King;
import classic.piece.Pawn;
import classic.piece.Piece;

public class PlayerMovement {
	protected boolean isKingInSavePosition(Movement movement, Player player) {
		if (movement.getStartPiece().getClass() == King.class && !isKingMovementSafePosition(movement, player)){
			System.out.println("King must always in safe position");
			return false;
		}
		return true;
	}
	
	private boolean isKingMovementSafePosition(Movement movement, Player player) {
		int file = movement.toFile();
		int rank = movement.toRank();
		
		Tile result = Board.notSafePosition.stream()
				.filter(e -> e.getFile() == file && e.getRank() == rank && e.isWhite() != player.isWhiteSide())
				.findAny()
				.orElse(null);
		
		if(result != null)
			return false;
		
		return true;
	}

	protected Piece getEnPassantMove(Movement movement, Player player) {
		if(movement.getEndPiece() instanceof Pawn &&  !((Pawn) movement.getEndPiece()).isEnPassant()){
			return null;
		}
		int sub = player.isWhiteSide() ? 1 : -1; 
		int rank = movement.getTo().getRank() +sub;
		int file = movement.getTo().getFile();
		Piece enPassPiece = BoardUtils.board[rank][file].getPiece();
		

		BoardUtils.board[rank][file] = new Tile(null,rank , file);
		return enPassPiece;
	}
	
	protected boolean playerCheckPositionMove(Movement movement, Player player) {
		if (movement.getStartPiece().getClass() != King.class) {
			return false;
		}
		return true;
	}

}

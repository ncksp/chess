package classic.board;

import java.util.Vector;

import classic.piece.King;
import classic.piece.Piece;
import classic.player.BlackPlayer;
import classic.player.Player;
import classic.player.PlayerMovement;
import classic.player.WhitePlayer;
import main.Main;
import utilities.MainUtilities;

public class Board extends PlayerMovement {
	private WhitePlayer white = new WhitePlayer();
	private BlackPlayer black = new BlackPlayer();
	private Vector<Movement> movesPlayer = new Vector<>();
	private BoardUtils util = new BoardUtils();
	public static Vector<Tile> notSafePosition = new Vector<>();
	
	public int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			checkMove(white, type, "white move: ");
		else
			checkMove(black, type, "black move: ");

		return move == 2 ? 1 : move + 1;
	}
	
	private void checkMove(Player player, int type, String text) {
		player.definePlayerCheck();
		if (player.isCheck()) {
			System.out.println("You're in check, move your King");
		}

		move(text, type, player);
	}

	private void move(String text, int type, Player player) {
		Movement movement = Movement.getInstance();
		String notation;
		boolean loop = true;
		do {
			loop = false;
			System.out.print(text);

			notation = MainUtilities.scan.nextLine();

			movement = util.convertCoordinate(notation, type);
			if (movement == null) {
				util.invalidFormatMovement();
				continue;
			} 
			
			if (!doMove(movement, player)) {
				System.out.println("Invalid Move");
				loop = true;
			}
		} while (movement == null || loop);

	}

	private boolean doMove(Movement movement, Player player) {
		Piece startPiece = movement.getStartPiece();
		if(!isLegalMove(movement, player))
			return false;
		
		movement.setPlayer(player);
		
		if(!isKingInSavePosition(movement, player))
			return false;
		
		movesPlayer.add(movement);

		if (movement.getStartPiece().getClass() == King.class)
			player.setKing(movement.getTo());

		movement.getTo().setPiece(startPiece);
		movement.getFrom().setPiece(null);

		movement.getNextMoves();
		
		Piece destPiece = movement.getEndPiece();
		if (destPiece != null) {
			destPiece.setKilled(true);
			movement.setPieceKilled(destPiece);
		}
		
		if (destPiece != null && destPiece.getClass() == King.class) {
			Main.win = destPiece.isWhite() ? 1 : 0;
		}
		
		Piece enPassPiece = getEnPassantMove(movement,player);
		if (enPassPiece != null) {
			enPassPiece.setKilled(true);
			movement.setPieceKilled(enPassPiece);
		}
		
		return true;
	}
	

	private boolean isLegalMove(Movement movement, Player player){
		Piece startPiece = movement.getStartPiece();

		if (player.isCheck() && !playerCheckPositionMove(movement, player))
			return false;
		
		if (startPiece == null)
			return false;

		if (movement.fromIsWhite() != player.isWhiteSide())
			return false;

		if (!movement.canMove()) 
			return false;	
		
		return true;
	}


	

}

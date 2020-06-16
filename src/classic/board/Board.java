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
	public static class Move{
		private int type;
		private String text;
		private static Move move;
		private Move() {}
		public static Move getInstance(int type, String text){
			if(move == null)
				move = new Move();
			
			move.type = type;
			move.text = text;
			return move;
		}
		public int getType() {
			return type;
		}
		public String getText() {
			return text;
		}
	}
	private WhitePlayer white = new WhitePlayer();
	private BlackPlayer black = new BlackPlayer();
	private Vector<Movement> movesPlayer = new Vector<>();
	private BoardUtils util = new BoardUtils();
	public static Vector<Tile> notSafePosition = new Vector<>();
	
	public int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			checkMove(white, Move.getInstance(type,"white move: " ));
		else
			checkMove(black, Move.getInstance(type,"black move: " ));

		return move == 2 ? 1 : move + 1;
	}
	
	private void checkMove(Player player, Move currentMove) {
		player.definePlayerCheck();
		if (player.isCheck()) {
			System.out.println("You're in check, move your King");
		}

		move(currentMove, player);
	}

	private void move(Move currentMove, Player player) {
		Movement movement = Movement.getInstance();
		String notation;
		boolean loop = true;
		do {
			loop = false;
			System.out.print(currentMove.getText());
			System.out.println(player.isWhiteSide());
			notation = MainUtilities.scan.nextLine();

			movement = util.convertCoordinate(notation, currentMove.getType());
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
		
		setKilledPiece(movement);
		
		setEnPassantKilled(movement,player);
		

		if(movement.getPromotionPiece() != 0)		
			promotionPawn(movement);
		
		return true;
	}
	

	private void setEnPassantKilled(Movement movement, Player player) {
		Piece enPassPiece = getEnPassantMove(movement,player);
		if (enPassPiece != null) {
			enPassPiece.setKilled(true);
			movement.setPieceKilled(enPassPiece);
		}
	}

	private void setKilledPiece(Movement movement) {
		Piece destPiece = movement.getEndPiece();
		if (destPiece != null) {
			destPiece.setKilled(true);
			movement.setPieceKilled(destPiece);
		}
		
		if (destPiece != null && destPiece.getClass() == King.class && destPiece.isWhite() != movement.getEndPiece().isWhite()) {
			Main.win = destPiece.isWhite() ? 1 : 0;
		}
	}

	private boolean isLegalMove(Movement movement, Player player){
		Piece startPiece = movement.getStartPiece();

		if (player.isCheck() && !playerCheckPositionMove(movement, player))
			return false;
		
		if (startPiece == null)
			return false;

		if (movement.fromIsWhite() != player.isWhiteSide())
			return false;

		if (!movement.canMove(player)) 
			return false;	
		
		return true;
	}


	private void promotionPawn(Movement movement){

		BoardUtils.board[movement.toRank()][movement.toFile()] = pawnPromotion(movement);
		
		movement.setPromotionPiece('\0');
	}
	

}

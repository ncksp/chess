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

public class Board extends Move {
	public static class PlayerMove{
		private int type;
		private String text;
		private static PlayerMove move;
		private PlayerMove() {}
		public static PlayerMove getInstance(int type, String text){
			if(move == null)
				move = new PlayerMove();
			
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
	private PlayerMovement playerMovement = new PlayerMovement();
	public static Vector<Tile> notSafePosition = new Vector<>();
	
	public int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			playerMove(white, PlayerMove.getInstance(type,"white move: " ));
		else
			playerMove(black, PlayerMove.getInstance(type,"black move: " ));

		return move == 2 ? 1 : move + 1;
	}
	
	public void playerMove(Player player, PlayerMove currentMove) {
		
			
		boolean checkMate = player.isPlayerCheckMate();
		player.definePlayerCheck();
		if(player.isCheck() && checkMate){
			System.out.println("You're in checkmate, You Lose");
			Main.win = player.isWhiteSide() ? 0 : 1;
			return;
		}
		if (player.isCheck()) {
			System.out.println("You're in check, move your King");
		}

		movePrompt(currentMove, player);
		
		if(isStalematePosition(player))
			return;
	}
	
	private void movePrompt(PlayerMove currentMove, Player player) {
		Movement movement = Movement.getInstance();
		String notation;
		boolean loop = true;
		do {
			loop = false;
			System.out.print(currentMove.getText());
			notation = MainUtilities.scan.nextLine();
			if(notation.equalsIgnoreCase("exit")){
				Main.win = -2;
				return;
			}
			movement = util.convertCoordinate(notation, currentMove.getType());
			if (movement == null) {
				util.invalidFormatMovement();
				continue;
			} 
			
			if (!movingTile(movement, player)) {
				System.out.println("Invalid Move");
				loop = true;
			}
		} while (movement == null || loop);

	}

	private void setEnPassantKilled(Movement movement, Player player) {
		Piece enPassPiece = playerMovement.getEnPassantMove(movement,player);
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

	private boolean movingTile(Movement movement, Player player) {
		Piece startPiece = movement.getStartPiece();
		if (player.isCheck() && !playerMovement.playerCheckPositionMove(movement, player))
			return false;	
		
		if(!isLegalMove(movement, player))
			return false;
		
		movement.setPlayer(player);
		
		if(!playerMovement.isKingInSavePosition(movement, player))
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

	private void promotionPawn(Movement movement){

		BoardUtils.board[movement.toRank()][movement.toFile()] = playerMovement.pawnPromotion(movement);
		
		movement.setPromotionPiece('\0');
	}

	private boolean isStalematePosition(Player player){
		if(!player.isStalematePosition())
			return false;
		
		Player opponent = player instanceof WhitePlayer ? black : white;
		if(!opponent.isStalematePosition())				
			return false;
		
		System.out.println("Stalemate position, Draw");
		Main.win = 2;
		return true;
	}
}

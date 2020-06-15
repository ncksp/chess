package classic.board;

import java.util.Vector;

import classic.piece.King;
import classic.piece.Pawn;
import classic.piece.Piece;
import classic.player.BlackPlayer;
import classic.player.Player;
import classic.player.WhitePlayer;
import main.Main;
import utilities.MainUtilities;

public class Board {
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
			
			if (!makeMove(movement, player)) {
				System.out.println("Invalid Move");
				loop = true;
			}
		} while (movement == null || loop);

	}

	private boolean makeMove(Movement movement, Player player) {
		Piece startPiece = movement.getStartPiece();
		if(!isLegalMove(movement, player))
			return false;
		
		movement.setPlayer(player);

		if (movement.getStartPiece().getClass() == King.class && !isMovementSafePosition(movement, player)){
			System.out.println("King must always in safe position");
			return false;
		}
		
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
	
	private Piece getEnPassantMove(Movement movement, Player player) {
		if(!((Pawn) movement.getEndPiece()).isEnPassant()){
			return null;
		}
		int sub = player.isWhiteSide() ? 1 : -1; 
		int rank = movement.getTo().getRank() +sub;
		int file = movement.getTo().getFile();
		Piece enPassPiece = BoardUtils.board[rank][file].getPiece();
		

		BoardUtils.board[rank][file] = new Tile(null,rank , file);
		return enPassPiece;
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
	
	private boolean isMovementSafePosition(Movement movement, Player player) {
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

	private boolean playerCheckPositionMove(Movement movement, Player player) {
		if (movement.getStartPiece().getClass() != King.class) {
			return false;
		}
		return true;
	}

}

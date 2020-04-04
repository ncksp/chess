package classic.board;

import java.util.Vector;

import classic.piece.King;
import classic.piece.Piece;
import classic.player.BlackPlayer;
import classic.player.Player;
import classic.player.WhitePlayer;
import main.Main;
import utilities.Utilities;

public class Board {
	public static WhitePlayer white = new WhitePlayer();
	public static BlackPlayer black = new BlackPlayer();
	public static Vector<Movement> movesPlayer = new Vector<>();
	public static Vector<Tile> notSafePosition = new Vector<>();

	public static void invalidFormat() {
		System.out.println("invalid move: expected format [A..H][1..8]-[A..H][1..8]");
		Utilities.scan.nextLine();
	}

	public static int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			checkMove(white, type, "white move: ");
		else
			checkMove(black, type, "black move: ");

		return move == 2 ? 1 : move + 1;
	}

	public static boolean isPlayerCheck(Player player) {
		for (Tile tile : notSafePosition) {

			if (tile.getFile() == player.getKing().getFile() && tile.getRank() == player.getKing().getRank()
					&& tile.isWhite() != player.isWhiteSide())
				return true;

		}
		return false;
	}

	public static void checkMove(Player player, int type, String text) {

		player.setCheck(isPlayerCheck(player));

		if (player.isCheck()) {
			System.out.println("You're in check, move your King");
		}

		move(text, type, player);
	}

	public static void move(String text, int type, Player player) {
		Movement movement = Movement.getInstance();
		String notation;
		boolean loop = true;
		do {
			loop = false;
			System.out.print(text);

			notation = Utilities.scan.nextLine();

			movement = BoardUtils.convertCoordinate(notation, type);
			if (movement == null) {
				invalidFormat();
			} else if (!makeMove(movement, player)) {
				System.out.println("Invalid Move");
				loop = true;
			}
		} while (movement == null || loop);

	}

	public static boolean makeMove(Movement movement, Player player) {
		Piece startPiece = movement.getStartPiece();

		if (player.isCheck() && !playerCheckPositionMove(movement, player)) {
			return false;
		}
		if (startPiece == null)
			return false;

		if (movement.fromIsWhite() != player.isWhiteSide())
			return false;

		if (!movement.canMove()) {
			return false;
		}

		movement.setPlayer(player);

		if (movement.getStartPiece().getClass() == King.class && !isMovementSafePosition(movement, player)){
			System.out.println("King must always in safe position");
			return false;
		}
		Piece destPiece = movement.getEndPiece();
		if (destPiece != null) {
			destPiece.setKilled(true);
			movement.setPieceKilled(destPiece);
		}

		if (destPiece != null && destPiece.getClass() == King.class) {
			Main.win = destPiece.isWhite() ? 1 : 0;
		}

		movesPlayer.add(movement);

		if (movement.getStartPiece().getClass() == King.class)
			player.setKing(movement.getTo());

		movement.getTo().setPiece(startPiece);
		movement.getFrom().setPiece(null);

		movement.getNextMoves();

		return true;
	}

	private static boolean isMovementSafePosition(Movement movement, Player player) {
		// TODO Auto-generated method stub
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

	private static boolean playerCheckPositionMove(Movement movement, Player player) {
		// TODO Auto-generated method stub

		if (movement.getStartPiece().getClass() != King.class) {
			return false;
		}
		return true;
	}

}

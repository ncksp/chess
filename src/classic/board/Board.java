package classic.board;

import java.util.Vector;

import classic.piece.Piece;
import classic.player.BlackPlayer;
import classic.player.Player;
import classic.player.WhitePlayer;
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
			move("white move: ", type, white);
		else
			move("black move: ", type, black);

		return move == 2 ? 1 : move + 1;
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

		if (startPiece == null)
			return false;

		if (movement.fromIsWhite() != player.isWhiteSide())
			return false;

		if (!movement.canMove())
			return false;

		movement.setPlayer(player);

		Piece destPiece = movement.getEndPiece();
		if (destPiece != null) {
			destPiece.setKilled(true);
			movement.setPieceKilled(destPiece);
		}
		
		movesPlayer.add(movement);

		movement.getTo().setPiece(startPiece);
		movement.getFrom().setPiece(null);

		movement.getNextMoves();
		System.out.println(notSafePosition.size());
		for (Tile tile : notSafePosition) {
			System.out.println(
					"Rank : " + tile.getRank() + "| File : " + tile.getFile() + "--" + tile.getPiece().isWhite());
		}
		
		return true;
	}
}

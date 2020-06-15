package classic.board;

import java.util.regex.Pattern;

import classic.piece.Bishop;
import classic.piece.King;
import classic.piece.Knight;
import classic.piece.Pawn;
import classic.piece.Queen;
import classic.piece.Rook;
import utilities.MainUtilities;

public class BoardUtils {
	public static Tile[][] board = new Tile[8][8];
	final static int FILE = 8;
	final static int RANK = 8;
	final static String matcherNotation = "[A-H][1-8]";
	
	public void invalidFormatMovement() {
		System.out.println("invalid move: expected format [A..H][1..8]-[A..H][1..8]");
		MainUtilities.scan.nextLine();
	}
	
	public void createBoard() {

		board[0][0] = new Tile(new Rook(false), 0, 0);
		board[0][1] = new Tile(new Knight(false), 0, 1);
		board[0][2] = new Tile(new Bishop(false), 0, 2);
		board[0][3] = new Tile(new Queen(false), 0, 3);
		board[0][4] = new Tile(new King(false), 0, 4);
		board[0][5] = new Tile(new Bishop(false), 0, 5);
		board[0][6] = new Tile(new Knight(false), 0, 6);
		board[0][7] = new Tile(new Rook(false), 0, 7);

		board[7][0] = new Tile(new Rook(true), 7, 0);
		board[7][1] = new Tile(new Knight(true), 7, 1);
		board[7][2] = new Tile(new Bishop(true), 7, 2);
		board[7][3] = new Tile(new Queen(true), 7, 3);
		board[7][4] = new Tile(new King(true), 7, 4);
		board[7][5] = new Tile(new Bishop(true), 7, 5);
		board[7][6] = new Tile(new Knight(true), 7, 6);
		board[7][7] = new Tile(new Rook(true), 7, 7);

		for (int i = 0; i < 8; ++i) {
			board[1][i] = new Tile(new Pawn(false), 1, i);
			board[6][i] = new Tile(new Pawn(true), 6, i);
		}

		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Tile(null, i, j);
			}
		}
	}

	private String getCode(int rank, int file) {

		String str = "-";
		if (rank % 2 == file % 2)
			str = "+";

		if (board[rank][file].getPiece() != null)
			str = board[rank][file].getCode();

		if (board[rank][file].getPiece() != null && board[rank][file].isWhite())
			str = board[rank][file].getCode().toLowerCase();

		return str;
	}

	public void drawBoard() {

		for (int i = 0; i < RANK; i++) {
			for (int j = 0; j < FILE; j++) {
				System.out.print(" " + getCode(i, j) + " ");
			}
			System.out.println(" | " + (RANK - i));
		}
		for (int k = 0; k < RANK; ++k) {
			System.out.print(" " + (char) (k + 65) + " ");
		}
		System.out.println();
//		for (Tile tile : Board.notSafePosition) {
//			System.out.println("Rank : " + (8-tile.getRank()) + "| File : " + (char) (tile.getFile() + 'A') + "--"
//					+ tile.getPiece().isWhite());
//		}
	}

	private boolean checkFetchNotation(String[] notation) {
		if (notation.length < 2)
			return false;
		return true;
	}

	private int getIndexTile(char position, char indicator) {
		int index = (position - indicator);
		return index;
	}

	private Movement setMovement(Movement movement, String from, String to) {
		int fromFile = getIndexTile(from.charAt(0), 'A');
		int fromRank = RANK - (getIndexTile(from.charAt(1), '0'));

		int toFile = getIndexTile(to.charAt(0), 'A');
		int toRank = RANK - (getIndexTile(to.charAt(1), '0'));
		movement.setFrom(board[fromRank][fromFile]);
		movement.setTo(board[toRank][toFile]);

		return movement;
	}

	private boolean checkNotation(String notation) {

		return Pattern.compile(matcherNotation).matcher(notation).matches();
	}

	public Movement convertCoordinate(String notation, int type) {
		Movement movement = Movement.getInstance();
		if (type == 1)
			movement = algebraicToIndex(notation, movement);
		else
			movement = coordinateToIndex(notation, movement);

		return movement;

	}

	private Movement coordinateToIndex(String notation, Movement movement) {
		String[] fetchNotation = notation.split("-");
		if (!checkFetchNotation(fetchNotation))
			return null;

		if (!checkNotation(fetchNotation[0]))
			return null;

		if (!checkNotation(fetchNotation[1]))
			return null;

		return setMovement(movement, fetchNotation[0], fetchNotation[1]);
	}

	private Movement algebraicToIndex(String notation, Movement movement) {
		return movement;
	}

}

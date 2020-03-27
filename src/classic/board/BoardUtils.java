package classic.board;

public class BoardUtils {

	final static int x = 9;
	final static int y = 9;
	static char[][] board = { "RNBQKBNR8".toCharArray(), "PPPPPPPP7".toCharArray(), "-+-+-+-+6".toCharArray(),
			"+-+-+-+-5".toCharArray(), "-+-+-+-+4".toCharArray(), "+-+-+-+-3".toCharArray(), "pppppppp2".toCharArray(),
			"rnbqkbnr1".toCharArray(), "ABCDEFGH ".toCharArray(), };

	public static void drawBoard() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int[] convertCoordinate(String notation, int type) {
		int[] coordinate = new int[5];

		if (type == 1)
			coordinate = algebraicToIndex(notation, coordinate);
		else
			coordinate = coordinateToIndex(notation, coordinate);

		return coordinate;

	}

	public static int[] algebraicToIndex(String notation, int[] coordinate) {

		return coordinate;
	}

	public static int[] coordinateToIndex(String notation, int[] coordinate) {

		return coordinate;
	}
}

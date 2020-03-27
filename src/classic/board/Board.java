package classic.board;

import utilities.Utilities;

public class Board {
	public static int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			whiteMove(type);
		else
			blackMove(type);

		return move == 2 ? 1 : move + 1;
	}

	
	public static void whiteMove(int type) {
		System.out.println("white move:");
		String notation = Utilities.scan.nextLine();
		
		BoardUtils.convertCoordinate(notation,type);
	}

	public static void blackMove(int type) {
		System.out.println("black move:");
	}
}

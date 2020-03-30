package classic.board;

import utilities.Utilities;

public class Board {

	public static void invalidFormat() {
		System.out.println("invalid move: expected format [A..H][1..8]-[A..H][1..8]");
		Utilities.scan.nextLine();
	}

	public static int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			move("white move: ",type);
		else
			move("black move: ",type);

		return move == 2 ? 1 : move + 1;
	}

	public static void move(String text, int type) {
		Movement movement = Movement.getInstance();
		String notation;
		do{
			System.out.print(text);
		
			notation = Utilities.scan.nextLine();

			movement = BoardUtils.convertCoordinate(notation,type);
			if(movement == null){
				invalidFormat();
			}
		}while(movement == null);
	}
}

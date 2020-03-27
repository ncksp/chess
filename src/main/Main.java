package main;

import classic.board.Board;
import classic.board.BoardUtils;
import utilities.Utilities;

public class Main {

	Utilities util = new Utilities();
	int win = -1;
	int playerMove = 1;

	public Main() {
		int choose = 0;
		do {
			choose = util.chooseMenu(choose);
			switch (choose) {
			case 1:
				// play(1);
				System.out.println("Algebraic not ready, use Coordinate please");
				Utilities.scan.nextLine();
				break;
			case 2:
				play(2);
				break;
			}
		} while (choose != 3);
	}

	private void play(int type) {
		do {
			util.cls();
			BoardUtils.drawBoard();
			playerMove = Board.setPlayerMove(playerMove, type);
			Utilities.scan.nextLine();
		} while (win == -1);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}

package main;

import classic.board.Board;
import classic.board.BoardUtils;
import utilities.MainUtilities;

public class Main {

	MainUtilities util = new MainUtilities();
	public static int win = -1;
	int playerMove = 1;

	public Main() {
		int choose = 0;
		do {
			choose = util.chooseMenu(choose);
			switch (choose) {
			case 1:
				// play(1);
				System.out.println("Algebraic not ready, use Coordinate please");
				MainUtilities.scan.nextLine();
				break;
			case 2:
				play(2);
				break;
			}
		} while (choose != 3);
	}

	private void play(int type) {
		BoardUtils boardUtils = new BoardUtils();
		Board board = new Board();
		boardUtils.createBoard();
		do {
			util.cls();
			boardUtils.drawBoard();
			playerMove = board.setPlayerMove(playerMove, type);
		} while (win == -1);
		
		util.printWinner(win);
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}

package classic.board;

import java.util.List;

import classic.player.BlackPlayer;
import classic.player.Player;
import classic.player.WhitePlayer;
import utilities.Utilities;

public class Board {

	public static WhitePlayer white = new WhitePlayer();
	public static BlackPlayer black = new BlackPlayer();
	public static List<Move> movesPlayer;
	public static void invalidFormat() {
		System.out.println("invalid move: expected format [A..H][1..8]-[A..H][1..8]");
		Utilities.scan.nextLine();
	}

	public static int setPlayerMove(int move, int type) {
		if (move % 2 == 1)
			move("white move: ",type, white);
		else
			move("black move: ",type, black);

		return move == 2 ? 1 : move + 1;
	}

	public static void move(String text, int type, Player player) {
		Movement movement = Movement.getInstance();
		String notation;
		boolean loop = true;
		do{
			loop = false;
			System.out.print(text);
		
			notation = Utilities.scan.nextLine();

			movement = BoardUtils.convertCoordinate(notation,type);
			if(movement == null){
				invalidFormat();
			}else if(!makeMove(movement, player)){
				System.out.println("Invalid Move");
				loop = true;
			}
//			System.out.println(movement.getTo().getFile());
		}while(movement ==null || loop);
		
	}
	
	public static boolean makeMove(Movement movement, Player player){
		
		int fromRank = movement.fromRank();
		int fromFile = movement.fromFile();
		if(BoardUtils.board[fromRank][fromFile] == null)
			return false;
		if(movement.fromIsWhite() != player.isWhiteSide())
			return false;
		
//		System.out.println(movement.getTo().getFile());
		
		int toRank = movement.toRank();
		int toFile = movement.toFile();
		
//		System.out.println(fromFile);
		
		
//		
		BoardUtils.board[toRank][toFile].setPiece(BoardUtils.board[fromRank][fromFile].getPiece());
		BoardUtils.board[fromRank][fromFile] = new Tile(null, fromRank, fromFile);
		return true;
	}
}

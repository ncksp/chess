package classic.player;

import classic.board.BoardUtils;
import classic.board.Tile;

public class Castling {
	public static boolean isCanToCastling(Player player, Tile from, Tile to){
		int rookRank = player.isWhiteSide() ? 7 : 0;
		int rookFile = from.getFile() > to.getFile() ? 0 : 7;
		Tile rook = BoardUtils.board[rookRank][rookFile];
		if(rook.getPiece().getEverMove() != 0 || from.getPiece().getEverMove() != 0){
			System.out.println("Rook / King ever move(s)");
			return false;
		}
		if (from.getRank() != 7 && from.getRank() != 0) {
			return false;
		}
		if (player.isCastling()) {
			return false;
		}
	
		return from.getFile() - to.getFile() > 0 ? queenCastling(player, from, to) : isKingcastling(player, from, to);
	}
	
	private static boolean isKingcastling(Player player, Tile from, Tile to){
		for (int i = from.getFile() + 1; i < 7; i++) {
			if (BoardUtils.board[from.getRank()][i].getPiece() != null)
				return false;
		}

		player.setCastling(true);
		BoardUtils.board[from.getRank()][5].setPiece(BoardUtils.board[from.getRank()][7].getPiece());
		BoardUtils.board[from.getRank()][7].setPiece(null);
		return true;
	}
	
	private static boolean queenCastling(Player player, Tile from, Tile to) {
		for (int i = from.getFile() - 1; i > 0; i--) {
			if (BoardUtils.board[from.getRank()][i].getPiece() != null)
				return false;
		}
	
		player.setCastling(true);
		BoardUtils.board[from.getRank()][3].setPiece(BoardUtils.board[from.getRank()][0].getPiece());
		BoardUtils.board[from.getRank()][0].setPiece(null);
		return true;
	}
}

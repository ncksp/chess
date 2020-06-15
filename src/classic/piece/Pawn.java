package classic.piece;

import java.util.Vector;

import classic.board.Board;
import classic.board.BoardUtils;
import classic.board.Tile;

public class Pawn extends Piece {

	Vector<Tile> moves = new Vector<>();
	private boolean enPassant;
	public Pawn(boolean white) {
		super(white);
	}

	public boolean isEnPassant(){
		return this.enPassant;
	}
	@Override
	public String pieceName() {
		return "Pawn";
	}

	@Override
	public String pieceCode() {
		return "P";
	}

	@Override
	public Vector<Tile> getMoves(Tile from, Tile to) {
		Board.notSafePosition.remove(moves);
		Tile[][] board = BoardUtils.board;
		boolean white = board[from.getRank()][from.getFile()].isWhite();
		moves = new Vector<>();
		int sub = white ? -1 : 1;

		int currentPosition = from.getRank();
		int max = white ? 4 : 3;

		boolean canMoveDouble = Math.abs(max - currentPosition) == 2 ? true : false;
		
		if (valid(from.getRank() + sub, from.getFile()) && getPiece(from.getRank() + sub, from.getFile()) == null)
			moves.add(setMovement(from.getRank() + sub, from.getFile(),from));

		if (valid(from.getRank() + sub, from.getFile()) && white
				&& getPiece(from.getRank() - 1, from.getFile() - 1) != null
				&& !getPiece(from.getRank() - 1, from.getFile() - 1).isWhite())
			moves.add(setMovement(from.getRank() - 1, from.getFile() - 1,from));

		if (valid(from.getRank() + sub, from.getFile()) && white
				&& getPiece(from.getRank() - 1, from.getFile() + 1) != null
				&& !getPiece(from.getRank() - 1, from.getFile() + 1).isWhite())
			moves.add(setMovement(from.getRank() - 1, from.getFile() + 1,from));

		if (valid(from.getRank() + sub, from.getFile()) && !white
				&& getPiece(from.getRank() + 1, from.getFile() - 1) != null
				&& getPiece(from.getRank() + 1, from.getFile() - 1).isWhite())
			moves.add(setMovement(from.getRank() + 1, from.getFile() - 1,from));

		if (valid(from.getRank() + sub, from.getFile()) && !white
				&& getPiece(from.getRank() + 1, from.getFile() + 1) != null
				&& getPiece(from.getRank() + 1, from.getFile() + 1).isWhite())
			moves.add(setMovement(from.getRank() + 1, from.getFile() + 1,from));
		
		boolean canEnPassant = (white && from.getRank() < max) || (!white && from.getRank() > max) ? true : false;
		if(canEnPassant)
			getEnPassantMove(from, to);

		if (!canMoveDouble)
			return moves;

		if (valid(from.getRank() + sub, from.getFile()) && getPiece(from.getRank() + sub + sub, from.getFile()) == null)
			moves.add(setMovement(from.getRank() + sub + sub, from.getFile(),from));

		return moves;
	}
	
	private void getEnPassantMove(Tile from, Tile to){
		int max = from.isWhite() ? 1 : 6;
		int sub = from.isWhite() ? -1 : 1;
		
		if(from.getFile() < 0 || from.getFile() > 7)
			return;
		
		if((from.isWhite() && from.getRank() < max) || (!from.isWhite() && from.getRank() > max) )
			return;
		
		int rank = from.getRank();
		int file = from.getFile();
		Tile[][] board = BoardUtils.board;
		Tile left = from.getFile() == 0 ? new Tile(null, rank, file) : board[rank][file - 1];
		Tile right = from.getFile() == 7 ? new Tile(null, rank, file) : board[from.getRank()][file + 1];
		
		
		try {
			if(left.getPiece() == null && right.getPiece() == null)
				return;	
		
			if(left.getPiece() != null && getPiece(from.getRank() + sub, from.getFile() - 1 ) == null){
				if(to.getFile() == from.getFile() - 1 && to.getRank() == from.getRank() + sub)
					this.enPassant = true;
				
				moves.add(setMovement(from.getRank() + sub, from.getFile() - 1, from));
			}
			
			if(right.getPiece() != null && getPiece(from.getRank() + sub, from.getFile() + 1 ) == null){
				if(to.getFile() == from.getFile() + 1 && to.getRank() == from.getRank() + sub)
					this.enPassant = true;
				
				moves.add(setMovement(from.getRank() + sub, from.getFile() + 1, from));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
			
	}

}

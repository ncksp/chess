package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class King extends Piece {

	Vector<Tile> moves = new Vector<>();
	private int[][] coordinate = { { 1, 0 }, // n
			{ 1, 1 }, // ne
			{ 0, 1 }, // e
			{ -1, 1 }, // se
			{ -1, 0 }, // s
			{ -1, -1 }, // sw
			{ 0, -1 }, // w
			{ +1, -1 },// nw

	};

	public King(boolean white) {
		super(white);
	}

	@Override
	public String pieceName() {
		return "King";
	}

	@Override
	public String pieceCode() {
		return "K";
	}

	@Override
	public Vector<Tile> getMoves(Tile from) {
		moves = new Vector<>();
		for (int[] is : this.coordinate) {
			if (valid(from.getRank() + is[0], from.getFile() + is[1])
					&& (getPiece(from.getRank() + is[0], from.getFile() + is[1]) == null
							|| (getPiece(from.getRank() + is[0], from.getFile() + is[1]) != null
									&& getPiece(from.getRank() + is[0], from.getFile() + is[1]).isWhite() != from
											.getPiece().isWhite())))
				moves.add(setMovement(from.getRank() + is[0], from.getFile() + is[1], from));
		}

		return moves;
	}

}

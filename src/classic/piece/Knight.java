package classic.piece;

import java.util.Vector;

import classic.board.Tile;

public class Knight extends Piece {
	Vector<Tile> moves = new Vector<>();
	private int[][] coordinate = { { 2, 1 }, // NNE
			{ 1, 2 }, // ENE
			{ -1, 2 }, // ESE
			{ -2, 1 }, // SSE
			{ -2, -1 }, // SSW
			{ -1, -2 }, // WSW
			{ 1, -2 }, // WNW
			{ 2, -1 },// NNW

	};

	public Knight(boolean white) {
		super(white);
	}

	@Override
	public String pieceName() {
		return "Knight";
	}

	@Override
	public String pieceCode() {
		return "N";
	}

	@Override
	public Vector<Tile> getMoves(Tile from, Tile to) {
		moves = new Vector<>();
		for (int[] is : this.coordinate) {
			if (valid(from.getRank() + is[0], from.getFile() + is[1])
					&& getPiece(from.getRank() + is[0], from.getFile() + is[1]) == null
					|| (getPiece(from.getRank() + is[0], from.getFile() + is[1]) != null
							&& getPiece(from.getRank() + is[0], from.getFile() + is[1]).isWhite() != from.getPiece()
									.isWhite()))
				moves.add(setMovement(from.getRank() + is[0], from.getFile() + is[1], from));
		}

		return moves;

	}

}

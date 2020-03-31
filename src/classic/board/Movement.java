package classic.board;

public class Movement {
	private Tile from;
	private Tile to;
	
	private static Movement movement = new Movement();
	
	private Movement(){
		
	}
	
	public static Movement getInstance(){
		return movement;
	}
	public Movement(Tile from, Tile to) {
		super();
		this.from = from;
		this.to = to;
	}
	public Tile getFrom() {
		return from;
	}
	public void setFrom(Tile from) {
		this.from = from;
	}
	public Tile getTo() {
		return to;
	}
	public void setTo(Tile to) {
		this.to = to;
	}
	
	public boolean fromIsWhite(){
		return this.getFrom().getPiece().isWhite();
	}
	
	public boolean toIsWhite(){
		return this.getTo().getPiece().isWhite();
	}
	
	public int fromRank(){
		return this.getFrom().getRank();
	}
	
	public int toRank(){
		return this.getTo().getRank();
	}
	
	public int fromFile(){
		return this.getFrom().getFile();
	}
	
	public int toFile(){
		return this.getTo().getFile();
	}
}

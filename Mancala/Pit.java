package Mancala;

public class Pit {
	private int seeds;
	private PlayerNumber player;
	
	public Pit(int seeds, PlayerNumber player){
		this.seeds = seeds;
		this.player = player;
	}
	
	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}
	
	public PlayerNumber getPitPlayer() {
		return player;
	}
}

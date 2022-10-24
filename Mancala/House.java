package Mancala;

public class House extends Pit {
	
	public House(PlayerNumber player) {
		super(4, player);
	}
	
	//For when a player chooses a pit to distribute
	public int takeSeeds() {
		int seeds = getSeeds();
		setSeeds(0);
		return seeds;
	}

	//only one piece can be placed in a pit at a time
		public void setSeeds() {
		setSeeds(getSeeds()+1);
	}
}

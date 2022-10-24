package Mancala;

public class Store extends Pit {

	public Store(PlayerNumber player) {
		super(0, player);
		
	}

	//only a store can receive multiple pieces
	public void placeSeeds(int seeds) {
		setSeeds(getSeeds() + seeds);
	}
	
	
}

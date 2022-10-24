package Mancala;

public class Player {
	private String name;
	private PlayerNumber playerNumb;
	private House[] playerPits;
	private Store playerStore;
	
	
	public Player(String name, PlayerNumber playerNumb) {
		this.name = name;
		this.playerNumb = playerNumb;
		playerPits = new House [6];
		createPlayerPits();
		playerStore = new Store(playerNumb);
	}

	private void createPlayerPits() {
		for(int i = 0; i < playerPits.length; i++) {
			playerPits[i] = new House(playerNumb);
		}
	}

	public String getPlayerName() {
		return name;
	}
	
	public PlayerNumber getPlayerNumber() {
		return playerNumb;
	}
	
	public House [] getPlayerPitsArray() {
		return playerPits;
	}
	
	public Store getPlayerStore() {
		return playerStore;
	}
	
	
}

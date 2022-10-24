package Mancala;

public class MancalaBoard {
	
	private House [] pits;
	private Store [] stores;
	
	public MancalaBoard(House [] playerOnePits,House [] playerTwoPits, Store playerOneStore, Store playerTwoStore) {
		pits = new House [12];
		System.arraycopy(playerOnePits, 0, pits, 0, 6);
		System.arraycopy(playerTwoPits, 0, pits, 6, 6);
		stores = new Store [2];
		stores [0] = playerOneStore;
		stores[1] = playerTwoStore;
	}
	
	public House getPit(int index) {
		return pits[index];
	}
	
	public Store getStore(int index) {
		return stores[index];
	}
	
	public void moveSidePiecesToStore(int side) {
		//accumulator for the number of pieces on the side to empty
		int pieces = 0;
		if(side == 0) {
			for(int i = 0; i< 6; i++) {
				pieces += pits[i].takeSeeds();
			}
		}else {
			for(int i = 6; i<12; i++) {
				pieces += pits[i].takeSeeds();
			}
		}
		
		stores[side].placeSeeds(pieces);
	}
	
	public int pitsEmpty() {
		int playerOneEmptyPits = 0;
		int playerTwoEmptyPits = 0;
		for(int i = 0; i< 6; i++) {
			if(pits[i].getSeeds() == 0) {
				playerOneEmptyPits++;
			}
		}
		
		for(int i = 6; i<12; i++) {
			if(pits[i].getSeeds() == 0) {
				playerTwoEmptyPits++;
			}
		}
		//if player 1 is empty return 0
		if(playerOneEmptyPits == 6) {
			return 0;
		//if player 2 is empty return 1
		} else if(playerTwoEmptyPits == 6) {
			return 1;
			//if they are both full return -1	
		} else {
			return -1;
		}
		
	}
	
	public int getOpposite(int pit) {
		return 11-pit;
	}
	
	public void displayBoard(Player player, Player player2) {
		
		if(player.getPlayerNumber().equals(PlayerNumber.ONE)) {
			System.out.println("\t\t\t#12\t#11\t#10\t#9\t#8\t#7");
			System.out.print(player2.getPlayerName()+"\t\t");
			for(int i = 11; i > 5; i--) {
				System.out.print("\t" + pits[i].getSeeds() );	
			}
			System.out.println("\n\t\t" + stores[1].getSeeds() + "\t\t\t\t\t\t\t"+ stores[0].getSeeds());
			System.out.print("\t\t");
			for(int j = 0; j<6; j++) {
				System.out.print("\t" + pits[j].getSeeds() );	
			}
			System.out.println("\t\t"+player.getPlayerName());
			System.out.println("\t\t\t#1\t#2\t#3\t#4\t#5\t#6");
	
		}else {
			System.out.println("\t\t\t#6\t#5\t#4\t#3\t#2\t#1");
			System.out.print(player2.getPlayerName()+"\t\t");
			for(int i = 5; i >= 0; i--) {
				System.out.print("\t" + pits[i].getSeeds() );	
			}
			System.out.println("\n\t\t" + stores[0].getSeeds() + "\t\t\t\t\t\t\t"+ stores[1].getSeeds());
			System.out.print("\t\t");
			for(int j = 6; j < 12; j++) {
				System.out.print("\t" + pits[j].getSeeds() );	
			}
			System.out.println("\t\t"+player.getPlayerName());
			System.out.println("\t\t\t#7\t#8\t#9\t#10\t#11\t#12");
		}
	}
}

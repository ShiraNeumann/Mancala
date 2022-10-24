package Mancala;

import java.util.*;

public class MancalaGame {
	private Player [] players;
	private boolean gameOver;
	private MancalaBoard board;
	
	public MancalaGame(String playerOne, String playerTwo) {
		players = new Player[2];
		players[0] = new Player(playerOne, PlayerNumber.ONE);
		players[1] = new Player(playerTwo, PlayerNumber.TWO);
		gameOver = false;
		board = new MancalaBoard( players[0].getPlayerPitsArray(), players[1].getPlayerPitsArray(), players[0].getPlayerStore(), players[1].getPlayerStore() );
	}
	
	
	public String endGame() {
		int emptySide = board.pitsEmpty();
		if(emptySide >= 0) {
			gameOver = true;
			board.moveSidePiecesToStore(++emptySide % 2);
			String winner = calculateWinner();
			return winner;
		}else {
			return null;
		}
	}
	
	
	private String calculateWinner() {
		if(board.getStore(0).getSeeds() > board.getStore(1).getSeeds()) {
			return players[0].getPlayerName() + " is the winner!";
		}else if(board.getStore(1).getSeeds()> board.getStore(0).getSeeds()) {
			return players[1].getPlayerName() + " is the winner!";
		}else {
			return "Tie";
		}
		
	}


	public boolean validPitEntry(int index, int currPlayer) {
		if(index < 0 || index > 11) { // if it is an invalid number
			return false;
		} else if(!board.getPit(index).getPitPlayer().equals(players[currPlayer].getPlayerNumber())) { //if it is a valid number but the pit isn't theirs
			return false;
		} else if (board.getPit(index).getSeeds() == 0) {//if it's their pit, but it is empty
			return false;
		} else {
			return true;
		}
	}
	
	public int determineTurn(int index, int currPlayer, boolean landedInStore) {
	
		if((index == 5 || index == 11) && landedInStore) {// if the player landed in their store, the player gets to go again
			System.out.println("You landed in your Mancala.");
		}else if(board.getPit(index).getPitPlayer().equals(players[currPlayer].getPlayerNumber()) && board.getPit(index).getSeeds() == 1) { //if the player lands on their own pit that was empty  
			int totalPieces = board.getPit(index).takeSeeds();//remove the piece from the landed on pit
			totalPieces+= board.getPit(board.getOpposite(index)).takeSeeds(); //remove the pieces from the opposite pit
			board.getStore(currPlayer).placeSeeds(totalPieces);//add all of the pieces to the players Mancala
			currPlayer++;
			System.out.println("You landed in your empty pit, you get that piece and all pieces from the opposite pit.");
		}else {
			currPlayer++;
		}
		
		return currPlayer % 2; 
		
	}
	
	public int distributePieces(int currPlayer, int index, int pieces) {
		int lastPieceIndex = 0;
		boolean landedInStore = false;
		for(int i = 1; i <= pieces; i++) {
			if(index == 5 ) { // if the player took the pieces from the 6th or 12th pit
				if (board.getStore(0).getPitPlayer().equals(players[currPlayer].getPlayerNumber())) { //check if the adjacent store belongs to the player
					board.getStore(0).placeSeeds(1);//if it does then add a piece to their store
					lastPieceIndex = index;
					landedInStore = true;
					if(pieces-i != 0) { //if there are more pieces
						board.getPit(index+1).setSeeds();
						i++;//since it used another piece, increment the loop counter
						lastPieceIndex = index+1;//calculate for the extra piece it distributed
						landedInStore = false;
					}
				}else {
					board.getPit(index+1).setSeeds();//otherwise distribute the piece to the regular pit
					lastPieceIndex = index+1;
				}
			}else if (index == 11) {
				if (board.getStore(1).getPitPlayer().equals(players[currPlayer].getPlayerNumber())) {
					board.getStore(1).placeSeeds(1);
					lastPieceIndex = index;
					landedInStore = true;
					if(pieces-i != 0) { //if there are more pieces
						board.getPit(0).setSeeds();
						i++;
						lastPieceIndex = 0;
						index=-1;//restart the array
						landedInStore = false;
					}
				}else {
					board.getPit(0).setSeeds();//otherwise distribute the piece to the regular pit
					lastPieceIndex = 0;
					index = -1;
				}
			}else {
				board.getPit(index+1).setSeeds();//otherwise distribute the pieces to the regular pits
				lastPieceIndex = index+1;
			}
			index++;
		}

		currPlayer = determineTurn(lastPieceIndex, currPlayer, landedInStore);
		return currPlayer;
		
	}

	public void playGame() {
		String winner = null;
		Scanner keyboard = new Scanner(System.in);
		int currentPlayer = 0;

		while(!gameOver) {
			System.out.println(" The board from " + players[currentPlayer].getPlayerName() + "'s perspective.");
			board.displayBoard(players[currentPlayer], players[(currentPlayer+1)%2]);
			//player one takes his turn. while he lands in a store keep going
			System.out.println(players[currentPlayer].getPlayerName() + ", choose one of your pits to remove the pieces.");
			System.out.println("Pieces get distributed from the pit, going towards your Mancala on the right.");
			int pitIndex = keyboard.nextInt();
			pitIndex--;
			//validate user entry
			boolean valid = validPitEntry(pitIndex, currentPlayer);
			while(!valid) {
				System.out.println("Invalid entry, choose one of your pits to remove the pieces: ");
				pitIndex = keyboard.nextInt();
				pitIndex--;
				valid = validPitEntry(pitIndex, currentPlayer);
			}
			int pieces = board.getPit(pitIndex).takeSeeds(); //remove the pieces from the pit chosen
			
			currentPlayer = distributePieces(currentPlayer, pitIndex, pieces);
			
			winner = endGame();//checks to see if there was a winner
			
		}
		
		System.out.println("**Game Over**");
		board.displayBoard(players[currentPlayer], players[(currentPlayer+1)%2]);
		System.out.println(winner);
		keyboard.close();
	}
	
}	


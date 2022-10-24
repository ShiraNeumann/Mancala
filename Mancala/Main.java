package Mancala;
import java.util.*;

public class Main {
	public static void main(String [] args) {
		System.out.println("~ Mancala ~");
		System.out.println("Game Rules:");
		System.out.println("The game begins with player one picking up all of the pieces in any one of the pits on his side.");
		System.out.println("Moving counter-clockwise, the player deposits one of the pieces in each pit until the pieces run out.");
		System.out.println("If you run into your own store, deposit one piece in it. If you run into your opponent's store, skip it.");
		System.out.println("If the last piece you drop is in your own store, you get a free turn.");
		System.out.println("If the last piece you drop is in an empty pit on your side, you capture that piece and any pieces in the hole directly opposite.");
		System.out.println("The game ends when all six spaces on one side of the Mancala board are empty. The player who still has pieces on his side of the board when the game ends captures all of those pieces.");
		System.out.println("The winner of the game is the player with the most pieces.");
		System.out.println();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter player 1 name: ");
		String player1 = keyboard.nextLine();
		System.out.println("Enter player 2 name: ");
		String player2 = keyboard.nextLine();
		MancalaGame game = new MancalaGame(player1, player2);
		game.playGame();
		keyboard.close();

	}
		
}



import java.util.Scanner;

public class Player {
	String name;
	char symbol;
	
	public Player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	
	public static Player takePlayerInput(int playerNumber) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player " + playerNumber + " name");
		String name = s.nextLine();
		System.out.println("Enter player " +  + playerNumber +" symbol");
		char symbol = s.next().charAt(0);
		return new Player(name, symbol);
	}
	
	
}

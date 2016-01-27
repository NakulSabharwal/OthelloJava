import java.util.Scanner;

public class GameManager {
	Board b;
	Player p1;
	Player p2;
	
	public GameManager() {
		p1 = Player.takePlayerInput(1);
		do {
			p2 = Player.takePlayerInput(2);
		} while (p1.symbol == p2.symbol);
		
		b  = new Board(p1.symbol, p2.symbol);
		play();
	}
	
	private void play() {
		boolean player1Turn = true;
		Scanner s = new Scanner(System.in);
		while (b.getGameStatus() == Board.NOT_FINISHED) {
			Player currentPlayer;
			if (player1Turn)
				currentPlayer = p1;
			else
				currentPlayer = p2;
			
			boolean done = false;
			while (!done) {
				b.print();
				System.out.println(currentPlayer.name + "'s turn");
				int x = s.nextInt();
				int y = s.nextInt();
				done = b.makeAMove(x,y, currentPlayer.symbol);
				if (!done) {
					System.out.println("Invalid move! Try Again!");
				}
			}
			
			player1Turn = !player1Turn;
		}
		// Print the winner
		b.print();
		int status = b.getGameStatus();
		if (status == Board.PLAYER1WON) {
			System.out.println(p1.name + " won");
		} else if (status == Board.PLAYER2WON) {
			System.out.println(p2.name + " won");
		} else if (status == Board.DRAW) {
			System.out.println("Draw ");
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameManager gm = new GameManager();
	}

}

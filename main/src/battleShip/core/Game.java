package battleShip.core;

import java.util.Random;

public class Game 
{
	private Player firstPlayer;
	private Player secondPlayer;
	
	public Game() 
	{
		this.firstPlayer = new Player();
		this.firstPlayer.initialze();
		
		this.secondPlayer = new Player();
		this.secondPlayer.initialze();
	}
	
	public void startGame() throws InterruptedException
	{
		Random random = new Random();
		int turn = random.nextInt(2) + 1;
		
		System.out.println("====== Battle Ship =========");
		
		do
		{
			String playerName = "A";
			Player shooterPlayer;
			Player defendarPlayer;
			
			if (turn == 1)
			{
				playerName = "A";
				shooterPlayer = firstPlayer;
				defendarPlayer = secondPlayer;
			}
			else
			{
				playerName = "B";
				shooterPlayer = secondPlayer;
				defendarPlayer = firstPlayer;
			}
			
			
			ShootResult shootResult;
			do
			{
				int x;
				int y;
				
				System.out.println("------------------------------------------");
				System.out.println("Player " + playerName + " : ");
				
				System.out.print("(X,Y): ");
				Cell guessedCell;
				
				do 
				{
					x = random.nextInt(Config.getScreenScale()) + 1;
					y = random.nextInt(Config.getScreenScale()) + 1;
					guessedCell = new Cell(x, y);
				}
				while (shooterPlayer.guessedCells().contains(guessedCell));
				
				shooterPlayer.addGuessedCell(guessedCell);
				
				System.out.println(x + ", " + y);
				Thread.sleep(Config.getGuessDelay());
				shootResult = shooterPlayer.shoot(defendarPlayer, x, y);
				
				System.out.println("Shoot Result: " + shootResult);
			}
			while (shootResult.equals(ShootResult.hitAgain));
			
			if (shootResult.equals(shootResult.miss))
				turn = (turn % 2) + 1;
		}
		while (!this.firstPlayer.isLooser() && !this.secondPlayer.isLooser());
		
		
		System.out.println("\n======================================================");
		if (this.firstPlayer.isLooser())
			System.out.println("Player B is winner");
		else
			System.out.println("Player A is winner");
	}
}

package battleShip.core;

import java.util.ArrayList;
import java.util.List;

public class Player 
{
	private List<Ship> ships = new ArrayList<>();
	private int[] shipSizes = {3, 3, 4, 5, 5};
	private List<Cell> guessedCells = new ArrayList<>();

	public void initialze()
	{
		for (int shipSize : shipSizes)
		{
			boolean overlap = false;
			Ship newShip = null;
			do
			{
				overlap = false;
				newShip = new Ship(shipSize);
				for (Ship ship : this.ships)
				{
					if (ship.hasOverlap(newShip))
					{
						overlap = true;
						break;
					}
				}
			} 
			while (overlap);
			
			this.ships.add(newShip);
		}
	}
	
	public ShootResult shoot(Player otherPlayer, int x, int y)
	{
		for (Ship ship : otherPlayer.getShips())
		{
			if (ship.includeCell(x, y))
			{
				Cell targetCell = ship.getCell(x, y);
				
				if (targetCell.getStatus().equals(CellStatus.empty))
				{
					targetCell.setStatus(CellStatus.hitted);
					return ShootResult.hit;
				}
				else if (targetCell.getStatus().equals(CellStatus.hitted))
					return ShootResult.hitAgain;
			}
		}
		
		return ShootResult.miss;
	}
	
	public List<Ship> getShips()
	{
		return this.ships;
	}
	
	public void addShip(Ship ship) throws ShipOverlapException
	{
		boolean overlap = false;
		for (Ship sp : this.ships)
		{
			if (sp.hasOverlap(ship))
			{
				overlap = true;
				throw new ShipOverlapException(ship, sp);
			}
		}
		
		if (!overlap)
			this.ships.add(ship);
	}
	
	public Ship getShip(int index)
	{
		return this.ships.get(index);
	}
	
	public boolean isLooser()
	{
		for (Ship ship : this.ships)
		{
			if (!ship.isDestroyed())
				return false;
		}
		
		return true;
	}
	
	public void addGuessedCell(Cell guessedCell)
	{
		this.guessedCells.add(guessedCell);
	}
	
	public List<Cell> guessedCells()
	{
		return this.guessedCells;
	}
}

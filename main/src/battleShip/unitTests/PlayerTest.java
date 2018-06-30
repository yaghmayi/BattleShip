package battleShip.unitTests;

import org.junit.Test;

import battleShip.core.Cell;
import battleShip.core.CellStatus;
import battleShip.core.Config;
import battleShip.core.Player;
import battleShip.core.Ship;
import battleShip.core.ShipLayout;
import battleShip.core.ShipOverlapException;
import battleShip.core.ShootResult;

import java.util.List;

import org.junit.Assert;

public class PlayerTest 
{
	@Test
	public void initialze()
	{
		Player player = new Player();
		player.initialze();
		
		List<Ship> ships = player.getShips();
		Assert.assertNotNull(ships);
		Assert.assertEquals(5, ships.size());
		
		for (int i=0; i< ships.size(); i++)
		{
			for (int j=i+1; j<ships.size(); j++)
				Assert.assertFalse(player.getShip(i).hasOverlap(player.getShip(j)));
		}
		
		for (Ship ship : ships)
		{
			for (Cell cell : ship.getCells())
			{
				Assert.assertTrue(cell.getX() <= Config.getScreenScale());
				Assert.assertTrue(cell.getY() <= Config.getScreenScale());
				Assert.assertEquals(CellStatus.empty, cell.getStatus());
			}
		}
	}
	
	@Test
	public void Shoot()
	{
		Player player1 = new Player();
		Player player2 = new Player();
		try
		{
			player1.addShip(new Ship(3, 2, 4, ShipLayout.horizental));
			
			Cell targetCell = player1.getShip(0).getCell(2, 4);
			Assert.assertEquals(CellStatus.empty, targetCell.getStatus());
			
			ShootResult shootResult = player2.shoot(player1, 2, 4);
			Assert.assertEquals(ShootResult.hit, shootResult);
			Assert.assertEquals(CellStatus.hitted, targetCell.getStatus());
			
			shootResult = player2.shoot(player1, 2, 4);
			Assert.assertEquals(ShootResult.hitAgain, shootResult);
			Assert.assertEquals(CellStatus.hitted, targetCell.getStatus());
			
			targetCell = player1.getShip(0).getCell(4, 4);
			Assert.assertEquals(CellStatus.empty, targetCell.getStatus());
			
			shootResult = player2.shoot(player1, 4, 4);
			Assert.assertEquals(ShootResult.hit, shootResult);
			Assert.assertEquals(CellStatus.hitted, targetCell.getStatus());
			
			shootResult = player2.shoot(player1, 3, 5);
			Assert.assertEquals(ShootResult.miss, shootResult);
		}
		catch (ShipOverlapException ex)
		{
			Assert.assertNull(ex);
		}
	}
}

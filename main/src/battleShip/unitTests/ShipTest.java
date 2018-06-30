package battleShip.unitTests;

import org.junit.Assert;
import org.junit.Test;

import battleShip.core.Ship;
import battleShip.core.ShipLayout;

public class ShipTest 
{
	@Test
	public void CreateCells()
	{
		Ship ship = new Ship(5, 2, 3, ShipLayout.horizental);
		Assert.assertNotNull(ship.getCells());
		Assert.assertEquals(5, ship.getCells().size());
		Assert.assertTrue(ship.includeCell(2, 3));
		Assert.assertTrue(ship.includeCell(3, 3));
		Assert.assertTrue(ship.includeCell(6, 3));
		Assert.assertFalse(ship.includeCell(7, 3));
		Assert.assertFalse(ship.includeCell(1, 3));
		
		ship = new Ship(2, 5, 1, ShipLayout.vertical);
		Assert.assertNotNull(ship.getCells());
		Assert.assertEquals(2, ship.getCells().size());
		Assert.assertTrue(ship.includeCell(5, 1));
		Assert.assertTrue(ship.includeCell(5, 2));
		Assert.assertFalse(ship.includeCell(3, 3));
		Assert.assertFalse(ship.includeCell(4, 1));
	}
}

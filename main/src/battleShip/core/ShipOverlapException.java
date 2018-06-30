package battleShip.core;

public class ShipOverlapException extends Exception 
{
	public ShipOverlapException(Ship ship1, Ship ship2)
	{
		super("There is an overlap between two ships: " + ship1 + " and " + ship2);
	}
}

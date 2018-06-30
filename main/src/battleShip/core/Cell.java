package battleShip.core;

public class Cell 
{
	private int x;
	private int y;
	private CellStatus status;
	
	public Cell(int x, int y) 
	{
		this.x = x;
		this.y = y;
		this.status = CellStatus.empty;
	}

	public CellStatus getStatus() 
	{
		return status;
	}
	
	public void setStatus(CellStatus status) 
	{
		this.status = status;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj instanceof Cell)
		{
			Cell thatCell = (Cell) obj;
			return this.getX() == thatCell.getX() && this.getY() == thatCell.getY();
		}
		
		return false;
	}
	
	
}

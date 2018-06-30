package battleShip.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship 
{
	private int size;
	private List<Cell> cells;
	private ShipLayout layout;
	
	public Ship(int size) 
	{
		Random random = new Random();
		int r = random.nextInt(1) + 1;
		ShipLayout shipLayout = null;
		int x; 
		int y;
		
		if (r == 1)
		{
			shipLayout = ShipLayout.horizental;
			y = random.nextInt(Config.getScreenScale()) + 1;
			x = random.nextInt(Config.getScreenScale() - size) + 1;
		}
		else
		{
			shipLayout = ShipLayout.vertical;
			y = random.nextInt(Config.getScreenScale() - size) + 1;
			x = random.nextInt(Config.getScreenScale()) + 1;
		}
		
		createCells(size, x, y, shipLayout);
	}
	
	public Ship(int size, int startX, int startY, ShipLayout shipLayout)
	{
		createCells(size, startX, startY, shipLayout);
	}
	
	private void createCells(int size, int startX, int startY, ShipLayout shipLayout)
	{
		int x = startX;
		int y = startY;
		
		this.layout = shipLayout;
		this.cells = new ArrayList<>();
		for (int i=0; i < size; i++)
		{
			Cell cell = new Cell(x, y);
			if (this.layout.equals(ShipLayout.horizental))
				x = x+1;
			else
				y = y+1;
			
			this.cells.add(cell);
		}
	}
	
	public boolean hasOverlap(Ship otherShip)
	{
		for (Cell cell : this.getCells())
		{
			for (Cell otherCell : otherShip.getCells())
			{
				if (cell.getX() == otherCell.getX() && cell.getY() == otherCell.getY())
					return true;
			}
		}
		
		return false;
	}
	
	public List<Cell> getCells()
	{
		return this.cells;
	}
	
	public boolean includeCell(int x, int y)
	{
		if (this.getCell(x, y) != null)
			return true;
		else
			return false;
	}
	
	public Cell getCell(int x, int y)
	{
		for (Cell cell : this.getCells())
		{
			if (cell.getX() == x && cell.getY() == y)
				return cell;
		}
		
		return null;
	}
	
	public Cell getCell(int index)
	{
		return this.getCells().get(index);
	}

	@Override
	public String toString() 
	{
		return "Ship [size=" + size + ", "
			 + "position=" + cells.get(0).getX() + ":" + cells.get(0).getY()  
			 + ", layout=" + layout + "]";
	}
	
	public boolean isDestroyed()
	{
		for (Cell cell : this.cells)
		{
			if (cell.getStatus().equals(CellStatus.empty))
				return false;
		}
		
		return true;
	}
	
	
}

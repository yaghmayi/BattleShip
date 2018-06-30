package battleShip.core;

public class Config 
{
	private static final int screenScale = 10;
	private static final int guessDelay = 20; //microSecends
	
	public static int getScreenScale()
	{
		return screenScale;
	}
	
	public static int getGuessDelay()
	{
		return guessDelay;
	}
}

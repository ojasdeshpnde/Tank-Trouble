import java.awt.Graphics;
import java.util.ArrayList;

public class Bullet 
{
	public int bX = 0;
	public int bY = 0;
	public String dir = "";
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public Bullet(int x, int y, String d) 
	{
		bX = x;
		bY = y;
		dir = d;
		
	}
	public static int getX(Bullet a) 
	{
		return a.bX;
	}
	public static int getY(Bullet a) 
	{
		return a.bY;
	}
	public static String getD(Bullet a) 
	{
		return a.dir;
	}
	
	public static void update() 
	{
		for(int i = 0; i < bullets.size(); i++) 
		{
			bullets.get(i);
			bullets.get(i);
			if(Bullet.getX(bullets.get(i)) < 2000 && Bullet.getY(bullets.get(i)) < 1000)
			{
				if(bullets.get(i).dir.equals("N")) 
				{
					bullets.get(i).bY= bullets.get(i).bY - 1;	
				}
				else if(bullets.get(i).dir.equals("S")) 
				{
					bullets.get(i).bY = bullets.get(i).bY + 1;	
				}
				else if(bullets.get(i).dir.equals("E")) 
				{
					bullets.get(i).bX = bullets.get(i).bX + 1;	
				}
				else if(bullets.get(i).dir.equals("W")) 
				{
					bullets.get(i).bX = bullets.get(i).bX - 1;	
				}
			}
			else 
			{
				bullets.remove(i);
			}
		}
		
	}
	
	public static void addBullet(Bullet a) 
	{
		bullets.add(a);
	}
	
	
}

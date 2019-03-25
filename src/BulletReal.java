import java.awt.Point;

public class BulletReal
{
	public double vx;
	public double vy;
	
	public double xpos;
	public double ypos;
	public double time;
	
	
	public BulletReal(double vx, double vy, double xpos, double ypos, double time)
	{
		this.vx = vx;
		this.vy = vy;
		this.xpos = xpos;
		this.ypos = ypos;
		this.time = time;
	}
		
	
	public double getvx()
	{
		return vx;
		
	}
	
	public double getvy()
	{
		return vy;
	}
	
	public double getxpos()
	{
		return xpos;
	}
	public double getypos()
	{
		return ypos;
	}
	public double getTime()
	{
		return time;
	}
	public void setVx(double velocity) 
	{
		vx = velocity;
		return;
	}
	
	public void setVy(double velocity) 
	{
		vy = velocity;
		return;
	}
	
	public void setxpos(double velocity) 
	
	{
		xpos = velocity;
		return;
	}
	
	public void setypos(double velocity) 
	{
		ypos = velocity;
		return;
	}
	
	public void delete() 
	{
		ypos = -10;
		xpos=-10;
		vx=0;
		vy=0;
		return;
	}

	
	
	
}
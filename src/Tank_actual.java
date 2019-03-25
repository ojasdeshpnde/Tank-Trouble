public class Tank_actual
{
	private double x, y;
	private double angle=0;
	private double v=5;
	private double vy=v*Math.sin(angle);
	private double vx=v*Math.cos(angle);
	
	//private Gun gun;
	
	public Tank_actual(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getAngle()
	{
		return angle;
	}
	//public Gun getGun()
//	{
	//	return gun;
	//}
	public void changeX(int d)
	{
		x += d;
	}
	public void changeY(int d)
	{
		y += d;
	}
	public void changeAngle(int d)
	{
		angle += d;
	}
	public void move(double d)
	{
		y+=d*Math.sin(Math.toRadians(angle));
		x+=d*Math.cos(Math.toRadians(angle));
	}
	//public void fire()
//	{
//		gun.fire();
//	}
}
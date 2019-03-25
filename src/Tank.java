import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Tank extends JFrame implements Runnable , KeyListener, ActionListener
{
	
	//YOU HAVE TO RUN BOTH INSTANCES OF THE GAME FOR THE IMAGES TO SHOW UP!
	public JFrame frame;
	public JPanel panel;
	Socket socket;
	ServerSocket servers;
	private int x = 41;
	private int y = 51;
	private boolean tR = false;
	private boolean tL = false;
	private static String  trial = "";
	private String direc = "N";
	private int velx = 10;
	private int vely = 10;
	private Image image;
	private Image imageT;
	private Image imageL;
	private Image imageB;
	public static int port = 1026;
	Thread th =  new Thread(this);
	public static boolean started = false;
	Timer timer=new Timer(1, this);
	public Tank() throws IOException
	{	
		timer.start();
		addKeyListener(this);
		panel = new JPanel();
		this.setSize(500, 500);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		this.add(panel);
		this.setTitle("Game");
		servers = new ServerSocket(port);
		socket = servers.accept();
		image = Toolkit.getDefaultToolkit().getImage("index.png");
		imageT = Toolkit.getDefaultToolkit().getImage("T.png");
		imageB = Toolkit.getDefaultToolkit().getImage("D.png");
		imageL = Toolkit.getDefaultToolkit().getImage("L.png");
	}
	
	public void paint(Graphics g) 
	{
		super.paintComponents(g);
		g.drawString("x=" + x + " y=" + y, 40 , 45);
		g.drawRect(40, 50, 400, 400);
		Graphics2D g2d = (Graphics2D)g;
		for(int i = 0; i < Bullet.bullets.size(); i++) 
		{
			g.fillOval(Bullet.getX(Bullet.bullets.get(i)), Bullet.getY(Bullet.bullets.get(i)), 10, 10);
		}
		if(direc.equals("E"))
		{
			g2d.drawImage(image,x,y, 50,88,this);
		}
		else if(direc.equals("N"))
		{
			g2d.drawImage(imageT,x,y, 50,88,this);
		}
		else if(direc.equals("S"))
		{
			g2d.drawImage(imageB,x,y, 50,88,this);
		}
		else
		{
			g2d.drawImage(imageL,x,y, 50,88,this);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		new Tank();
	}
	public void thread() 
	{
	}
	
	public void keyTyped(KeyEvent ke) 
	{
	}

	public void keyPressed(KeyEvent ke)
	{
		
		int keyCode = ke.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_W:
				// ADD BOX PIXEL CONDITIONS
					if(direc.equals("N"))
					{	
						y = y - vely;
						break;
					}
					else if(direc.equals("S"))
					{
						y = y + vely;
						break;
					}
					else if(direc.equals("E"))
					{
						x = x +velx;
						break;
					}
					else
					{
						x = x - velx;
						break;
					}
				
			case KeyEvent.VK_A:
				if(direc.equals("N"))
				{
					direc = "W";
				}
				else if(direc.equals("W"))
				{
					direc = "S";
				}
				else if(direc.equals("S"))
				{
					direc = "E";
				}
				else
				{
					direc = "N";
				}
				tL = true;
				break;
			case KeyEvent.VK_D:
				if(direc.equals("N"))
				{
					direc = "E";
				}
				else if(direc.equals("E"))
				{
					direc = "S";
				}
				else if(direc.equals("S"))
				{
					direc = "W";
				}
				else
				{
					direc = "N";
				}
				tR = true;
				break;
			case KeyEvent.VK_S:
			{
				if(direc.equals("N"))
				{	
					y = y + vely;
					break;
				}
				else if(direc.equals("S"))
				{
					y = y - vely;
					break;
				}
				else if(direc.equals("E"))
				{
					x = x - velx;
					break;
				}
				else
				{
					x = x + velx;
					break;
				}
			}
			case KeyEvent.VK_SPACE:
			{
				Bullet a = new Bullet(x,y,direc);
				Bullet.bullets.add(a);
			}
				
		}
		repaint();
		if(!started) 
		{
			th.start();
		}
	}

	
	public void keyReleased(KeyEvent e) {}

	public void run()
	{
			DataInputStream dis;
			try {
				dis = new DataInputStream(socket.getInputStream());
				String str = dis.readUTF();
				System.out.println(str);
				if(str.equals("HEY"))
				{
					trial = "1";
					System.out.println(trial);
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			repaint();
	}
	public void updateB() 
	{
		Bullet.update();
		for(int i = 0; i < Bullet.bullets.size(); i++) 
		{
			repaint();
		}
		
	}
	
	 public void actionPerformed(ActionEvent ev)
	 {
	    if(ev.getSource()==timer){
	     updateB();// this will call at every 1 second
	    }
	 }
	
}

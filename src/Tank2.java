import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
public class Tank2 extends JFrame implements Runnable , KeyListener
{
	public JFrame frame;
	public JPanel panel;
	private int x = 41;
	private int y = 51;
	private boolean tR = false;
	private boolean tL = false;
	private String direc = "N";
	private int velx = 10;
	private int vely = 10;
	private Image image;
	private Image imageT;
	private Image imageL;
	private Image imageB;
	public static int port = 1026;
	Thread th2 = new Thread(this);
	Socket socket;
	
	public Tank2() throws IOException 
	{	
		addKeyListener(this);
		panel = new JPanel();
		this.setSize(500, 500);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		this.add(panel);
		this.setTitle("Game");
		connect();
		image = Toolkit.getDefaultToolkit().getImage("index.png");
		imageT = Toolkit.getDefaultToolkit().getImage("T.png");
		imageB = Toolkit.getDefaultToolkit().getImage("D.png");
		imageL = Toolkit.getDefaultToolkit().getImage("L.png");
	}
	
	public void connect() throws UnknownHostException, IOException 
	{
		socket = new Socket("127.0.0.1",port);
	}
	
	public void paint(Graphics g) 
	{
		super.paintComponents(g);
		g.drawString("x=" + x + " y=" + y, 40 , 45);
		g.drawRect(40, 50, 400, 400);
		Graphics2D g2d = (Graphics2D)g;
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
		new Tank2();
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
		}
		th2.run();
	}

	
	public void keyReleased(KeyEvent e) {}

	@Override
	public void run() 
	{
		try
		{
			DataOutputStream dos = new
			DataOutputStream(socket.getOutputStream());
			dos.writeUTF("400");
			
		}
		catch(Exception e1){
		}
		repaint();
	}
	
}

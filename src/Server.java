import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Server extends JFrame
{
	public JFrame frame;
	public JPanel panel;
	
	public static int nOfClients = 4;
	
	public static ArrayList<Tank_actual> tanks;
	
	public static ArrayList<Integer> ports;
	public static ArrayList<Socket> sc;
	public static ArrayList<ServerSocket> s;
	
	Thread th;
	
	public Server() throws IOException
	{
			frame = new JFrame();
			panel = new JPanel();
			this.setSize(500, 500);
			this.setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setLayout(null);
			this.add(panel);
			this.setTitle("Server");
			ports = new ArrayList<Integer>();
			for(int i = 0; i < nOfClients; i++)
			{
				ports.add(i+1);
			}
			sc = new ArrayList<Socket>();
			s = new ArrayList<ServerSocket>();
			tanks = new ArrayList<Tank_actual>();
			connect();
			createTanks();
	}
	
	public void connect() throws IOException
	{
		for(int i = 0; i < nOfClients; i++)
		{
			ServerSocket sTemp = new ServerSocket(ports.get(i));
			s.add(sTemp);
			Socket scTemp = new Socket();
			scTemp = sTemp.accept();
			sc.add(scTemp);
			listening(i);
		}
		return;
	}
	
	
	public void listening(int i) throws IOException
	{
		Thread listener = new Thread()
		{
			public void run()
			{
				while(true){
				DataInputStream dis;
				try {
					dis = new DataInputStream(sc.get(i).getInputStream());
					String str = dis.readUTF();
					String[] arr = str.split(",");
					int pNum = Integer.parseInt(arr[0]);
					updatePosition(pNum, arr[1]);
				} catch (IOException e) {
					e.printStackTrace();
				}
							}
			}
		};
		listener.start();
		return;
	}
	
	public static void send(String str) throws IOException
	{
		
		for(int i = 0; i < nOfClients; i++)
		{
			DataOutputStream dos = new DataOutputStream(sc.get(i).getOutputStream());
			dos.writeUTF(str);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception
	{
		new Server();
	}
	
	public static void createTanks() throws IOException
	{
		for(int i = 0; i < nOfClients; i++)
		{
			Tank_actual tank = new Tank_actual(randomX(), randomY());
			tanks.add(tank);
			send("P," + tanks.get(i).getX() + "," + tanks.get(i).getY());
		}
		return;
		
	}
	
	public static int randomX()
	{
		int x = (int) (Math.random() * 1000);
		return x;
	}
	
	public static int randomY()
	{
		int y = (int) (Math.random() * 1000);
		return y;
	}
	
	public static void updatePosition(int pNum, String m)throws IOException
	{
		if(m.equals("w")){
			tanks.get(pNum-1).move(5);
		}
		else if(m.equals("a")){
			tanks.get(pNum-1).changeAngle(-4);
		}
		else if(m.equals("d")){
			tanks.get(pNum-1).changeAngle(4);
		}
		else if(m.equals("s")){
			tanks.get(pNum-1).move(-5);
		}
		send(pNum + "," + tanks.get(pNum-1).getAngle() + "," + tanks.get(pNum-1).getX() + "," + tanks.get(pNum-1).getY());
		return;
	}

	
}

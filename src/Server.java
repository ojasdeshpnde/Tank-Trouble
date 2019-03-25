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
			connect();
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
					send(str);
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

	
}

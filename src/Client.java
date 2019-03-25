import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JFrame
{
	public JFrame frame;
	public JPanel panel;
	public static int port = 6;
	static Socket socket;
	
	public Client() throws IOException
	{ 
		panel = new JPanel();
		this.setSize(500, 500);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		this.add(panel);
		this.setTitle("Client");
		connect();
	}
	
	public void connect() throws UnknownHostException, IOException 
	{
		socket = new Socket("127.0.0.1",port);
		startListener();
		return;
		
	}
	
	public void startListener() throws IOException
	{
		Thread listener = new Thread()
		{
			public void run()
			{
				DataInputStream dis;
				try {
					dis = new DataInputStream(socket.getInputStream());
					dis = new DataInputStream(socket.getInputStream());
					String str = dis.readUTF();
					System.out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		listener.start();
		return;
	}
	
	public static void send(String str) throws IOException
	{
			DataOutputStream dos = new
			DataOutputStream(socket.getOutputStream());
			dos.writeUTF(str);
	}
	

	
	public static void main(String[] args) throws IOException
	{
		Client c = new Client();
		send("client is me 2");
	}
	
	
}

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel{

	//public boolean[][] vertical = new boolean[18][10];
	//public boolean[][] horizontal = new boolean[19][9];


	public boolean[][] horizontal = new boolean[][] {
			{true,false,false,true,false,false,true,false,false},
			{false,false,false,false,false,true,true,true,true},
			{true,true,true,false,false,false,true,true,false},
			{false,false,false,true,false,true,false,true,false},
			{false,false,true,true,false,false,true,true,false},
			{true,false,true,true,true,false,false,true,true},
			{false,true,true,false,true,false,true,true,false},
			{false,false,true,true,true,true,false,false,false},
			{true,false,true,false,false,false,true,true,false},
			{false,false,false,true,false,false,true,false,true},
			{true,true,true,true,true,false,false,true,false},
			{true,false,false,false,false,true,false,false,false},
			{false,false,true,true,true,true,false,false,false},
			{false,true,false,false,true,false,false,true,false},
			{true,false,false,true,false,false,false,false,true},
			{false,false,false,false,false,true,false,true,false},
			{true,false,true,false,true,false,false,false,true},
			{false,true,true,true,true,true,false,false,false},
			{true,false,false,true,false,true,false,false,false}
	};
	public boolean[][] vertical = new boolean[][] {
		{false,true,false,true,false,true,true,false,false,false},
		{true,false,true,true,true,true,false,false,false,true},
		{false,false,false,false,false,true,false,true,false,true},
		{true,false,true,false,false,true,true,true,true,false},
		{false,false,true,false,false,true,true,false,true,false},
		{true,true,false,false,true,false,true,true,false,false},
		{false,true,false,false,false,false,true,false,false,true},
		{false,true,true,true,false,false,false,false,true,true},
		{false,false,true,false,true,true,false,true,false,true},
		{false,true,true,true,false,true,false,false,false,false},
		{false,false,false,false,false,true,true,false,true,false},
		{false,true,true,false,false,false,true,true,true,false},
		{false,true,true,true,false,false,true,false,true,true},
		{false,false,false,true,false,false,true,true,false,false},
		{true,false,true,true,false,true,true,false,true,true},
		{false,true,true,true,true,true,true,false,false,false},
		{false,false,true,false,true,false,true,false,true,false},
		{false,false,false,false,false,false,false,true,true,true}
	};
	BulletReal b = new BulletReal(.5,.5,400,220,System.currentTimeMillis());
	Tank_actual t = new Tank_actual(200,200);
	
	public static int port = 4;
	static Socket socket;
	
	public static ArrayList<Tank_actual> tanks;
	public static ArrayList<BufferedImage> images;
	public int nOfClients = 4;
	
	static DataOutputStream dos;
	
	boolean init = true;
	
	
	Map() throws IOException
	{
		setFocusable(true);	
		
		images = new ArrayList<BufferedImage>();
		for(int i = 0; i < nOfClients; i++)
		{
			if(i+1==1)
			{
				BufferedImage k = ImageIO.read(new File("t1.jpg"));
				images.add(k);
			}
			else if(i+1 == 2)
			{
				BufferedImage k = ImageIO.read(new File("t2.jpg"));
				images.add(k);
			}
			else if(i+1 == 3)
			{
				BufferedImage k = ImageIO.read(new File("t3.jpg"));
				images.add(k);
			}
			else if(i+1 == 4)
			{
				BufferedImage k = ImageIO.read(new File("t4.jpg"));
				images.add(k);
			}
			else
			{
				BufferedImage k = ImageIO.read(new File("t1.jpg"));
				images.add(k);
			}
			
				
		}
		addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='w') {
					try {
						send(port + ",w");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(e.getKeyChar()=='a') {
					
					try {
						send(port + ",a");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(e.getKeyChar()=='s') {
					
					try {
						send(port + ",s");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(e.getKeyChar()=='d') {
					
					try {
						send(port + ",d");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		/*
		for(int i = 0;i<vertical.length;i++) {
			int[] ran= new int[vertical[0].length];
			for(int j = 0;j<vertical[0].length;j++) {
				ran[j]=(int)(Math.random()*2);
			}
			for(int j = 0;j<vertical[0].length;j++) {
				vertical[i][j]=(ran[j]==0);
			}
		}
		
		for(int i = 0;i<horizontal.length;i++) {
			int[] ran= new int[horizontal[0].length];
			for(int j = 0;j<horizontal[0].length;j++) {
				ran[j]=(int)(Math.random()*2);
			}
			for(int j = 0;j<horizontal[0].length;j++) {
				horizontal[i][j]=(ran[j]==0);
			}
		}
		for(int i = 0;i<horizontal.length;i++) {
			String s ="{";
			for(int j = 0;j<horizontal[0].length;j++) {
				if(horizontal[i][j]) {
					s+="true,";
				}else {
					s+="false,";
				}
			}
			s=s.substring(0,s.length()-1)+"},";
			System.out.println(s);
		}
		System.out.println();
		for(int i = 0;i<vertical.length;i++) {
			String s ="{";
			for(int j = 0;j<vertical[0].length;j++) {
				if(vertical[i][j]) {
					s+="true,";
				}else {
					s+="false,";
				}
			}
			s=s.substring(0,s.length()-1)+"},";
			System.out.println(s);
		}
		*/
		tanks = new ArrayList<Tank_actual>();
		connect();
		repaint();
	}
	
	
	//                 NETWORKING METHODS
	
	public void connect() throws UnknownHostException, IOException 
	{
		socket = new Socket("192.168.0.10",port);
		startListener();
		dos = new DataOutputStream(socket.getOutputStream());
		return;
		
	}
	
	public void startListener() throws IOException
	{
		Thread listener = new Thread()
		{
			public void run()
			{
				while(true)
				{
				DataInputStream dis;
					try {
						dis = new DataInputStream(socket.getInputStream());
						String str = dis.readUTF();
						String[] ar = str.split(",");
						
						if(ar[0].equals("P"))
						{
							double x = Double.parseDouble(ar[1]);
							double y = Double.parseDouble(ar[2]);
							Tank_actual temp = new Tank_actual(x,y);
							tanks.add(temp);
						}
						else
						{
							System.out.println(str);
							int p = Integer.parseInt(ar[0]);
							double a = Double.parseDouble(ar[1]);
							double x = Double.parseDouble(ar[2]);
							double y = Double.parseDouble(ar[3]);
							tanks.get(p-1).changeX(x-tanks.get(p-1).getX());
							tanks.get(p-1).changeY(y-tanks.get(p-1).getY());
							tanks.get(p-1).changeAngle(a-tanks.get(p-1).getAngle());
						}
						
//						System.out.println(str);
//						if(str.equals("w"))
//							t.move(5);
//						else if(str.equals("a"))
//							t.changeAngle(-4);
//						else if(str.equals("d"))
//							t.changeAngle(4);
//						else if(str.equals("s"))
//							t.move(-5);
						repaint();
							
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
			dos.writeUTF(str);
			return;
	}
	
	
	//						MAP METHODS - NETWORKING ENDS
	
	public void paintComponent(Graphics g) {
		for(int i = 0;i<vertical.length;i++) {
			for(int j = 0;j<vertical[0].length;j++) {
				if(vertical[i][j]) {
					g.drawLine((i*100)+100, (j*100)+100, (i*100)+100, j*100);
				}
				
			}
		}
		for(int i = 0;i<horizontal.length;i++) {
			for(int j = 0;j<horizontal[0].length;j++) {
				if(horizontal[i][j]) {
					g.drawLine((i*100)+100, (j*100)+100, i*100, (j*100)+100);
				}
			}
		}
		g.drawRect(0, 0, 1900, 1000);
		b.setxpos(b.getxpos()+b.getvx());
		b.setypos(b.getypos()+b.getvy());
		
		if(b.getypos() ==0||b.getypos() ==990) {
			b.setVy(-b.getvy());
		}
		if(b.getxpos() ==0||b.getxpos()==1890) {
			b.setVx(-b.getvx());
		}
		
		if(b.getxpos()%100==0) {
			int x = (int)(b.getxpos()/100)-1;
			int y = (int)(b.getypos()/100);
			if(x>=0){
				if(vertical[x][y]) {
					b.setVx(-b.getvx());
				}
			}
		}
		
		if(b.getypos()%100==0) {
			int x = (int)(b.getxpos()/100);
			int y = (int)(b.getypos()/100)-1;
			if(y>=0) {
				if(horizontal[x][y]) {
					b.setVy(-b.getvy());
				}
			}
		}
		for(int i = 0; i < nOfClients; i++)
		{
			g.fillOval((int)b.getxpos(), (int)b.getypos(), 15, 15);
			double rotationRequired = Math.toRadians(tanks.get(i).getAngle());
			double locationX = 25;
			double locationY = 25;
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			g.drawImage(op.filter(images.get(i), null), (int)tanks.get(i).getX(), (int)tanks.get(i).getY(), null);
			if(System.currentTimeMillis()>b.time+10000)
			{
				b.delete();
			}
		}
		
	}
	public static void main (String[] args) throws IOException
	{
		//Instantiate the GUI part
		Frame frm = new JFrame();    
		//Set the application's window width and height in pixels
		frm.setSize (2560, 1600);  
		((JFrame) frm).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.add(new Map());
		//Make the window visible to the user
	    frm.setVisible (true);
	    refreshMap(frm);
	}
	
	public static void refreshMap(Frame frm)
	{
		Thread th = new Thread()
		{
			public void run()
			{
				while(true)
				{
					frm.repaint();
				}
			}
		};
		th.start();
		return;
	}


}
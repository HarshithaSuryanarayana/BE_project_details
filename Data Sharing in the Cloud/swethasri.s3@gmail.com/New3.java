import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class New3 extends JFrame implements Runnable,ActionListener
{
	JTextArea jta;
	JScrollPane jsp;
	JButton clear;
	ServerSocket ss;
	Socket s;
	String data="";

	New3()
	{
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(null);

		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jta.setLineWrap(true);
		clear = new JButton("Clear");

		jsp.setBounds(0,53,395,320);
		clear.setBounds(0,0,400,53);

		clear.addActionListener(this);

		this.add(jsp);
		this.add(clear);

		this.setVisible(true);
		this.setTitle("File Receiver");
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try
		{
			ss = new ServerSocket(2000);
		}catch(Exception ex){ex.printStackTrace();}
	}

	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("Server Waiting");
				s = ss.accept();
				System.out.println("Server Started");
				ObjectInputStream oInStream = new ObjectInputStream(s.getInputStream());
				data = (String) oInStream.readObject();
				if(data.equals("FILE_TRANSFER"))
				{
					int a = Integer.parseInt((String)oInStream.readObject());
					int c = Integer.parseInt((String)oInStream.readObject());
					System.out.println("Received Packet Size: "+a);
					int m = oInStream.available();
					byte b[] = new byte[c];
					System.out.println("----"+b.length);
					for(int i =1;i<=a;i++)
					{
						System.out.println("Reading File---");
						  b  = (byte[])	oInStream.readObject();
				//		oInStream.read(b,0,0);
						System.out.println("\nXXX--------"+new String(b));
				//		System.out.println("XXX--------"+b.toString()+"--------XXX");
						System.out.println("Read byte--");
			//			System.out.println("----"+b.length);
					}
				}
				oInStream.close();
				System.out.println("Closing Socket--");
			}
			catch(Exception ex){ex.printStackTrace();}

			jta.append(data);
		}

	}

	public static void main(String arg[])
	{
		New3 n3 = new New3();
		Thread t1 = new Thread(n3);
		t1.start();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==clear)
		{
			jta.setText("");
		}
	}
}

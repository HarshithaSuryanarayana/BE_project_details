import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FileDialog;
import java.util.*;

class New2 extends JFrame implements ActionListener
{
	JTextArea jta;
	JScrollPane jsp;
	JButton browse, send;
	String file="", path="",file_data="";
	FileDialog fileDialog;
	ServerSocket ss;
	Socket s;
	ObjectOutputStream oOutStream;
	byte file_byte[];
	
	New2()
	{
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(null);
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jta.setLineWrap(true);
		browse = new JButton("Browse");
		send = new JButton("Send");

		jsp.setBounds(0,53,395,320);
		send.setBounds(0,0,195,50);
		browse.setBounds(200,0,190,50);

		this.add(browse);
		this.add(send);
		this.add(jsp);

		browse.addActionListener(this);
		send.addActionListener(this);


		this.setVisible(true);
		this.setTitle("My Frame");
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String arg[])
	{
		new New2();
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==send)
		{
			sendFile();
		}
		else if(ae.getSource()==browse)
		{
			browseFile();
		}
	}

	public void browseFile()
	{
	   fileDialog = new FileDialog(new JFrame(),"Select file",FileDialog.LOAD);
	   fileDialog.setFile("*.txt");
	   fileDialog.setVisible(true);
	   String dir=fileDialog.getDirectory();
       file=fileDialog.getFile();
	   path=(dir+file);
	   System.out.println("Selected file path: "+path);

	   if (!path.equals("null")|| path==null)
	   {
		jta.setText(path);
	    try
		{
			FileInputStream fin=new FileInputStream(path);
			file_byte=new byte[fin.available()];
			fin.read(file_byte);
			file_data=new String(file_byte);
			jta.setText(file_data);
			fin.close();
		 }
	    catch (IOException io){io.printStackTrace();}
	}
	}

	public void sendFile()
	{
		int file_length = file_byte.length;
		int bit_size = 48;
		int a = file_length/bit_size;
		int b = file_length%bit_size;
		if(b!=0)
		a+= 1;
		System.out.println("FileLength:"+file_length+"  BitSize:"+bit_size+"  Packets:"+a+"  ExtraBits:"+b);

		try{
				s = new Socket("localhost",2000);
	 			oOutStream = new ObjectOutputStream(s.getOutputStream());
	 			int c = 0;
	 			oOutStream.writeObject("FILE_TRANSFER");
	 			oOutStream.writeObject(Integer.toString(a));
	 			oOutStream.writeObject(Integer.toString(file_length));
/*	 			for(int i =1;i<=a;i++)
	 			{
	 				System.out.println("Writing bytes --- 1");
	 				System.out.println("Value of i : "+i+"   Value of a:  "+a+"   Value of c:"+c);
	 				if(i<a)
		 			oOutStream.write(file_byte,c,0);
	 				if((b!=0)&&(i==a))
	 				{
	 					System.out.println("Writing bytes--- 2");
	 					System.out.println("Value of c:"+c);
	 					oOutStream.write(file_byte,c,b);
	 				}
	 				if(i<a)
		 			c +=48;
		 			else
		 			c +=b;
	 			}
*/			
			byte[] p = new byte[48];
			for(int i = 1;i<=a;i++)
			{
				for(int j = 0;j<48;j++)
				{
					if(c<file_length)
					p[j] = file_byte[c];		
					c++;		
				}
				oOutStream.writeObject(p);
				System.out.println("\nXXXXXX===="+new String(p));
				
			}

			 }
	 	catch(Exception ex){ex.printStackTrace();}
	}
}

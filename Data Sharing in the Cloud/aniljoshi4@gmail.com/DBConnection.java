package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection  
{	
	private String url;
	private Connection con;
	public String driver,ip;
	private FileInputStream fis;
	public DBConnection()
	{
		try
		{		
				ip=getIp();
				url = "jdbc:mysql://"+ip+":3306/data sharing in cloud"; 	
				driver = "com.mysql.jdbc.Driver";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException,ClassNotFoundException
	{
		Class.forName( driver );
		con = DriverManager.getConnection(url,"root","root");
		return con;
	}
	public String getIp()throws IOException,FileNotFoundException
	{
		fis = new FileInputStream("C:\\ip.txt");

		byte[] b = new byte[fis.available()];

		fis.read(b);

		String ipAdd = new String(b);
		
		return ipAdd;

	}
	}

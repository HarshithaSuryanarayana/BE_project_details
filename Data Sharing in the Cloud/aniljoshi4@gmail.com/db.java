import java.sql.*;

class db
{
	public static void main(String args[])
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:userinfo");
			
			PreparedStatement pst=con.prepareStatement("delete from userinfor");
			pst.executeUpdate();
			PreparedStatement pst2=con.prepareStatement("delete from user_name_ip_port");
			pst2.executeUpdate();
			System.out.println("Execution Over");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

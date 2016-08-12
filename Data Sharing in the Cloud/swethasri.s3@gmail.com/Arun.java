package SenderPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Arun {
public static void main(String arg[])
{
	
}

public Set fun1(String ddd) {
	// TODO Auto-generated method stub
	Set li=new HashSet();
	DbConnection db=new DbConnection();
	Connection con=db.getCon();
	try
	{
		Statement stmt=con.createStatement();
		ResultSet rst=stmt.executeQuery("select * from userinfor where UName='"+ddd+"'");
		
		if(rst.next())
		{
			System.out.println("list value is "+li);
			li.add(rst.getString(1).toString());
			System.out.println("list value is "+li);
			li.add(rst.getString(2).toString());
			System.out.println("list value is "+li);
			li.add(rst.getString(3).toString());
			System.out.println("list value is "+li);
			li.add(rst.getString(4).toString());
		
		}
		System.out.println("list value is "+li);
		
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return li;
}
}

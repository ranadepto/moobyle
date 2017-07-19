package CommonClasses;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database
{
	public Connection getConnection()
	{
		final String URL = "jdbc:mysql://127.0.0.1:3306/moobyle";
		final String USER = "root";
		final String PASSWORD = "";

		//moobyle Database
//		final String URL = "jdbc:mysql://127.0.0.1:3306/tomcathosting1_5";
//		final String USER = "tomcathosting1_5";
//		final String PASSWORD = "wcJbtftzW5Fjaws";
		
		final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		Connection connection = null;
		try
		{
			Class.forName(DRIVER_CLASS).newInstance();
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}

}

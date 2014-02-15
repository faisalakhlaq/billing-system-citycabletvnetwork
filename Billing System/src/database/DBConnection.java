package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	private static String driver = "com.mysql.jdbc.Driver";

	private static String url = "jdbc:mysql://localhost:3306/";

	private static String dbname = "citycabletvnetwork";

	private static DBConnection instance = null;

	private static Connection conn = null;

	private DBConnection()
	{
	}

	public static DBConnection getInstance()
	{
		if (instance == null)
		{
			instance = new DBConnection();
		}
		return instance;
	}

	public synchronized Connection getConnection()
	{
		try
		{
			if (conn != null && !conn.isClosed()) return conn;
			Class.forName(driver).newInstance();
		}
		catch (java.lang.ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			System.out.println("InstantiationException: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			System.out.println("IllegalAccessException: " + e.getMessage());
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("MySQL JDBC Driver Registered!");

		try
		{
			conn = DriverManager.getConnection(url + dbname, "root", "root");
		}
		catch (SQLException ex)
		{
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			ex.printStackTrace();
		}
		if (conn != null)
		{
			System.out.println("connection established");
		}
		else
		{
			System.out.println("Failed to make database connection!");
		}
		return conn;
	}

}

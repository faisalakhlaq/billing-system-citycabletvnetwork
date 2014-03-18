package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import properties.DBProperties;

public class DBConnection
{
	private static String driver = "com.mysql.jdbc.Driver";

	// private static String url = "jdbc:mysql://localhost:3306/";

	// private static String dbname = "citycabletvnetwork";

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
			DBProperties DBProp = new DBProperties();
			Properties prop = DBProp.getPropFile();
			String dbname = prop.getProperty("dbname");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pwd = prop.getProperty("password");

			conn = DriverManager.getConnection(url + dbname, user, pwd);
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
			System.out.println("Failed to get database connection!");
		}
		return conn;
	}

	public static void closeConnection()
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}

	}

}

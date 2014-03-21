package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyConnection
{
	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";

	// private String dbName = "BillingDB";

	private String dbName = "BillingSystemDB";

	private String protocol = "jdbc:derby:";

	private static DerbyConnection instance = null;

	private static Connection conn = null;

	private DerbyConnection()
	{
	}

	public synchronized Connection getConnection()
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				return conn;
			}
			loadDriver();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		try
		{
			// DBProperties DBProp = new DBProperties();
			// Properties prop = DBProp.getPropFile();
			// String dbname = prop.getProperty("dbname");
			// String url = prop.getProperty("url");
			// String user = prop.getProperty("user");
			// String pwd = prop.getProperty("password");

			conn = DriverManager.getConnection(protocol + dbName);
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

	public static DerbyConnection getInstance()
	{
		if (instance == null)
		{
			instance = new DerbyConnection();
		}
		return instance;
	}

	public static void closeConnection() throws SQLException
	{
		if (conn != null)
		{
			conn.close();
		}
	}

	private void loadDriver()
	{
		/*
		 * The JDBC driver is loaded by loading its class. If you are using JDBC
		 * 4.0 (Java SE 6) or newer, JDBC drivers may be automatically loaded,
		 * making this code optional.
		 * 
		 * In an embedded environment, this will also start up the Derby engine
		 * (though not any databases), since it is not already running. In a
		 * client environment, the Derby engine is being run by the network
		 * server framework.
		 * 
		 * In an embedded environment, any static Derby system properties must
		 * be set before loading the driver to take effect.
		 */
		try
		{
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		}
		catch (ClassNotFoundException cnfe)
		{
			System.err.println("\nClassNotFoundException! Unable to load the JDBC driver " + driver);
			System.err.println("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		}
		catch (InstantiationException ie)
		{
			System.err.println("\nInstantiationException! Unable to instantiate the JDBC driver " + driver);
			ie.printStackTrace(System.err);
		}
		catch (IllegalAccessException iae)
		{
			System.err.println("\nIllegalAccessException! Not allowed to access the JDBC driver " + driver);
			iae.printStackTrace(System.err);
		}
	}

	/**
	 * This method generates an error. Therefore it should not be used.
	 * <p>
	 * TODO change OR remove
	 */
	public void shutDownDataBase()
	{
		try
		{
			// the shutdown=true attribute shuts down Derby
			DriverManager.getConnection("jdbc:derby:" + "BillingSystemDB" + ";shutdown=true");

			// To shut down a specific database only, but keep the
			// engine running (for example for connecting to other
			// databases), specify a database in the connection URL:
			// DriverManager.getConnection("jdbc:derby:" + dbName +
			// ";shutdown=true");
		}
		catch (SQLException se)
		{
			if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState()))))
			{
				// we got the expected exception
				System.out.println("Derby shut down normally");
				// Note that for single database shutdown, the expected
				// SQL state is "08006", and the error code is 45000.
			}
			else
			{
				// if the error code or SQLState is different, we have
				// an unexpected exception (shutdown failed)
				System.err.println("Derby did not shut down normally");
				se.printStackTrace();
			}
		}
	}
}

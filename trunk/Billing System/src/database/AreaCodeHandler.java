package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import model.AreaCode;
import utils.CustomPair;

public class AreaCodeHandler
{
	private DBConnection db = null;

	// private DerbyConnection db = null;

	public AreaCodeHandler()
	{
	}

	/**
	 * Provide the area code and the area name will be retrieved from the
	 * database
	 * 
	 * @param AreaCode
	 */
	public String searchAreaName(int areaCode) throws Exception
	{
		String areaName = null;
		db = DBConnection.getInstance();

		Connection conn = db.getConnection();

		PreparedStatement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database. conection = null!");
		}
		try
		{
			String query = "Select area_name from area_codes where area_code = ?";
			st = conn.prepareStatement(query);
			st.setInt(1, areaCode);
			System.out.println("Executed Query: " + query + areaCode);
			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				areaName = rs.getString("area_name");
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve area name from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve area name from the database!" + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (st != null)
				{
					st.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or statement: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement. " + e1.getMessage());
			}
		}
		return areaName;
	}

	/**
	 * Provide the area name and the corresponding code will be retrieved for it
	 */
	public int searchAreaCode(String areaName) throws Exception
	{
		int areaCode = 0;
		db = DBConnection.getInstance();

		Connection conn = db.getConnection();

		PreparedStatement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database. conection = null!");
		}
		try
		{
			String query = "Select area_code from area_codes where area_name = ?";
			st = conn.prepareStatement(query);
			st.setString(1, areaName);
			System.out.println("Executed Query: " + query + areaName);
			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				areaCode = rs.getInt("area_code");
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve area code from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve area code from the database!" + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (st != null)
				{
					st.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or statement: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement. " + e1.getMessage());
			}
		}
		return areaCode;
	}

	public Vector<AreaCode> getAllAreaCodes() throws Exception
	{
		Vector<AreaCode> codes = null;

		db = DBConnection.getInstance();

		Connection con = db.getConnection();

		Statement stmt = null;

		if (con == null)
		{
			throw new Exception("Unable to connect to the database!");
		}
		try
		{
			stmt = con.createStatement();
			String query = "SELECT * FROM AREA_CODES";
			System.out.println("Query Executed: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				codes = new Vector<AreaCode>();
				while (rs.next())
				{
					AreaCode code = new AreaCode();
					code.setAreaCode(rs.getInt("area_code"));
					code.setAreaName(rs.getString("area_name"));

					codes.add(code);
				}
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve area codes from the database. " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve area codes data from the database!<p>" + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or statement: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement.");
			}
		}
		return codes;
	}

	public Vector<Integer> getCodes() throws Exception
	{
		Vector<Integer> codes = null;

		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		Statement stmt = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database!");
		}
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT AREA_CODE FROM AREA_CODES;";
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				codes = new Vector<Integer>();
				while (rs.next())
				{
					codes.add(rs.getInt("area_code"));
				}
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve area codes from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve area codes data from the database!" + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or statement: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement.");
			}
		}
		return codes;
	}

	/**
	 * Retrieve all area names from the databases
	 */
	public ArrayList<String> getAreaNames() throws Exception
	{
		ArrayList<String> codes = null;

		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		Statement stmt = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database!");
		}
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT area_name FROM AREA_CODES;";
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				codes = new ArrayList<String>();
				while (rs.next())
				{
					codes.add(rs.getString("area_name"));
				}
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve area names from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve area names data from the database!" + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or statement: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement.");
			}
		}
		return codes;
	}

	public boolean deleteAreaCode(int areaCode) throws Exception
	{
		boolean deleted = false;
		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		Statement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database. conection = null!");
		}
		try
		{
			st = conn.createStatement();
			// The execute method return boolean value. check what is the
			// meaning of it
			st.execute("DELETE FROM AREA_CODES WHERE AREA_CODE = " + areaCode + ";");
			deleted = true;
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to delete area code: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to delete area code!" + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (st != null)
				{
					st.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or statement: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement. " + e1.getMessage());
			}
		}
		return deleted;
	}

	public CustomPair addAreaCode(AreaCode c)
	{
		CustomPair inserted = new CustomPair();
		inserted.setBooleanKey(false);
		if (c == null)
		{
			inserted.setValue("Cannot add an empty or null area code");
			return inserted;
		}

		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null)
		{
			inserted.setValue("Cannot get a connection to the database");
			return inserted;
		}
		try
		{
			stmt = conn.prepareStatement("INSERT INTO AREA_CODES(area_code, area_name) " + "VALUES (?,?)");

			stmt.setInt(1, c.getAreaCode());
			stmt.setString(2, c.getAreaName());

			stmt.executeUpdate();
			inserted.setValue("Area code added successfully!");
			inserted.setBooleanKey(true);
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while adding the area code: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			inserted.setValue("Unable to add area code. " + e1.getMessage());
		}
		finally
		{
			try
			{
				DBConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while inserting the area code: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				inserted.setValue("Unable to add area code. " + e1.getMessage());
			}
		}
		return inserted;
	}
}

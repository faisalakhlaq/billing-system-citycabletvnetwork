package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.AdvertisementBill;

public class AdvertisementBillHandler
{
	DBConnection db = null;

	public AdvertisementBillHandler()
	{
	}

	public void deleteBill(int id) throws Exception
	{
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
			st.execute("Delete from ADVERTISEMENT_Bill where ID = " + id + ";");
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to delete ADVERTISEMENT Bill: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to delete ADVERTISEMENT Bill!" + e1.getMessage());
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
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
	}

	public void addBill(AdvertisementBill bill) throws Exception
	{
		if (bill == null)
		{
			throw new Exception("Cannot add an empty or null bill");
		}

		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null)
		{
			throw new Exception("Cannot get a connection to the database");
		}
		try
		{
			stmt = conn.prepareStatement("INSERT INTO citycabletvnetwork.ADVERTISEMENT_BILL(payable_amount, date, account_number, paid)" + "VALUES (?,?,?,?)");
			java.sql.Date date = (bill.getDate() == null) ? null : new java.sql.Date(bill.getDate().getTime());

			stmt.setInt(1, bill.getPayableAmount());
			stmt.setDate(2, date);
			stmt.setInt(3, bill.getAccountNumber());
			stmt.setBoolean(4, bill.getPaid());

			stmt.executeUpdate();
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while adding the ADVERTISEMENT bill: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to add the ADVERTISEMENT bill. " + e1.getMessage());
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while inserting the ADVERTISEMENT bill: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Unable to add ADVERTISEMENT bill. " + e1.getMessage());
			}
		}
	}
}

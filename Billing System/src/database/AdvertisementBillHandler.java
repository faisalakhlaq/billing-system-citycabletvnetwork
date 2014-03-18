package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import model.AdvertisementBill;

public class AdvertisementBillHandler
{
	DBConnection db = null;

	public AdvertisementBillHandler()
	{
	}

	/**
	 * Returns the Advertisement bills paid between the specified dates
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public ArrayList<AdvertisementBill> getSales(Date fromDate, Date toDate) throws Exception
	{
		ArrayList<AdvertisementBill> billList = null;
		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		Statement stmt = null;

		if (conn == null)
		{
			throw new Exception("Cannot get a connection to the database");
		}
		try
		{
			stmt = conn.createStatement();
			String query = "Select * from advertisement_bill where " + new java.sql.Date(fromDate.getTime()) + " <= date <= " + new java.sql.Date(toDate.getTime())
					+ " and paid = True;";
			System.out.println("Executing Query: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				billList = new ArrayList<AdvertisementBill>();
				while (rs.next())
				{
					AdvertisementBill bill = new AdvertisementBill();

					bill.setId(rs.getInt("id"));
					bill.setDate(rs.getDate("date"));
					bill.setAccountNumber(rs.getInt("account_number"));
					bill.setPayableAmount(rs.getInt("payable_amount"));
					bill.setPaid(rs.getBoolean("paid"));

					billList.add(bill);
				}
			}
		}
		catch (Exception e)
		{
			Logger.getGlobal().severe("Error occured while retrieving paid Advertisement bills: " + e.getMessage());
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Error while retrieving paid Advertisement bills:" + e.getMessage());
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
				Logger.getGlobal().severe("Error occured while retrieving paid Advertisement bills: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Unable to retrieve paid Advertisement bills. " + e1.getMessage());
			}
		}
		return billList;
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

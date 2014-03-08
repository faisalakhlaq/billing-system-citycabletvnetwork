package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import model.Bill;

public class BillHandler
{
	DBConnection db = null;

	public BillHandler()
	{
	}

	/**
	 * Returns the bills paid among the specified dates
	 * @throws Exception 
	 */
	public void getSales(Date fromDate, Date toDate) throws Exception
	{
		Vector<Bill> billList = null;
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
			String query = "Select * from bill where account_number = " + fromDate + ";";
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				billList = new Vector<Bill>();
				while (rs.next())
				{
					Bill bill = new Bill();

					bill.setBillNumber(rs.getInt("bill_number"));
					bill.setIssueDate(rs.getDate("issue_date"));
					bill.setDueDate(rs.getDate("due_date"));
					bill.setAccountNumber(rs.getInt("account_number"));
					bill.setMonth(rs.getString("month"));
					bill.setYear(rs.getInt("year"));
					bill.setPayableAmount(rs.getInt("payable_amount"));
					bill.setReceivedAmount(rs.getInt("received_amount"));
					bill.setReceivedBy(rs.getString("received_by"));
					bill.setPaid(rs.getBoolean("paid"));

					billList.add(bill);
				}
			}
		}
		catch (Exception e)
		{
			Logger.getGlobal().severe("Error occured while retrieving customer bill: " + e.getMessage());
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Error while retrieving customer bill:" + e.getMessage());
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
				Logger.getGlobal().severe("Error occured while retrieving customer bills: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Unable to retrieve customer bills. " + e1.getMessage());
			}
		}
//		return null;
	}

	public void deleteBill(int billNumber) throws Exception
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
			st.execute("Delete from Bill where bill_number = " + billNumber + ";");
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to delete Bill: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to delete Bill!" + e1.getMessage());
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

	public void updateBill(Bill b) throws Exception
	{
		if (b == null)
		{
			throw new Exception("Unable to pay the bill. Bill = NULL!");
		}

		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		Statement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database! conn = null");
		}
		try
		{
			st = conn.createStatement();
			String query = "Update BILL set received_amount = '" + b.getReceivedAmount() + "', received_by = '" + b.getReceivedBy() + "', paid = '" + (b.getPaid() == true ? 1 : 0)
					+ "' where bill_number = '" + b.getBillNumber() + "';";
			System.out.println("Query Executed: " + query);
			Logger.getGlobal().fine("Query Executed: " + query);
			st.execute(query);
		}
		catch (SQLException e)
		{
			Logger.getGlobal().severe("Error occured while updating - paying bill: " + e.getMessage());
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Unable to update - pay bill. " + e.getMessage());
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
				Logger.getGlobal().severe("Error occured while updating - paying: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Unable to update - pay. " + e1.getMessage());
			}
		}
	}

	/**
	 * Query the database
	 * 
	 * @param Bill
	 *            number which is to be searched
	 * @return Bill record or NULL if no record found
	 * @throws Exception
	 */
	public Bill searchBill(int billNumber) throws Exception
	{
		Bill bill = null;
		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database. conection = null!");
		}
		try
		{
			st = conn.prepareStatement("Select * from bill where bill_number = ?");
			st.setInt(1, billNumber);
			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				bill = new Bill();
				bill.setBillNumber(rs.getInt("bill_number"));
				bill.setIssueDate(rs.getDate("issue_date"));
				bill.setDueDate(rs.getDate("due_date"));
				bill.setAccountNumber(rs.getInt("account_number"));
				bill.setMonth(rs.getString("month"));
				bill.setYear(rs.getInt("year"));
				bill.setPayableAmount(rs.getInt("payable_amount"));
				bill.setReceivedAmount(rs.getInt("received_amount"));
				bill.setReceivedBy(rs.getString("received_by"));
				bill.setPaid(rs.getBoolean("paid"));
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve bill data from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve bill data from the database!" + e1.getMessage());
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
		return bill;
	}

	/**
	 * Retrieve all the bills of a customer
	 * 
	 * @return Vector of bill
	 * */
	public Vector<Bill> getCustomerBills(int accountNumber) throws Exception
	{
		Vector<Bill> billList = null;
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
			String query = "Select * from bill where account_number = " + accountNumber + ";";
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				billList = new Vector<Bill>();
				while (rs.next())
				{
					Bill bill = new Bill();

					bill.setBillNumber(rs.getInt("bill_number"));
					bill.setIssueDate(rs.getDate("issue_date"));
					bill.setDueDate(rs.getDate("due_date"));
					bill.setAccountNumber(rs.getInt("account_number"));
					bill.setMonth(rs.getString("month"));
					bill.setYear(rs.getInt("year"));
					bill.setPayableAmount(rs.getInt("payable_amount"));
					bill.setReceivedAmount(rs.getInt("received_amount"));
					bill.setReceivedBy(rs.getString("received_by"));
					bill.setPaid(rs.getBoolean("paid"));

					billList.add(bill);
				}
			}
		}
		catch (Exception e)
		{
			Logger.getGlobal().severe("Error occured while retrieving customer bill: " + e.getMessage());
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Error while retrieving customer bill:" + e.getMessage());
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
				Logger.getGlobal().severe("Error occured while retrieving customer bills: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Unable to retrieve customer bills. " + e1.getMessage());
			}
		}
		return billList;
	}

	public void insertGeneratedBill(Bill bill) throws Exception
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
			stmt = conn
					.prepareStatement("INSERT INTO citycabletvnetwork.BILL(issue_date, due_date, account_number, month, year, payable_amount, received_amount, received_by, paid)"
							+ "VALUES (?,?,?,?,?,?,?,?,?)");
			java.sql.Date issueDate = (bill.getIssueDate() == null) ? null : new java.sql.Date(bill.getIssueDate().getTime());
			java.sql.Date dueDate = (bill.getDueDate() == null) ? null : new java.sql.Date(bill.getDueDate().getTime());

			/*
			 * issue_date DATE, due_date DATE, account_number INT, month
			 * VARCHAR(10), year INT, payable_amount INT, received_amount INT,
			 * received_by VARCHAR(20), paid BOOL
			 */

			stmt.setDate(1, issueDate);
			stmt.setDate(2, dueDate);
			stmt.setInt(3, bill.getAccountNumber());
			stmt.setString(4, bill.getMonth());
			stmt.setInt(5, bill.getYear());
			stmt.setInt(6, bill.getPayableAmount());
			stmt.setInt(7, bill.getReceivedAmount());
			stmt.setString(8, bill.getReceivedBy());
			stmt.setBoolean(9, bill.getPaid());

			stmt.executeUpdate();
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while adding the generated bill: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to add the generated bill. " + e1.getMessage());
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
				Logger.getGlobal().severe("Error occured while inserting the generated bill: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Unable to add generated bill. " + e1.getMessage());
			}
		}
	}
}

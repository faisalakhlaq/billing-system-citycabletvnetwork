package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import model.Bill;

public class BillHandler
{
	DBConnection db = null;

	public BillHandler()
	{
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

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import model.Customer;
import utils.CustomPair;

public class CustomerHandler
{
	DBConnection db = null;

	public CustomerHandler()
	{
	}

	/**
	 * Queries the database
	 * 
	 * @return An ArrayList of Customers or NULL if no record found
	 * @throws Exception
	 */
	public Vector<Customer> getAllCustomers() throws Exception
	{
		Vector<Customer> customerList = null;

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
			String query = "Select * from Customer;";
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				customerList = new Vector<Customer>();
				while (rs.next())
				{
					Customer c = new Customer();

					c.setAccountNumber(rs.getInt("account_number"));
					c.setDate(rs.getDate("date"));
					c.setCustomerName(rs.getString("name"));
					c.setCustomerAddress(rs.getString("address"));
					c.setAdvance(rs.getInt("advance"));
					c.setNicNumber(rs.getString("nic_number"));
					c.setTelNumber(rs.getInt("telephone"));
					c.setConnectionType(rs.getString("connection_type"));
					c.setConnectionFee(rs.getInt("connection_fee"));

					customerList.add(c);
				}
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve customer data from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve customer data from the database!");
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
				Logger.getGlobal().severe("Error occured while closing the connection : " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or statement.");
			}
		}
		return customerList;
	}

	/**
	 * Add a new customer to the database.
	 * <p>
	 * Return and informative message if the customer cannot be added
	 */
	public CustomPair addNewCustomer(Customer c)
	{
		CustomPair inserted = new CustomPair();
		inserted.setBooleanKey(false);
		if (c == null)
		{
			inserted.setValue("Cannot add an empty or null customer");
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
			stmt = conn
					.prepareStatement("INSERT INTO citycabletvnetwork.CUSTOMER(account_number, date, name, address, advance, nic_number, telephone, connection_type, connection_fee) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)");
			java.sql.Date date = (c.getDate() == null) ? null : new java.sql.Date(c.getDate().getTime());

			stmt.setInt(1, c.getAccountNumber());
			stmt.setDate(2, date);
			stmt.setString(3, c.getCustomerName());
			stmt.setString(4, c.getCustomerAddress());
			stmt.setInt(5, c.getAdvance());
			stmt.setString(6, c.getNicNumber());
			stmt.setInt(7, c.getTelNumber());
			stmt.setString(8, c.getConnectionType());
			stmt.setInt(9, c.getConnectionFee());

			stmt.executeUpdate();
			inserted.setValue("Customer added successfully!");
			inserted.setBooleanKey(true);
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while adding the customer: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			inserted.setValue("Unable to add customer. " + e1.getMessage());
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
				Logger.getGlobal().severe("Error occured while inserting the customer: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				inserted.setValue("Unable to add customer. " + e1.getMessage());
			}
		}
		return inserted;
	}
}

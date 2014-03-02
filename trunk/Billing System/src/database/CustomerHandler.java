package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

import model.Customer;
import utils.CustomPair;

public class CustomerHandler
{
	DBConnection db = null;

	// TODO should execute the queries in a separate thread
	public CustomerHandler()
	{
	}

	public boolean deleteCustomer(int customerAccouontNumer) throws Exception
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
			st.execute("Delete from Customer where account_number = " + customerAccouontNumer + ";");
			deleted = true;
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to delete customer: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to delete customer!" + e1.getMessage());
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
		return deleted;
	}

	/**
	 * Query the database
	 * 
	 * @param Account
	 *            number of the customer to be searched in the database
	 * @return Customer record or NULL if no record found
	 * @throws Exception
	 */
	public Customer searchCustomer(int customerAccountNumber) throws Exception
	{
		Customer c = null;
		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database. conection = null!");
		}
		try
		{
			st = conn.prepareStatement("Select * from Customer where account_number = ?");
			st.setInt(1, customerAccountNumber);
			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				c = new Customer();
				c.setAccountNumber(customerAccountNumber);
				c.setDate(rs.getDate("date"));
				c.setCustomerName(rs.getString("name"));
				c.setCustomerAddress(rs.getString("address"));
				c.setAdvance(rs.getInt("advance"));
				c.setNicNumber(rs.getString("nic_number"));
				c.setTelNumber(rs.getInt("telephone"));
				c.setConnectionType(rs.getString("connection_type"));
				c.setConnectionFee(rs.getInt("connection_fee"));
				c.setAreaCode(rs.getInt("area_code"));
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve customer data from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve customer data from the database!" + e1.getMessage());
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
		return c;
	}

	/**
	 * Queries the database
	 * 
	 * @return Vector of Customers or NULL if no record found
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
					c.setAreaCode(rs.getInt("area_code"));

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

	public Map<Integer, String> getCustomerConnectionTypes() throws Exception
	{
		Map<Integer, String> conTypes = null;
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
			String query = "Select account_number, connection_type from Customer;";
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				conTypes = new HashMap<Integer, String>();
				while (rs.next())
				{
					conTypes.put(rs.getInt("account_number"), rs.getString("connection_type"));
				}
			}
		}
		catch (Exception e1)
		{
			Logger.getGlobal().severe("Unable to retrieve customer account numbers from the database: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception("Unable to retrieve customer account numbers from the database!");
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
		return conTypes;
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
					.prepareStatement("INSERT INTO citycabletvnetwork.CUSTOMER(account_number, date, name, address, advance, nic_number, telephone, connection_type, connection_fee, area_code) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
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
			stmt.setInt(10, c.getAreaCode());

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

	public void updateCustomer(Customer b) throws Exception
	{
		db = DBConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		Statement st = null;

		if (conn == null)
		{
			throw new Exception("Unable to connect to the database! <p>conn = null");
		}
		try
		{
			/*
			 * date DATE, name VARCHAR(20) NOT NULL, address VARCHAR(60) NOT
			 * NULL, advance INT, nic_number VARCHAR(20), telephone int,
			 * connection_type VARCHAR(15), connection_fee int, area_code int
			 */
			st = conn.createStatement();
			String query = "Update CUSTOMER set date = '" + b.getDate() + "', name = '" + b.getCustomerName() + "', address = '" + b.getCustomerAddress() + "', advance = '"
					+ b.getAdvance() + "', nic_number = '" + b.getNicNumber() + "', telephone = '" + b.getTelNumber() + "', connection_type = '" + b.getConnectionType()
					+ "', connection_fee = '" + b.getConnectionFee() + "', area_code = '" + b.getAreaCode() + "' where account_number = '" + b.getAccountNumber() + "';";
			System.out.println("Query Executed: " + query);
			Logger.getGlobal().fine("Query Executed: " + query);
			st.execute(query);
		}
		catch (SQLException e)
		{
			Logger.getGlobal().severe("Error occured while updating - customer<p> " + e.getMessage());
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Error! Unable to update - customer. <p>" + e.getMessage());
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
				Logger.getGlobal().severe("Error occured while updating - customer<p> " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new Exception("Error . Unable to update - customer.<p> " + e1.getMessage());
			}
		}
	}

	/**
	 * Retrieves the customers of a specific area from the customer table using
	 * the area code
	 * 
	 * @throws Exception
	 */
	public Vector<Customer> getAreaCustomers(int areaCode) throws Exception
	{
		Vector<Customer> customers = null;

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
			String query = "Select * from Customer where area_code = '" + areaCode + "';";
			System.out.println("Query Executed: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				customers = new Vector<Customer>();
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
					c.setAreaCode(rs.getInt("area_code"));

					customers.add(c);
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
		return customers;
	}
}

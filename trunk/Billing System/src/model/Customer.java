package model;

import java.util.Date;

public class Customer
{
	private int accountNumber;

	private Date date;

	private String customerName;

	private String customerAddress;

	private int advance;

	private String nicNumber;

	private int telNumber;

	private String connectionType;

	private int connectionFee;

	public Customer()
	{

	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public int getAccountNumber()
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getCustomerAddress()
	{
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress)
	{
		this.customerAddress = customerAddress;
	}

	public String getNicNumber()
	{
		return nicNumber;
	}

	public void setNicNumber(String nicNumber)
	{
		this.nicNumber = nicNumber;
	}

	public int getTelNumber()
	{
		return telNumber;
	}

	public void setTelNumber(int telNumber)
	{
		this.telNumber = telNumber;
	}

	public int getConnectionFee()
	{
		return connectionFee;
	}

	public void setConnectionFee(int connectionFee)
	{
		this.connectionFee = connectionFee;
	}

	public String getConnectionType()
	{
		return connectionType;
	}

	public void setConnectionType(String connectionType)
	{
		this.connectionType = connectionType;
	}

	public int getAdvance()
	{
		return advance;
	}

	public void setAdvance(int connectionFee)
	{
		this.advance = connectionFee;
	}

}

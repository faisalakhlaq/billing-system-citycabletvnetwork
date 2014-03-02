package model;

import java.util.Date;

public class Customer implements ModelObject
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

	private int areaCode;

	public Customer()
	{

	}

	public Customer(int acNumber, Date d, String name, String address, int advance, String nic, int tel, String conType, int conFee)
	{
		accountNumber = acNumber;
		date = d;
		customerName = name;
		customerAddress = address;
		this.advance = advance;
		nicNumber = nic;
		telNumber = tel;
		connectionType = conType;
		connectionFee = conFee;

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

	public int getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(int areaCode)
	{
		this.areaCode = areaCode;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		if (!(obj instanceof Customer)) return equal;

		Customer c = (Customer) obj;

		// if(this.hashCode() == c.hashCode()) // TODO check if hashcode needs
		// to be checked here
		if (this.accountNumber == c.getAccountNumber() && this.date.equals(c.getDate()) && this.customerName.equals(c.getCustomerName())
				&& this.customerAddress.equals(c.getCustomerAddress()) && this.advance == c.getAdvance() && this.nicNumber.equals(c.getNicNumber())
				&& this.telNumber == c.getTelNumber() && this.connectionType.equals(c.getConnectionType()) && this.connectionFee == c.getConnectionFee()
				&& this.areaCode == c.getAreaCode())
		{
			equal = true;
		}
		return equal;
	}
}

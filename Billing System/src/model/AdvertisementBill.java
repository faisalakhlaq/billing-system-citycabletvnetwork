package model;

import java.util.Date;

public class AdvertisementBill implements ModelObject
{
	private int id;

	private Date date;

	private int accountNumber;

	private int payableAmount;

	private Boolean paid;

	public AdvertisementBill()
	{
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public int getPayableAmount()
	{
		return payableAmount;
	}

	public void setPayableAmount(int payableAmount)
	{
		this.payableAmount = payableAmount;
	}

	public Boolean getPaid()
	{
		return paid;
	}

	public void setPaid(Boolean paid)
	{
		this.paid = paid;
	}

}

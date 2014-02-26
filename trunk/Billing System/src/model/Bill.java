package model;

import java.util.Date;

public class Bill
{
	private int billNumber;

	private Date issueDate;

	private Date dueDate;

	private int accountNumber;

	private String month;

	private int year;

	private int payableAmount;

	private int receivedAmount;

	private String receivedBy;

	private Boolean paid;

	public Bill()
	{
	}

	public int getBillNumber()
	{
		return billNumber;
	}

	public void setBillNumber(int billNumber)
	{
		this.billNumber = billNumber;
	}

	public Date getIssueDate()
	{
		return issueDate;
	}

	public void setIssueDate(Date issueDate)
	{
		this.issueDate = issueDate;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	public int getAccountNumber()
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getPayableAmount()
	{
		return payableAmount;
	}

	public void setPayableAmount(int payableAmount)
	{
		this.payableAmount = payableAmount;
	}

	public int getReceivedAmount()
	{
		return receivedAmount;
	}

	public void setReceivedAmount(int receivedAmount)
	{
		this.receivedAmount = receivedAmount;
	}

	public String getReceivedBy()
	{
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy)
	{
		this.receivedBy = receivedBy;
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

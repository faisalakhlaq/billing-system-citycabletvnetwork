package database.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.PrintBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import model.Bill;
import model.Customer;
import database.BillHandler;
import database.CustomerHandler;

/**
 * Retrieve all the unpaid bills from the table
 * <p>
 * and send it to the print bill panel one by one
 */
public class PrintUnpaidBillsCaller implements ActionListener
{
	/**
	 * Retrieve all the unpaid bills from the table
	 * <p>
	 * and send it to the print bill panel one by one
	 */
	public PrintUnpaidBillsCaller()
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillHandler billHandler = new BillHandler();
		try
		{
			ArrayList<Bill> unpaidBills = billHandler.getAllUnpaidBills();
			for (Bill b : unpaidBills)
			{
				if (b == null) continue;
				try
				{
					int acNo = b.getAccountNumber();
					PrintBillPanel printBillPanel = new PrintBillPanel(getCustomer(acNo), b, getBillHistory(acNo));
					BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
					desktop.addPanel("Print Number = " + b.getBillNumber(), printBillPanel);
					printBillPanel.printAndClose();
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
					// Skip this customer and do not print the bill. And carry
					// on printing the other bills
				}
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	private Vector<Bill> getBillHistory(int accountNumber) throws Exception
	{
		BillHandler handler = new BillHandler();
		Vector<Bill> bills = handler.getCustomerBills(accountNumber);

		// if there is no previous bill history still we should be able to print
		// the bill.
		// therefore we are not returning null bills
		if (bills == null)
		{
			bills = new Vector<Bill>(0);
		}

		return bills;
	}

	private Customer getCustomer(int accountNumber) throws Exception
	{
		Customer c = null;
		try
		{
			CustomerHandler custHandler = new CustomerHandler();
			c = custHandler.searchCustomer(accountNumber);
			if (c == null)
			{
				throw new Exception("Unable to Print the Bill! No customer retrieved from the database. Customer = null");
			}
		}
		catch (Exception e)
		{
			throw new Exception("Unable to Print the Bill! " + e.getMessage());
		}
		return c;
	}
}

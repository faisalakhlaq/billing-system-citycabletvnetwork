package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.BillPanel;
import gui.panels.GuiPanel;
import gui.panels.PrintBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import model.Bill;
import model.Customer;
import database.BillHandler;
import database.CustomerHandler;

/**
 * Print all or an individual bill.
 * <p>
 * Provide the bill object if individual bill needs to be printed
 */
public class PrintBillPanelCaller implements ActionListener
{
	private GuiPanel panel = null;

	public PrintBillPanelCaller(GuiPanel p)
	{
		panel = p;
	}

	public PrintBillPanelCaller()
	{
	}

	/**
	 * Get the bill from the BillPanel.
	 * <p>
	 * Search the customer according to the Bills customer account number
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			Bill bill = getBill();

			Customer customer = getCustomer(bill.getAccountNumber());
			Vector<Bill> previousBills = getBillHistory(customer.getAccountNumber());

			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addPanel("Print Bill", new PrintBillPanel(customer, bill, previousBills));
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

	private Bill getBill() throws Exception
	{
		if (panel == null)
		{
			throw new Exception("Unable to print bill! Panel = null");
		}
		Bill bill = null;
		if (panel instanceof BillPanel)
		{
			bill = ((BillPanel) panel).getBill();
		}
		if (bill == null)
		{
			throw new Exception("Unable to Print the Bill. Not able to retrieve the bill from the panel. <p> Bill = null");
		}
		return bill;
	}
}
package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.callers.BillsDisplayPanelCaller;
import database.BillHandler;

public class CustomerBillCaller
{

	public CustomerBillCaller()
	{
	}

	public static void getCustomerBills(int accountNumber)
	{
		BillHandler handler = new BillHandler();
		try
		{
			BillsDisplayPanelCaller.perform(handler.getCustomerBills(accountNumber));
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	public static void searchBill(int billNumber)
	{
		BillHandler handler = new BillHandler();
		try
		{
			BillsDisplayPanelCaller.perform(handler.searchBill(billNumber));
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}

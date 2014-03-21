package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.callers.BillsDisplayPanelCaller;
import database.AdvertisementBillHandler;
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

	/**
	 * Search customer bill or an advertisement bill.
	 * <p>
	 * If the bill to be searched is advertisement bill then the isAdBill flag
	 * should be set true
	 */
	public static void searchBill(int billNumber, boolean isAdBill)
	{
		try
		{
			if (isAdBill)
			{
				AdvertisementBillHandler handler = new AdvertisementBillHandler();
				BillsDisplayPanelCaller.perform(handler.searchBill(billNumber));
			}
			else
			{
				BillHandler handler = new BillHandler();
				BillsDisplayPanelCaller.perform(handler.searchBill(billNumber));
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}

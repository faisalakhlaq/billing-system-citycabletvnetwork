package database.callers;

import java.util.Vector;

import model.Customer;
import gui.dialog.MessageDialog;
import gui.panels.callers.DisplayCustomersPanelCaller;
import database.AreaCodeHandler;
import database.CustomerHandler;

/**
 * Retrieves the customers from the database according to a specific area
 * <p>
 * Displays the customers in a table panel
 */
public class ViewAreaCustomersCaller
{
	public static void perform(String areaName)
	{
		try
		{
			AreaCodeHandler handler = new AreaCodeHandler();
			int areaCode = handler.searchAreaCode(areaName);
			CustomerHandler custhandler = new CustomerHandler();
			Vector<Customer> customers = custhandler.getAreaCustomers(areaCode);
			DisplayCustomersPanelCaller caller = new DisplayCustomersPanelCaller(customers);
			caller.perform();
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}

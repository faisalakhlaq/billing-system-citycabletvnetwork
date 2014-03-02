package database.callers;

import gui.dialog.MessageDialog;
import model.Customer;
import database.CustomerHandler;

/**
 * Edit - Update the customer in the database.
 * <p>
 * Provide an object of the customer which is to be changed
 */
public class UpdateCustomerCaller
{
	public static void perform(Customer customer)
	{
		if (customer == null)
		{
			new MessageDialog("Error", "Unable to update the customer! <p>Customer = null");
		}

		CustomerHandler handler = new CustomerHandler();
		try
		{
			handler.updateCustomer(customer);
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}

package database.callers;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;
import database.CustomerHandler;

public class DeleteCustomerCaller
{

	public static void perform(int customerAccountNumber)
	{
		// if (panel == null)
		// {
		// new MessageDialog("Error",
		// "Sorry the customer cannot be deleted. Panel = null!");
		// return;
		// }
		// Customer c = panel.getCustomer();
		// if (c == null)
		// {
		// new MessageDialog("Error",
		// "Sorry the customer cannot be deleted. Customer = null!");
		// return;
		// }

		CustomerHandler handler = new CustomerHandler();
		boolean deleted;
		try
		{
			deleted = handler.deleteCustomer(customerAccountNumber);

			if (deleted)
			{
				new MessageDialog("Deleted", "Customer is deleted!");
				CloseViewCaller.perform(); // close the view after customer
											// deletion
			}
		}
		catch (Exception ex)
		{
			new MessageDialog("Error", ex.getMessage());
		}
	}
}

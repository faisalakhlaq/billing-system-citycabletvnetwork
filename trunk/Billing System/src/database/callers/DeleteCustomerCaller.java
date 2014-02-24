package database.callers;

import javax.swing.JOptionPane;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;
import database.CustomerHandler;

public class DeleteCustomerCaller
{
	public static void perform(int customerAccountNumber)
	{
		CustomerHandler handler = new CustomerHandler();
		boolean deleted;
		try
		{
			deleted = handler.deleteCustomer(customerAccountNumber);

			if (deleted)
			{
				new MessageDialog("Deleted", "Customer is deleted!", JOptionPane.INFORMATION_MESSAGE);
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

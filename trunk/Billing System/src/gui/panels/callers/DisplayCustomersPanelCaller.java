package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.DisplayAllCustomersPanel;

import java.util.Vector;

import model.Customer;

/**
 * Displays the provided customers in a table panel
 */
public class DisplayCustomersPanelCaller
{
	private Vector<Customer> customerList = null;

	public DisplayCustomersPanelCaller(Vector<Customer> customerList)
	{
		this.customerList = customerList;
	}

	public void perform()
	{
		if (customerList == null || customerList.isEmpty())
		{
			new MessageDialog("Sorry", "No customer were found from the database");
			return;
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			// TODO check if it is OK to run it like this
			public void run()
			{
				BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
				desktop.addPanel("Customers", new DisplayAllCustomersPanel(customerList));
			}
		});
	}
}
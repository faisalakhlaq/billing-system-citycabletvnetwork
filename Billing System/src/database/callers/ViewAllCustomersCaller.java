package database.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.ShowAllCustomersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import database.CustomerHandler;

import model.Customer;

public class ViewAllCustomersCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{

			public void run()
			{
				try
				{
					CustomerHandler custHandler = new CustomerHandler();
					Vector<Customer> customerList = custHandler.getAllCustomers();
					BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
					desktop.addPanel("All Customers", new ShowAllCustomersPanel(customerList));
				}
				catch (Exception e)
				{
					new MessageDialog("Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

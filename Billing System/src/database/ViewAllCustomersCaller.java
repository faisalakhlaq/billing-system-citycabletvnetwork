package database;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.ShowAllCustomersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Customer;

public class ViewAllCustomersCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		CustomerHandler custHandler = new CustomerHandler();
		try
		{
			Vector<Customer> customerList = custHandler.getAllCustomers();
			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addTab("Add Customer", new ShowAllCustomersPanel(customerList));
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}

}

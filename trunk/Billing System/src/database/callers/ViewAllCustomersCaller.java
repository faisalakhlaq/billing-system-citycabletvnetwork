package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.callers.DisplayCustomersPanelCaller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Customer;
import database.CustomerHandler;

public class ViewAllCustomersCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			// TODO check if it is OK to run it like this
			public void run()
			{
				try
				{
					CustomerHandler custHandler = new CustomerHandler();
					Vector<Customer> customerList = custHandler.getAllCustomers();
					DisplayCustomersPanelCaller caller = new DisplayCustomersPanelCaller(customerList);
					caller.perform();
				}
				catch (Exception e)
				{
					new MessageDialog("Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

package database.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.BasicGuiPanel;
import gui.panels.CustomerPanel;
import gui.panels.MainViewLeftPanel;
import gui.panels.SearchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Customer;
import utils.Helper;
import database.CustomerHandler;

public class SearchCuctomerCaller implements ActionListener
{
	BasicGuiPanel panel;

	public SearchCuctomerCaller(BasicGuiPanel p)
	{
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (panel == null)
		{
			new MessageDialog("Error", "Error while searching the customer! No panel found");
			return;
		}

		String accountNumberText = null;
		if (panel instanceof SearchPanel)
		{
			accountNumberText = ((SearchPanel) panel).getAccoutNumber();
		}
		else if (panel instanceof MainViewLeftPanel)
		{
			accountNumberText = ((MainViewLeftPanel) panel).getAccoutNumber();
		}

		if (Helper.isEmpty(accountNumberText))
		{
			new MessageDialog("No number provided", "Customer account number cannot be empty", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!Helper.isDigit(accountNumberText.trim()))
		{
			new MessageDialog("Wrong number", "Customer account number can only contain digits", JOptionPane.ERROR_MESSAGE);
			return;
		}
		CustomerHandler handler = new CustomerHandler();
		try
		{
			Customer c = handler.searchCustomer(Integer.valueOf(accountNumberText.trim()));

			if (c == null)
			{
				new MessageDialog("Record not found", "No customer found with account number = " + accountNumberText, JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
				desktop.addPanel(c.getCustomerName(), new CustomerPanel(c));
			}
		}
		catch (NumberFormatException e1)
		{
			new MessageDialog("NumberFormatException", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e1)
		{
			new MessageDialog("Exception", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
}

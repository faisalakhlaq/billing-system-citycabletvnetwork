package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.AddNewCustomerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerCaller implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addTab("Add Customer", new AddNewCustomerPanel());
		//TODO currently opened window should be visible and in focus
	}
}

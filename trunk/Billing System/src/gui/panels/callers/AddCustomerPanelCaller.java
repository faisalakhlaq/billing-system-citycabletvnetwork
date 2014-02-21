package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.AddNewCustomerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerPanelCaller implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Add Customer", new AddNewCustomerPanel());
	}
}

package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.SearchCustomerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCustomerCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addTab("Search Customer", new SearchCustomerPanel());
		//TODO currently opened window should be visible and in focus		
	}

}

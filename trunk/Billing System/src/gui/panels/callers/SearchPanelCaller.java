package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.SearchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanelCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Search Customer", new SearchPanel());
	}

}

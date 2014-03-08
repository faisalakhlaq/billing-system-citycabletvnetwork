package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.GenerateSalesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateSalesPanelCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Sales Report", new GenerateSalesPanel());
	}

}

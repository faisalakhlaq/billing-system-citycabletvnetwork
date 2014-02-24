package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.PrintBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintBillPanelCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Print Bill", new PrintBillPanel());
	}

}

package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.PrintBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPrintBillPanelCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addTab("Print Bill", new PrintBillPanel());
		//TODO currently opened window should be visible and in focus
	}

}

package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.DisplayUnpaidBillsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import model.Bill;
import database.BillHandler;

/**
 * Gets the unpaid bills from the database
 * <p>
 * Calls the Panel and supplies it with the unpaid bills to be displayed
 */
public class DisplayUnpaidBillsPanelCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillHandler handler = new BillHandler();
		try
		{
			ArrayList<Bill> bills = handler.getAllUnpaidBills();
			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addPanel("Unpaid Bills", new DisplayUnpaidBillsPanel(new Vector<Bill>(bills)));
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

}

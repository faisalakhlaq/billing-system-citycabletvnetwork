package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.AdvertisementBillPanel;
import gui.panels.BillPanel;
import gui.panels.DisplayAllBillsPanel;

import java.util.Vector;

import model.AdvertisementBill;
import model.Bill;

public class BillsDisplayPanelCaller
{
	public static void perform(Vector<Bill> bills)
	{
		if (bills == null)
		{
			new MessageDialog("Sorry", "No bills retrieved");
		}
		else
		{
			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addPanel("Customer Bill", new DisplayAllBillsPanel(bills));
		}
	}

	public static void perform(Bill bill)
	{
		if (bill == null)
		{
			new MessageDialog("Sorry", "No bill data to be displayed");
		}
		else
		{
			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addPanel("Customer Bill", new BillPanel(bill));
		}
	}

	public static void perform(AdvertisementBill bill)
	{
		if (bill == null)
		{
			new MessageDialog("Sorry", "No bill data to be displayed");
		}
		else
		{
			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addPanel("Customer Bill", new AdvertisementBillPanel(bill));
		}
	}
}

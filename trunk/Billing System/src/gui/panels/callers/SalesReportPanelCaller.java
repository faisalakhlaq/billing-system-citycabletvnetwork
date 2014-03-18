package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.SalesReportPanel;

import java.util.ArrayList;

import model.ModelObject;

public class SalesReportPanelCaller
{
	public static void perform(ArrayList<ModelObject> billList)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Report", new SalesReportPanel(billList));
	}
}

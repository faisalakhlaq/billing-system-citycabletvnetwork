package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.AdvertisementBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvertisementBillPanelCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Advertisement Bill", new AdvertisementBillPanel(null));
	}

}

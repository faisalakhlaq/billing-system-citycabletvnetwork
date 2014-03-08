package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.BillPaymentPanel;
import gui.panels.CustomerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Customer;

public class BillPaymentPanelCaller implements ActionListener
{
	CustomerPanel panel = null;

	public BillPaymentPanelCaller(CustomerPanel p)
	{
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Customer c = null;

		if (panel != null)
		{
			c = panel.getCustomer();
		}

		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Bill Payment", new BillPaymentPanel(c));
	}

}

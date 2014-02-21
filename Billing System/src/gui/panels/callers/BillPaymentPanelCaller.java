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
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		Customer c = null;

		if (panel != null)
		{
			c = panel.getCustomer();
		}

		desktop.addPanel("Bill Payment", new BillPaymentPanel(c));
	}

}

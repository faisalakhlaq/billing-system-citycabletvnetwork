package database.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.BillPaymentPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCustomerCaller implements ActionListener
{
// TODO test implementation
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addTab("Delete Customer", new BillPaymentPanel());
	}
}

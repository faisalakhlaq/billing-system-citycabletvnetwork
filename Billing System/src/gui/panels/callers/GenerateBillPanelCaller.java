package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.BasicGuiPanel;
import gui.panels.CustomerPanel;
import gui.panels.GenerateBillPanel;
import gui.panels.GenerateIndividualBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Customer;

public class GenerateBillPanelCaller implements ActionListener
{
	/**
	 * True if the bill is for individual customer or for all the customers
	 */
	private boolean individualBill;

	/**
	 * Keeping the panel in the caller to get some required parameters e.g.
	 * Customer
	 */
	private BasicGuiPanel panel;

	/**
	 * If individual customer's bill is to be generated then boolean value must
	 * be true.
	 */
	public GenerateBillPanelCaller(boolean individualBill)
	{
		this.individualBill = individualBill;
	}

	public GenerateBillPanelCaller(boolean individualBill, BasicGuiPanel p)
	{
		this.individualBill = individualBill;
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		if (!individualBill)
		{
			desktop.addPanel("Generate Bill", new GenerateBillPanel());
		}
		else
		{
			if (panel == null)
			{
				new MessageDialog("Error", "Error while generating bill for the customer! Panel = null");
				return;
			}
			if (panel instanceof CustomerPanel)
			{
				Customer c = ((CustomerPanel) panel).getCustomer();
				if (c == null)
				{
					new MessageDialog("Error", "Error while generating bill for the customer! <p>Customer record not found. <p>Customer = null");
					return;
				}
				desktop.addPanel("Generate Bill", new GenerateIndividualBillPanel(c));
			}
		}
	}
}

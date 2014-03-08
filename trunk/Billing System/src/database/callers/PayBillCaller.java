package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.AdvertisementBillPanel;
import gui.panels.BasicGuiPanel;
import gui.panels.BillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.AdvertisementBill;
import model.Bill;
import database.AdvertisementBillHandler;
import database.BillHandler;

public class PayBillCaller implements ActionListener
{
	BasicGuiPanel panel = null;

	public PayBillCaller(BasicGuiPanel p)
	{
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent action)
	{
		if (panel == null)
		{
			new MessageDialog("Error", "Error occured while updating - paying bill. BillPanel = null!");
			return;
		}
		try
		{
			if (panel instanceof BillPanel)
			{
				payCableTvBill();
			}
			else if (panel instanceof AdvertisementBillPanel)
			{
				payAdvertisementBill();
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	private void payCableTvBill() throws Exception
	{
		Bill b = ((BillPanel) panel).getBill();
		BillHandler handler = new BillHandler();
		handler.updateBill(b);
		new MessageDialog("Updated", "Bill Updated - Paid Successfully", JOptionPane.INFORMATION_MESSAGE);
	}

	private void payAdvertisementBill() throws Exception
	{
		AdvertisementBill b = ((AdvertisementBillPanel) panel).getBill();
		AdvertisementBillHandler handler = new AdvertisementBillHandler();
		handler.addBill(b);
		new MessageDialog("Paid", "Bill Saved Successfully", JOptionPane.INFORMATION_MESSAGE);
	}
}

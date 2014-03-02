package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.BasicGuiPanel;
import gui.panels.BillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Bill;
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
		Bill b = null;
		try
		{
			if (panel instanceof BillPanel)
			{
				b = ((BillPanel) panel).getBill();
			}
			if (b == null)
			{
				new MessageDialog("Error", "Error occured while updating - paying bill. Bill = null!");
				return;
			}
			BillHandler handler = new BillHandler();
			handler.updateBill(b);
			new MessageDialog("Updated", "Bill Updated - Paid Successfully", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}

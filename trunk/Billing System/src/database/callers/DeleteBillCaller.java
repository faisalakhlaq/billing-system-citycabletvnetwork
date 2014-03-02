package database.callers;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;
import gui.panels.BasicGuiPanel;
import gui.panels.BillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import database.BillHandler;

import model.Bill;

public class DeleteBillCaller implements ActionListener
{
	BasicGuiPanel panel = null;

	public DeleteBillCaller(BasicGuiPanel p)
	{
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (panel == null)
		{
			new MessageDialog("Error", "Unable to delete the bill! <p>Panel == null");
			return;
		}

		if (panel instanceof BillPanel)
		{
			try
			{
				BillPanel p = ((BillPanel) panel);
				Bill b = p.getBill();
				if (b == null)
				{
					new MessageDialog("Error", "Unable to delete the bill! <p>Bill == null");
					return;
				}
				int billNumber = b.getBillNumber();

				MessageDialog msg = new MessageDialog();
				int delete = msg.showConfirmDialog("Confirm", "Do you want to delete the bill?");

				if (delete == 1)
				{
					BillHandler handler = new BillHandler();
					handler.deleteBill(billNumber);

					new MessageDialog("Deleted", "Success! Bill deleted", JOptionPane.INFORMATION_MESSAGE);
					CloseViewCaller.perform();
				}
			}
			catch (Exception e)
			{
				new MessageDialog("Error", e.getMessage());
			}
		}
	}
}
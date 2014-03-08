package database.callers;

import gui.caller.CloseViewCaller;
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

/**
 * Delete the customer cable tv bill
 * <p>
 * OR
 * <p>
 * Delete the advertisement bill
 */
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

		try
		{
			if (panel instanceof BillPanel)
			{
				deleteCableTvBill();
			}
			else if (panel instanceof AdvertisementBillPanel)
			{
				deleteAdvertisementBill();
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	/**
	 * Delete an individual customers cable tv bill
	 * 
	 * @throws Exception
	 * */
	private void deleteCableTvBill() throws Exception
	{
		BillPanel p = ((BillPanel) panel);
		Bill b = p.getBill();
		if (b == null)
		{
			new MessageDialog("Error", "Unable to delete the bill! Bill == null");
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

	/**
	 * Delete an advertisement bill
	 * 
	 * @throws Exception
	 * */
	private void deleteAdvertisementBill() throws Exception
	{
		AdvertisementBillPanel p = ((AdvertisementBillPanel) panel);
		AdvertisementBill b = p.getBill();
		if (b == null)
		{
			new MessageDialog("Error", "Unable to delete the bill! Bill == null");
			return;
		}
		int id = b.getId();

		MessageDialog msg = new MessageDialog();
		int delete = msg.showConfirmDialog("Confirm", "Do you want to delete the bill?");

		if (delete == 1)
		{
			AdvertisementBillHandler handler = new AdvertisementBillHandler();
			handler.deleteBill(id);

			new MessageDialog("Deleted", "Success! Bill deleted", JOptionPane.INFORMATION_MESSAGE);
			CloseViewCaller.perform();
		}
	}
}
package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.GenerateSalesPanel;
import gui.panels.GuiPanel;
import gui.panels.callers.SalesReportPanelCaller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import model.AdvertisementBill;
import model.Bill;
import model.ModelObject;
import database.AdvertisementBillHandler;
import database.BillHandler;

public class GetSalesCaller implements ActionListener
{
	private GuiPanel panel;

	public GetSalesCaller(GuiPanel p)
	{
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (panel == null)
		{
			new MessageDialog("Error", "Error occured while generating report! Panel = null");
			return;
		}
		if (panel instanceof GenerateSalesPanel)
		{
			GenerateSalesPanel p = (GenerateSalesPanel) panel;
			Date fromDate = p.getFromDate();
			Date toDate = p.getToDate();

			if (fromDate == null || toDate == null)
			{
				new MessageDialog("Error", "From Date and To Date cannot be empty");
				return;
			}
			if (fromDate.after(toDate) || fromDate.equals(toDate))
			{
				new MessageDialog("Error", "From Date should be before To Date");
				return;
			}

			try
			{
				BillHandler billHandler = new BillHandler();
				ArrayList<Bill> bills = billHandler.getSales(fromDate, toDate);

				AdvertisementBillHandler adBillHandler = new AdvertisementBillHandler();
				ArrayList<AdvertisementBill> adBills = adBillHandler.getSales(fromDate, toDate);

				ArrayList<ModelObject> billList = new ArrayList<ModelObject>();
				billList.addAll(bills);
				billList.addAll(adBills);

				SalesReportPanelCaller.perform(billList);
			}
			catch (Exception e1)
			{
				new MessageDialog("Error", e1.getMessage());
			}
		}
	}
}
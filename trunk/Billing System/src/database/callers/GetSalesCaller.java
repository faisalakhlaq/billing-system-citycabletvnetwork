package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.GenerateSalesPanel;
import gui.panels.GuiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
				new MessageDialog("Error", "Unable to generate report! Date = null");
				return;
			}
			if (fromDate.after(toDate) || fromDate.equals(toDate))
			{
				new MessageDialog("Error", "From Date cannot be equal OR after To Date");
				return;
			}
			BillHandler handler = new BillHandler();
			try
			{
				handler.getSales(fromDate, toDate);
			}
			catch (Exception e1)
			{
				new MessageDialog("Error", e1.getMessage());
			}
		}
	}
}
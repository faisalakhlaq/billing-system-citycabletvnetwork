package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.dialog.MessageDialog;
import gui.panels.DisplayAllAreaCodesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import model.AreaCode;
import database.AreaCodeHandler;

public class DisplayAllAreaCodesPanelCaler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			AreaCodeHandler handler = new AreaCodeHandler();
			Vector<AreaCode> codes = handler.getAllAreaCodes();
			if (codes == null || codes.isEmpty())
			{
				new MessageDialog("Result", "No area codes were found in the database.");
				return;
			}
			BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
			desktop.addPanel("Area Codes", new DisplayAllAreaCodesPanel(codes));
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}
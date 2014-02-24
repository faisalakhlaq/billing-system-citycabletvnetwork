package database.callers;

import gui.dialog.MessageDialog;

import javax.swing.JOptionPane;

import database.AreaCodeHandler;

public class DeleteAreaCodeCaller
{
	public static void perform(int areaCode)
	{
		AreaCodeHandler handler = new AreaCodeHandler();
		boolean deleted;
		try
		{
			deleted = handler.deleteAreaCode(areaCode);

			if (deleted)
			{
				new MessageDialog("Deleted", "Area code is deleted!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception ex)
		{
			new MessageDialog("Error", ex.getMessage());
		}
	}
}

package gui.caller;

import gui.dialog.SystemAboutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutMenuItemCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new SystemAboutDialog();
	}

}

package gui.caller;

import gui.views.BillingSystemMainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitSystemCaller extends WindowAdapter implements ActionListener
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o instanceof BillingSystemMainFrame)
		{
			//TODO close all the including windows and check for unsaved data
			// prompt the user if something is unsaved
		}
		windowClosing(null);		
	}
}


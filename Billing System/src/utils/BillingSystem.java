package utils;

import javax.swing.SwingUtilities;

import gui.views.BillingSystemMainFrame;

public class BillingSystem
{
	public void startGUI()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				BillingSystemMainFrame frame = BillingSystemMainFrame.getInstance();
				frame.setVisible(true);
			}
		});
	}
}

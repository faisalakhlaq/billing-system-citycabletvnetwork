package gui.caller;

import gui.BillingSystemStatusBar;
import gui.BillingSystemView;
import gui.views.BillingSystemMainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStatusbarCaller implements ActionListener
{
	private BillingSystemMainFrame mFrame = null;

	public ShowStatusbarCaller(BillingSystemView mFrame)
	{
		this.mFrame = (BillingSystemMainFrame)mFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		BillingSystemStatusBar statusbar = mFrame.getStatusBar();
		if (statusbar != null)
		{
			if (statusbar.isVisible()) statusbar.setVisible(false);
			else
				statusbar.setVisible(true);
		}
	}
}
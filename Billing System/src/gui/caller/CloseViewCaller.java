package gui.caller;

import gui.BillingSystemDesktopPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseViewCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// BillingSystemDesktopPane instance =
		// BillingSystemDesktopPane.getInstance();
		// instance.removeTabAt(instance.getSelectedIndex());
		perform();
	}

	public static void perform()
	{
		BillingSystemDesktopPane instance = BillingSystemDesktopPane.getInstance();
		instance.removeTabAt(instance.getSelectedIndex());
	}
}

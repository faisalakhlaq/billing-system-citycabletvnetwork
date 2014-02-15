package gui;

import javax.swing.JTabbedPane;

public class BillingSystemDesktopPane extends JTabbedPane
{
	private static final long serialVersionUID = -7640076684581134967L;

	private static BillingSystemDesktopPane instance = null;

	private BillingSystemDesktopPane()
	{
	}

	public static BillingSystemDesktopPane getInstance()
	{
		if (instance == null)
		{
			instance = new BillingSystemDesktopPane();
		}
		return instance;
	}
}

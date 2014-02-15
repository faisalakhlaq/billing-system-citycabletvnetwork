package gui;

import javax.swing.JLabel;

public class BillingSystemStatusBar extends JLabel
{
	private static final long serialVersionUID = 6792722537581885267L;

	private static volatile BillingSystemStatusBar instance = null;

	private BillingSystemStatusBar()
	{
	}

	public static BillingSystemStatusBar getInstance()
	{
		if (instance == null) instance = new BillingSystemStatusBar();

		return instance;
	}
}

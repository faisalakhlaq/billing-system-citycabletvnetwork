package utils;

import gui.views.LoginView;

public class BillingSystemDriver
{
	public static void main(String args[])
	{
		BillingSystem system = new BillingSystem();
		system.startGUI();
		LoginView loginD = new LoginView();
		loginD.setVisible(true);
		loginD.requestFocus();
		loginD.requestFocus();
	}
}

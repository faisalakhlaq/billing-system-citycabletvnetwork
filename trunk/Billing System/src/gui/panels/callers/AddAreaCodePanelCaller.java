package gui.panels.callers;

import gui.BillingSystemDesktopPane;
import gui.panels.AreaCodePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAreaCodePanelCaller implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		BillingSystemDesktopPane desktop = BillingSystemDesktopPane.getInstance();
		desktop.addPanel("Area Code", new AreaCodePanel());
	}

}

package gui;

import gui.buttons.TabCloseButton;
import gui.panels.BasicGuiPanel;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
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

	public void addPanel(String title, final BasicGuiPanel panel)
	{
		BasicGuiPanel titlePanel = new BasicGuiPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setOpaque(false);
		JLabel titleLbl = new JLabel(title);
		titlePanel.add(titleLbl);
		// add more space between the label and the button
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		// tab button
		TabCloseButton closeButton = new TabCloseButton();
		closeButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				instance.remove(panel);
			}
		});
		titlePanel.add(closeButton);

		add(panel);
		this.setTabComponentAt(this.indexOfComponent(panel), titlePanel);
		setSelectedComponent(panel);
	}
}

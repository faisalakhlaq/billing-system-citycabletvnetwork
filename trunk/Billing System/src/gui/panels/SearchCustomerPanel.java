package gui.panels;

import gui.GUIPanel;
import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import database.callers.SearchCuctomerCaller;

@SuppressWarnings("serial")
public class SearchCustomerPanel extends GUIPanel
{
	private JTextField accountNumbertxt;

	private JButton resetbtn;

	private JButton searchbtn;

	private JButton exitbtn;

	private JButton getBillbtn;

	public SearchCustomerPanel()
	{
		intitializePanel();
	}

	private void intitializePanel()
	{
		GUIPanel header = configureHeader();
		GUIPanel fieldsPanel = configureFieldsPanel();
		GUIPanel btnPanel = configureBtnPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(header, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(fieldsPanel, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(btnPanel, c);
	}

	public String getAccoutNumber()
	{
		return accountNumbertxt.getText();
	}

	private GUIPanel configureBtnPanel()
	{
		resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ResetFieldsListener());

		searchbtn = new JButton("Search");
		searchbtn.addActionListener(new SearchCuctomerCaller(SearchCustomerPanel.this));

		getBillbtn = new JButton("Get Bill");

		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());

		GUIPanel p = new GUIPanel(new FlowLayout());

		p.add(resetbtn);
		p.add(searchbtn);
		p.add(getBillbtn);
		p.add(exitbtn);

		return p;
	}

	private GUIPanel configureFieldsPanel()
	{
		JLabel accountNumberLbl = new JLabel("Customer Account Number");

		accountNumbertxt = new JTextField(10);

		GUIPanel p = new GUIPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(accountNumberLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(accountNumbertxt, c);

		return p;
	}

	private GUIPanel configureHeader()
	{
		JLabel headerLbl = new JLabel("Search Customer");

		GUIPanel headerPanel = new GUIPanel(new GridBagLayout());
		headerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		GridBagConstraints hc = new GridBagConstraints();

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 0;
		hc.gridy = 1;
		hc.gridwidth = 1;
		headerPanel.add(headerLbl, hc);

		return headerPanel;
	}

	private class ResetFieldsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			accountNumbertxt.setText(null);
		}
	}
}

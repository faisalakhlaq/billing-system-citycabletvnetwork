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

public class SearchCustomerPanel extends GUIPanel
{
	private static final long serialVersionUID = -6291947031059895308L;

	private JTextField nametxt;

	private JTextField nicNumbertxt;

	private JButton resetbtn;

	private JButton searchbtn;

	private JButton exitbtn;

	private JButton getBillbtn;

	// TODO can it be singleton???
	public SearchCustomerPanel()
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

	private GUIPanel configureBtnPanel()
	{
		resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ResetFieldsListener());
		searchbtn = new JButton("Search");
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());
		getBillbtn = new JButton("Get Bill");

		GUIPanel p = new GUIPanel(new FlowLayout());

		p.add(exitbtn);
		p.add(resetbtn);
		p.add(searchbtn);
		p.add(getBillbtn);

		return p;
	}

	private GUIPanel configureFieldsPanel()
	{
		JLabel nameLbl = new JLabel("Customer Name");
		JLabel nicNumberLbl = new JLabel("N.I.C Number");

		nametxt = new JTextField(20);
		nicNumbertxt = new JTextField(20);

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
		p.add(nameLbl, c);
		//
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(nametxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		p.add(nicNumberLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		p.add(nicNumbertxt, c);

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
			nametxt.setText(null);
			nicNumbertxt.setText(null);
		}
	}
}

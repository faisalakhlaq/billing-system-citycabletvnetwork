package gui.panels;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.GUIPanel;
import gui.caller.CloseViewCaller;

public class CustomerPanel extends GUIPanel
{
	private static final long serialVersionUID = 780942135536586736L;

	JButton payBillbtn;

	JButton billHistorybtn;

	JButton editCustomerbtn;

	JButton deleteCustomerbtn;

	JButton exitbtn;

	JTextField customerNametxt;

	JTextField nicNumbertxt;

	JTextField addresstxt;

	JTextField customerJoinDatetxt;

	public CustomerPanel()
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
		payBillbtn = new JButton("Pay Bill");
		billHistorybtn = new JButton("Bill History");
		editCustomerbtn = new JButton("Edit Customer");
		deleteCustomerbtn = new JButton("Delete Customer");
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());

		GUIPanel p = new GUIPanel(new FlowLayout());

		p.add(payBillbtn);
		p.add(billHistorybtn);
		p.add(editCustomerbtn);
		p.add(deleteCustomerbtn);
		p.add(exitbtn);

		return p;
	}

	private GUIPanel configureFieldsPanel()
	{
		JLabel customerNameLbl = new JLabel("Customer Name");
		JLabel nicNumberLbl = new JLabel("N.I.C Number");
		JLabel addressLbl = new JLabel("Address");
		JLabel dateJoinedLbl = new JLabel("Date Joined");

		customerNametxt = new JTextField(20);
		nicNumbertxt = new JTextField(20);
		addresstxt = new JTextField(20);
		customerJoinDatetxt = new JTextField(20);

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
		p.add(customerNameLbl, c);
		//
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(customerNametxt, c);

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

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		p.add(addressLbl, c);
		//
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		p.add(addresstxt, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(dateJoinedLbl, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(customerJoinDatetxt, c);
		
		return p;
	}

	private GUIPanel configureHeader()
	{
		JLabel headerLbl = new JLabel("Customer Information");

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
}

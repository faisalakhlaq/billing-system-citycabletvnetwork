package gui.panels;

import gui.GUIPanel;
import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BillPaymentPanel extends GUIPanel
{
	private static final long serialVersionUID = 3496878284152665018L;

	JButton payBillbtn;

	JButton exitbtn;

	JTextField customerNametxt;

	JTextField nicNumbertxt;

	JComboBox<String> billingMonthCombobox;

	JTextField amounttxt;

	JTextField surchargetxt;

	JTextField advertisementBilltxt;

	JTextField paymentDatetxt; // Change it to clock

	// JComboBox<Vector<Integer>> billingYearCombobox;
	JComboBox<String> billingYearCombobox; // change data type

	JTextField customerJoinDatetxt; // Change it to clock

	public BillPaymentPanel()
	{
		GUIPanel header = configureHeader();
		GUIPanel fieldsPanel = configureFieldsPanel();
		GUIPanel btnPanel = configureButtonPanel();
		
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

	private GUIPanel configureFieldsPanel()
	{
		JLabel customerNameLbl = new JLabel("Customer Name");
		JLabel nicNumberLbl = new JLabel("N.I.C Number");
		JLabel billingMonthLbl = new JLabel("Billing Month");
		JLabel amountLbl = new JLabel("Amount");
		JLabel surchargeLbl = new JLabel("Surcharge");
		JLabel advertisementBillLbl = new JLabel("Advertisement Bill");
		JLabel paymentDateLbl = new JLabel("Payment Date");
		JLabel billingYearLbl = new JLabel("Billing Year");
		JLabel customerJoinDateLbl = new JLabel("Customer Join Date");

		customerNametxt = new JTextField(20);
		nicNumbertxt = new JTextField(20);
		// JComboBox<String> billingMonthCombobox;
		amounttxt = new JTextField(20);
		surchargetxt = new JTextField(20);
		advertisementBilltxt = new JTextField(20);
		paymentDatetxt = new JTextField(20); // TODO Change it to clock
		// billingYearCombobox = new JComboBox<String>(null);
		customerJoinDatetxt = new JTextField(20); // TODO Change it to clock

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
		p.add(billingMonthLbl, c);

		// c.fill = GridBagConstraints.HORIZONTAL;
		// c.anchor = GridBagConstraints.LINE_END;
		// c.weightx = 0.75;
		// c.weighty = 0;
		// c.gridx = 1;
		// c.gridy = 3;
		// c.gridwidth = 1;
		// p.add(billingMonthLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(amountLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(amounttxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(surchargeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(surchargetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(advertisementBillLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(advertisementBilltxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(paymentDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(paymentDatetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(billingYearLbl, c);

		// c.fill = GridBagConstraints.HORIZONTAL;
		// c.anchor = GridBagConstraints.LINE_END;
		// c.weightx = 0.75;
		// c.weighty = 0;
		// c.gridx = 1;
		// c.gridy = 8;
		// c.gridwidth = 1;
		// p.add(billingYeartxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(customerJoinDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(customerJoinDatetxt, c);

		return p;
	}

	private GUIPanel configureButtonPanel()
	{
		payBillbtn = new JButton("Pay Bill");
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());

		GUIPanel p = new GUIPanel(new FlowLayout());

		p.add(payBillbtn);
		p.add(exitbtn);

		return p;
	}

	private GUIPanel configureHeader()
	{
		JLabel headerLbl = new JLabel("Bill Payment");

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

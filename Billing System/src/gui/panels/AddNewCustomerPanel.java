package gui.panels;

import gui.GUIPanel;
import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import model.CompanyInformation;
import model.Customer;

import org.jdesktop.swingx.JXDatePicker;

import utils.CustomPair;
import database.CustomerHandler;

public class AddNewCustomerPanel extends GUIPanel
{
	private static final long serialVersionUID = 4926137820999013388L;

	private JButton exitbtn;

	private JButton resetbtn;

	private JButton savebtn;

	private JXDatePicker datePicker;

	private JTextField accountNumbertxt;

	private JTextField nametxt;

	private JTextArea addresstxt;

	private JTextField advancetxt;

	private JTextField nicNumbertxt;

	private JTextField telNumbertxt;

	@SuppressWarnings("rawtypes")
	private JComboBox connectionTypes;

	private JTextField connectionFeetxt;

	public AddNewCustomerPanel()
	{
		configurePanel();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void configurePanel()
	{
		JLabel accountNumberlbl = new JLabel("Account Number");
		JLabel datelbl = new JLabel("Date");
		JLabel namelbl = new JLabel("Name");
		JLabel addresslbl = new JLabel("Address");
		JLabel advancelbl = new JLabel("Advance");
		JLabel nicNumberlbl = new JLabel("NIC Number");
		JLabel telNumberlbl = new JLabel("Telephone");
		/** weather the connection is private or commercial */
		JLabel connectionForlbl = new JLabel("Usage (private/commercial)");
		JLabel connectionFeelbl = new JLabel("Connection Fee");
		datePicker = new JXDatePicker();
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		accountNumbertxt = new JTextField(15);
		nametxt = new JTextField(15);
		addresstxt = new JTextArea();
		addresstxt.setRows(3);
		addresstxt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		advancetxt = new JTextField(15);
		nicNumbertxt = new JTextField(15);
		telNumbertxt = new JTextField(15);
		/** weather the connection is private or commercial */
		connectionTypes = new JComboBox(new CompanyInformation().getConnectionTypes());
		connectionFeetxt = new JTextField(15);

		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());
		savebtn = new JButton("Save");
		savebtn.addActionListener(new AddNewCustomer());
		resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ResetFieldsListener());

		GUIPanel panel = new GUIPanel(new GridBagLayout());
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.25;
		c.weighty = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(accountNumberlbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(accountNumbertxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		panel.add(datelbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		panel.add(datePicker, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(namelbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(nametxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		panel.add(addresslbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		panel.add(addresstxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		panel.add(advancelbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		panel.add(advancetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		panel.add(nicNumberlbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		panel.add(nicNumbertxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		panel.add(telNumberlbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		panel.add(telNumbertxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		panel.add(connectionForlbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		panel.add(connectionTypes, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		panel.add(connectionFeelbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		panel.add(connectionFeetxt, c);

		GUIPanel btnPanel = new GUIPanel(new FlowLayout());
		btnPanel.add(exitbtn);
		btnPanel.add(resetbtn);
		btnPanel.add(savebtn);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		panel.add(btnPanel, c);

		add(panel);
	}

	private class ResetFieldsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			datePicker.setDate(null);
			accountNumbertxt.setText(null);
			nametxt.setText(null);
			addresstxt.setText(null);
			addresstxt.setText(null);
			advancetxt.setText(null);
			nicNumbertxt.setText(null);
			telNumbertxt.setText(null);
			connectionFeetxt.setText(null);
		}
	}

	private class AddNewCustomer implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String accountNumber = accountNumbertxt.getText();
			if (isEmpty(accountNumber))
			{
				new MessageDialog("Account number", "Please provide the account number", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!isDigit(accountNumber))
			{
				new MessageDialog("Account number", "Account number can contain only numbers-digits", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String name = nametxt.getText();
			if (isEmpty(name))
			{
				new MessageDialog("Customer Name", "Customer Name cannot be empty", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String address = addresstxt.getText();
			if (isEmpty(address))
			{
				new MessageDialog("Empty Address", "Please provide the address", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String advance = advancetxt.getText();
			String nicNumber = nicNumbertxt.getText();
			String telNumber = telNumbertxt.getText();
			if (!isEmpty(telNumber) && !isDigit(telNumber))
			{
				new MessageDialog("Phone number", "Phone number can contain only numbers-digits", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String connectionType = (String)connectionTypes.getSelectedItem();
			String connectionFee = connectionFeetxt.getText();
			if (!isEmpty(connectionFee) && !isDigit(connectionFee))
			{
				new MessageDialog("Conection fee", "Conection fee can contain only numbers-digits", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Customer c = new Customer();
			c.setAccountNumber(Integer.parseInt(accountNumber));
			c.setDate(datePicker.getDate());
			c.setCustomerName(name);
			c.setCustomerAddress(address);
			if (!isEmpty(advance) && isDigit(advance))
			{
				c.setAdvance(Integer.parseInt(advance));
			}
			c.setNicNumber(nicNumber);
			if (!isEmpty(telNumber) || isDigit(telNumber))
			{
				c.setTelNumber(Integer.parseInt(telNumber));
			}
			c.setConnectionType(connectionType);

			if (!isEmpty(connectionFee) || isDigit(connectionFee))
			{
				c.setConnectionFee(Integer.parseInt(connectionFee));
			}
			addNewCustomer(c);
		}

		private void addNewCustomer(Customer c)
		{
			CustomerHandler custHandler = new CustomerHandler();
			CustomPair pair = custHandler.addNewCustomer(c);
			if (pair.isBooleanKey())
			{
				new MessageDialog("Success", pair.getValue(), JOptionPane.INFORMATION_MESSAGE);
				resetbtn.doClick();
			}
			else
			{
				new MessageDialog("Add new Customer Error", pair.getValue());
			}
		}

		private boolean isDigit(String data)
		{
			boolean isDigit = false;
			String regex = "\\d+";
			isDigit = data.matches(regex);
			return isDigit;
		}

		private boolean isEmpty(String str)
		{
			boolean isEmpty = false;
			if (str == null)
			{
				isEmpty = true;
			}
			else if (str.trim().isEmpty())
			{
				isEmpty = true;
			}
			return isEmpty;
		}
	}
}

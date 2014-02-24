package gui.panels;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
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
import utils.Helper;
import database.AreaCodeHandler;
import database.CustomerHandler;

public class AddNewCustomerPanel extends BasicGuiPanel
{
	private static final long serialVersionUID = 4926137820999013388L;

	private JButton exitbtn;

	private JButton resetbtn;

	private JButton savebtn;

	private JXDatePicker datePicker;

	private JTextField accountNumbertxt;

	private JTextField nametxt;

	private JTextArea addresstxt;

	private JComboBox<Integer> areaCodeCbx;

	private JTextField advancetxt;

	private JTextField nicNumbertxt;

	private JTextField telNumbertxt;

	@SuppressWarnings("rawtypes")
	private JComboBox connectionTypesCbx;

	private JTextField connectionFeetxt;

	public AddNewCustomerPanel()
	{
		initializePanel();
	}

	private void initializePanel()
	{
		BasicGuiPanel header = configureHeader();
		BasicGuiPanel fieldsPanel = configureFieldsPanel();
		BasicGuiPanel btnPanel = configureBtnPanel();

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

	private BasicGuiPanel configureBtnPanel()
	{
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());
		savebtn = new JButton("Save");
		savebtn.addActionListener(new AddNewCustomer());
		resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ResetFieldsListener());

		BasicGuiPanel btnPanel = new BasicGuiPanel(new FlowLayout());
		btnPanel.add(exitbtn);
		btnPanel.add(resetbtn);
		btnPanel.add(savebtn);

		return btnPanel;
	}

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	private BasicGuiPanel configureFieldsPanel()
	{
		JLabel accountNumberlbl = new JLabel("Account Number");
		JLabel datelbl = new JLabel("Date");
		JLabel namelbl = new JLabel("Name");
		JLabel addresslbl = new JLabel("Address");
		JLabel areaCodelbl = new JLabel("Area Code");
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
		areaCodeCbx = new JComboBox<Integer>();
		areaCodeCbx.setModel(getAreaCodes());
		advancetxt = new JTextField(15);
		nicNumbertxt = new JTextField(15);
		telNumbertxt = new JTextField(15);
		/** weather the connection is private or commercial */
		connectionTypesCbx = new JComboBox(new CompanyInformation().getConnectionTypes());
		connectionFeetxt = new JTextField(15);

		BasicGuiPanel panel = new BasicGuiPanel(new GridBagLayout());
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
		panel.add(accountNumberlbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
		panel.add(accountNumbertxt, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
		panel.add(datelbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
		panel.add(datePicker, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
		panel.add(namelbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
		panel.add(nametxt, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
		panel.add(addresslbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
		panel.add(addresstxt, c);

		setGridBagConstraints(c, 2, 4, GridBagConstraints.LINE_START, 5, 10);
		panel.add(areaCodelbl, c);

		setGridBagConstraints(c, 3, 4, GridBagConstraints.LINE_END, 5, 10);
		panel.add(areaCodeCbx, c);

		setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
		panel.add(advancelbl, c);

		setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
		panel.add(advancetxt, c);

		setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 5, 0);
		panel.add(nicNumberlbl, c);

		setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 5, 10);
		panel.add(nicNumbertxt, c);

		setGridBagConstraints(c, 0, 7, GridBagConstraints.LINE_START, 5, 0);
		panel.add(telNumberlbl, c);

		setGridBagConstraints(c, 1, 7, GridBagConstraints.LINE_END, 5, 10);
		panel.add(telNumbertxt, c);

		setGridBagConstraints(c, 0, 8, GridBagConstraints.LINE_START, 5, 0);
		panel.add(connectionForlbl, c);

		setGridBagConstraints(c, 1, 8, GridBagConstraints.LINE_END, 5, 10);
		panel.add(connectionTypesCbx, c);

		setGridBagConstraints(c, 0, 9, GridBagConstraints.LINE_START, 5, 0);
		panel.add(connectionFeelbl, c);

		setGridBagConstraints(c, 1, 9, GridBagConstraints.LINE_END, 5, 10);
		panel.add(connectionFeetxt, c);

		return panel;
	}

	private BasicGuiPanel configureHeader()
	{
		JLabel headerLbl = new JLabel("Add new customer");

		BasicGuiPanel headerPanel = new BasicGuiPanel(new GridBagLayout());
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

	private void setGridBagConstraints(GridBagConstraints c, int gridx, int gridy, int placement, int paddingTop, int paddingLeft)
	{
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = placement;
		c.insets = new Insets(paddingTop, paddingLeft, 0, 0); // top and left
																// padding
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
	}

	private ComboBoxModel<Integer> getAreaCodes()
	{
		AreaCodeHandler handler = new AreaCodeHandler();
		ComboBoxModel<Integer> model = null;
		try
		{
			model = new javax.swing.DefaultComboBoxModel<Integer>(handler.getCodes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (model == null)
		{
			model = new javax.swing.DefaultComboBoxModel<Integer>();
		}
		return model;
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
			if (Helper.isEmpty(accountNumber))
			{
				new MessageDialog("Account number", "Please provide the account number", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!Helper.isDigit(accountNumber))
			{
				new MessageDialog("Account number", "Account number can contain only numbers-digits", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String name = nametxt.getText();
			if (Helper.isEmpty(name))
			{
				new MessageDialog("Customer Name", "Customer Name cannot be empty", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (Helper.isDigit(name))
			{
				new MessageDialog("Customer Name", "Customer Name cannot be a number", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String address = addresstxt.getText();
			if (Helper.isEmpty(address))
			{
				new MessageDialog("Empty Address", "Please provide the address", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String advance = advancetxt.getText();
			String nicNumber = nicNumbertxt.getText();
			String telNumber = telNumbertxt.getText();
			if (!Helper.isEmpty(telNumber) && !Helper.isDigit(telNumber))
			{
				new MessageDialog("Phone number", "Phone number can contain only numbers-digits", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String connectionType = (String) connectionTypesCbx.getSelectedItem();
			String connectionFee = connectionFeetxt.getText();
			if (!Helper.isEmpty(connectionFee) && !Helper.isDigit(connectionFee))
			{
				new MessageDialog("Conection fee", "Conection fee can contain only numbers-digits", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Customer c = new Customer();
			c.setAccountNumber(Integer.parseInt(accountNumber));
			c.setDate(datePicker.getDate());
			c.setCustomerName(name);
			c.setCustomerAddress(address);
			if (!Helper.isEmpty(advance) && Helper.isDigit(advance))
			{
				c.setAdvance(Integer.parseInt(advance));
			}
			c.setNicNumber(nicNumber);
			if (!Helper.isEmpty(telNumber) || Helper.isDigit(telNumber))
			{
				c.setTelNumber(Integer.parseInt(telNumber));
			}
			c.setConnectionType(connectionType);

			if (!Helper.isEmpty(connectionFee) || Helper.isDigit(connectionFee))
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
	}
}

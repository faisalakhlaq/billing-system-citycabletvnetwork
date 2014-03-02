package gui.panels;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;
import gui.panels.callers.GenerateBillPanelCaller;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.CompanyInformation;
import model.Customer;
import model.ModelObject;
import utils.CustomPair;
import utils.Helper;
import database.AreaCodeHandler;
import database.callers.CustomerBillCaller;
import database.callers.DeleteCustomerCaller;
import database.callers.UpdateCustomerCaller;

public class CustomerPanel extends BasicGuiPanel
{
	private static final long serialVersionUID = 780942135536586736L;

	private boolean editMode = false;

	private Customer customer = null;

	private JButton generateBillbtn;

	private JButton billHistorybtn;

	private JButton editCustomerbtn;

	private JButton deleteCustomerbtn;

	private JButton exitbtn;

	private JButton cancelbtn;

	private JTextField customerNametxt;

	private JTextField nicNumbertxt;

	private JTextField addresstxt;

	private JTextField customerJoinDatetxt;

	private JTextField accountNumbertxt;

	private JTextField advancetxt;

	private JTextField telephonetxt;

	private JComboBox<String> connectionTypeCbx;

	private JTextField connectionFeetxt;

	private JComboBox<Integer> areaCodeCbx;

	public CustomerPanel()
	{
		initializePanel();
	}

	public CustomerPanel(ModelObject obj)
	{
		customer = (Customer) obj;
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

		initTextFields();
		if (!isInEditMode())
		{
			disableTextFields();
		}
	}

	/**
	 * insert the values of ModelObject in the text fields
	 * */
	private void initTextFields()
	{
		if (customer == null)
		{
			return;
		}
		customerNametxt.setText(customer.getCustomerName());
		nicNumbertxt.setText(customer.getNicNumber());
		addresstxt.setText(customer.getCustomerAddress());
		customerJoinDatetxt.setText(customer.getDate().toString());
		accountNumbertxt.setText(String.valueOf(customer.getAccountNumber()));
		advancetxt.setText(String.valueOf(customer.getAdvance()));
		telephonetxt.setText(String.valueOf(customer.getTelNumber()));
		// connectionTypetxt.setText(String.valueOf(customer.getConnectionType()));
		connectionTypeCbx.setSelectedItem(customer.getConnectionType());
		connectionFeetxt.setText(String.valueOf(customer.getConnectionFee()));
		areaCodeCbx.setSelectedItem(customer.getAreaCode());
	}

	/**
	 * If the panel is in edit mode set the text fields as active otherwise make
	 * the fields inactive
	 * <p>
	 * Account number field will be in editable as we do not allow to change it
	 * */
	private void disableTextFields()
	{
		customerNametxt.setEditable(editMode);
		nicNumbertxt.setEditable(editMode);
		addresstxt.setEditable(editMode);
		customerJoinDatetxt.setEditable(editMode);
		accountNumbertxt.setEditable(false); // Account number cannot be changed
		advancetxt.setEditable(editMode);
		telephonetxt.setEditable(editMode);
		// connectionTypetxt.setEditable(editMode);
		connectionTypeCbx.setEditable(editMode);
		connectionFeetxt.setEditable(editMode);
	}

	public boolean isInEditMode()
	{
		return editMode;
	}

	/**
	 * To edit the displayed customer the panel has to be in edit mode.
	 * <p>
	 * In edit mode the text fields will be enabled to be edited otherwise
	 * disabled.
	 * <p>
	 * Edit button's label is set to "Update" in edit mode otherwise "Edit"
	 */
	public void setEditMode(boolean editMode)
	{
		this.editMode = editMode;
		disableTextFields();
		if (editMode)
		{
			editCustomerbtn.setText("Update");
		}
		else
		{
			editCustomerbtn.setText("Edit");
		}
	}

	private BasicGuiPanel configureBtnPanel()
	{
		billHistorybtn = new JButton("Bill History");
		billHistorybtn.addActionListener(new BillHistoryButtonListener());

		generateBillbtn = new JButton("Generate Bill");
		generateBillbtn.addActionListener(new GenerateBillPanelCaller(true, CustomerPanel.this));

		editCustomerbtn = new JButton("Edit");
		editCustomerbtn.addActionListener(new EditCustomerListener());

		cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new CancelEdit());

		deleteCustomerbtn = new JButton("Delete");
		deleteCustomerbtn.addActionListener(new DeleteCustomerListener());

		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseCustomerPanel());

		showCancelbtn();

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(generateBillbtn);
		p.add(billHistorybtn);
		p.add(editCustomerbtn);
		p.add(cancelbtn);
		p.add(deleteCustomerbtn);
		p.add(exitbtn);

		return p;
	}

	private BasicGuiPanel configureFieldsPanel()
	{
		JLabel customerNameLbl = new JLabel("Customer Name");
		JLabel nicNumberLbl = new JLabel("N.I.C Number");
		JLabel addressLbl = new JLabel("Address");
		JLabel dateJoinedLbl = new JLabel("Date Joined");
		JLabel accountNumberLbl = new JLabel("Account Number");
		JLabel advanceLbl = new JLabel("Advance");
		JLabel telephoneLbl = new JLabel("Telephone");
		JLabel connectionTypeLbl = new JLabel("Connection Type");
		JLabel connectionFeeLbl = new JLabel("Connection Fee");
		JLabel areaCodeLbl = new JLabel("Area Code");

		customerNametxt = new JTextField(20);
		nicNumbertxt = new JTextField(20);
		addresstxt = new JTextField(20);
		customerJoinDatetxt = new JTextField(20);
		accountNumbertxt = new JTextField(20);
		advancetxt = new JTextField(20);
		telephonetxt = new JTextField(20);
		// connectionTypetxt = new JTextField(20);
		connectionTypeCbx = new JComboBox<String>(new CompanyInformation().getConnectionTypes());
		connectionFeetxt = new JTextField(20);
		areaCodeCbx = new JComboBox<Integer>();
		areaCodeCbx.setModel(getAreaCodes());

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
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

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(accountNumberLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(accountNumbertxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(advanceLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(advancetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(telephoneLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(telephonetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(connectionTypeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(connectionTypeCbx, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(connectionFeeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(connectionFeetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		p.add(areaCodeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		p.add(areaCodeCbx, c);

		return p;
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

	/**
	 * Show the cancel button only in edit mode
	 */
	private void showCancelbtn()
	{
		cancelbtn.setVisible(editMode);
	}

	private BasicGuiPanel configureHeader()
	{
		JLabel headerLbl = new JLabel("Customer Information");

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

	/**
	 * Creates and returns a customer object obtaining the values from the text
	 * fields
	 * 
	 * @return Customer
	 */
	private Customer getTextFieldValues()
	{
		Customer c = new Customer();

		c.setCustomerName(customerNametxt.getText());
		c.setNicNumber(nicNumbertxt.getText());
		c.setCustomerAddress(addresstxt.getText());
		c.setDate(Date.valueOf(customerJoinDatetxt.getText()));
		c.setConnectionType(String.valueOf(connectionTypeCbx.getSelectedItem()));

		String acNumber = accountNumbertxt.getText();
		if (!Helper.isEmpty(acNumber) && Helper.isDigit(acNumber))
		{
			c.setAccountNumber(Integer.valueOf(acNumber));
		}
		String advance = advancetxt.getText();
		if (!Helper.isEmpty(advance) && Helper.isDigit(advance))
		{
			c.setAdvance(Integer.valueOf(advance));
		}
		String tel = telephonetxt.getText();
		if (!Helper.isEmpty(tel) && Helper.isDigit(tel))
		{
			c.setTelNumber(Integer.valueOf(tel));
		}
		String fee = connectionFeetxt.getText();
		if (!Helper.isEmpty(fee) && Helper.isDigit(fee))
		{
			c.setConnectionFee(Integer.valueOf(fee));
		}
		c.setAreaCode(Integer.valueOf(String.valueOf(areaCodeCbx.getSelectedItem())));
		return c;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	/**
	 * Check if the customer has provided all the mandatory values while editing
	 * <p>
	 * 
	 * @return message and false is returned if required fields do not have
	 *         valid values
	 */
	private CustomPair isValidInput()
	{
		CustomPair p = new CustomPair();
		p.setBooleanKey(true);
		String accountNumber = accountNumbertxt.getText();
		if (Helper.isEmpty(accountNumber))
		{
			accountNumbertxt.setText(String.valueOf(customer.getAccountNumber()));
			p.setBooleanKey(false);
			p.setValue("Account number cannot be empty");
			return p;
		}
		if (!Helper.isDigit(accountNumber.trim()))
		{
			accountNumbertxt.setText(String.valueOf(customer.getAccountNumber()));
			p.setBooleanKey(false);
			p.setValue("Account number can contain only numbers-digits");
			return p;
		}

		String name = customerNametxt.getText();
		if (Helper.isEmpty(name))
		{
			customerNametxt.setText(customer.getCustomerName());
			p.setBooleanKey(false);
			p.setValue("Customer Name cannot be empty");
			return p;
		}
		if (Helper.isDigit(name))
		{
			customerNametxt.setText(customer.getCustomerName());
			p.setBooleanKey(false);
			p.setValue("Customer Name cannot be a number");
			return p;
		}

		String address = addresstxt.getText();
		if (Helper.isEmpty(address))
		{
			addresstxt.setText(customer.getCustomerAddress());
			p.setBooleanKey(false);
			p.setValue("Please provide the address");
			return p;
		}

		return p;
	}

	private class CloseCustomerPanel implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (isInEditMode())
			{
				MessageDialog msgDialog = new MessageDialog();
				int selection = msgDialog.showConfirmDialog("Editing", "Do you really want to abort editing and close this view");
				if (selection == JOptionPane.OK_OPTION)
				{
					CloseViewCaller.perform();
				}
			}
			else
			{
				CloseViewCaller.perform();
			}
		}
	}

	private class EditCustomerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (isInEditMode())
			{
				Customer c = getTextFieldValues();

				// if the current values are equal to the customer object then
				// do not update
				if (!customer.equals(c))
				{
					CustomPair p = isValidInput();
					if (p.isBooleanKey())
					{
						UpdateCustomerCaller.perform(c);
						customer = c;
						new MessageDialog("Update Successful", "Customer was updated successfully", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						new MessageDialog("Invalid Values", p.getValue());
					}
				}
				else
				{
					new MessageDialog("Result", "No values were changed", JOptionPane.INFORMATION_MESSAGE);
				}
				setEditMode(false); // once the customer is edited the panel
				// will go back to un-editable mode
				showCancelbtn(); // hide the cancel button
			}
			else
			{
				setEditMode(true);
				showCancelbtn();
			}
		}
	}

	private class CancelEdit implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			setEditMode(false);
			showCancelbtn();
		}
	}

	private class DeleteCustomerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (editMode)
			{
				new MessageDialog("Editing", "Editing is in process. Either finish editing or cancel and then delete", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				MessageDialog msgDialog = new MessageDialog();
				int selection = msgDialog.showConfirmDialog("Confirm deleteion", "Are you sure you want to delete the customer");
				if (selection == JOptionPane.OK_OPTION)
				{
					DeleteCustomerCaller.perform(customer.getAccountNumber());
				}
			}
		}
	}

	private class BillHistoryButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			CustomerBillCaller.getCustomerBills(customer.getAccountNumber());
		}
	}
}

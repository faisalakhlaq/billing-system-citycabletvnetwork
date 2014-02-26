package gui.panels;

import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Customer;
import model.ModelObject;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;

@SuppressWarnings("serial")
public class BillPaymentPanel extends BasicGuiPanel
{
	private Customer customer = null;

	private JButton payBillbtn;

	private JButton exitbtn;

	private JTextField customerNametxt;

	private JTextField accountNumbertxt;

	private JComboBox<String> billingMonthCombobox;

	private JTextField amounttxt;

	private JTextField surchargetxt;

	private JTextField advertisementBilltxt;

	private JXDatePicker datePicker;

	private JComboBox<Integer> billingYearCombobox; // change data type

	private JTextField customerJoinDatetxt;

	private JTextField connectionTypetxt;

	public BillPaymentPanel()
	{
		initializePanel();
	}

	public BillPaymentPanel(ModelObject obj)
	{
		customer = (Customer) obj;
		initializePanel();
	}

	private void initializePanel()
	{
		BasicGuiPanel header = configureHeaderPanel();
		BasicGuiPanel fieldsPanel = configureFieldsPanel();
		BasicGuiPanel btnPanel = configureButtonPanel();

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
	}

	private BasicGuiPanel configureFieldsPanel()
	{
		JLabel customerNameLbl = new JLabel("Customer Name");
		JLabel accountNummberLbl = new JLabel("Account Number");
		JLabel customerJoinDateLbl = new JLabel("Customer Join Date");
		JLabel connectionTypeLbl = new JLabel("Connection Type");

		JLabel paymentDateLbl = new JLabel("Payment Date");
		JLabel billingMonthLbl = new JLabel("Billing Month");
		JLabel billingYearLbl = new JLabel("Billing Year");

		JLabel amountLbl = new JLabel("Amount");
		JLabel surchargeLbl = new JLabel("Surcharge");
		JLabel advertisementBillLbl = new JLabel("Advertisement Bill");

		customerNametxt = new JTextField(20);
		accountNumbertxt = new JTextField(20);
		String[] months =
		{ "JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
		billingMonthCombobox = new JComboBox<String>(months);
		amounttxt = new JTextField(20);
		surchargetxt = new JTextField(20);
		advertisementBilltxt = new JTextField(20);
		datePicker = new JXDatePicker();
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		billingYearCombobox = new JComboBox<Integer>();
		billingYearCombobox.setModel(new javax.swing.DefaultComboBoxModel<Integer>(Helper.getNextYears(5)));
		customerJoinDatetxt = new JTextField(20);
		connectionTypetxt = new JTextField(20);

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
		p.add(accountNummberLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		p.add(accountNumbertxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		p.add(customerJoinDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		p.add(customerJoinDatetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(connectionTypeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(connectionTypetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(paymentDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(datePicker, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(billingMonthLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(billingMonthCombobox, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(billingYearLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(billingYearCombobox, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(amountLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(amounttxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(surchargeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(surchargetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		p.add(advertisementBillLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		p.add(advertisementBilltxt, c);

		return p;
	}

	private BasicGuiPanel configureButtonPanel()
	{
		payBillbtn = new JButton("Pay Bill");
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(payBillbtn);
		p.add(exitbtn);

		return p;
	}

	private BasicGuiPanel configureHeaderPanel()
	{
		JLabel headerLbl = new JLabel("Bill Payment");

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
	 * insert the values of ModelObject in the text fields
	 * */
	private void initTextFields()
	{
		if (customer == null)
		{
			return;
		}
		customerNametxt.setText(customer.getCustomerName());
		accountNumbertxt.setText(String.valueOf(customer.getAccountNumber()));
		customerJoinDatetxt.setText(customer.getDate().toString());
		connectionTypetxt.setText(String.valueOf(customer.getConnectionType()));
	}

}

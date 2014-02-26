package gui.panels;

import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Bill;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;

/**
 * Displays an individual bill record.
 * <p>
 * Allows to edit, delete and pay bill
 * */
@SuppressWarnings("serial")
public class BillPanel extends AbstractGuiPanel
{
	private Bill bill = null;

	private JTextField billNumberTxt;

	private JXDatePicker issueDate;

	private JXDatePicker dueDate;

	private JTextField accountNumberTxt;

	private JComboBox<String> billingMonthCbx;

	private JComboBox<Integer> billingYearCbx;

	private JTextField payableAmountTxt;

	private JTextField receivedAmountTxt;

	private JTextField receivedByTxt;

	private JComboBox<String> paidCbx;

	private JButton exitBtn;

	private JButton payBtn;

	private JButton editBtn;

	private JButton deleteBtn;

	public BillPanel(Bill b)
	{
		bill = b;
		initPanel();
	}

	@Override
	public void initPanel()
	{
		BasicGuiPanel header = getHeaderPanel();
		BasicGuiPanel fieldsPanel = getCenterPanel();
		BasicGuiPanel btnPanel = getButtonPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setPanelGridBagConstraints(c, 0, 1, 10);
		c.ipady = 20; // make this component tall
		add(header, c);

		c.ipady = 0;
		setPanelGridBagConstraints(c, 0, 2, 30);
		add(fieldsPanel, c);

		setPanelGridBagConstraints(c, 0, 3, 30);
		add(btnPanel, c);
		setFieldsEditable(false);
	}

	@Override
	public BasicGuiPanel getCenterPanel()
	{
		JLabel billNumberLbl = new JLabel("Bill Number");
		JLabel issueDateLbl = new JLabel("Issue Date");
		JLabel dueDateLbl = new JLabel("Due Date");
		JLabel accountNumberLbl = new JLabel("Account Number");
		JLabel billMonthLbl = new JLabel("Billing Month");
		JLabel billYearLbl = new JLabel("Billing Year");
		JLabel payableAmountLbl = new JLabel("Payable Amount");
		JLabel receivedAmountLbl = new JLabel("Received Amount");
		JLabel receivedByLbl = new JLabel("Received By");
		JLabel paidLbl = new JLabel("Paid");

		billNumberTxt = new JTextField(10);
		billNumberTxt.setText(String.valueOf(bill.getBillNumber()));
		issueDate = new JXDatePicker();
		issueDate.setDate(bill.getIssueDate());
		dueDate = new JXDatePicker();
		dueDate.setDate(bill.getDueDate());
		accountNumberTxt = new JTextField(10);
		accountNumberTxt.setText(String.valueOf(bill.getAccountNumber()));
		billingMonthCbx = new JComboBox<String>(Helper.getMonths());
		billingMonthCbx.setSelectedItem(bill.getMonth());
		Integer[] years = Helper.getNextYears(5);
		billingYearCbx = new JComboBox<Integer>();
		billingYearCbx.setModel(new javax.swing.DefaultComboBoxModel<Integer>(years));
		billingYearCbx.setSelectedItem(bill.getYear());
		payableAmountTxt = new JTextField(10);
		payableAmountTxt.setText(String.valueOf(bill.getPayableAmount()));
		receivedAmountTxt = new JTextField(10);
		receivedAmountTxt.setText(String.valueOf(bill.getReceivedAmount()));
		receivedByTxt = new JTextField(10);
		receivedByTxt.setText(bill.getReceivedBy());
		String[] trueFalse =
		{ "True", "False" };
		paidCbx = new JComboBox<String>(trueFalse);
		if (bill.getPaid())
		{
			paidCbx.setSelectedIndex(0);
		}
		else
		{
			paidCbx.setSelectedIndex(1);
		}

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(billNumberLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(billNumberTxt, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(issueDateLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(issueDate, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(dueDateLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(dueDate, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(accountNumberLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(accountNumberTxt, c);

		setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 10, 0);
		p.add(billMonthLbl, c);

		setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 10, 10);
		p.add(billingMonthCbx, c);

		setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 10, 0);
		p.add(billYearLbl, c);

		setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 10, 10);
		p.add(billingYearCbx, c);

		setGridBagConstraints(c, 0, 7, GridBagConstraints.LINE_START, 10, 0);
		p.add(payableAmountLbl, c);

		setGridBagConstraints(c, 1, 7, GridBagConstraints.LINE_END, 10, 10);
		p.add(payableAmountTxt, c);

		setGridBagConstraints(c, 0, 8, GridBagConstraints.LINE_START, 10, 0);
		p.add(receivedAmountLbl, c);

		setGridBagConstraints(c, 1, 8, GridBagConstraints.LINE_END, 10, 10);
		p.add(receivedAmountTxt, c);

		setGridBagConstraints(c, 0, 9, GridBagConstraints.LINE_START, 10, 0);
		p.add(receivedByLbl, c);

		setGridBagConstraints(c, 1, 9, GridBagConstraints.LINE_END, 10, 10);
		p.add(receivedByTxt, c);

		setGridBagConstraints(c, 0, 10, GridBagConstraints.LINE_START, 10, 0);
		p.add(paidLbl, c);

		setGridBagConstraints(c, 1, 10, GridBagConstraints.LINE_END, 10, 10);
		p.add(paidCbx, c);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{
		JLabel headerLbl = new JLabel("Bill");

		BasicGuiPanel headerPanel = new BasicGuiPanel(new GridBagLayout());
		headerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		GridBagConstraints hc = new GridBagConstraints();

		setGridBagConstraints(hc, 1, 4, GridBagConstraints.CENTER, 0, 0);
		headerPanel.add(headerLbl, hc);

		return headerPanel;
	}

	@Override
	public BasicGuiPanel getButtonPanel()
	{
		payBtn = new JButton("Pay Bill");
		editBtn = new JButton("Edit");
		// editBtn.addActionListener(new EditCustomer());

		// cancelbtn = new JButton("Cancel");
		// cancelbtn.addActionListener(new CancelEdit());

		deleteBtn = new JButton("Delete");
		// deleteBtn.addActionListener(new DeleteCustomer());

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new CloseViewCaller());
		// TODO if in edit more then do not close the panel without prompt
		// showCancelbtn();

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(payBtn);
		p.add(editBtn);
		p.add(deleteBtn);
		// p.add(cancelbtn);
		p.add(exitBtn);

		return p;
	}

	@Override
	public GuiPanel getOwningView()
	{
		// TODO correct implementation required
		GuiPanel owningView = null;
		Container c = this.getParent();
		if (c instanceof GuiPanel)
		{
			owningView = (GuiPanel) c;
		}
		return owningView;
	}

	private void setPanelGridBagConstraints(GridBagConstraints c, int gridx, int gridy, int paddingTop)
	{
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(paddingTop, 0, 0, 0); // top padding
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
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

	/**
	 * If the panel is in edit mode set the text fields editable otherwise make
	 * the fields un-editable
	 * */
	private void setFieldsEditable(Boolean edit)
	{
		billNumberTxt.setEditable(edit);
		issueDate.setEditable(edit);
		dueDate.setEditable(edit);
		accountNumberTxt.setEditable(edit);
		billingMonthCbx.setEnabled(edit);
		billingYearCbx.setEnabled(edit);
		payableAmountTxt.setEditable(edit);
		receivedAmountTxt.setEditable(edit);
		receivedByTxt.setEditable(edit);
		paidCbx.setEnabled(edit);
	}
}
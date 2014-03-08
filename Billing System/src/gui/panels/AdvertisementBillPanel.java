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

import model.AdvertisementBill;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.callers.DeleteBillCaller;
import database.callers.PayBillCaller;

@SuppressWarnings("serial")
public class AdvertisementBillPanel extends AbstractGuiPanel
{
	private AdvertisementBill bill = null;

	private JTextField billNumberTxt;

	private JXDatePicker datePicker;

	private JTextField accountNumberTxt;

	private JTextField payableAmountTxt;

	private JComboBox<String> paidCbx;

	private JButton exitBtn;

	private JButton payBtn;

	private JButton editBtn;

	private JButton deleteBtn;

	private JButton printBtn;

	public AdvertisementBillPanel(AdvertisementBill b)
	{
		bill = b;
		initPanel();
	}

	@Override
	public void initPanel()
	{
		addPanels();
		initFields();
	}

	@Override
	public BasicGuiPanel getCenterPanel()
	{
		JLabel billNumberLbl = new JLabel("Bill Number");
		JLabel amountLbl = new JLabel("Amount");
		JLabel dateLbl = new JLabel("Date");
		JLabel accountNumberLbl = new JLabel("Account Number");
		JLabel paidLbl = new JLabel("Paid");

		billNumberTxt = new JTextField(10);
		billNumberTxt.setEditable(false);
		payableAmountTxt = new JTextField(10);
		datePicker = new JXDatePicker();
		accountNumberTxt = new JTextField(10);
		String[] trueFalse =
		{ "True", "False" };
		paidCbx = new JComboBox<String>(trueFalse);

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(billNumberLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(billNumberTxt, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(amountLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(payableAmountTxt, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(dateLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(datePicker, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(accountNumberLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(accountNumberTxt, c);

		setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 10, 0);
		p.add(paidLbl, c);

		setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 10, 10);
		p.add(paidCbx, c);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{

		JLabel headerLbl = new JLabel("Advertisement Bill");

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
		payBtn.addActionListener(new PayBillCaller(AdvertisementBillPanel.this));

		editBtn = new JButton("Edit");
		// editBtn.addActionListener(new EditCustomer());

		// cancelbtn = new JButton("Cancel");
		// cancelbtn.addActionListener(new CancelEdit());

		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new DeleteBillCaller(AdvertisementBillPanel.this));

		printBtn = new JButton("Print");
		// printBtn.addActionListener(new PrintBillPanelCaller(BillPanel.this));

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new CloseViewCaller());
		// TODO if in edit more then do not close the panel without prompt
		// showCancelbtn();

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(payBtn);
		p.add(printBtn);
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

	public AdvertisementBill getBill() throws Exception
	{
		java.util.Date date = datePicker.getDate();
		String payable = payableAmountTxt.getText();
		if (Helper.isEmpty(payable) || !Helper.isDigit(payable))
		{
			throw new Exception("Amount payable cannot be empty and can contain only numbers - digits");
		}
		String paid = String.valueOf(paidCbx.getSelectedItem());

		if (bill == null)
		{
			bill = new AdvertisementBill();
		}
		bill.setPayableAmount(Integer.valueOf(payable));
		bill.setDate(date);
		bill.setAccountNumber(Integer.valueOf(accountNumberTxt.getText()));
		if (("True").equalsIgnoreCase(paid))
		{
			bill.setPaid(true);
		}
		else
		{
			bill.setPaid(false);
		}
		return bill;
	}

	/** Insert the values of the bill to be displayed in the text fields */
	private void initFields()
	{
		if (bill == null)
		{
			return;
		}
		billNumberTxt.setText(String.valueOf(bill.getId()));
		payableAmountTxt.setText(String.valueOf(bill.getPayableAmount()));
		datePicker.setDate(bill.getDate());
		accountNumberTxt.setText(String.valueOf(bill.getAccountNumber()));
		if (bill.getPaid())
		{
			paidCbx.setSelectedIndex(0);
		}
		else
		{
			paidCbx.setSelectedIndex(1);
		}
	}
}
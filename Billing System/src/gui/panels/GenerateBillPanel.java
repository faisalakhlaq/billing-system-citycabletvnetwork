package gui.panels;

import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.callers.GenerateBillCaller;

@SuppressWarnings("serial")
public class GenerateBillPanel extends AbstractGuiPanel
{
	private JTextField commercialBilltxt;

	private JTextField privateBilltxt;

	private JComboBox<String> billingMonthCbx;

	private JComboBox<Integer> billingYearCbx;

	private JXDatePicker billIssueDate;

	private JXDatePicker billDueDate;

	private JButton generateBillBtn;

	private JButton exitBtn;

	/**
	 * The panel generated bill for all the customers.
	 */
	public GenerateBillPanel()
	{
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
	}

	@Override
	public BasicGuiPanel getCenterPanel()
	{
		JLabel commercialBillLbl = new JLabel("Commercial Bill");
		JLabel privateBillLbl = new JLabel("Private Bill");
		JLabel billIssueDateLbl = new JLabel("Issue Date");
		JLabel billDueDateLbl = new JLabel("Due Date");
		JLabel billMonthLbl = new JLabel("Billing Month");
		JLabel billYearLbl = new JLabel("Billing Year");

		commercialBilltxt = new JTextField(10);
		privateBilltxt = new JTextField(10);
		billIssueDate = new JXDatePicker();
		billIssueDate.setDate(Calendar.getInstance().getTime());
		billIssueDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		billDueDate = new JXDatePicker();
		billDueDate.setDate(Calendar.getInstance().getTime());
		billDueDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		billingMonthCbx = new JComboBox<String>(Helper.getMonths());
		Integer[] years = Helper.getNextYears(5);
		billingYearCbx = new JComboBox<Integer>();
		billingYearCbx.setModel(new javax.swing.DefaultComboBoxModel<Integer>(years));

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(commercialBillLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(commercialBilltxt, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(privateBillLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(privateBilltxt, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(billIssueDateLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(billIssueDate, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(billDueDateLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(billDueDate, c);

		setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 10, 0);
		p.add(billMonthLbl, c);

		setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 10, 10);
		p.add(billingMonthCbx, c);

		setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 10, 0);
		p.add(billYearLbl, c);

		setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 10, 10);
		p.add(billingYearCbx, c);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{
		JLabel headerLbl = new JLabel("Generate Bill");

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
		generateBillBtn = new JButton("Generate Bill");
		generateBillBtn.addActionListener(new GenerateBillCaller(this));
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new CloseViewCaller());

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(generateBillBtn);
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

	public String getCommercialBill()
	{
		return commercialBilltxt.getText();
	}

	public String getPrivateBill()
	{
		return privateBilltxt.getText();
	}

	public String getBillingMonth()
	{
		return String.valueOf(billingMonthCbx.getSelectedItem());
	}

	public int getBillingYear()
	{
		return Integer.valueOf(String.valueOf(billingYearCbx.getSelectedItem()));
	}

	public Date getBillIssueDate()
	{
		return billIssueDate.getDate();
	}

	public Date getBillDueDate()
	{
		return billDueDate.getDate();
	}
}
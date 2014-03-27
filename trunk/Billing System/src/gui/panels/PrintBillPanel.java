package gui.panels;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import model.Bill;
import model.CompanyInformation;
import model.Customer;
import table.CustomerBillHistoryTableModel;
import table.TableSorter;

@SuppressWarnings("serial")
public class PrintBillPanel extends BasicGuiPanel implements Printable
{
	private JButton exitbtn = null;

	private JButton resetbtn = null;

	private JButton printBtn = null;

	private JTextField issueDatetxt;

	private JTextField dueDatetxt;

	private JTextField billNotxt;

	private JTextField acNotxt;

	private JTextField areaCodetxt;

	private JTextField custNametxt;

	private JTextField telNumbertxt;

	private JTextArea addresstxt;

	private JTextField billingMonthtxt;

	private JTextField currentPayableAmounttxt;

	private JTextField previousAmounttxt;

	private JTextField payableAmountByDueDatetxt;

	private JTextField payableAfterDueDatetxt;

	private JTextField surchargetxt;

	private JTextField advertisementChargestxt;

	private JTextField receivedAmounttxt;

	private JTextField balancetxt;

	private JTextField signatureReceivedAuthoritytxt;

	private Customer customer = null;

	private Bill bill = null;

	private Color background = null;

	private BasicGuiPanel header = null;

	private BasicGuiPanel fieldsPanel = null;

	private BasicGuiPanel btnPanel = null;

	private BasicGuiPanel recordPanel = null;

	private Vector<Bill> previousBills = null;

	private String[] columnNames =
	{ "Billing Month", "Billing Year", "Payable Amount", "Paid" };

	/**
	 * Provide the customer and bill list to get the printed bills
	 * 
	 * @param Customer
	 *            for which bill is to be printed
	 *            <p>
	 *            Bill to be printed
	 *            <p>
	 *            Vector<Bill> previous bills of the customer
	 * */
	public PrintBillPanel(Customer customer, Bill bill, Vector<Bill> previousBills)
	{
		background = this.getBackground();
		this.customer = customer;
		this.bill = bill;
		this.previousBills = previousBills;
		configurePanel();
	}

	private void configurePanel()
	{
		header = configureHeader();
		fieldsPanel = configureDataFieldsPanel();
		btnPanel = configureBtnPanel();
		recordPanel = getRecordPanel();

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

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(recordPanel, c);
	}

	private BasicGuiPanel configureBtnPanel()
	{
		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());
		resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ResetFieldsListener());
		printBtn = new JButton("Print");
		printBtn.addActionListener(new PrintActionListener());

		p.add(printBtn);
		p.add(resetbtn);
		p.add(exitbtn);

		return p;
	}

	private BasicGuiPanel configureDataFieldsPanel()
	{
		JLabel issueDateLbl = new JLabel("Date of Issue");
		JLabel dueDateLbl = new JLabel("Due Date");
		JLabel billNoLbl = new JLabel("Bill No");
		JLabel acNoLbl = new JLabel("A/c No");
		JLabel areaCodeLbl = new JLabel("Area Code");
		JLabel custNameLbl = new JLabel("Customer's Name");
		JLabel telNumberLbl = new JLabel("Phone No");
		JLabel addressLbl = new JLabel("Address");
		JLabel billingMonthLbl = new JLabel("Billing Month");
		JLabel currentPayableAmountLbl = new JLabel("Current Payable Amount");
		JLabel previousAmountLbl = new JLabel("Previous Amount");
		JLabel payableAmountByDueDateLbl = new JLabel("Payable Amount By Due Date");
		JLabel payableAfterDueDateLbl = new JLabel("Payable After Due Date");
		JLabel surchargeLbl = new JLabel("Surcharge");
		JLabel advertisementChargesLbl = new JLabel("Advertisement Charges");
		JLabel receivedAmountLbl = new JLabel("Received Amount");
		JLabel balanceLbl = new JLabel("Balance");
		JLabel signatureReceivedAuthorityLbl = new JLabel("Signature Recieved Authority");

		issueDatetxt = new JTextField(20);
		dueDatetxt = new JTextField(20);
		billNotxt = new JTextField(20);
		acNotxt = new JTextField(20);
		areaCodetxt = new JTextField(20);
		custNametxt = new JTextField(20);
		telNumbertxt = new JTextField(20);
		addresstxt = new JTextArea();
		addresstxt.setRows(3);
		addresstxt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		billingMonthtxt = new JTextField(20);
		currentPayableAmounttxt = new JTextField(20);
		previousAmounttxt = new JTextField(20);
		payableAmountByDueDatetxt = new JTextField(20);
		payableAfterDueDatetxt = new JTextField(20);
		surchargetxt = new JTextField(20);
		advertisementChargestxt = new JTextField(20);
		receivedAmounttxt = new JTextField(20);
		balancetxt = new JTextField(20);
		signatureReceivedAuthoritytxt = new JTextField(20);

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(issueDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(issueDatetxt, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 0;
		c.gridheight = 20;
		p.add(getBillHistoryPanel(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		p.add(dueDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		p.add(dueDatetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		p.add(billNoLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		p.add(billNotxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(acNoLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		p.add(acNotxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(areaCodeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		p.add(areaCodetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(custNameLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		p.add(custNametxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(custNameLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		p.add(custNametxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(telNumberLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		p.add(telNumbertxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(addressLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		p.add(addresstxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		p.add(billingMonthLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		p.add(billingMonthtxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		p.add(currentPayableAmountLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;
		p.add(currentPayableAmounttxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		p.add(previousAmountLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 12;
		c.gridwidth = 1;
		p.add(previousAmounttxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 1;
		p.add(payableAmountByDueDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 13;
		c.gridwidth = 1;
		p.add(payableAmountByDueDatetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		p.add(payableAfterDueDateLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 14;
		c.gridwidth = 1;
		p.add(payableAfterDueDatetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		p.add(surchargeLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 15;
		c.gridwidth = 1;
		p.add(surchargetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = 1;
		p.add(advertisementChargesLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 16;
		c.gridwidth = 1;
		p.add(advertisementChargestxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 17;
		c.gridwidth = 1;
		p.add(receivedAmountLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 17;
		c.gridwidth = 1;
		p.add(receivedAmounttxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 1;
		p.add(balanceLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 18;
		c.gridwidth = 1;
		p.add(balancetxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 19;
		c.gridwidth = 1;
		p.add(signatureReceivedAuthorityLbl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 19;
		c.gridwidth = 1;
		p.add(signatureReceivedAuthoritytxt, c);

		setFields();

		return p;
	}

	private BasicGuiPanel configureHeader()
	{
		CompanyInformation compInfo = new CompanyInformation();

		JLabel companyName = new JLabel(compInfo.getCompanyName());
		Font f = new Font("Monospaced", Font.BOLD, 20);
		companyName.setFont(f);

		JLabel companyAddress = new JLabel(compInfo.getCompanyAddress() + compInfo.getCompanyTelephoneNumber());
		Font addressFont = new Font("Monospaced", Font.BOLD, 20);
		companyAddress.setFont(addressFont);

		JLabel companyOwner = new JLabel(compInfo.getCompanyOwnerName());
		Font ownerFont = new Font("Monospaced", Font.BOLD, 20);
		companyOwner.setFont(ownerFont);

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
		headerPanel.add(companyName, hc);

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 0;
		hc.gridy = 2;
		hc.gridwidth = 1;
		headerPanel.add(companyAddress, hc);

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 0;
		hc.gridy = 3;
		hc.gridwidth = 1;
		headerPanel.add(companyOwner, hc);

		return headerPanel;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException
	{
		if (pageIndex > 0)
		{
			return Printable.NO_SUCH_PAGE;
		}
		double scaleX = pf.getImageableWidth() / this.getWidth();
		double scaleY = pf.getImageableHeight() / this.getHeight();
		// Maintain aspect ratio
		double scale = Math.min(scaleX, scaleY);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pf.getImageableX(), pf.getImageableY());
		g2.scale(scale, scale);
		Font f = new Font("Monospaced", Font.PLAIN, 12);
		g2.setFont(f);
		this.paint(g2);
		return Printable.PAGE_EXISTS;
	}

	private void setFieldsEditable(boolean edit)
	{
		issueDatetxt.setEditable(edit);
		dueDatetxt.setEditable(edit);
		billNotxt.setEditable(edit);
		acNotxt.setEditable(edit);
		areaCodetxt.setEditable(edit);
		custNametxt.setEditable(edit);
		telNumbertxt.setEditable(edit);
		addresstxt.setEditable(edit);
		billingMonthtxt.setEditable(edit);
		currentPayableAmounttxt.setEditable(edit);
		previousAmounttxt.setEditable(edit);
		payableAmountByDueDatetxt.setEditable(edit);
		payableAfterDueDatetxt.setEditable(edit);
		surchargetxt.setEditable(edit);
		advertisementChargestxt.setEditable(edit);
		receivedAmounttxt.setEditable(edit);
		balancetxt.setEditable(edit);
		signatureReceivedAuthoritytxt.setEditable(edit);
	}

	/**
	 * Insert the bill customer data in the text fields
	 * */
	private void setFields()
	{
		if (bill == null || customer == null) return;

		issueDatetxt.setText(bill.getIssueDate().toString());
		dueDatetxt.setText(bill.getDueDate().toString());
		billNotxt.setText(String.valueOf(bill.getBillNumber()));
		acNotxt.setText(String.valueOf(bill.getAccountNumber()));
		areaCodetxt.setText(String.valueOf(customer.getAreaCode()));
		custNametxt.setText(customer.getCustomerName());
		telNumbertxt.setText(String.valueOf(customer.getTelNumber()));
		addresstxt.setText(customer.getCustomerAddress());
		billingMonthtxt.setText(bill.getMonth());
		currentPayableAmounttxt.setText(String.valueOf(bill.getPayableAmount()));
		previousAmounttxt.setText("");
		payableAmountByDueDatetxt.setText(String.valueOf(bill.getPayableAmount()));
		payableAfterDueDatetxt.setText("");
		surchargetxt.setText("");
		advertisementChargestxt.setText("");
		receivedAmounttxt.setText(String.valueOf(bill.getReceivedAmount()));
		balancetxt.setText(String.valueOf(""));
		signatureReceivedAuthoritytxt.setText(String.valueOf(bill.getReceivedBy()));
	}

	/**
	 * Returns the panel which displays the bill history of the customer. Panel
	 * includes a table with bill records
	 */
	private BasicGuiPanel getBillHistoryPanel()
	{
		BasicGuiPanel billHistoryPanel = new BasicGuiPanel();
		CustomerBillHistoryTableModel model = new CustomerBillHistoryTableModel(previousBills, columnNames);
		TableSorter sorter = new TableSorter(model); // ADDED THIS
		// JTable table = new JTable(new MyTableModel()); //OLD
		JTable table = new JTable(sorter); // NEW
		table.setBackground(Color.WHITE);
		sorter.setTableHeader(table.getTableHeader()); // ADDED THIS
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		// Set up tool tips for column headers.
		table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.WHITE);
		// scrollPane.setPreferredSize(new Dimension(1000, 500));

		billHistoryPanel.setOpaque(true);
		billHistoryPanel.setBackground(Color.WHITE);
		billHistoryPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		billHistoryPanel.add(scrollPane, c);

		return billHistoryPanel;
	}

	/**
	 * Enable a caller to print a bill and close the panel without user
	 * interference
	 */
	public void printAndClose()
	{
		printBtn.doClick();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				exitbtn.doClick();
			}
		});
	}

	private class ResetFieldsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			issueDatetxt.setText(null);
			dueDatetxt.setText(null);
			billNotxt.setText(null);
			acNotxt.setText(null);
			areaCodetxt.setText(null);
			custNametxt.setText(null);
			telNumbertxt.setText(null);
			addresstxt.setText(null);
			addresstxt.setText(null);
			billingMonthtxt.setText(null);
			currentPayableAmounttxt.setText(null);
			previousAmounttxt.setText(null);
			payableAmountByDueDatetxt.setText(null);
			payableAfterDueDatetxt.setText(null);
			surchargetxt.setText(null);
			advertisementChargestxt.setText(null);
			receivedAmounttxt.setText(null);
			balancetxt.setText(null);
			signatureReceivedAuthoritytxt.setText(null);
		}
	}

	private class PrintActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			PrinterJob printJob = PrinterJob.getPrinterJob();
			// setFieldsEditable(false);
			printJob.setPrintable(PrintBillPanel.this);
			showButtons(false); // Hide buttons before printing
			setBackgroundColor(Color.WHITE);
			// if (printJob.printDialog())
			// {
			try
			{
				printJob.print();
			}
			catch (Exception printException)
			{
				new MessageDialog("Error", "Error occured while printing! +\n" + printException.getMessage());
				printException.printStackTrace();
			}
			// }
			setBackgroundColor(background);
			showButtons(true);// Show buttons after printing
			// setFieldsEditable(true);
		}
	}

	private void setBackgroundColor(Color color)
	{
		this.setBackground(color);
		header.setBackground(color);
		btnPanel.setBackground(color);
		recordPanel.setBackground(color);
		fieldsPanel.setBackground(color);
	}

	/**
	 * Add the same fields at the end of the panel for the company to keep with
	 * them.
	 * <p>
	 * The top part of the printed bill will be given to the customer as receipt
	 * and the lower part of the page will be kept in the company for record
	 */
	private BasicGuiPanel getRecordPanel()
	{
		JLabel billNoLbl = new JLabel("Bill No");
		JLabel acNoLbl = new JLabel("A/c No");
		JLabel areaCodeLbl = new JLabel("Area Code");
		JLabel custNameLbl = new JLabel("Customer's Name");
		JLabel addressLbl = new JLabel("Address");
		JLabel billingMonthLbl = new JLabel("Billing Month");
		JLabel receivedAmountLbl = new JLabel("Received Amount");
		JLabel signatureReceivedAuthorityLbl = new JLabel("Signature Recieved Authority");

		JTextField billNo = new JTextField(20);
		JTextField acNo = new JTextField(20);
		JTextField areaCode = new JTextField(20);
		JTextField custName = new JTextField(20);
		JTextField address = new JTextField(20);
		JTextField billingMonth = new JTextField(20);
		JTextField receivedAmount = new JTextField(20);
		JTextField signatureReceivedAuthority = new JTextField(20);

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(billNoLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(billNo, c);

		setGridBagConstraints(c, 2, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(acNoLbl, c);

		setGridBagConstraints(c, 3, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(acNo, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(areaCodeLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(areaCode, c);

		setGridBagConstraints(c, 2, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(custNameLbl, c);

		setGridBagConstraints(c, 3, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(custName, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(addressLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(address, c);

		setGridBagConstraints(c, 2, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(billingMonthLbl, c);

		setGridBagConstraints(c, 3, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(billingMonth, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(receivedAmountLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(receivedAmount, c);

		setGridBagConstraints(c, 2, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(signatureReceivedAuthorityLbl, c);

		setGridBagConstraints(c, 3, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(signatureReceivedAuthority, c);

		{ // fill the fields with the customer and bill data
			if (bill != null || customer != null)
			{
				billNo.setText(String.valueOf(bill.getBillNumber()));
				acNo.setText(String.valueOf(bill.getAccountNumber()));
				areaCode.setText(String.valueOf(customer.getAreaCode()));
				custName.setText(customer.getCustomerName());
				address.setText(customer.getCustomerAddress());
				billingMonth.setText(bill.getMonth());
				receivedAmount.setText(String.valueOf(bill.getReceivedAmount()));
				signatureReceivedAuthority.setText(String.valueOf(bill.getReceivedBy()));
			}
		}
		return p;
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
	 * Make the buttons invisible or visible
	 */
	private void showButtons(boolean show)
	{
		exitbtn.setVisible(show);
		resetbtn.setVisible(show);
		printBtn.setVisible(show);
	}
}
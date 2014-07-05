package gui.panels;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import model.Bill;
import model.CompanyInformation;
import model.Customer;
import table.CustomerBillHistoryTableModel;
import table.TableSorter;

@SuppressWarnings("serial")
public class PrintBillPanel extends BasicGuiPanel implements Printable
{
	private Font txtFieldFont = new Font("Monospaced", Font.BOLD, 30);

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
		JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
		x.setMinimumSize(new Dimension(this.getWidth(), 3));

		setPanelGridBagConstraints(c, 0, 1, 10);
		c.ipady = 20; // make this component tall
		add(header, c);

		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(x, c);

		setPanelGridBagConstraints(c, 0, 3, 30);
		add(fieldsPanel, c);
		// add(x);

		setPanelGridBagConstraints(c, 0, 4, 30);
		add(getInstructionsPanel(), c);
		// add(x);

		// setPanelGridBagConstraints(c, 0, 4, 30);
		x = new JSeparator(SwingConstants.HORIZONTAL);
		x.setMinimumSize(new Dimension(this.getWidth(), 3));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(x, c);

		setPanelGridBagConstraints(c, 0, 6, 30);
		add(btnPanel, c);

		setPanelGridBagConstraints(c, 0, 7, 30);
		add(recordPanel, c);
	}

	private BasicGuiPanel getInstructionsPanel()
	{
		BasicGuiPanel p = new BasicGuiPanel();
		BufferedImage myPicture = null;
		try
		{
			myPicture = ImageIO.read(this.getClass().getResource("/resources/instructions.bmp"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBorder(null);
		p.add(picLabel, BorderLayout.CENTER);
		p.setBorder(null);
		return p;
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
		issueDateLbl.setFont(txtFieldFont);
		JLabel dueDateLbl = new JLabel("Due Date");
		dueDateLbl.setFont(txtFieldFont);
		JLabel billNoLbl = new JLabel("Bill No");
		billNoLbl.setFont(txtFieldFont);
		JLabel acNoLbl = new JLabel("A/c No");
		acNoLbl.setFont(txtFieldFont);
		JLabel areaCodeLbl = new JLabel("Area Code");
		areaCodeLbl.setFont(txtFieldFont);
		JLabel custNameLbl = new JLabel("Customer's Name");
		custNameLbl.setFont(txtFieldFont);
		JLabel telNumberLbl = new JLabel("Phone No");
		telNumberLbl.setFont(txtFieldFont);
		JLabel addressLbl = new JLabel("Address");
		addressLbl.setFont(txtFieldFont);
		JLabel billingMonthLbl = new JLabel("Billing Month");
		billingMonthLbl.setFont(txtFieldFont);
		JLabel currentPayableAmountLbl = new JLabel("Current Payable Amount");
		currentPayableAmountLbl.setFont(txtFieldFont);
		JLabel previousAmountLbl = new JLabel("Previous Amount");
		previousAmountLbl.setFont(txtFieldFont);
		JLabel payableAmountByDueDateLbl = new JLabel("Payable Amount By Due Date");
		payableAmountByDueDateLbl.setFont(txtFieldFont);
		JLabel payableAfterDueDateLbl = new JLabel("Payable After Due Date");
		payableAfterDueDateLbl.setFont(txtFieldFont);
		JLabel surchargeLbl = new JLabel("Surcharge");
		surchargeLbl.setFont(txtFieldFont);
		JLabel advertisementChargesLbl = new JLabel("Advertisement Charges");
		advertisementChargesLbl.setFont(txtFieldFont);
		JLabel receivedAmountLbl = new JLabel("Received Amount");
		receivedAmountLbl.setFont(txtFieldFont);
		JLabel balanceLbl = new JLabel("Balance");
		balanceLbl.setFont(txtFieldFont);
		JLabel signatureReceivedAuthorityLbl = new JLabel("Signature Recieved Authority");
		signatureReceivedAuthorityLbl.setFont(txtFieldFont);

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
		p.setBorder(BorderFactory.createEtchedBorder());
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(issueDateLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(issueDatetxt, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(10, 10, 10, 20);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 0;
		c.gridheight = 20;
		p.add(getBillHistoryPanel(), c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(dueDateLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(dueDatetxt, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(billNoLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(billNotxt, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(acNoLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(acNotxt, c);

		setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 10, 0);
		p.add(areaCodeLbl, c);

		setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 10, 10);
		p.add(areaCodetxt, c);

		setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 10, 0);
		p.add(custNameLbl, c);

		setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 10, 10);
		p.add(custNametxt, c);

		setGridBagConstraints(c, 0, 7, GridBagConstraints.LINE_START, 10, 0);
		p.add(custNameLbl, c);

		setGridBagConstraints(c, 1, 7, GridBagConstraints.LINE_END, 10, 10);
		p.add(custNametxt, c);

		setGridBagConstraints(c, 0, 8, GridBagConstraints.LINE_START, 10, 0);
		p.add(telNumberLbl, c);

		setGridBagConstraints(c, 1, 8, GridBagConstraints.LINE_END, 10, 10);
		p.add(telNumbertxt, c);

		setGridBagConstraints(c, 0, 9, GridBagConstraints.LINE_START, 10, 0);
		p.add(addressLbl, c);

		setGridBagConstraints(c, 1, 9, GridBagConstraints.LINE_END, 10, 10);
		p.add(addresstxt, c);

		setGridBagConstraints(c, 0, 10, GridBagConstraints.LINE_START, 10, 0);
		p.add(billingMonthLbl, c);

		setGridBagConstraints(c, 1, 10, GridBagConstraints.LINE_END, 10, 10);
		p.add(billingMonthtxt, c);

		setGridBagConstraints(c, 0, 11, GridBagConstraints.LINE_START, 10, 0);
		p.add(currentPayableAmountLbl, c);

		setGridBagConstraints(c, 1, 11, GridBagConstraints.LINE_END, 10, 10);
		p.add(currentPayableAmounttxt, c);

		setGridBagConstraints(c, 0, 12, GridBagConstraints.LINE_START, 10, 0);
		p.add(previousAmountLbl, c);

		setGridBagConstraints(c, 1, 12, GridBagConstraints.LINE_END, 10, 10);
		p.add(previousAmounttxt, c);

		setGridBagConstraints(c, 0, 13, GridBagConstraints.LINE_START, 10, 0);
		p.add(payableAmountByDueDateLbl, c);

		setGridBagConstraints(c, 1, 13, GridBagConstraints.LINE_END, 10, 10);
		p.add(payableAmountByDueDatetxt, c);

		setGridBagConstraints(c, 0, 14, GridBagConstraints.LINE_START, 10, 0);
		p.add(payableAfterDueDateLbl, c);

		setGridBagConstraints(c, 1, 14, GridBagConstraints.LINE_END, 10, 10);
		p.add(payableAfterDueDatetxt, c);

		setGridBagConstraints(c, 0, 15, GridBagConstraints.LINE_START, 10, 0);
		p.add(surchargeLbl, c);

		setGridBagConstraints(c, 1, 15, GridBagConstraints.LINE_END, 10, 10);
		p.add(surchargetxt, c);

		setGridBagConstraints(c, 0, 16, GridBagConstraints.LINE_START, 10, 0);
		p.add(advertisementChargesLbl, c);

		setGridBagConstraints(c, 1, 16, GridBagConstraints.LINE_END, 10, 10);
		p.add(advertisementChargestxt, c);

		setGridBagConstraints(c, 0, 17, GridBagConstraints.LINE_START, 10, 0);
		p.add(receivedAmountLbl, c);

		setGridBagConstraints(c, 1, 17, GridBagConstraints.LINE_END, 10, 10);
		p.add(receivedAmounttxt, c);

		setGridBagConstraints(c, 0, 18, GridBagConstraints.LINE_START, 10, 0);
		p.add(balanceLbl, c);

		setGridBagConstraints(c, 1, 18, GridBagConstraints.LINE_END, 10, 10);
		p.add(balancetxt, c);

		setGridBagConstraints(c, 0, 19, GridBagConstraints.LINE_START, 10, 0);
		c.insets = new Insets(10, 0, 10, 0);
		p.add(signatureReceivedAuthorityLbl, c);

		setGridBagConstraints(c, 1, 19, GridBagConstraints.LINE_END, 10, 10);
		c.insets = new Insets(10, 10, 10, 0);
		p.add(signatureReceivedAuthoritytxt, c);
		setTextFieldFonts();
		setFieldValues();

		return p;
	}

	private BasicGuiPanel configureHeader()
	{
		CompanyInformation compInfo = new CompanyInformation();

		JLabel companyName = new JLabel(compInfo.getCompanyName());
		Font f = new Font("Monospaced", Font.BOLD, 40);
		companyName.setFont(f);

		JLabel companyAddress = new JLabel(compInfo.getCompanyAddress() + compInfo.getCompanyTelephoneNumber());
		Font addressFont = new Font("Monospaced", Font.BOLD, 40);
		companyAddress.setFont(addressFont);

		JLabel companyOwner = new JLabel(compInfo.getCompanyOwnerName());
		Font ownerFont = new Font("Monospaced", Font.BOLD, 40);
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
	private void setFieldValues()
	{
		if (bill == null || customer == null) return;

		issueDatetxt.setText(bill.getIssueDate().toString());
		issueDatetxt.setBorder(null);
		dueDatetxt.setText(bill.getDueDate().toString());
		dueDatetxt.setBorder(null);
		billNotxt.setText(String.valueOf(bill.getBillNumber()));
		billNotxt.setBorder(null);
		acNotxt.setText(String.valueOf(bill.getAccountNumber()));
		acNotxt.setBorder(null);
		areaCodetxt.setText(String.valueOf(customer.getAreaCode()));
		areaCodetxt.setBorder(null);
		custNametxt.setText(customer.getCustomerName());
		custNametxt.setBorder(null);
		telNumbertxt.setText(String.valueOf(customer.getTelNumber()));
		telNumbertxt.setBorder(null);
		addresstxt.setText(customer.getCustomerAddress());
		addresstxt.setBorder(null);
		billingMonthtxt.setText(bill.getMonth());
		billingMonthtxt.setBorder(null);
		currentPayableAmounttxt.setText(String.valueOf(bill.getPayableAmount()));
		currentPayableAmounttxt.setBorder(null);
		previousAmounttxt.setText("");
		previousAmounttxt.setBorder(null);
		payableAmountByDueDatetxt.setText(String.valueOf(bill.getPayableAmount()));
		payableAmountByDueDatetxt.setBorder(null);
		payableAfterDueDatetxt.setText("");
		payableAfterDueDatetxt.setBorder(null);
		surchargetxt.setText("");
		surchargetxt.setBorder(null);
		advertisementChargestxt.setText("");
		advertisementChargestxt.setBorder(null);
		receivedAmounttxt.setText(String.valueOf(bill.getReceivedAmount()));
		receivedAmounttxt.setBorder(null);
		balancetxt.setText(String.valueOf(""));
		balancetxt.setBorder(null);
		signatureReceivedAuthoritytxt.setText(String.valueOf(bill.getReceivedBy()));
		signatureReceivedAuthoritytxt.setBorder(null);
	}

	/**
	 * Returns the panel which displays the bill history of the customer. Panel
	 * includes a table with bill records
	 */
	private BasicGuiPanel getBillHistoryPanel()
	{
		BasicGuiPanel billHistoryPanel = new BasicGuiPanel();
		CustomerBillHistoryTableModel model = new CustomerBillHistoryTableModel(previousBills, columnNames);
		TableSorter sorter = new TableSorter(model);
		JTable table = new JTable(sorter);
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		table.getTableHeader().setBackground(Color.WHITE);
		sorter.setTableHeader(table.getTableHeader());
		// Set up tool tips for column headers.
		table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		// scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(null);
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
		Thread t = new Thread()
		{
			public void run()
			{
				try
				{
					Thread.sleep(20000);
					exitbtn.doClick();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	private void setTextFieldFonts()
	{
		issueDatetxt.setFont(txtFieldFont);
		dueDatetxt.setFont(txtFieldFont);
		billNotxt.setFont(txtFieldFont);
		acNotxt.setFont(txtFieldFont);
		areaCodetxt.setFont(txtFieldFont);
		custNametxt.setFont(txtFieldFont);
		telNumbertxt.setFont(txtFieldFont);
		addresstxt.setFont(txtFieldFont);
		addresstxt.setFont(txtFieldFont);
		billingMonthtxt.setFont(txtFieldFont);
		currentPayableAmounttxt.setFont(txtFieldFont);
		previousAmounttxt.setFont(txtFieldFont);
		payableAmountByDueDatetxt.setFont(txtFieldFont);
		payableAfterDueDatetxt.setFont(txtFieldFont);
		surchargetxt.setFont(txtFieldFont);
		advertisementChargestxt.setFont(txtFieldFont);
		receivedAmounttxt.setFont(txtFieldFont);
		balancetxt.setFont(txtFieldFont);
		signatureReceivedAuthoritytxt.setFont(txtFieldFont);
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
		billNoLbl.setFont(txtFieldFont);
		JLabel acNoLbl = new JLabel("A/c No");
		acNoLbl.setFont(txtFieldFont);
		JLabel areaCodeLbl = new JLabel("Area Code");
		areaCodeLbl.setFont(txtFieldFont);
		JLabel custNameLbl = new JLabel("Customer's Name");
		custNameLbl.setFont(txtFieldFont);
		JLabel addressLbl = new JLabel("Address");
		addressLbl.setFont(txtFieldFont);
		JLabel billingMonthLbl = new JLabel("Billing Month");
		billingMonthLbl.setFont(txtFieldFont);
		JLabel receivedAmountLbl = new JLabel("Received Amount");
		receivedAmountLbl.setFont(txtFieldFont);
		JLabel signatureReceivedAuthorityLbl = new JLabel("Signature Recieved Authority");
		signatureReceivedAuthorityLbl.setFont(txtFieldFont);

		JTextField billNo = new JTextField(20);
		billNo.setBorder(null);
		billNo.setFont(txtFieldFont);
		JTextField acNo = new JTextField(20);
		acNo.setBorder(null);
		acNo.setFont(txtFieldFont);
		JTextField areaCode = new JTextField(20);
		areaCode.setBorder(null);
		areaCode.setFont(txtFieldFont);
		JTextField custName = new JTextField(20);
		custName.setBorder(null);
		custName.setFont(txtFieldFont);
		JTextField address = new JTextField(20);
		address.setBorder(null);
		address.setFont(txtFieldFont);
		JTextField billingMonth = new JTextField(20);
		billingMonth.setBorder(null);
		billingMonth.setFont(txtFieldFont);
		JTextField receivedAmount = new JTextField(20);
		receivedAmount.setBorder(null);
		receivedAmount.setFont(txtFieldFont);
		JTextField signatureReceivedAuthority = new JTextField(20);
		signatureReceivedAuthority.setBorder(null);
		signatureReceivedAuthority.setFont(txtFieldFont);

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
		c.gridheight = 1;
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
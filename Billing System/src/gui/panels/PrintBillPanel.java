package gui.panels;

import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import model.CompanyInformation;

public class PrintBillPanel extends BasicGuiPanel implements Printable
{
	private static final long serialVersionUID = -7417720568612211026L;

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

	public PrintBillPanel()
	{
		configurePanel();
	}

	private void configurePanel()
	{
		BasicGuiPanel header = configureHeader();
		BasicGuiPanel fieldsPanel = configureDataFieldsPanel();
		BasicGuiPanel btnPanel = configureBtnPanel();

		// add(header);
		// add(fieldsPanel);
		// add(btnPanel);
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
		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		final JButton exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());
		final JButton resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ResetFieldsListener());
		final JButton printBtn = new JButton("Print");
		// printBtn.addActionListener(new PrintBillListener());
		printBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				printBtn.setVisible(false);
				resetbtn.setVisible(false);
				exitbtn.setVisible(false);

				PrinterJob printJob = PrinterJob.getPrinterJob();

				// Get and change default page format settings if necessary.
				setFieldsEditable(false);
				printJob.setPrintable(PrintBillPanel.this);
				if (printJob.printDialog())
				{
					try
					{
						printJob.print();
					}
					catch (Exception PrintException)
					{
						PrintException.printStackTrace();
					}
					printJob.cancel();
				}
				printJob.cancel();
				setFieldsEditable(true);
				printBtn.setVisible(true);
				resetbtn.setVisible(true);
				exitbtn.setVisible(true);
			}
		});

		p.add(exitbtn);
		p.add(resetbtn);
		p.add(printBtn);

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

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
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

		return p;
	}

	private BasicGuiPanel configureHeader()
	{
		CompanyInformation compInfo = new CompanyInformation();

		JLabel companyName = new JLabel(compInfo.getCompanyName());
		JLabel companyAddress = new JLabel(compInfo.getCompanyAddress() + compInfo.getCompanyTelephoneNumber());
		JLabel companyOwner = new JLabel(compInfo.getCompanyOwnerName());

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
	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pf.getImageableX() + 5, pf.getImageableY() + 5);

		Font f = new Font("Monospaced", Font.PLAIN, 12);
		g2.setFont(f);
		paint(g2);

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
}

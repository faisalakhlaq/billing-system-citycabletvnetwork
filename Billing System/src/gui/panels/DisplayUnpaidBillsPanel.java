package gui.panels;

import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Bill;

import table.BillTableModel;
import table.TableSorter;

@SuppressWarnings("serial")
public class DisplayUnpaidBillsPanel extends AbstractGuiPanel
{
	private JButton exitbtn = null;

	private Vector<Bill> billList;

	private String[] columnNames =
	{ "Bill Number", "Issue Date", "Due Date", "Account Number", "Billing Month", "Billing Year", "Payable Amount", "Received Amount", "Received By", "Paid" };

	public DisplayUnpaidBillsPanel(Vector<Bill> billList)
	{
		this.billList = billList;
		initPanel();
	}

	@Override
	public void initPanel()
	{
		addPanels();
	}

	@Override
	public BasicGuiPanel getCenterPanel()
	{
		BillTableModel model = new BillTableModel(billList, columnNames);
		TableSorter sorter = new TableSorter(model);
		JTable table = new JTable(sorter); // NEW
		sorter.setTableHeader(table.getTableHeader());

		// Set up tool tips for column headers.
		table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setPreferredSize(new Dimension(1000, 500));

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setOpaque(true);
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.25;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		p.add(scrollPane, c);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{
		JLabel headerLbl = new JLabel("Unpaid Bills");

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

	@Override
	public BasicGuiPanel getButtonPanel()
	{
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());
		p.add(exitbtn);

		return p;
	}

	@Override
	public GuiPanel getOwningView()
	{
		// TODO Auto-generated method stub
		return null;
	}

}

package gui.panels;

import gui.caller.CloseViewCaller;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.AdvertisementBill;
import model.Bill;
import model.ModelObject;
import table.SalesReportTableModel;
import table.TableSorter;

@SuppressWarnings("serial")
public class SalesReportPanel extends AbstractGuiPanel
{
	private JButton exitbtn;

	private ArrayList<ModelObject> billList;

	// Type is for weather it is Bill (Private-Commercial) OR Ad-bill
	private String[] columnNames =
	{ "Amount", "Date", "Type", "Account Number" };

	public SalesReportPanel(ArrayList<ModelObject> billList)
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
		SalesReportTableModel model = new SalesReportTableModel(billList, columnNames);
		TableSorter sorter = new TableSorter(model);
		JTable table = new JTable(sorter);
		sorter.setTableHeader(table.getTableHeader());

		// Set up tool tips for column headers.
		table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

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
		JLabel headerLbl = new JLabel("Report");
		JLabel totalAmountLbl = new JLabel("Total Amount = ");
		JLabel totalLbl = new JLabel();

		int total = 0;
		for (ModelObject obj : billList)
		{
			if (obj instanceof Bill)
			{
				total += ((Bill) obj).getReceivedAmount();
			}
			else if (obj instanceof AdvertisementBill)
			{
				total += ((AdvertisementBill) obj).getPayableAmount();
			}
		}
		totalLbl.setText(String.valueOf(total));

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

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.insets = new Insets(5, 0, 0, 0);
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 0;
		hc.gridy = 2;
		hc.gridwidth = 1;
		headerPanel.add(totalAmountLbl, hc);

		hc.fill = GridBagConstraints.VERTICAL;
		hc.anchor = GridBagConstraints.CENTER;
		hc.weightx = 0.75;
		hc.weighty = 0;
		hc.gridx = 1;
		hc.gridy = 2;
		hc.gridwidth = 1;
		headerPanel.add(totalLbl, hc);

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

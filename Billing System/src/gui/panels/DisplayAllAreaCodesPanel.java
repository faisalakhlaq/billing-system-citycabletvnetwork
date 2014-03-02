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

import model.AreaCode;
import table.AreaCodeTableModel;
import table.TableSorter;

@SuppressWarnings("serial")
public class DisplayAllAreaCodesPanel extends AbstractGuiPanel
{
	private JButton exitbtn;

	private Vector<AreaCode> areaCodeList;

	private String[] columnNames =
	{ "Area Name", "Code" };

	public DisplayAllAreaCodesPanel(Vector<AreaCode> areaCodeList)
	{
		this.areaCodeList = areaCodeList;
		super.addPanels();
	}

	@Override
	public void initPanel()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BasicGuiPanel getCenterPanel()
	{
		AreaCodeTableModel model = new AreaCodeTableModel(areaCodeList, columnNames);
		TableSorter sorter = new TableSorter(model); // ADDED THIS
		// JTable table = new JTable(new MyTableModel()); //OLD
		JTable table = new JTable(sorter); // NEW
		sorter.setTableHeader(table.getTableHeader()); // ADDED THIS
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));

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
		JLabel headerLbl = new JLabel("Area Codes");

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

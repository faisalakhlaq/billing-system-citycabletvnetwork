package gui.panels;

import gui.GUIPanel;
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

import model.Customer;
import table.CustomerTableModel;

public class ShowAllCustomersPanel extends GUIPanel
{
	private static final long serialVersionUID = 7502960386600952711L;

	private JButton exitbtn;
	private JButton refreshbtn;

	private JTable customerTable;

	private Vector<Customer> customersList;

	private String[] columnNames =
	{ "Account Number", "Date", "Name", "Address", "NIC number", "Advance", "Telephone Number", "Connection Fee", "Conection Type" };

	public ShowAllCustomersPanel(Vector<Customer> customers)
	{
		customersList = customers;
		GUIPanel header = configureHeader();
		GUIPanel fieldsPanel = configureFieldsPanel();
		GUIPanel btnPanel = configureBtnPanel();

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

	private GUIPanel configureBtnPanel()
	{
		exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new CloseViewCaller());

		refreshbtn = new JButton("Refresh");
		refreshbtn.addActionListener(new CloseViewCaller());

		GUIPanel p = new GUIPanel(new FlowLayout());
		p.add(refreshbtn);
		p.add(exitbtn);

		return p;
	}

	private GUIPanel configureFieldsPanel()
	{
		customerTable = new JTable();
		CustomerTableModel model = new CustomerTableModel(customersList, columnNames);
		customerTable.setModel(model);

		JScrollPane scrollPane = new JScrollPane(customerTable);
		scrollPane.setPreferredSize(new Dimension(800, 150));

		GUIPanel p = new GUIPanel(new GridBagLayout());
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

	private GUIPanel configureHeader()
	{
		JLabel headerLbl = new JLabel("Customer Information");

		GUIPanel headerPanel = new GUIPanel(new GridBagLayout());
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
}

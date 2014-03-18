package gui.panels;

import gui.dialog.MessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.AreaCode;
import utils.Helper;
import database.AreaCodeHandler;
import database.callers.CustomerBillCaller;
import database.callers.SearchCuctomerCaller;
import database.callers.ViewAreaCustomersCaller;

@SuppressWarnings("serial")
public class MainViewLeftPanel extends AbstractGuiPanel
{
	private JTextField billNumberTxt = new JTextField(10);

	private JTextField accountNumberTxt = new JTextField(10);

	private JTextField adBillNumberTxt = new JTextField(10);

	private JComboBox<String> areaCbx = null;

	public MainViewLeftPanel()
	{
		areaCbx = new JComboBox<String>();
		areaCbx.setModel(getAreaCodes());
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
		JLabel areaLbl = new JLabel("Area");
		JLabel billNumberLbl = new JLabel("Bill Number");
		JLabel accountNumberLbl = new JLabel("Account Number");
		JLabel adBillLbl = new JLabel("Advertisement Bill Number");

		JButton bill = new JButton("Get Bill");
		bill.addActionListener(new SearchBillListener());

		JButton adBill = new JButton("Get Bill");
		bill.addActionListener(new SearchBillListener());

		JButton customer = new JButton("Search Customer");
		customer.addActionListener(new SearchCuctomerCaller(this));

		JButton areaCustomers = new JButton("Get Customers");
		areaCustomers.setToolTipText("Displays customers from the selected area");
		areaCustomers.addActionListener(new ViewAreaCustomersListener());

		// The panel to keep search the customer according to area code
		BasicGuiPanel searchAreaCustomer = new BasicGuiPanel();
		BoxLayout boxLayout = new BoxLayout(searchAreaCustomer, BoxLayout.Y_AXIS);
		searchAreaCustomer.setLayout(boxLayout);
		searchAreaCustomer.add(areaLbl);
		searchAreaCustomer.add(areaCbx);
		searchAreaCustomer.add(areaCustomers);
		searchAreaCustomer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		searchAreaCustomer.setOpaque(true);

		BasicGuiPanel p = new BasicGuiPanel();
		BoxLayout layout = new BoxLayout(p, BoxLayout.Y_AXIS);
		p.setLayout(layout);

		p.add(searchAreaCustomer);
		p.add(billNumberLbl);
		p.add(billNumberTxt);
		p.add(bill);
		p.add(adBillLbl);
		p.add(adBillNumberTxt);
		p.add(adBill);
		p.add(accountNumberLbl);
		p.add(accountNumberTxt);
		p.add(customer);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicGuiPanel getButtonPanel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiPanel getOwningView()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrieves the area name from the database
	 * <p>
	 * if no areas are retrieved, an empty model will be returned
	 */
	private ComboBoxModel<String> getAreaCodes()
	{
		AreaCodeHandler handler = new AreaCodeHandler();
		ComboBoxModel<String> model = null;
		try
		{
			Vector<AreaCode> areaCodes = handler.getAllAreaCodes();
			if (areaCodes != null && areaCodes.size() > 0)
			{
				Vector<String> areas = new Vector<String>();
				for (AreaCode a : areaCodes)
				{
					areas.add(a.getAreaName());
				}
				model = new javax.swing.DefaultComboBoxModel<String>(areas);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (model == null)
		{
			model = new javax.swing.DefaultComboBoxModel<String>();
		}
		return model;
	}

	public String getAccoutNumber()
	{
		return accountNumberTxt.getText();
	}

	private class SearchBillListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String billNumber = billNumberTxt.getText();
			if (Helper.isEmpty(billNumber))
			{
				new MessageDialog("No number provided", "Bill number cannot be empty");
				return;
			}
			if (!Helper.isDigit(billNumber.trim()))
			{
				new MessageDialog("Wrong number", "Bill number can only contain digits");
				return;
			}
			CustomerBillCaller.searchBill(Integer.valueOf(billNumber));
		}
	}

	private class ViewAreaCustomersListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String areaName = String.valueOf(areaCbx.getSelectedItem());
			ViewAreaCustomersCaller.perform(areaName);
		}

	}
}

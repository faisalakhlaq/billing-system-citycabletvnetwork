package gui;

import gui.caller.ExitSystemCaller;
import gui.caller.StartCalculatorCaller;
import gui.panels.callers.AddCustomerPanelCaller;
import gui.panels.callers.DisplayPrintBillPanelCaller;
import gui.panels.callers.SearchCustomerPanelCaller;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class BillingSystemToolBar extends JToolBar
{
	private static final long serialVersionUID = -3111840679726761563L;

	public BillingSystemToolBar()
	{
		addToolBarItems();
		// setFloatable(false);
	}

	private void addToolBarItems()
	{
		addExitButton();
		addSeparator();
		addSearchCustomerButton();
		addSeparator();
		addDeleteCustomerBtn();
		addSeparator();
		addAddCustomerBtn();
		addSeparator();
		addCalculatorBtn();
		addSeparator();
		addPrintBillBtn();
	}

	private void addPrintBillBtn()
	{
		JButton printBillBtn = new JButton();
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/resources/print.png"));
		printBillBtn.setIcon(addIcon);
		printBillBtn.addActionListener(new DisplayPrintBillPanelCaller());
		printBillBtn.setToolTipText("Print Bill");
		this.add(printBillBtn);
	}

	private void addCalculatorBtn()
	{
		JButton calculatorBtn = new JButton();
		ImageIcon calculatorIcon = new ImageIcon(getClass().getResource("/resources/calc.png"));
		calculatorBtn.setIcon(calculatorIcon);
		calculatorBtn.addActionListener(new StartCalculatorCaller());
		calculatorBtn.setToolTipText("Start calculator");
		this.add(calculatorBtn);
	}

	private void addAddCustomerBtn()
	{
		JButton addCustomerBtn = new JButton();
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/resources/add.gif"));
		addCustomerBtn.setIcon(addIcon);
		addCustomerBtn.addActionListener(new AddCustomerPanelCaller());
		addCustomerBtn.setToolTipText("Add new Customer");
		this.add(addCustomerBtn);
	}

	private void addDeleteCustomerBtn()
	{
		JButton delCustomerBtn = new JButton();
		ImageIcon delIcon = new ImageIcon(getClass().getResource("/resources/delete.png"));
		delCustomerBtn.setIcon(delIcon);
		delCustomerBtn.addActionListener(new SearchCustomerPanelCaller());
		delCustomerBtn.setToolTipText("Search and delete Customer");
		this.add(delCustomerBtn);
	}

	private void addSearchCustomerButton()
	{
		JButton searchCustomerBtn = new JButton();
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/resources/search.png"));
		searchCustomerBtn.setIcon(searchIcon);
		searchCustomerBtn.addActionListener(new SearchCustomerPanelCaller());
		searchCustomerBtn.setToolTipText("Search Customer");
		this.add(searchCustomerBtn);
	}

	private void addExitButton()
	{
		JButton exitBtn = new JButton();
		ImageIcon exitIcon = new ImageIcon(getClass().getResource("/resources/exit_icon.png"));
		exitBtn.setIcon(exitIcon);
		exitBtn.addActionListener(new ExitSystemCaller());
		exitBtn.setToolTipText("Exit System");
		this.add(exitBtn);
	}
}

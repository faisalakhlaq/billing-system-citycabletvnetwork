package gui.toolbar;

import gui.buttons.ToolBarButton;
import gui.caller.ExitSystemCaller;
import gui.caller.StartCalculatorCaller;
import gui.panels.callers.AddAreaCodePanelCaller;
import gui.panels.callers.AddCustomerPanelCaller;
import gui.panels.callers.GenerateBillPanelCaller;
import gui.panels.callers.PrintBillPanelCaller;
import gui.panels.callers.SearchPanelCaller;

import javax.swing.JToolBar;

public class BillingSystemToolBar extends JToolBar
{
	private static final long serialVersionUID = -3111840679726761563L;

	public BillingSystemToolBar()
	{
		addToolBarItems();
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
		addPrintBillBtn();
		addSeparator();
		addGenerateBillBtn();
		addSeparator();
		addAreaCodeBtn();
		addSeparator();
		addCalculatorBtn();
	}

	private void addAreaCodeBtn()
	{
		ToolBarButton addAreaCodeBtn = new ToolBarButton("Add-Delete area code", "/resources/area_code.png", new AddAreaCodePanelCaller());
		this.add(addAreaCodeBtn);
	}

	private void addGenerateBillBtn()
	{
		// this button generates the bill for all customers
		ToolBarButton generateBillBtn = new ToolBarButton("Generate Bill for all customers", "/resources/generate_bill.png", new GenerateBillPanelCaller(false));
		this.add(generateBillBtn);
	}

	private void addPrintBillBtn()
	{
		ToolBarButton printBillBtn = new ToolBarButton("Print Bill", "/resources/print.png", new PrintBillPanelCaller());
		this.add(printBillBtn);
	}

	private void addCalculatorBtn()
	{
		ToolBarButton calculatorBtn = new ToolBarButton("Start calculator", "/resources/calc.png", new StartCalculatorCaller());
		this.add(calculatorBtn);
	}

	private void addAddCustomerBtn()
	{
		ToolBarButton addCustomerBtn = new ToolBarButton("Add new Customer", "/resources/add_customer.png", new AddCustomerPanelCaller());
		this.add(addCustomerBtn);
	}

	private void addDeleteCustomerBtn()
	{
		ToolBarButton delCustomerBtn = new ToolBarButton("Search and delete Customer", "/resources/delete.png", new SearchPanelCaller());
		this.add(delCustomerBtn);
	}

	private void addSearchCustomerButton()
	{
		ToolBarButton searchCustomerBtn = new ToolBarButton("Search Customer", "/resources/search.png", new SearchPanelCaller());
		this.add(searchCustomerBtn);
	}

	private void addExitButton()
	{
		ToolBarButton exitBtn = new ToolBarButton("Exit System", "/resources/exit_icon.png", new ExitSystemCaller());
		this.add(exitBtn);
	}
}

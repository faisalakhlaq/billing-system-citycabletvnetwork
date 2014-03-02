package gui.menubar;

import gui.BillingSystemView;
import gui.GUIMenuBar;
import gui.caller.AboutMenuItemCaller;
import gui.caller.ExitSystemCaller;
import gui.caller.ShowStatusbarCaller;
import gui.panels.callers.AddAreaCodePanelCaller;
import gui.panels.callers.AddCustomerPanelCaller;
import gui.panels.callers.DisplayAllAreaCodesPanelCaler;
import gui.panels.callers.SearchCustomerPanelCaller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import database.callers.ViewAllCustomersCaller;

public class MainFrameMenuBar extends GUIMenuBar implements BillingSystemView
{
	private static final long serialVersionUID = 1110199467284380103L;

	private BillingSystemView owningView = null;

	// TODO increase the size of the menubar
	public MainFrameMenuBar(String name, BillingSystemView owningView)
	{
		setName(name);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setOwningView(owningView);
		configureMenuBar();
	}

	private void configureMenuBar()
	{
		JMenu menu = addMenu("Menu");
		JMenu help = addMenu("Help");
		JMenu view = addMenu("View");
		JMenu customer = addMenu("Customer");
		JMenu billing = addMenu("Billing");
		JMenu areaCode = addMenu("Area Code");

		configureHelpMenu(help);
		configureExitMenu(menu);
		configureViewMenu(view);
		configureCustomerMenu(customer);
		configureBillingMenu(billing);
		configureAreaCodeMenu(areaCode);
	}

	/**
	 * adds a menu to the Main frame of the billing system
	 * */
	private JMenu addMenu(String title)
	{
		return add(new JMenu(title));
	}

	/**
	 * adds a menu item to the provided menu
	 * */
	private JMenuItem addMenuItem(JMenu menu, String title)
	{
		return menu.add(new JMenuItem(title));
	}

	private void configureHelpMenu(JMenu help)
	{
		help.setMnemonic(KeyEvent.VK_H);
		JMenuItem helpMenuItem = addMenuItem(help, "About");
		helpMenuItem.addActionListener(new AboutMenuItemCaller());
	}

	private void configureCustomerMenu(JMenu customerMenu)
	{
		customerMenu.setMnemonic(KeyEvent.VK_C);

		JMenuItem addCustomerMenuItem = addMenuItem(customerMenu, "Add Customer");
		addCustomerMenuItem.addActionListener(new AddCustomerPanelCaller());

		JMenuItem deleteCustomerMenuItem = addMenuItem(customerMenu, "Search and Delete Customer");
		deleteCustomerMenuItem.addActionListener(new SearchCustomerPanelCaller());

		JMenuItem searchCustomerMenuItem = addMenuItem(customerMenu, "Search Customer");
		searchCustomerMenuItem.addActionListener(new SearchCustomerPanelCaller());

		JMenuItem viewAllCustomerMenuItem = addMenuItem(customerMenu, "View All Customers");
		viewAllCustomerMenuItem.addActionListener(new ViewAllCustomersCaller());
	}

	private void configureExitMenu(JMenu menu)
	{
		// TODO make a separate package for menuitems and add separate callers
		// for the menu items
		menu.setMnemonic(KeyEvent.VK_M); // Mnemonic to access the menu
		JMenuItem exitMenuItem = addMenuItem(menu, "Exit");
		exitMenuItem.addActionListener(new ExitSystemCaller());

		ImageIcon exitIcon = new ImageIcon(getClass().getResource("/resources/exit_icon.png"));
		exitMenuItem.setIcon(exitIcon);
		exitMenuItem.setToolTipText("Exit System");
		// Menu commands can be launched via keyboard shortcuts
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
	}

	private void configureViewMenu(JMenu viewMenu)
	{
		viewMenu.setMnemonic(KeyEvent.VK_V);

		JCheckBoxMenuItem showStatusBar = new JCheckBoxMenuItem("Hide StatusBar");
		showStatusBar.setEnabled(true);
		showStatusBar.addActionListener(new ShowStatusbarCaller(owningView));
		viewMenu.add(showStatusBar);
	}

	private void configureBillingMenu(JMenu billMenu)
	{
		billMenu.setMnemonic(KeyEvent.VK_B); // Mnemonic to access the menu
		JMenuItem printAllBillsMenuItem = addMenuItem(billMenu, "Print All Bills");
		// exitMenuItem.addActionListener(new ExitSystemCaller());
		ImageIcon printIcon = new ImageIcon(getClass().getResource("/resources/print.png"));
		printAllBillsMenuItem.setIcon(printIcon);
		printAllBillsMenuItem.setToolTipText("Print out all Bills");

		JMenuItem searchCustomerBillMenuItem = addMenuItem(billMenu, "Customer Bill");
		// exitMenuItem.addActionListener(new ExitSystemCaller());
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/resources/search.png"));
		searchCustomerBillMenuItem.setIcon(searchIcon);
		searchCustomerBillMenuItem.setToolTipText("Search a Customer's bill");
	}

	private void configureAreaCodeMenu(JMenu areaCodeMenu)
	{
		JMenuItem displayAllCodesMenuItem = addMenuItem(areaCodeMenu, "Display All Area Codes");
		displayAllCodesMenuItem.addActionListener(new DisplayAllAreaCodesPanelCaler());
		displayAllCodesMenuItem.setToolTipText("Displays all area codes");

		JMenuItem addAreaCode = addMenuItem(areaCodeMenu, "Add New Area Code");
		addAreaCode.addActionListener(new AddAreaCodePanelCaller());
	}

	@Override
	public void setOwningView(BillingSystemView owningView)
	{
		this.owningView = owningView;
	}

	@Override
	public BillingSystemView getOwningView()
	{
		return owningView;
	}
}
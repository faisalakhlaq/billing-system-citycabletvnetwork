package gui.views;

import gui.BillingSystemDesktopPane;
import gui.BillingSystemStatusBar;
import gui.BillingSystemToolBar;
import gui.BillingSystemView;
import gui.GUIFrame;
import gui.GUIPanel;
import gui.menubar.MainFrameMenuBar;
import gui.panels.CompanyInfoBannerPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

/**
 * This is the main JFrame of the application
 * 
 * @author Faisal Akhlaq
 * @version 1.0, 5 Feb 2014
 * */
public class BillingSystemMainFrame extends GUIFrame implements BillingSystemView
{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 2681336469250233501L;

	private MainFrameMenuBar menuBar = null;

	private BillingSystemStatusBar statusbar = null;

	private BillingSystemToolBar toolBar = null;

	private static BillingSystemMainFrame instance = null;

	private BillingSystemMainFrame(String title)
	{
		configureFrameWindow(title);
		setFrameLocation();

		setStatusBar();
		configureMenuBar();
		configureToolBar();
		configurePanel();
	}

	public static BillingSystemMainFrame getInstance()
	{
		if (instance == null)
		{
			instance = new BillingSystemMainFrame("City Calble Network");
		}
		return instance;
	}

	private void configureFrameWindow(String title)
	{
		setTitle(title);
		setDefaultLookAndFeelDecorated(true);
		Toolkit toolkit = getToolkit();
		Dimension screensize = toolkit.getScreenSize();
		setPreferredSize(screensize);
		setMinimumSize(screensize);
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/billing_system_icon.png"));
		if (icon != null) setIconImage(icon.getImage());
	}

	private void configurePanel()
	{
		BillingSystemDesktopPane desktopPane = BillingSystemDesktopPane.getInstance();
		CompanyInfoBannerPanel bannerPanel = new CompanyInfoBannerPanel();
		Container pane = getContentPane();
		GUIPanel p = new GUIPanel(new BorderLayout());
		p.add(BorderLayout.NORTH, bannerPanel);
		p.add(BorderLayout.CENTER, desktopPane);
		pane.add(BorderLayout.CENTER, p);
	}

	private void configureToolBar()
	{
		toolBar = new BillingSystemToolBar();
		getContentPane().add(BorderLayout.NORTH, toolBar);
	}

	private void configureMenuBar()
	{
		menuBar = new MainFrameMenuBar("MenuBar", BillingSystemMainFrame.this);
		setJMenuBar(menuBar);
	}

	private void setStatusBar()
	{
		statusbar = BillingSystemStatusBar.getInstance();
		statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		getContentPane().add(statusbar, BorderLayout.SOUTH);
		statusbar.setText(" Status: ");
	}

	public BillingSystemStatusBar getStatusBar()
	{
		return statusbar;
	}

	private void setFrameLocation()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setOwningView(BillingSystemView owningView)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BillingSystemView getOwningView()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
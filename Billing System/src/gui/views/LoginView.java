package gui.views;

import gui.GUIFrame;
import gui.panels.BasicGuiPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.CompanyInformation;

public class LoginView extends GUIFrame
{
	private static final long serialVersionUID = -4740992886559954235L;

	public LoginView()
	{
		configureFrameWindow("Login");

		setLocation();
		setAlwaysOnTop(true);
		validate();
		configurePanel();
		addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        requestFocus();
		        requestFocusInWindow();
		    }
		}); 
	}

	private void configureFrameWindow(String title)
	{
		setTitle(title);
		setDefaultLookAndFeelDecorated(true);
		setSize(350, 180);
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/Keys.gif"));
		if (icon != null) setIconImage(icon.getImage());
		setResizable(false);
	}

	private void setLocation()
	{
		Toolkit toolkit = getToolkit();
		Dimension screensize = toolkit.getScreenSize();
		setLocation((screensize.width - getWidth()) / 2, (screensize.height - getHeight()) / 2);
	}

	private void configurePanel()
	{
		Container pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(getLoginPanel());
	}

	private Component getLoginPanel()
	{
		BasicGuiPanel panel = new BasicGuiPanel();

		BasicGuiPanel headerPanel = new BasicGuiPanel();
		headerPanel.add(new JLabel(new CompanyInformation().getCompanyName()));
		panel.add(headerPanel);

		BasicGuiPanel fieldsPanel = new BasicGuiPanel(new GridLayout(2, 2));
		fieldsPanel.add(new JLabel("Username"));
		JTextField userNametxt = new JTextField(15);
		userNametxt.requestFocusInWindow();
		userNametxt.requestFocus(true);
		userNametxt.grabFocus();
		fieldsPanel.add(userNametxt);
		fieldsPanel.add(new JLabel("Password"));
		fieldsPanel.add(new JPasswordField(15));
		fieldsPanel.setOpaque(true);

		panel.add(fieldsPanel);

		BasicGuiPanel buttonPanel = new BasicGuiPanel(new FlowLayout());
		JButton loginbtn = new JButton("Login", new ImageIcon(getClass().getResource("/resources/Key.gif")));
		buttonPanel.add(loginbtn);
		JButton exitbtn = new JButton("Exit", new ImageIcon(getClass().getResource("/resources/Keys.gif")));
		exitbtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				dispose();
			}
		});
		buttonPanel.add(exitbtn);

		panel.add(buttonPanel);

		return panel;
	}
}

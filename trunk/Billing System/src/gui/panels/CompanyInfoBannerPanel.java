package gui.panels;

import gui.GUIPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.CompanyInformation;

/**
 * This panel will be displayed on the main window. It displays the information
 * about the company
 * */
public class CompanyInfoBannerPanel extends GUIPanel
{
	private static final long serialVersionUID = 3644484225934630220L;

	public CompanyInfoBannerPanel()
	{
		configurePanel();
	}

	private void configurePanel()
	{
		CompanyInformation info = new CompanyInformation();
		
		JLabel compName = new JLabel(info.getCompanyName());
		Font f = new Font("Monospaced", Font.BOLD, 20);
		compName.setFont(f);
		compName.setForeground(Color.WHITE);

		JLabel compAddress = new JLabel(info.getCompanyAddress());
		compAddress.setForeground(Color.WHITE);
		ImageIcon addressIcon = new ImageIcon(getClass().getResource("/resources/Branches.png"));
		if (addressIcon != null)
		{
			compAddress.setIcon(addressIcon);
		}

		JLabel compTel = new JLabel(info.getCompanyTelephoneNumber());
		compTel.setForeground(Color.WHITE);
//		ImageIcon telIcon = new ImageIcon(getClass().getResource("/resources/Branches.png"));
//		if (telIcon != null)
//		{
//			compAddress.setIcon(telIcon);
//		}

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(compName, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(compAddress, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(compTel, c);
		
		setBackground(Color.DARK_GRAY);
	}
}

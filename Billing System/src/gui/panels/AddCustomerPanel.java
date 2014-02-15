package gui.panels;

import gui.GUIPanel;
import gui.caller.CloseViewCaller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCustomerPanel extends GUIPanel implements ComponentListener
{
	private static final long serialVersionUID = -975344126025321293L;

	private final int divFactor = 27;

	private GUIPanel buttonPanel = new GUIPanel();

	private GUIPanel fieldsPanel = new GUIPanel(new GridBagLayout());

	private GUIPanel panel = new GUIPanel();

	private JLabel headingLabel = new JLabel("Add a Customer");

	private JButton resetButton = new JButton("Reset");

	private JButton saveButton = new JButton("Save");

	private JButton exitButton = new JButton("Exit");

	private JLabel custNamelbl = new JLabel("Name");

	private JLabel addresslbl = new JLabel("Address");

	private JLabel idNumberlbl = new JLabel("ID Card");

	private JTextField custNametxt = new JTextField(20);

	private JTextField addresstxt = new JTextField(40);

	private JTextField idNumbertxt = new JTextField(20);

	public AddCustomerPanel()
	{
		Insets currentInsets;
		GridBagConstraints gridBagConstraints;
		panel.setLayout(new BorderLayout(0, 20));
		String currentFont = custNamelbl.getFont().getName();
		currentInsets = new Insets(0, 10, 0, 30);

		custNamelbl.setFont(new Font(currentFont, Font.BOLD, 12));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = currentInsets;
		fieldsPanel.add(custNamelbl, gridBagConstraints);

		addresslbl.setFont(new Font(currentFont, Font.BOLD, 12));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = currentInsets;
		fieldsPanel.add(addresslbl, gridBagConstraints);

		idNumberlbl.setFont(new Font(currentFont, Font.BOLD, 12));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = currentInsets;
		fieldsPanel.add(idNumberlbl, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 1.0;
		fieldsPanel.add(custNametxt, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 1.0;
		fieldsPanel.add(addresstxt, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 1.0;
		fieldsPanel.add(idNumbertxt, gridBagConstraints);

		panel.add(fieldsPanel, "North");
		// saveButton.addActionListener(new AddCustomerCaller());
		exitButton.addActionListener(new CloseViewCaller());
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(resetButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(exitButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		panel.add(buttonPanel, "Center");
		add(panel);
	}

	@Override
	public void componentResized(ComponentEvent ev)
	{
		if (ev.getSource() == this)
		{
			int width = getWidth();

			if (width >= 0)
			{
				/**
				 * these text fields had to be resized manually. Using insets
				 * didn't work for smaller areas of the panel.
				 */
				custNametxt.setColumns(width / divFactor);
				addresstxt.setColumns((width / divFactor) + 3); // Larger box
				idNumbertxt.setColumns(width / divFactor);
			}
		}
	}

	@Override
	public void componentHidden(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}

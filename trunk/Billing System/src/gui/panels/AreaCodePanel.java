package gui.panels;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.AreaCode;
import utils.CustomPair;
import utils.Helper;
import database.AreaCodeHandler;
import database.callers.DeleteAreaCodeCaller;

/** Allows addition, deletion or updating the area code */
@SuppressWarnings("serial")
public class AreaCodePanel extends AbstractGuiPanel
{
	private JTextField areaCodeTxt;

	private JTextField areaNameTxt;

	private JButton exitBtn;

	private JButton saveBtn;

	private JButton resetBtn;

	private JButton deleteBtn;

	private JButton searchBtn;

	public AreaCodePanel()
	{
		initPanel();
	}

	@Override
	public GuiPanel getOwningView()
	{
		// TODO correct implementation required
		GuiPanel owningView = null;
		Container c = this.getParent();
		if (c instanceof GuiPanel)
		{
			owningView = (GuiPanel) c;
		}
		return owningView;
	}

	@Override
	public void initPanel()
	{
		BasicGuiPanel header = getHeaderPanel();
		BasicGuiPanel fieldsPanel = getCenterPanel();
		BasicGuiPanel btnPanel = getButtonPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setPanelGridBagConstraints(c, 0, 1, 10);
		c.ipady = 20; // make this component tall
		add(header, c);

		c.ipady = 0;
		setPanelGridBagConstraints(c, 0, 2, 30);
		add(fieldsPanel, c);

		setPanelGridBagConstraints(c, 0, 3, 30);
		add(btnPanel, c);
	}

	@Override
	public BasicGuiPanel getCenterPanel()
	{
		JLabel areaCodeLbl = new JLabel("Area Code");
		JLabel areaNameLbl = new JLabel("Area Name");

		areaCodeTxt = new JTextField(10);
		areaNameTxt = new JTextField(10);

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(areaCodeLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(areaCodeTxt, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(areaNameLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(areaNameTxt, c);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{
		BasicGuiPanel p = new BasicGuiPanel();
		JLabel label = new JLabel("Area Code");
		p.add(label);

		return p;
	}

	@Override
	public BasicGuiPanel getButtonPanel()
	{
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ResetFieldsListener());

		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new SearchAreaListener());

		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new DeleteAreaCode());

		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new AddAreaCode());

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new CloseViewCaller());

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(resetBtn);
		p.add(searchBtn);
		p.add(deleteBtn);
		p.add(saveBtn);
		p.add(exitBtn);

		return p;
	}

	private void setGridBagConstraints(GridBagConstraints c, int gridx, int gridy, int placement, int paddingTop, int paddingLeft)
	{
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = placement;
		c.insets = new Insets(paddingTop, paddingLeft, 0, 0); // top and left
																// padding
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
	}

	private void setPanelGridBagConstraints(GridBagConstraints c, int gridx, int gridy, int paddingTop)
	{
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(paddingTop, 0, 0, 0); // top padding
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
	}

	private class ResetFieldsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			areaCodeTxt.setText(null);
			areaNameTxt.setText(null);
		}
	}

	private class AddAreaCode implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String areaCode = areaCodeTxt.getText();
			if (Helper.isEmpty(areaCode))
			{
				new MessageDialog("Area Code", "Please provide the Area Code");
				return;
			}
			if (!Helper.isDigit(areaCode.trim()))
			{
				new MessageDialog("Area Code", "Area Code can contain only numbers-digits");
				return;
			}

			String name = areaNameTxt.getText();
			if (Helper.isEmpty(name))
			{
				new MessageDialog("Area Name", "Area Name cannot be empty");
				return;
			}
			if (Helper.isDigit(name))
			{
				new MessageDialog("Area Name", "Area Name cannot be a number");
				return;
			}

			AreaCode area = new AreaCode();
			area.setAreaCode(Integer.parseInt(areaCode));
			area.setAreaName(name);

			addArea(area);
		}

		private void addArea(AreaCode areaCode)
		{
			AreaCodeHandler handler = new AreaCodeHandler();
			CustomPair pair = handler.addAreaCode(areaCode);
			if (pair.isBooleanKey())
			{
				new MessageDialog("Success", pair.getValue(), JOptionPane.INFORMATION_MESSAGE);
				resetBtn.doClick();
			}
			else
			{
				new MessageDialog("Add new area code error", pair.getValue());
			}
		}
	}

	private class DeleteAreaCode implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String areaCode = areaCodeTxt.getText();
			if (Helper.isEmpty(areaCode))
			{
				new MessageDialog("Area Code", "Please provide the Area Code");
				return;
			}
			if (!Helper.isDigit(areaCode.trim()))
			{
				new MessageDialog("Area Code", "Area Code can contain only numbers-digits");
				return;
			}
			MessageDialog msgDialog = new MessageDialog();
			int selection = msgDialog.showConfirmDialog("Confirm deletion", "Are you sure you want to delete the area code");
			if (selection == JOptionPane.OK_OPTION)
			{
				DeleteAreaCodeCaller.perform(Integer.valueOf(areaCode));
			}
		}
	}

	private class SearchAreaListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String code = areaCodeTxt.getText();
			String name = areaNameTxt.getText();

			if (Helper.isEmpty(code) && Helper.isEmpty(name))
			{
				new MessageDialog("Error", "Please enter code OR name to search");
				return;
			}

			if (!Helper.isEmpty(code))
			{
				if (!Helper.isDigit(code.trim()))
				{
					new MessageDialog("Error", "Area code can only be a digit");
					return;
				}
				else
				{
					AreaCodeHandler handler = new AreaCodeHandler();
					try
					{
						String areaName = handler.searchAreaName(Integer.valueOf(code.trim()));
						if (areaName != null)
						{
							areaNameTxt.setText(areaName);
						}
						return;
					}
					catch (NumberFormatException e)
					{
						new MessageDialog("Error", e.getMessage());
						return;
					}
					catch (Exception e)
					{
						new MessageDialog("Error", e.getMessage());
						return;
					}
				}
			}
			if (!Helper.isEmpty(name))
			{
				if (Helper.isDigit(name.trim()))
				{
					new MessageDialog("Error", "Area name can only contain string - alphabets. Digits not allowed");
					return;
				}
				else
				{
					try
					{
						AreaCodeHandler handler = new AreaCodeHandler();
						int areaCode = handler.searchAreaCode(name);

						if (areaCode != 0)
						{
							areaCodeTxt.setText(String.valueOf(areaCode));
						}
						else
						{
							new MessageDialog("Sorry", "No area code was found for this area. Check area name spelling or consider adding a new area code");
						}
					}
					catch (Exception e)
					{
						new MessageDialog("Error", e.getMessage());
						return;
					}
				}
			}
		}
	}
}
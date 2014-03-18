package gui.panels;

import gui.caller.CloseViewCaller;
import gui.dialog.MessageDialog;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.jdesktop.swingx.JXDatePicker;

import database.callers.GetSalesCaller;

@SuppressWarnings("serial")
public class GenerateSalesPanel extends AbstractGuiPanel
{
	private JXDatePicker fromDate;

	private JXDatePicker toDate;

	private JComboBox<Integer> areaCodeCbx;

	private JCheckBox areaChkBx;

	private JButton exitBtn;

	private JButton reportBtn;

	public GenerateSalesPanel()
	{
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
		fromDate = new JXDatePicker();
		toDate = new JXDatePicker();
		areaChkBx = new JCheckBox();
		areaCodeCbx = new JComboBox<Integer>();
		areaChkBx.addActionListener(new EnableAreaCodeListener());

		JLabel fromLbl = new JLabel("From Date");
		JLabel toLbl = new JLabel("To Date");
		JLabel areaCodeChkBxLbl = new JLabel("According to Area Code");
		JLabel areaCodeLbl = new JLabel("Area Code");

		BasicGuiPanel p = new BasicGuiPanel(new GridBagLayout());
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();

		setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 10, 0);
		p.add(fromLbl, c);

		setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 10, 10);
		p.add(fromDate, c);

		setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 10, 0);
		p.add(toLbl, c);

		setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 10, 10);
		p.add(toDate, c);

		setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 10, 0);
		p.add(areaCodeChkBxLbl, c);

		setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 10, 10);
		p.add(areaChkBx, c);

		setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 10, 0);
		p.add(areaCodeLbl, c);

		setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 10, 10);
		p.add(areaCodeCbx, c);

		return p;
	}

	@Override
	public BasicGuiPanel getHeaderPanel()
	{
		JLabel headerLbl = new JLabel("Generate Sales Report");

		BasicGuiPanel headerPanel = new BasicGuiPanel(new GridBagLayout());
		headerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		GridBagConstraints hc = new GridBagConstraints();

		setGridBagConstraints(hc, 1, 4, GridBagConstraints.CENTER, 0, 0);
		headerPanel.add(headerLbl, hc);

		return headerPanel;
	}

	@Override
	public BasicGuiPanel getButtonPanel()
	{
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new CloseViewCaller());

		reportBtn = new JButton("Get Report");
		reportBtn.addActionListener(new GetSalesCaller(GenerateSalesPanel.this));

		BasicGuiPanel p = new BasicGuiPanel(new FlowLayout());

		p.add(reportBtn);
		p.add(exitBtn);

		return p;
	}

	@Override
	public GuiPanel getOwningView()
	{
		// TODO Auto-generated method stub
		return null;
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

	public Date getFromDate()
	{
		return fromDate.getDate();
	}

	public Date getToDate()
	{
		return toDate.getDate();
	}

	private class EnableAreaCodeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			new MessageDialog("Hey", "Check box clicked");
		}
	}
}
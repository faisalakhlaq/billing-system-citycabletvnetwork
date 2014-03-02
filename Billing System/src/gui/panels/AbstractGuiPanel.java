package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public abstract class AbstractGuiPanel extends BasicGuiPanel implements GuiPanel
{
	public void addPanels()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setPanelGridBagConstraints(c, 0, 1, 10);
		c.ipady = 20; // make this component tall

		BasicGuiPanel header = getHeaderPanel();
		if (header != null)
		{
			add(header, c);
		}

		c.ipady = 0;
		BasicGuiPanel center = getCenterPanel();
		if (center != null)
		{
			setPanelGridBagConstraints(c, 0, 2, 30);
			add(center, c);
		}

		BasicGuiPanel button = getButtonPanel();
		if (button != null)
		{
			setPanelGridBagConstraints(c, 0, 3, 30);
			add(button, c);
		}
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

}

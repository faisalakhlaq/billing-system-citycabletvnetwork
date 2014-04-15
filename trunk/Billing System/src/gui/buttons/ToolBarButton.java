package gui.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

@SuppressWarnings("serial")
public class ToolBarButton extends JButton
{
	public ToolBarButton(String toolTipText, String icon, ActionListener listener)
	{
		setToolTipTextBackground(toolTipText);
		// Make the button looks the same for all Laf's
		setUI(new BasicButtonUI());
		// Make it transparent
		setContentAreaFilled(false);
		// No need to be focusable
		setFocusable(false);
		setBorder(BorderFactory.createEtchedBorder());

		setBorderPainted(false);
		// Making nice roll over effect
		// we use the same listener for all buttons
		addMouseListener(buttonMouseListener);
		setRolloverEnabled(true);
		setIcon(icon);
		addActionListener(listener);
	}

	/** Sets white background for the tool tip text */
	private void setToolTipTextBackground(String toolTipText)
	{
		Color backgroundColor = new Color(255, 255, 255);
		// Set tooltiptext background color using created Color
		UIManager.put("ToolTip.background", backgroundColor);
		setToolTipText(toolTipText);
	}

	private void setIcon(String iconURL)
	{
		if (iconURL == null || iconURL.trim().isEmpty()) return;

		ImageIcon icon = new ImageIcon(getClass().getResource(iconURL));
		if (icon != null)
		{
			setIcon(icon);
		}
	}

	private final static MouseListener buttonMouseListener = new MouseAdapter()
	{
		public void mouseEntered(MouseEvent e)
		{
			Component component = e.getComponent();
			if (component instanceof AbstractButton)
			{
				AbstractButton button = (AbstractButton) component;
				button.setBorder(BorderFactory.createRaisedBevelBorder());
				button.setBorderPainted(true);
			}
		}

		public void mouseExited(MouseEvent e)
		{
			Component component = e.getComponent();
			if (component instanceof AbstractButton)
			{
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(false);
			}
		}
	};
}

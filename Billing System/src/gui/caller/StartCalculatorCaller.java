package gui.caller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class StartCalculatorCaller implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Runtime rt = Runtime.getRuntime();
		try
		{
			rt.exec("Calc.exe");
		}
		catch (IOException evt)
		{
			JOptionPane.showMessageDialog(null, evt.getMessage(), "Error occured while loading ", JOptionPane.ERROR_MESSAGE);
		}
	}

}

package utils;

import gui.dialog.MessageDialog;
import gui.panels.BasicGuiPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintUtilities implements Printable
{
	private BasicGuiPanel panel = null;

	public PrintUtilities(BasicGuiPanel panel)
	{
		this.panel = panel;
	}

	public void print()
	{
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog())
		{
			try
			{
				printJob.print();
			}
			catch (PrinterException pe)
			{
				System.out.println("Error printing: " + pe.getMessage());
				new MessageDialog("Error printing: ", pe.getMessage());
			}
		}
	}

	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex)
	{
		if (pageIndex > 0)
		{
			return Printable.NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		Font f = new Font("Monospaced", Font.PLAIN, 12);
		g2d.setFont(f);
		panel.print(g2d);
		return Printable.PAGE_EXISTS;
	}
}

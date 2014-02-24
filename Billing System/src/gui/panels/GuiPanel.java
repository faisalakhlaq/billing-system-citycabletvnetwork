package gui.panels;

/**
 * All the panels and views must implement this interface to keep an order in
 * the GUI interface
 */
public interface GuiPanel
{
	public void initPanel();

	public BasicGuiPanel getCenterPanel();

	public BasicGuiPanel getHeaderPanel();

	public BasicGuiPanel getButtonPanel();

	/**
	 * Return the view in which this view is displayed
	 */
	public GuiPanel getOwningView();
}

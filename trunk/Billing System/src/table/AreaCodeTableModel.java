package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.AreaCode;

@SuppressWarnings("serial")
public class AreaCodeTableModel extends AbstractTableModel
{
	private static final int AREA_NAME = 0;

	private static final int CODE = 1;

	private String[] columnNames;

	private Vector<AreaCode> data;

	public AreaCodeTableModel(Vector<AreaCode> areaCodesVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<AreaCode>(areaCodesVector);
	}

	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		AreaCode record = (AreaCode) data.get(row);
		switch (column)
		{
		case AREA_NAME:
			return record.getAreaName();
		case CODE:
			return record.getAreaCode();
		default:
			return new Object();
		}
	}
}

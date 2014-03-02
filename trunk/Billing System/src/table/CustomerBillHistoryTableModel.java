package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Bill;

@SuppressWarnings("serial")
public class CustomerBillHistoryTableModel extends AbstractTableModel
{
	private static final int MONTH = 0;

	private static final int YEAR = 1;

	private static final int AMOUNT = 2;

	private static final int PAID = 3;

	private String[] columnNames;

	private Vector<Bill> data;

	public CustomerBillHistoryTableModel(Vector<Bill> billsVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<Bill>(billsVector);
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
		Bill record = (Bill) data.get(row);
		switch (column)
		{
		case MONTH:
			return record.getMonth();
		case YEAR:
			return record.getYear();
		case AMOUNT:
			return record.getPayableAmount();
		case PAID:
			return record.getPaid();
		default:
			return new Object();
		}
	}
}

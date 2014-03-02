package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Customer;

@SuppressWarnings("serial")
public class CustomerTableModel extends AbstractTableModel
{
	private static final int ACCOUNT_NUMBER = 0;

	private static final int DATE = 1;

	private static final int NAME = 2;

	private static final int ADDRESS = 3;

	private static final int ADVANCE = 4;

	private static final int NIC_NUMBER = 5;

	private static final int TELEPHONE = 6;

	private static final int CONNECTION_TYPE = 7;

	private static final int CONNECTION_FEE = 8;

	private static final int AREA_CODE = 9;

	private String[] columnNames;

	private Vector<Customer> data;

	public CustomerTableModel(Vector<Customer> customerVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<Customer>(customerVector);
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
		Customer record = (Customer) data.get(row);
		switch (column)
		{
		case ACCOUNT_NUMBER:
			return record.getAccountNumber();
		case DATE:
			return record.getDate();
		case NAME:
			return record.getCustomerName();
		case ADDRESS:
			return record.getCustomerAddress();
		case ADVANCE:
			return record.getAdvance();
		case NIC_NUMBER:
			return record.getNicNumber();
		case TELEPHONE:
			return record.getTelNumber();
		case CONNECTION_TYPE:
			return record.getConnectionType();
		case CONNECTION_FEE:
			return record.getConnectionFee();
		case AREA_CODE:
			return record.getAreaCode();
		default:
			return new Object();
		}
	}
}

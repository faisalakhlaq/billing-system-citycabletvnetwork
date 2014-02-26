package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Bill;

@SuppressWarnings("serial")
public class BillTableModel extends AbstractTableModel
{
	private static final int BILL_NUMBER = 0;

	private static final int ISSUE_DATE = 1;

	private static final int DUE_DATE = 2;

	private static final int ACCOUTN_NUMBER = 3;

	private static final int MONTH = 4;

	private static final int YEAR = 5;

	private static final int PAYABLE_AMOUNT = 6;

	private static final int RECEIVED_AMOUNT = 7;

	private static final int RECEIVED_BY = 8;

	private static final int PAID = 9;

	private String[] columnNames;

	private Vector<Bill> data;

	public BillTableModel(Vector<Bill> billVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<Bill>(billVector);
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
		case BILL_NUMBER:
			return record.getBillNumber();
		case ISSUE_DATE:
			return record.getIssueDate();
		case DUE_DATE:
			return record.getDueDate();
		case ACCOUTN_NUMBER:
			return record.getAccountNumber();
		case MONTH:
			return record.getMonth();
		case YEAR:
			return record.getYear();
		case PAYABLE_AMOUNT:
			return record.getPayableAmount();
		case RECEIVED_AMOUNT:
			return record.getReceivedAmount();
		case RECEIVED_BY:
			return record.getReceivedBy();
		case PAID:
			return record.getPaid();
		default:
			return new Object();
		}
	}
}

package table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.AdvertisementBill;
import model.Bill;
import model.ModelObject;

@SuppressWarnings("serial")
public class SalesReportTableModel extends AbstractTableModel
{
	private static final int AMOUNT = 0;

	private static final int DATE = 1;

	private static final int TYPE = 2;

	private static final int ACCOUNT_NUMBER = 3;

	private String[] columnNames;

	private ArrayList<ModelObject> data;

	public SalesReportTableModel(ArrayList<ModelObject> billsList, String[] columns)
	{
		columnNames = columns;
		data = new ArrayList<ModelObject>(billsList);
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
		ModelObject record = (ModelObject) data.get(row);
		switch (column)
		{
		case AMOUNT:
			if (record instanceof Bill)
			{
				return ((Bill) record).getReceivedAmount();
			}
			else if (record instanceof AdvertisementBill)
			{
				return ((AdvertisementBill) record).getPayableAmount();
			}
		case DATE:
			if (record instanceof Bill)
			{
				return ((Bill) record).getDatePaid();
			}
			else if (record instanceof AdvertisementBill)
			{
				return ((AdvertisementBill) record).getDate();
			}
		case TYPE: // TODO
			if (record instanceof Bill)
			{
				return "Customer Bill";
			}
			else if (record instanceof AdvertisementBill)
			{
				return "Advertisement Bill";
			}
		case ACCOUNT_NUMBER:
			if (record instanceof Bill)
			{
				return ((Bill) record).getAccountNumber();
			}
			else if (record instanceof AdvertisementBill)
			{
				return ((AdvertisementBill) record).getAccountNumber();
			}
		default:
			return new Object();
		}
	}
}
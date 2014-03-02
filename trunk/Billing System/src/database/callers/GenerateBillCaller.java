package database.callers;

import gui.dialog.MessageDialog;
import gui.panels.AbstractGuiPanel;
import gui.panels.GenerateBillPanel;
import gui.panels.GenerateIndividualBillPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

import javax.swing.JOptionPane;

import model.Bill;
import utils.Helper;
import database.BillHandler;
import database.CustomerHandler;

public class GenerateBillCaller implements ActionListener
{
	AbstractGuiPanel panel = null;

	public GenerateBillCaller(AbstractGuiPanel p)
	{
		panel = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (panel == null)
		{
			new MessageDialog("Error", "Action cannot be performed <p> Cannot get the values from the Generate Bill Panel");
			return;
		}
		try
		{
			if (panel instanceof GenerateBillPanel)
			{
				generateAllBills((GenerateBillPanel) panel);
			}
			else if (panel instanceof GenerateIndividualBillPanel)
			{
				generateIndividualBill((GenerateIndividualBillPanel) panel);
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	private void generateIndividualBill(GenerateIndividualBillPanel p)
	{
		try
		{
			String comBill = p.getCommercialBill();
			boolean isCommercialBill = p.isCommercial();
			if (isCommercialBill)
			{
				if (Helper.isEmpty(comBill))
				{
					new MessageDialog("Error", "Commercial bill field cannot be empty");
					return;
				}
				if (!Helper.isDigit(comBill.trim()))
				{
					new MessageDialog("Error", "Commercial bill field can contain only digits");
					return;
				}
			}
			String privateBill = p.getPrivateBill();
			if (!isCommercialBill)
			{
				if (Helper.isEmpty(privateBill))
				{
					new MessageDialog("Error", "Private bill field cannot be empty");
					return;
				}
				if (!Helper.isDigit(privateBill.trim()))
				{
					new MessageDialog("Error", "Private bill field can contain only digits");
					return;
				}
			}
			Date issueDate = p.getBillIssueDate();
			Date dueDate = p.getBillDueDate();

			if (dueDate.before(issueDate))
			{
				new MessageDialog("Error", "Due Date cannot be before issue date");
				return;
			}

			String month = p.getBillingMonth();
			int year = p.getBillingYear();

			Bill bill = new Bill();
			bill.setIssueDate(issueDate);
			bill.setDueDate(dueDate);
			bill.setAccountNumber(Integer.valueOf(p.getAccountNumber().trim()));
			bill.setMonth(month);
			bill.setYear(year);
			if (!isCommercialBill)
			{
				bill.setPayableAmount(Integer.valueOf(privateBill));
			}
			else
			{
				bill.setPayableAmount(Integer.valueOf(comBill));
			}
			bill.setReceivedAmount(0);
			bill.setReceivedBy(null);
			bill.setPaid(false);

			BillHandler billHandler = new BillHandler();
			billHandler.insertGeneratedBill(bill);
			new MessageDialog("Success", "Bill Generated!", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	private void generateAllBills(GenerateBillPanel p)
	{
		try
		{
			String comBill = p.getCommercialBill();
			if (Helper.isEmpty(comBill))
			{
				new MessageDialog("Error", "Commercial bill field cannot be empty");
				return;
			}
			if (!Helper.isDigit(comBill.trim()))
			{
				new MessageDialog("Error", "Commercial bill field can contain only digits");
				return;
			}

			String privateBill = p.getPrivateBill();
			if (Helper.isEmpty(privateBill))
			{
				new MessageDialog("Error", "Private bill field cannot be empty");
				return;
			}
			if (!Helper.isDigit(privateBill.trim()))
			{
				new MessageDialog("Error", "Private bill field can contain only digits");
				return;
			}

			Date issueDate = p.getBillIssueDate();
			Date dueDate = p.getBillDueDate();

			if (dueDate.before(issueDate))
			{
				new MessageDialog("Error", "Due Date cannot be before issue date");
				return;
			}

			String month = p.getBillingMonth();
			int year = p.getBillingYear();

			CustomerHandler custHandler = new CustomerHandler();
			Map<Integer, String> list = custHandler.getCustomerConnectionTypes();

			if (list == null || list.isEmpty())
			{
				new MessageDialog("Error", "Unable to get customer records. Check if there are any customers");
				return;
			}

			BillHandler billHandler = new BillHandler();
			for (Map.Entry<Integer, String> entry : list.entrySet())
			{
				/*
				 * issue_date DATE, due_date DATE, account_number INT, month
				 * VARCHAR(10), year INT, payable_amount INT, received_amount
				 * INT, received_by VARCHAR(20), paid BOOL
				 */
				Bill bill = new Bill();
				bill.setIssueDate(issueDate);
				bill.setDueDate(dueDate);
				bill.setAccountNumber(entry.getKey());
				bill.setMonth(month);
				bill.setYear(year);
				String type = entry.getValue();
				if (("private").equalsIgnoreCase(type))
				{
					bill.setPayableAmount(Integer.valueOf(privateBill));
				}
				else
				{
					bill.setPayableAmount(Integer.valueOf(comBill));
				}
				bill.setReceivedAmount(0);
				bill.setReceivedBy(null);
				bill.setPaid(false);

				billHandler.insertGeneratedBill(bill);// TODO insert all the
														// records in one go
			}
			new MessageDialog("Success", "Bills Generated!", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}
}
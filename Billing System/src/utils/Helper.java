package utils;

import java.util.Calendar;

public class Helper
{
	public static boolean isDigit(String data)
	{
		boolean isDigit = false;
		String regex = "\\d+";
		isDigit = data.matches(regex);
		return isDigit;
	}

	/**
	 * Check if the provided string is empty or null
	 */
	public static boolean isEmpty(String str)
	{
		boolean isEmpty = false;
		if (str == null)
		{
			isEmpty = true;
		}
		else if (str.trim().isEmpty())
		{
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * Returns the specified number of next years starting from the current year
	 * */
	public static Integer[] getNextYears(int numberOfYears)
	{
		Integer[] years = new Integer[numberOfYears];
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);

		for (int i = 0; i < numberOfYears; i++)
			years[i] = thisYear + i;

		return years;
	}

	public static String[] getMonths()
	{
		String[] months =
		{ "JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
		return months;
	}
}

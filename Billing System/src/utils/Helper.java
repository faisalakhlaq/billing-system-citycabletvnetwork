package utils;

public class Helper
{
	public static boolean isDigit(String data)
	{
		boolean isDigit = false;
		String regex = "\\d+";
		isDigit = data.matches(regex);
		return isDigit;
	}

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
}

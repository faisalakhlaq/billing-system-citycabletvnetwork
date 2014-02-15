package utils;

public class CustomPair
{
	private String key;

	private String value;

	private boolean booleanKey;

	public CustomPair()
	{
	}

	public CustomPair(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public CustomPair(boolean boolKey, String value)
	{
		this.booleanKey = boolKey;
		this.value = value;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean isBooleanKey()
	{
		return booleanKey;
	}

	public void setBooleanKey(boolean booleanKey)
	{
		this.booleanKey = booleanKey;
	}

}

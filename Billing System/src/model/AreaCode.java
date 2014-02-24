package model;

public class AreaCode
{
	private int areaCode;

	private String areaName;

	public AreaCode()
	{
	}

	public AreaCode(int code, String area)
	{
		areaCode = code;
		areaName = area;
	}

	public int getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(int areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

}

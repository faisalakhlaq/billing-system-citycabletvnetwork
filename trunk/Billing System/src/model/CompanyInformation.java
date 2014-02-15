package model;

public class CompanyInformation
{
	private String companyName;

	private String companyAddress;

	private String companyTelephoneNumber;

	private String companyOwnerName;

	private String termsAndCondition;

	private String openingHours;
	private final String[] connectionTypes = {"Private", "Commercial"};

	public CompanyInformation()
	{
		companyName = "City Cable T.V Network";
		companyAddress = "Kotli Chowk Pallandri A.k.";
		companyTelephoneNumber = " Ph. 05825-443825";
		companyOwnerName = "Chief Executive Dr. Muhammad Yaseen Khan";
		termsAndCondition = "";
		openingHours = "9 to 5";
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getCompanyAddress()
	{
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress)
	{
		this.companyAddress = companyAddress;
	}

	public String getCompanyTelephoneNumber()
	{
		return companyTelephoneNumber;
	}

	public void setCompanyTelephoneNumber(String companyTelephoneNumber)
	{
		this.companyTelephoneNumber = companyTelephoneNumber;
	}

	public String getCompanyOwnerName()
	{
		return companyOwnerName;
	}

	public void setCompanyOwnerName(String companyOwnerName)
	{
		this.companyOwnerName = companyOwnerName;
	}

	public String getTermsAndCondition()
	{
		return termsAndCondition;
	}

	public void setTermsAndCondition(String termsAndCondition)
	{
		this.termsAndCondition = termsAndCondition;
	}

	public String getOpeningHours()
	{
		return openingHours;
	}

	public void setOpeningHours(String openingHours)
	{
		this.openingHours = openingHours;
	}

	public String[] getConnectionTypes()
	{
		return connectionTypes;
	}
}

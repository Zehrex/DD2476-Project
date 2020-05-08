74
https://raw.githubusercontent.com/harshalbenake/hbworkspace1-100/master/CustomSpinner/src/com/androidexample/customspinner/SpinnerModel.java
package com.androidexample.customspinner;


public class SpinnerModel {
	
	private  String CompanyName="";
	private  String Image=""; 
	private  String Url="";
	
	/*********** Set Methods ******************/
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}
	
	public void setImage(String Image)
	{
		this.Image = Image;
	}
	
	public void setUrl(String Url)
	{
		this.Url = Url;
	}
	
	/*********** Get Methods ****************/
	public String getCompanyName()
	{
		return this.CompanyName;
	}
	
	public String getImage()
	{
		return this.Image;
	}

	public String getUrl()
	{
		return this.Url;
	}	
}

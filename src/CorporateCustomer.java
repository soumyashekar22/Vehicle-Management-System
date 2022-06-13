import java.io.*;
public class CorporateCustomer extends Customer implements Serializable
{
	private double rate;
	
	public CorporateCustomer(String customerID, String customerName, double rate)
	{
		super(customerID, customerName);
		this.rate = rate;
		setType('C');
	}

	public double getRate() 
	{
		return rate;
	}

	public void setRate(double rate) 
	{
		this.rate = rate;
	}
	
	//calculate discount for corporate customers
	public double getDiscount(double amount) 
	{
		//100,000 mileage are offered 10% discount
		double discount = (rate * amount)/100;
		return discount;
	}
	
	
	//print details of corporate customers
	public void print()
	{
		System.out.println("*** Corporate Customer details ***");
		System.out.print("The customerID is: "+getCustomerID()+"\t");
		System.out.print("The customer name is: "+getCustomerName()+"\t");
		System.out.println("The customer rate is: "+getRate());
	}
	
}

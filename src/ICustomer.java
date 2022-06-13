import java.io.*;
public class ICustomer extends Customer implements Serializable
{
	
	public ICustomer(String customerID, String customerName, String phoneNumber)
	{
		super(customerID, customerName, phoneNumber);
		setType('I');
	}
	
	
	//to calculate discount for icustomer
    @Override
	public double getDiscount(double amount) 
	{
		//100,000 mileage are offered 10% discount
		double discount = 0;
		if(getPastMilage() > 200000)
		{
			discount = amount * (0.2);
		}
		else if(getPastMilage() > 100000)
		{
			discount = amount * (0.1);
		}
		else
		{
			discount = 0;
		}
		return discount;
	}

	
    //print details of icustomers
	public void print()
	{
		System.out.println("*** Individual Customer details ***");
		super.print();
		System.out.println("The customer mileage is: "+getPastMilage());
	}
	
}

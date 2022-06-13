import java.io.Serializable;
public abstract class Customer implements Serializable
{
	private String customerID;
	private String customerName;
	private String phoneNumber;
	private int numberOfHire;
	private char type;
	private int pastMilage;
	
	public Customer(String customerID, String customerName, String phoneNumber)
	{
		this.customerID = customerID;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;	
		this.numberOfHire = 0;
		this.pastMilage = 0;
	}
	
	public Customer(String customerID, String customerName)
	{
		this.customerID = customerID;
		this.customerName = customerName;
	}
	
	public abstract double getDiscount(double amount);
	

	public String getCustomerID() 
	{
		return customerID;
	}

	public String getCustomerName() 
	{
		return customerName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public int getNumberOfHires()
	{
		return numberOfHire;
	}
	
	public void setNumberOfHires(int numberOfHires )
	{
		this.numberOfHire = numberOfHires;
	}
	
	public int getPastMilage()
	{
		return pastMilage;
	}
	
	public void setPastMilage(int pastMilage )
	{
		this.pastMilage += pastMilage;
	}
	public char getType()
	{
		return type;
	}
	
	public void setType(char type)
	{
		this.type = type;
	}
	
	
	//print details of customer
	public void print()
	{
		System.out.print("The customerID is: "+getCustomerID()+"\t");
		System.out.print("The customer name is: "+getCustomerName()+"\t");
		System.out.print("The customer phone number is: "+getPhoneNumber()+"\t");
	}
	
	
}

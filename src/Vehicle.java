import java.io.*;
public class Vehicle implements Serializable
{
	private String vehicleID;
	private String hirerID;
	private String description; 
	private char status; 
	private double dailyRate;
	private int odometer;
	private DateTime hireDate;
	
	public Vehicle(String vehicleID, String description, double dailyRate, int odometer)
	{
		this.vehicleID = vehicleID;
		this.description = description;
		this.dailyRate = dailyRate;
		this.odometer = odometer;
		status = 'A';
	}
	
	
    public void setStatus(char status) 
    {
		this.status = status;
	}


    public void setOdometer(int odometer) 
	{
	 	this.odometer = odometer;
	}
    
    
    public void setHirerID(String hirerID) 
    {
		this.hirerID = hirerID;
	}
    

	public String getID()
	{
		return vehicleID;
	}
	
	
	public String getHirer()
	{
		return hirerID;
	}
	
	
	public String getDescription()
	{
		return description;
	}
	
	
	public char getStatus()
	{
		return status;
	}
	
	
	public double getDailyRate()
	{
		return dailyRate;
	}
	
	
	public int getOdometer()
	{
		return odometer;
	}
	
	
	public String toString()
	{
		return hireDate.toString();
	}
	
	
	//called to hire a vehicle
	public void hire(String hirerID) throws StatusException, OdoException
	{
		if(status == 'S' || status == 'H')
		{
			throw new StatusException("The vehicle "+vehicleID+" cannot be hired");
			
		}
		else
		{
			this.hirerID = hirerID;
			hireDate = new DateTime();
			status = 'H';
			System.out.println("The vehicle "+vehicleID+" is hired");
		}
	  }
	
	
	//called when vehicle is sent for service
	public void service() throws StatusException
	{
		if(status == 'A')
		{
			status = 'S';
			System.out.println("The vehicle "+vehicleID+" is sent for service");
		}
		else
		{
			throw new StatusException("The vehicle "+vehicleID+" cannot be sent for service");
		}
		
	}
	
	
	//called when vehicle is back from service
	public void serviceComplete(int odo) throws StatusException, OdoException
	{
		if(status == 'S' && odo >= odometer)
		{
			status = 'A';
			odometer = odo;
			System.out.println("The service for "+vehicleID+" is completed");
		}
		else if(status != 'S')
		{
			throw new StatusException("The service for "+vehicleID+" cannot be completed due to invalid status");
		}
		if(odo < odometer)
		{
			throw new OdoException("The service for "+vehicleID+" cannot be completed due to invalid odometer reading");
		}
		
	}
	
	
	//called when hire is completed
	public double hireComplete(int odo) throws StatusException,OdoException
	{
		double charge = 0.0;
		if(status == 'H' && odo>odometer)
		{
			DateTime retDate = new DateTime();
			int numOfDays = DateTime.diffDays(retDate, hireDate);
			charge = dailyRate * numOfDays;
			status = 'A';
			odometer = odo;
			System.out.println("The hire for vehicle "+vehicleID+" is completed");
		}
		else if(status != 'H')
		{
			charge = -1.0;
			throw new StatusException("The hire for vehicle "+vehicleID+" cannot be completed due to invalid status");
		}
		else if(odo<=odometer)
		{
			charge = -1.0;
			throw new OdoException("The hire for vehicle "+vehicleID+" cannot be completed due to invalid odometer reading");
		}
		return charge;
	}
	
	
	//Prints all the details of vehicles
	public void print()
	{
		    System.out.println("********Vehicle Details********");
		    System.out.println(DateTime.getCurrentTime());
		    System.out.print("vehicleID:"+vehicleID+"\t");
		    System.out.print("description:"+description+"\t");
		    System.out.print("status:"+status+"\t");
		    System.out.print("dailyRate:"+dailyRate+"\t\t");
		    System.out.println("odometer:"+odometer+"\t");
		    
		    if(status == 'H')
			{
			    System.out.print("hirerID:"+hirerID+"\t");
				System.out.println("hireDate:"+toString()+"\t");
			}
		
		
	}
	
}

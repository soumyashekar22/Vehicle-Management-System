import java.io.*;
public class PremiumVehicle extends Vehicle implements Serializable
{
	private int freeMilage;
	private int serviceLength;
	private int odoLast;
	private DateTime hireDate;
	
	public PremiumVehicle(String vehicleID, String description, double dailyRate, int odometer,int freeMilage,int serviceLength,int odoLast)
	{
		super(vehicleID, description, dailyRate, odometer);
		this.freeMilage = freeMilage;
		this.serviceLength = serviceLength;
		this.odoLast = odoLast;
	}
	

	@Override
	public String toString() 
	{
		return hireDate.toString();
	}
	
	//called to hire a car
	@Override
	public void hire(String hirerID) throws StatusException, OdoException
	{
		int odoDiff = Math.abs(odoLast - getOdometer());
		if(getStatus() == 'S' || getStatus() == 'H')
		{
			throw new StatusException("The vehicle "+getID()+" cannot be hired due to the status");
		}
		else if(odoDiff >= serviceLength)
		{
			throw new OdoException("The vehicle "+getID()+" cannot be hired as it has to be sent for service");
		}
		else
		{
			setHirerID(hirerID);
			hireDate = new DateTime();
			setStatus('H');
			System.out.println("The vehicle "+getID()+" is hired");
		}
	}
	
    //called to complete service
	@Override
	public void serviceComplete(int odo) throws StatusException, OdoException
	{
		if(getStatus() == 'S' && odo >= getOdometer())
		{
				setStatus('A');
				setOdometer(odo);
				odoLast = odo;
				System.out.println("The service for vehicle "+getID()+" is completed");
		}
		
		else if(getStatus() != 'S')
		{
				throw new StatusException("The service for vehicle "+getID()+" cannot be completed due to invalid status");
		}
		if(odo < getOdometer())
		{
				throw new OdoException("The service for vehicle "+getID()+" cannot be completed due to invalid odometer reading");
		}
	}
	
    //Called to complete hire
	@Override
	public double hireComplete(int odo) throws StatusException,OdoException
	{
		double charge = 0.0;
		double drivenKm = odo - getOdometer();
		if(getStatus() == 'H' && odo>getOdometer())
		{
			DateTime retDate = new DateTime();
			int numOfDays = DateTime.diffDays(retDate, hireDate);
			if(drivenKm > freeMilage)
			{
				charge = (getDailyRate() * numOfDays) + (0.10 * (drivenKm - (freeMilage * numOfDays)));
			}
			else
			{
				charge = getDailyRate() * numOfDays;
			}
		setStatus('A');
		setOdometer(odo);
		System.out.println("The hire for the vehicle "+getID()+" is completed");
		}
		else if(getStatus() != 'H')
		{
			charge = -1.0;
			throw new StatusException("The hire for vehicle "+getID()+" cannot be completed due to invalid status");
		}
		else if(odo<=getOdometer())
		{
			charge = -1.0;
			throw new OdoException("The hire for vehicle "+getID()+" cannot be completed due to invalid odometer reading");
		}
		return charge;
	}
	
    //called to print all details of premium vehicles
	@Override
	public void print() 
	{
		 super.print();
		 System.out.print("Milage Allowance:"+freeMilage+"   ");
		 System.out.print("serviceLength:"+serviceLength+"   ");
		 System.out.println("Last odometer reading:"+odoLast+"   ");
		 System.out.print(" ");
	}
	
	
}

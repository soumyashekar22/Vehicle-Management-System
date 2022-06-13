import java.io.*;
import java.util.*;

public class Report implements Serializable
{
	private String vehicleID;
	private Date dateTime;
	private Double charge;
	
	public Report(String vehicleID, Date dateTime, Double charge)
	{
		this.vehicleID = vehicleID;
		this.dateTime = dateTime;
		this.charge = charge;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
	
	public void printReport ()
	{
		System.out.println("***Report for vehicle***");
		System.out.print("Vehicle ID : " + vehicleID+"\t");
		System.out.print("DateTime : " + dateTime+"\t");
		System.out.println("Charge : " + charge);
	}
	
	
}

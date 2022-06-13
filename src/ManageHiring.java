import java.util.*;
import java.time.LocalDate;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class ManageHiring 
{
	public static void main(String[] args)
	{
		ArrayList<Vehicle> vehs = new ArrayList<Vehicle>();;
		ArrayList<Customer> cus = new ArrayList<Customer>();;
		ArrayList<Report> report = new ArrayList<Report>();
		File vehFile = new File("C:\\temp\\Vehicle.dat");
		if(!vehFile.exists())
		{
			try 
			{
				vehFile.createNewFile();
			}
			catch(IOException io)
			{
				System.out.println("Problem creating a new vehicle file");
			}
			catch(Exception e)
			{
				System.out.println("Program terminated due to unhandled exception while creating vehicle file");
			}
		}
		else
		{
			try 
			{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(vehFile));              
				vehs =  (ArrayList<Vehicle>) in.readObject();       
				in.close();
			}
			catch(FileNotFoundException f)
			{
				System.out.println("Vehicle file cannot be found");
			}
        
			catch(IOException io)
			{
				System.out.println("Vehicle file cannot be read");
			}
			catch(ClassNotFoundException c)
			{
				System.out.println("Class to read the vehicle file cannot be found");
			}
			catch(Exception e)
			{
				System.out.println("Program terminated due to unhandled exception while reading vehicle file");
			}
		}
		File cusFile = new File("C:\\temp\\Customers.dat");
		if(!cusFile.exists())
		{
			try
			{
				cusFile.createNewFile();
			}
			catch(IOException io)
			{
				System.out.println("Problem creating a new customer file");
			}
			catch(Exception e)
			{
				System.out.println("Program terminated due to unhandled exception while creating customer file");
			}
		}
		else
		{
			try
			{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(cusFile));              
				cus =  (ArrayList<Customer>) in.readObject();       
				in.close(); 
			}
			catch(FileNotFoundException f)
			{
				System.out.println("Customer file cannot be found");
			}
        
			catch(IOException io)
			{
				System.out.println("Customer file cannot be read");
			}
			catch(ClassNotFoundException c)
			{
				System.out.println("Class to read the customer file cannot be found");
			}
			catch(Exception e)
			{
				System.out.println("Program terminated due to unhandled exception while reading customer file");
			}
	}
	File reportFile = new File("C:\\temp\\Report.dat");
	if(reportFile.exists())
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(reportFile)); 
			report =  (ArrayList<Report>) in.readObject();  
			in.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Report File not found");
			System.out.println(fnfe);
			System.exit(1);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Class to read the Report file is not found");
			System.out.println(cnfe);
			System.exit(1);
		}
		catch(IOException ioe)
		{
			System.out.println("Problem reading from Report file");
			System.out.println(ioe);
			System.exit(1);
		}
	}
	else
	{
		try
		{
			boolean vReportFileChk = reportFile.createNewFile();
		}
		catch(IOException ioe)
		{
			System.out.println("Problem creating report file");
			System.out.println(ioe);
			System.exit(1);
		}		
	}
	String response = "";
	do
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("*** Vehicle Menu ***");
		System.out.println("Add new Vehicle\t\t\t1");
		System.out.println("Add new Premium Vehicle\t\t2");
		System.out.println("Add new Individual Customer\t3");
		System.out.println("Add new Corporate Customer\t4");
		System.out.println("Display available vehicles range5");
		System.out.println("Display Customers\t\t6");
		System.out.println("Display all vehicles\t\t7");
		System.out.println("Hire Vehicle\t\t\t8");
		System.out.println("Service Vehicle\t\t\t9");
		System.out.println("Complete Hire\t\t\t10");
		System.out.println("Retun from Service\t\t11");
		System.out.println("Report of income of vehicle\t12");
		System.out.println("Report for all vehicles\t\t13");
		System.out.println("Exit\t\t\t\t14");
		System.out.println("Enter your selection: ");
		int option = scan.nextInt();
		switch(option)
		{
			case 1:
				boolean check;
				String res = "";
				String vID = "";
				System.out.println("**** Enter vehicle details ****");
				do
				{
					res="";
					check = true;
					System.out.println("Enter the vehicle ID(6 characters)");
					vID = scan.next().toUpperCase();
					if(vID.length() != 6)
					{
						check = false;
						System.out.println("Please enter a vehicle ID that has 6 characters");
						System.out.println("Do you want to continue(Y/N)");
						res = scan.next();
					}
					for(int i =0; i < vehs.size(); i++)
					{
						if(vehs.get(i).getID().equalsIgnoreCase(vID))
						{
							check = false;
							System.out.println("The vehicle "+vID+" already exist. Please enter a new vehicleID");
							System.out.println("Do you want to continue(Y/N)");
							res = scan.next();
						}
					}
				}while(res.equalsIgnoreCase("Y"));
				if(check)
				{
					System.out.println("Enter the vehicle description");
					String vDes = scan.next();
					System.out.println("Enter the daily rate");
					double rate = scan.nextDouble();
					System.out.println("Enter the odometer reading");
					int odometer = scan.nextInt();
					vehs.add(new Vehicle(vID, vDes, rate, odometer));
					System.out.println("The vehicle is successfully added");
				}
				System.out.println("Do you want to return to main menu(Y/N)");
				response = scan.next();
			break;
				
			case 2:
					String pvID = "";
					res = "";
					System.out.println("**** Enter premium vehicle details ****");
					do
					{
						res="";
						check = true;
						System.out.println("Enter the vehicle ID(6 characters)");
						pvID = scan.next().toUpperCase();
						if(pvID.length() != 6)
						{
							check = false;
							System.out.println("Please Enter a vehicle ID that has 6 characters");
							System.out.println("Do you want to continue(Y/N)");
							res = scan.next();
						}
						for(int i =0; i < vehs.size(); i++)
						{
							if(vehs.get(i).getID().equalsIgnoreCase(pvID))
							{
								check = false;
								System.out.println("The vehicle "+pvID+" already exist. Please enter a new premium vehicleID");
								System.out.println("Do you want to continue(Y/N)");
								res = scan.next();
							}
						}
					}while(res.equalsIgnoreCase("Y"));
					if(check)
					{
						System.out.println("Enter the vehicle description");
						String pDes = scan.next();
						System.out.println("Enter the daily rate");
						double pRate = scan.nextDouble();
						System.out.println("Enter the odometer reading");
						int pOdometer = scan.nextInt();
						System.out.println("Enter the free milage");
						int fMilage = scan.nextInt();
						System.out.println("Enter the service length");
						int sLen = scan.nextInt();
						System.out.println("Enter the last odometer reading");
						int oLast = scan.nextInt();
						vehs.add(new PremiumVehicle(pvID, pDes, pRate, pOdometer, fMilage, sLen, oLast));
						System.out.println("The premium vehicle is successfully added");
					}
					System.out.println("Do you want to return to main menu(Y/N)");
					response = scan.next();
		break;
		
		case 3:
			String cID = "";
			res = "";
			System.out.println("**** Enter individual customer details ****");
			do
			{
				res="";
				check = true;
				System.out.println("Enter the customerID");
				cID = scan.next().toUpperCase();
				if(cID.length() != 6)
				{
					check = false;
					System.out.println("Please enter a customer ID that has 6 characters");
					System.out.println("Do you want to continue(Y/N)");
					res = scan.next();
				}
				if(cID.charAt(0) != 'C')
				{
					check = false;
					System.out.println("The customer ID should begin with C");
					System.out.println("Do you want to continue(Y/N)");
					res = scan.next();
				}
				for(int i =0; i < cus.size(); i++)
				{
					if(cus.get(i).getCustomerID().equalsIgnoreCase(cID))
					{
						check = false;
						System.out.println("The customerID "+cID+" already exists. Please enter a new icustomer ID");
						System.out.println("Do you want to continue(Y/N)");
						res = scan.next();
					}
				}
			}while(res.equalsIgnoreCase("Y"));
			if(check)
			{
				System.out.println("Enter the customer name");
				String cName = scan.next();
				System.out.println("Enter the phone number");
				String phone = scan.next();
				cus.add(new ICustomer(cID, cName, phone));
				System.out.println("The individual customer is successfully added");
			}
			System.out.println("Do you want to return to main menu(Y/N)");
			response = scan.next();
		break;
				
	    case 4:
			String corID = "";
			res = "";
			System.out.println("**** Enter corporate customer details ****");
			do
			{
				res="";
				check = true;
				System.out.println("Enter the customerID");
				corID = scan.next().toUpperCase();
				if(corID.length() != 6)
				{
					check = false;
					System.out.println("Please enter a customer ID that has 6 characters");
					System.out.println("Do you want to continue(Y/N)");
					res = scan.next();
				}
				if(corID.charAt(0) != 'C')
				{
					check = false;
					System.out.println("The customer ID should begin with C");
					System.out.println("Do you want to continue(Y/N)");
					res = scan.next();
				}
				for(int i =0; i < cus.size(); i++)
				{
					if(cus.get(i).getCustomerID().equalsIgnoreCase(corID))
					{
						check = false;
						System.out.println("The customerID "+corID+" already exists. Please enter a new corporate customer ID");
						System.out.println("Do you want to continue(Y/N)");
						res = scan.next();
					}
				}
		 }while(res.equalsIgnoreCase("Y"));
		 if(check)
		 {
			 System.out.println("Enter the customer name");
			 String corName = scan.next();
			 System.out.println("Enter the rate");
			 double corRate = scan.nextDouble();
			 cus.add(new CorporateCustomer(corID, corName, corRate));
			 System.out.println("The corporate customer is successfully added");
		}
		System.out.println("Do you want to return to main menu(Y/N)");
		response = scan.next();
	break;
				
	case 5:
		String user="";
		boolean resp = false;
		do
		{
			resp = false;
			user="";
			System.out.println("Enter the lower limit: ");
			double lowerLimit = scan.nextDouble();
			System.out.println("Enter the upper limit: ");
			double upperLimit = scan.nextDouble();
			if(lowerLimit < upperLimit)
			{
				for(int i = 0; i < vehs.size(); i++)
				{
					if(vehs.get(i).getDailyRate() >= lowerLimit && vehs.get(i).getDailyRate() <= upperLimit && vehs.get(i).getStatus() == 'A')
					{
						vehs.get(i).print();
						resp = true;
					}
				}
			}
			if(lowerLimit > upperLimit)
			{
				System.out.println("The lower limit should be greater than the upper limit");
				System.out.println("Do you want to continue(Y/N)");
				user = scan.next();
			}
			else if(!resp)
			{
				System.out.println("No available vehicle found in the range");
				System.out.println("Do you want to continue(Y/N)");
				user = scan.next();
			}
		}while(user.equalsIgnoreCase("Y"));
		System.out.println("Do you want to return to the main menu(Y/N)");
		response = scan.next();
	break;
				
	case 6:
		for(int i = 0; i < cus.size(); i++)
		{
			cus.get(i).print();
		}
		System.out.println("Do you want to return to main menu(Y/N)");
		response = scan.next();
	break;
					
	case 7:
		for (int i = 0; i < vehs.size(); i++)
		{
			vehs.get(i).print();
		}
		System.out.println("Do you want to return to the main menu(Y/N)");
		response = scan.next();
	break;
				
	case 8:
		String vehicleID = "";
		String customerID = "";
		String ures = "";
		boolean respn = false;
		int i = 0;
		int cindex = 0;
label:  do
		{
	        cindex = 0;
	        int vindex = 0;
			check = false;
			ures = "";
			respn = false;
			System.out.println("Enter your customerID");
			customerID = scan.next();
			for(i = 0; i < cus.size(); i++)
			{
				if(cus.get(i).getCustomerID().equalsIgnoreCase(customerID))
				{
					cindex = i;
					System.out.println("Enter vehicle ID");
					vehicleID = scan.next();
					check = true;
				}
			}
			if(!check)
			{
				System.out.println("Customer "+customerID+" does not exist");
				System.out.println("Do you want to continue(Y/N)");
				ures = scan.next();
				if(ures.equalsIgnoreCase("Y"))
				{
					continue label;
				}
				else 
				{
					break label;
				}
			}
			if(check)
			{
				for(int j = 0; j < vehs.size(); j++)
				{
					if(vehs.get(j).getID().equalsIgnoreCase(vehicleID))
					{
						vindex = j;
					}
				}
				if(vindex >= 0)
				{
					respn = true;
					try
					{
						if(cus.get(cindex).getNumberOfHires() == 0 && cus.get(cindex).getType() == 'I' )
						{
							vehs.get(vindex).hire(customerID);
							cus.get(cindex).setNumberOfHires(1);
						}
						else if(cus.get(cindex).getType() == 'C')
						{
							vehs.get(vindex).hire(customerID);
						}
						else
						{
							System.out.println("Individual customers can hire a single vehicle at a time");
						}
					}
					catch(StatusException e)
					{
						System.out.println(e);
						System.out.println("Do you want to continue(Y/N)");
						ures = scan.next();
						if(ures.equalsIgnoreCase("Y"))
						{
							continue label;
						}
					}
					catch(OdoException o)
					{
						System.out.println(o);
						System.out.println("Do you want to continue(Y/N)");
						ures = scan.next();
						if(ures.equalsIgnoreCase("Y"))
						{
							continue label;
						}
					}
					catch(Exception e)
					{
						System.out.println("The program terminated due to unhandled exception while hiring car");
						System.out.println("Do you want to continue(Y/N)");
						ures = scan.next();
						if(ures.equalsIgnoreCase("Y"))
						{
							continue label;
						}
					}
				}
			}
			if(!respn)
			{
				System.out.println("The vehicle "+vehicleID+"does not exist");
				System.out.println("Do you want to continue(Y/N)");
				ures = scan.next();
			}
	    }while(ures.equalsIgnoreCase("Y"));
	    System.out.println("Do you want to return to the main menu(Y/N)");
	    response = scan.next();
	 break;	
				
	 case 9:
		respn = false;
label1:do
	   {
	    	ures = "";
			System.out.println("Enter the vehicle ID");
			String id = scan.next();
			respn = false;
			for(i =0 ; i< vehs.size(); i++)
			{
				if(vehs.get(i).getID().equalsIgnoreCase(id))
			    {
					respn = true;
			    	try
			    	{
			    		vehs.get(i).service();
			    	}
			    	catch(StatusException s)
			    	{
			    		System.out.println(s);
			    		System.out.println("Do you want to continue(Y/N)");
			    		ures = scan.next();
			    		if(ures.equalsIgnoreCase("Y"))
			    		{
			    			continue label1;
			    		}
			    	}
			    }
		   }
		   if(!respn)
		   {
			   System.out.println("The vehicle "+id+" does not exist");
			   System.out.println("Do you want to continue(Y/N)");
			   ures = scan.next();
		   }
	  }while(ures.equalsIgnoreCase("Y"));
	  System.out.println("Do you want to return to the main menu(Y/N)");
	  response = scan.next();
    break;
	
	case 10:
		String vehID = "";
		String custID = "";
		int odometer = 0;
		ures = "";
		check = false;
		boolean vcheck = false;
		int index = 0;
		cindex = 0;
  label:do
		{
	  		ures = "";
			vcheck = false;
			check = false;
			index = 0;
			cindex = 0;
			System.out.println("Enter your customerID");
			custID = scan.next();
			System.out.println("Enter vehicle ID");
			vehID = scan.next();
			for(i = 0; i < vehs.size(); i++)
			{
				if(vehs.get(i).getID().equalsIgnoreCase(vehID))
				{
					index = i;
					vcheck = true;
				}
			}
			if(!vcheck)
			{
				System.out.println("The vehicle "+vehID+" does not exist");
				System.out.println("Do you want to continue(Y/N)");
				ures = scan.next();
				if(ures.equalsIgnoreCase("Y"))
				{
					continue label;
				}
				else
				{
					break label;
				}
			}
			if(vehs.get(index).getHirer().equalsIgnoreCase(custID))
			{
				check = true;
			}
			if(!check)
			{
				System.out.println("The customer "+custID+" has not hired "+vehID);
				System.out.println("Do you want to continue(Y/N)");
				ures = scan.next();
				if(ures.equalsIgnoreCase("Y"))
				{
					continue label;
				}
				else
				{
					break label;
				}
			}
			if(check && vcheck)
			{
				System.out.println("Enter the odometer reading");
				odometer = scan.nextInt();
				try
				{
					respn = true;
					int hireDistance = odometer - vehs.get(index).getOdometer();
					double charge = vehs.get(index).hireComplete(odometer);
					if(charge >= 0)
					{
						for(int j = 0; j < cus.size(); j++)
						{
							if(cus.get(j).getCustomerID().equalsIgnoreCase(custID))
						      {
								cindex = j; 
						      }
						}
						if(cus.get(cindex).getType() == 'I')
						{
							LocalDate ldate = LocalDate.now();
							String date = ldate.toString();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date pDate = null;
							try
						    {
							    pDate = sdf.parse(date);
							 }
							 catch(ParseException pe)
							 {
								 System.out.println("Cannot parse");
							 }
							double discount = cus.get(cindex).getDiscount(charge);
							double totalCharge = charge - discount;
							System.out.println("Total charge after discount is " + totalCharge);
							cus.get(cindex).setNumberOfHires(0);
							cus.get(cindex).setPastMilage(hireDistance);
							if(report.add(new Report(vehID,pDate,totalCharge)))
							{
								System.out.println("Data is added to report successfully");
							}
							else
							{
								System.out.println("Data cannot be added to report");
							}
						}
						else if(cus.get(cindex).getType() == 'C')
						{
							LocalDate ldate = LocalDate.now();
							String date = ldate.toString();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date pDate = null;
							try
						    {
							    pDate = sdf.parse(date);
							 }
							 catch(ParseException pe)
							 {
								 System.out.println("Cannot parse");
							 }
							double discount = cus.get(cindex).getDiscount(charge);
							double totalCharge = charge - discount;
							System.out.println("Total charge after discount is " + totalCharge);
							if(report.add(new Report(vehID,pDate,totalCharge)))
							{
								System.out.println("Data is added to report successfully");
							}
							else
							{
								System.out.println("Data cannot be added to report");
							}
						}
					}
					else
					{
						System.out.println("Hire could not be completed");
					}
				}
				catch(StatusException e)
				{
					System.out.println(e);
					System.out.println("Do you want to continue(Y/N)");
					ures = scan.next();
					if(ures.equalsIgnoreCase("Y"))
					{
						continue label;
					}
				}
				catch(OdoException o)
				{
					System.out.println(o);
					System.out.println("Do you want to continue(Y/N)");
					ures = scan.next();
					if(ures.equalsIgnoreCase("Y"))
					{
						continue label;
					}
				}
		 }
	  }while(ures.equalsIgnoreCase("Y"));
	  System.out.println("Do you want to return to the main menu(Y/N)");
	  response = scan.next();
	break;	
			
	case 11:
		respn = false;
 label2:do
		{
		 	ures = "";
			System.out.println("Enter the vehicle ID");
			String id = scan.next();
			System.out.println("Enter odometer reading");
			int odo = scan.nextInt();
			respn = false;
			for(i =0 ; i< vehs.size(); i++)
			{
				if(vehs.get(i).getID().equalsIgnoreCase(id))
				{
					respn = true;
					try
					{
						vehs.get(i).serviceComplete(odo);
					}
					catch(StatusException se)
					{
						System.out.println(se);
						System.out.println("Do you want to continue(Y/N)");
						ures = scan.next();
						if(ures.equalsIgnoreCase("Y"))
						{
							continue label2;
						}
					}
					catch(OdoException oe)
					{
						System.out.println(oe);
						System.out.println("Do you want to continue(Y/N)");
						ures = scan.next();
						if(ures.equalsIgnoreCase("Y"))
						{
							continue label2;
						}
					}
				}
			}
			if(!respn)
			{
			    System.out.println("The vehicle "+id+" does not exist");
			    System.out.println("Do you want to continue(Y/N)");
			    ures = scan.next();
			}
		}while(ures.equalsIgnoreCase("Y"));
		System.out.println("Do you want to return to the main menu(Y/N)");
		response = scan.next();
	 break;
	
	 case 12:
		 double vincome = 0.0;
		 System.out.println("Enter the vehicle id for which report is to be generated");
		 String ID = scan.next();
		 int vin = 0;
		 int rin = 0;
		 
		 for (i = 0; i < vehs.size(); i++)
		 {
			 vin = i;
		 }
		 if(vin >= 0 )
		 {
			 for (int j = 0; j < report.size(); j++)
			 {
				 rin = j; 
			 }
		 }
		 if (rin >= 0)
		 {
			 Date date1 = null;
			 Date date2 = null;
			 System.out.println("Enter the start year(4 digits)");
			 int syear = scan.nextInt();
			 System.out.println("Enter the start month(2 digits)");
			 int smon = scan.nextInt();
			 System.out.println("Enter the start day(2 digits)");
			 int sday = scan.nextInt();
			 System.out.println("Enter the end year(4 digits)");
			 int eyear = scan.nextInt();
			 System.out.println("Enter the end month(2 digits)");
			 int emon = scan.nextInt();
			 System.out.println("Enter the end day(2 digits)");
			 int eday = scan.nextInt();
			 
			 String year1 = String.valueOf(syear);
			 String month1 = String.valueOf(smon);
			 String day1 = String.valueOf(sday);
			 String sdate = year1+"-"+month1+"-"+day1;
			 
			 String year2 = String.valueOf(eyear);
			 String month2 = String.valueOf(emon);
			 String day2 = String.valueOf(eday);
			 String edate = year2+"-"+month2+"-"+day2;
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 try
			 {
			    date1 = sdf.parse(sdate);
			    date2 = sdf.parse(edate);
			 }
			 catch(ParseException pe)
			 {
				 System.out.println("Unable to parse");
			 }
			 for(i = 0; i < report.size(); i ++)
			 {
				 if(report.get(i).getVehicleID().equalsIgnoreCase(ID))
				 {
					 if(report.get(i).getDateTime().compareTo(date1) > 0 || report.get(i).getDateTime().compareTo(date1) == 0 )
					 {
						 if(report.get(i).getDateTime().compareTo(date2) < 0 || report.get(i).getDateTime().compareTo(date2) == 0 )
						 {
							 vincome = vincome + report.get(i).getCharge();
						 }
					 }
				 }
			 }
			 System.out.println("Income of " + ID + " between " + date1 + " and " + date2 + " is : " + vincome);
		 }
		 System.out.println("Do you want to return to the main menu(Y/N)");
		 response = scan.next();
	break;
	
	 case 13:
		 System.out.println("Report of all vehicles");
		    for(i = 0; i < report.size(); i ++)
		    {
			  report.get(i).printReport();
			  System.out.println();
		    }
		    System.out.println("Do you want to return to the main menu(Y/N)");
			response = scan.next();
		 break;
		 
	 case 14:
		response = "N";
	 break;
		
	 default:
		System.out.println("Please enter a valid option");
		System.out.println("Do you want to return to the main menu(Y/N)");
		response = scan.next();
	 break;
   }
}while(response.equalsIgnoreCase("Y"));
   
   try
   {
	   ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(vehFile));       
		out.writeObject(vehs);   
		System.out.println("Vehicle objects stored into file successfully");
		out.close();  
   }
   catch(FileNotFoundException fnfe)
   {
		System.out.println("File not found, Please check the filename and path specified and try again...Exiting");
		System.out.println(fnfe);
		System.exit(1);
   }
   catch(IOException ioe)
   {
		System.out.println("Problem reading from file, Please check and retry...Exiting");
		System.out.println(ioe);
		System.exit(1);
   }
		
	try
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cusFile));       
		out.writeObject(cus);   
		System.out.println("Customer objects stored into file successfully");
		out.close();  
	}
	catch(FileNotFoundException fnfe)
	{
		System.out.println("File not found, Please check the filename and path specified and try again...Exiting");
		System.out.println(fnfe);
		System.exit(1);
	}
	catch(IOException ioe)
	{
		System.out.println("Problem reading from file, Please check and retry...Exiting");
		System.out.println(ioe);
		System.exit(1);
	}
	
	try
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(reportFile));       
		out.writeObject(report);   
		System.out.println("Report objects stored into file successfully");
		out.close();  
	}
	catch(FileNotFoundException fnfe)
	{
		System.out.println("File not found");
		System.out.println(fnfe);
		System.exit(1);
	}
	catch(IOException ioe)
	{
		System.out.println("Problem writing to file report");
		System.out.println(ioe);
		System.exit(1);
	}
}
	
}


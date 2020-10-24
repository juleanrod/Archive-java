import java.util.Scanner;

public class ParkingCarSimulator 
{

	   class ParkedCar
	   {
	       String make, model, color, licenseNumber;
	       int minutesParked;

	       public ParkedCar(String make, String model, String color, String licensenumber, int minutes) {

	           this.make = make;
	           this.model = model;
	           this.color = color;
	           this.licenseNumber = licensenumber;
	           this.minutesParked = minutes;
	       }

	       public String getMake() 
	       {
	           return make;
	       }
	       public String getModel() 
	       {
	           return model;
	       }

	       public String getColor() 
	       {
	           return color;
	       }
	       public String getLicenseNumber() 
	       {
	           return licenseNumber;
	       }

	       public int getMinutesParked() 
	       {
	           return minutesParked;
	       }
	   }

	   class ParkingMeter
	   {
	       int minutes;
	       public ParkingMeter(int minutes) 
	       {
	           this.minutes = minutes;
	       }

	       public int getMinutes() 
	       {
	           return minutes;
	       }
	   }

	   class ParkingTicket
	   {

	       final int finePerHour = 25;
	       final int additionalHourFine = 10;

	       ParkedCar citedCar;

	       PoliceOfficer inspectionOfficer;

	       double fineAmount;

	       public ParkingTicket(ParkedCar car, PoliceOfficer officer) 
	       {

	           this.citedCar = car;
	           this.inspectionOfficer = officer;
	           fineAmount = 0;

	       }

	       public void calculateFine(int minsPurchased)
	       {

	           int hours = (citedCar.getMinutesParked() - minsPurchased)/60;

	           if((citedCar.getMinutesParked() - minsPurchased)%60 >0)

	               hours++;

	           fineAmount = finePerHour + (hours-1)*additionalHourFine;

	       }

	       public void generateParkingTicket()

	       {
	    	  System.out.println("Car parking time has expired.\n");
	    	  System.out.println("Ticket data:\n");
	           System.out.println("Make: " + citedCar.getMake()+"\n");
	           System.out.println("Model: " +  citedCar.getModel()+"\n");
	           System.out.println("Color: " + citedCar.getColor()+"\n");
	           System.out.println("Liscense Number: " + citedCar.getLicenseNumber()+"\n");
	           System.out.println("Officer Name: " + inspectionOfficer.getName()+"\n");
	           System.out.println("Badge Number: " + inspectionOfficer.getBadgeNumber()+"\n");
	           System.out.println("Fine: " + fineAmount);
	       }

	   }

	   class PoliceOfficer
	   {
	       String name;
	       int badgeNumber;
	       ParkingTicket ticket;
	       public PoliceOfficer(String name, int number) {

	           this.name = name;

	           this.badgeNumber = number;

	       }

	       public String getName() 
	       {
	           return name;
	       }

	       public int getBadgeNumber() 
	       {

	           return badgeNumber;

	       }

	       public void examine(ParkedCar car, ParkingMeter meter)
	       {

	           if(car.getMinutesParked() > meter.getMinutes())
	        	   
	        {

	               ticket = new ParkingTicket(car, this);
	               ticket.calculateFine(meter.getMinutes());
	               ticket.generateParkingTicket();

	         }
	           else
	           {
	        	   System.out.println("The car parking minutes are valid");
	           }
	       }

	   }

	   public static void main(String[] args) 
	   {
	       ParkingCarSimulator simulator = new ParkingCarSimulator();

	       Scanner keyboard = new Scanner(System.in);

	       String make, model, license, color, officerName;
	       int minsParked, minsPurchased, badgeNumber;

	       System.out.println("Enter the officer's name");
	       officerName = keyboard.nextLine();

	       System.out.println("Enter officer's badge number");
	       badgeNumber = keyboard.nextInt();
	       
	       keyboard.nextLine();

	       System.out.println("Enter the car's make");
	       make = keyboard.nextLine();

	       System.out.prntln("Enter the car's model");
	       model = keyboard.nextLine();

	       System.out.println("Enter the car's color");
	       color = keyboard.nextLine();

	       System.out.println("Enter the car's liscense number");
	       license = keyboard.nextLine();

	       System.out.println("Enter Minutes on car");
	       minsParked = keyboard.nextInt();
	       
	       if (minsParked <= 0)
	       {
	    	   System.out.println("Invalid Entry. Please try again.");
	    	   minsParked = keyboard.nextInt();
	       }

	       System.out.println("Enter the number of minutes purchased on the meter ");
	       minsPurchased = keyboard.nextInt();
	       
	       if (minsPurchased <= 0)
	       {
	    	   System.out.println("Invalid Entry. Please try again.");
	    	   minsPurchased = keyboard.nextInt();
	       }
	       keyboard.close();
	       ParkingCarSimulator.PoliceOfficer officer = simulator.new PoliceOfficer(officerName, badgeNumber);

	       ParkingCarSimulator.ParkingMeter meter = simulator.new ParkingMeter(minsPurchased);

	       ParkingCarSimulator.ParkedCar parkedCar = simulator.new ParkedCar(make, model, color, license, minsParked);

	       officer.examine(parkedCar, meter);

	   }

	}i

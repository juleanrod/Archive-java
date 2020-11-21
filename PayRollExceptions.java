import java.util.*;

class EmptyNameException extends Exception{
	public EmptyNameException() {
		super("Error: Empty name is not allowed");
	}
}
class InvalidEmployeeIdException extends Exception{
	public InvalidEmployeeIdException() {
		super("Error: Numericals in ID must be between 0-9 and letters must be between A-M");
	}
}
class InvalidHoursException extends Exception{
	public InvalidHoursException() {
		super("Error: Hours Cannot be negative or greater than 84");
	}
}
class InvalidPayRateException extends Exception{
	public InvalidPayRateException () {
		super("Error: Hourly Rate Cannot be negative or greater than 25");
	}
}

public class PayRollExceptions {

	public static void main(String[] args) throws EmptyNameException, InvalidEmployeeIdException, InvalidHoursException, InvalidPayRateException{
		Scanner keyboard = new Scanner(System.in);
		String name;
		String employeeId;
		int hours;
		double payRate;
		try {
		System.out.print("Enter the employee's name: ");
		name = keyboard.nextLine();
		System.out.print("Enter employee number, (ex. 999-M): ");
		employeeId = keyboard.nextLine();
		
		System.out.print("Enter the employee's hourly rate: ");
		payRate = keyboard.nextDouble();
		
		System.out.print("Enter the number of hours the employee has worked: ");
		hours = keyboard.nextInt();
		keyboard.nextLine();
		keyboard.close();
		Payroll employee = new Payroll(name, employeeId, payRate, hours);
		System.out.println(employee);
		} catch(EmptyNameException | InvalidEmployeeIdException | InvalidHoursException | InvalidPayRateException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	System.exit(0);
	}
}
class EmployeePayRoll{
	private String name;
	private String employeeNumber;
	
	public EmployeePayRoll(String n, String num)throws InvalidEmployeeIdException, EmptyNameException {
		if(n.isEmpty())
			throw new EmptyNameException();
		else
			name = n;
		setEmployeeId(num);
	}
	public EmployeePayRoll() {
		name = "";
		employeeNumber = "";
	}
	public void setName(String n) throws EmptyNameException {
		if(n.isEmpty())
			throw new EmptyNameException();
		else {
			name = n;
		}
	}
	public void setEmployeeId(String e)throws InvalidEmployeeIdException {
		if (isValidEmpNum(e))
			employeeNumber = e;
		else 
			throw new InvalidEmployeeIdException();
	}
	public String getName() {
		return name;
	}
	public String getEmployeeId() {
		return employeeNumber;
	}
	private boolean isValidEmpNum(String e) {
		boolean status = true;
		if (e.length() != 5)
			status = false;
		else {
			if ((!Character.isDigit(e.charAt(0))) ||
				(!Character.isDigit(e.charAt(1))) ||
				(!Character.isDigit(e.charAt(2))) ||
				(e.charAt(3) != '-')                ||
				(Character.toUpperCase(e.charAt(4)) < 'A') ||
				(Character.toUpperCase(e.charAt(4)) > 'M'))
					status = false;
		}
		return status;
	}
	public String toString() {
		String str = "Employees name: " + name + "\nID: ";
		if (employeeNumber == "")
			str += "INVALID EMPLOYEE NUMBER";
		else {
			str += employeeNumber;
		}
		return str;
	}
}

class Payroll extends EmployeePayRoll
{
	private int hours;
	private double payRate;
	
	public Payroll() {
		super();
		hours = 0;
		payRate = 0.0;
	};
	public Payroll(String n, String num, double p, int h) throws EmptyNameException, InvalidEmployeeIdException, InvalidHoursException, 
	                                                                     InvalidPayRateException
	{
		super(n, num);
		if (p < 0 || p > 25) 
			throw new InvalidPayRateException();
		else 
			payRate = p;
		if (h < 0 || h > 84)
			throw new InvalidHoursException();
		else {
			hours = h;
		};
		
	}
	public void setPayRate(double p)throws InvalidPayRateException {
		if (p < 0 || p > 25) 
			throw new InvalidPayRateException();
		else 
			payRate = p;
	}	
	public void setHours(int h) throws InvalidHoursException {
		if (h < 0 || h > 84)
			throw new InvalidHoursException();
		else {
			hours = h;
		}
	}
	public double getPayRate() {
		return payRate;
	}
	public double getHours() {
		return hours;
	}
	public double getGrossPay() {
		returnhours*payRate;
	}
	@Override
	public String toString() {
		String str = super.toString();
		str += String.format("\nHourly Rate: $%,.1f", payRate);
		str += "\nHours: " + hours + " hrs ";
		str+= String.format("\nGross Pay: $%,.1f", hours*payRate);
		return str;
	}
} 

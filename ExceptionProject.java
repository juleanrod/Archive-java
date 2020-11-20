import java.util.*;

class InvalidEmployeeNumber extends Exception{
	public InvalidEmployeeNumber() {
		super("ERROR: InvalidEmployeeNumber");
	}
}
class InvalidShift extends Exception{
	public InvalidShift() {
		super("ERROR: InvalidShift");
	}
}
class InvalidPayRate extends Exception{
	public InvalidPayRate() {
		super("ERROR: InvalidPayRate");
	}
}
class Employee{
	private String name;
	private String employeeNumber;
	private String hireDate;
	
	public Employee(String n, String num, String date)throws InvalidEmployeeNumber {
		name = n;
		setEmployeeNumber(num);
		hireDate = date;
	}
	public Employee() {
		name = "";
		employeeNumber = "";
		hireDate = "";
	}
	public void setName(String n) {
		name = n;
	}
	public void setEmployeeNumber(String e)throws InvalidEmployeeNumber {
		if (isValidEmpNum(e))
			employeeNumber = e;
		else 
			throw new InvalidEmployeeNumber();
	}
	public void setHireDate(String h) {
		hireDate = h;
	}
	public String getName() {
		return name;
	}
	public String getEmpNum() {
		return employeeNumber;
	}
	public String getHireDate() {
		return hireDate;
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
		String str = "Employees name: " + name + "\nEmployee Number: ";
		if (employeeNumber == "")
			str += "INVALID EMPLOYEE NUMBER";
		else {
			str += employeeNumber;
			str += ("\nHire Date: " + hireDate);
		}
		return str;
	}
}
class ProductionWorker extends Employee{
	private int shift;
	private double payRate;
	public static final int DAY_SHIFT = 1;
	public static final int NIGHT_SHIFT = 2;
	
	public ProductionWorker(String n, String num, String date, int sh, double rate) throws InvalidShift, InvalidPayRate, InvalidEmployeeNumber{
		super(n, num, date);
		shift = sh;
		payRate = rate;
	}
	public ProductionWorker() {
		super();
		shift = DAY_SHIFT;
		payRate = 0.0;
	}
	public void setPayRate(double p)throws InvalidPayRate {
		if (p < 0) 
			throw new InvalidPayRate();
		else 
			payRate = p;
	}
	public void setShift(int sh) throws InvalidShift {
		if (sh != DAY_SHIFT && sh != NIGHT_SHIFT)
			throw new InvalidShift();
		else {
			shift = sh;
		}
	}
	public int getShift() {
		return shift;
	}
	public double getPayRate() {
		return payRate;
	}
	@Override
	public String toString() {
		String str = super.toString();
		str += "\nShift: ";
		if(shift == DAY_SHIFT)
			str += "1";
		else if (shift == NIGHT_SHIFT) 
			str += "2";
		else
			str += "INVALID SHIFT NUMBER";
		
		str+= String.format("\nHourly Pay Rate: $%,.1f", payRate);
		return str;
	}

}

public class ExceptionProject {
	public static void main(String[] args) {
		String nameString;
		String numString;
		String hireString;
		int shiftInt;
		double payRateDouble;
		
		Scanner input =  new Scanner(System.in);
		
		try {
			System.out.print("Enter employee name: ");
			nameString = input.nextLine();
			System.out.print("Enter employee number, (ex. 999-M): ");
			numString = input.nextLine();
			System.out.print("Enter employee hire date: ");
			hireString = input.nextLine();
			System.out.print("Employee shift, (1 = day or 2 = night): ");
			shiftInt = input.nextInt();
			input.nextLine();
			System.out.print("Enter employee hourly pay rate: ");
			payRateDouble = input.nextDouble();
			input.close();
			ProductionWorker pw  = new ProductionWorker(nameString, numString, hireString, shiftInt, payRateDouble);
			pw.setShift(shiftInt);
			pw.setPayRate(payRateDouble);
			System.out.println("Employee Details");
			System.out.println(pw);
		} catch (InvalidEmployeeNumber | InvalidShift | InvalidPayRate e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}

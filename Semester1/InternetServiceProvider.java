import java.util.Scanner;

public class InternetServiceProvider 
{
	public static void main(String[] args) 
	{
		String bundle_type;
		double rate_packA = 9.95;
		double rate_packB = 13.95;
		double rate_packC = 19.95;
		int hours_used, extra_charge, extra_hours; 
		double total_charge; 
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the letter of the package purchased: ");
		bundle_type = keyboard.nextLine();
		System.out.println("Enter the number of hours that were used: ");
		hours_used =  keyboard.nextInt();
		
		switch (bundle_type) 
		{
			case "A":
			case "a":
				if (hours_used <= 10)
				{
					System.out.printf("Your tatal charges are $%.2f\n", rate_packA);
					break;
				}
				else
				{
					extra_hours = hours_used - 10;
					extra_charge = extra_hours * 2;
					total_charge = rate_packA + extra_charge;
					System.out.printf("Your total charges are $%.2f", total_charge);
					break;
				}
			case "B":
			case "b":
				if (hours_used <= 20)
				{
					System.out.printf("Your total charges are $%.2f", rate_packB);
					break;
				}
				else
				{
					extra_hours = (hours_used - 20);
					extra_charge = extra_hours * 2;
					total_charge = rate_packB + extra_charge;
					System.out.printf("Your total charges are $%.2f", total_charge);
					break;
				}
			case "C":
			case "c":
				System.out.printf("Your total charges are $%.2f", rate_packC);
				break;
			default:
				System.out.println("That package input was not an option.");
				break;
		}
		System.exit(0);
		
	}

}


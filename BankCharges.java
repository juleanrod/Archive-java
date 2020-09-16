import java.util.Scanner;

public class BankCharges 
{

	public static void main(String[] args) 
	{
		int input;
		double total_fee, extra_fee;
		double base_fee = 10.00;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the number of checks written this month:");
		input = keyboard.nextInt();
		
		if (input < 20) 
		{
			extra_fee = input*.10;
			total_fee = base_fee + extra_fee;
			System.out.printf("This month's service fees are $ %.1f", total_fee);
		}
		else
		{
			if (input >= 20 && input <= 39) 
			{
				extra_fee = input*.08;
				total_fee = base_fee + extra_fee;
				System.out.printf("This month's service fees are $ %.1f", total_fee);
			}
			else 
			{
				if (input >= 40 && input <= 59) 
				{
					extra_fee = input*.06;
					total_fee = base_fee + extra_fee;
					System.out.printf("This month's service fees are $ %.1f", total_fee);
				}
				else 
				{
					if (input >= 60) 
					{
						extra_fee = input*.04;
						total_fee = base_fee + extra_fee;
						System.out.printf("This month's service fees are $ %.1f", total_fee);
					}
				}
			}
		 }
		System.exit(0);
	}
}



import java.util.Random;

public class DieDemo {

	public static void main(String[] args)
	{
		Random rand = new Random();
		int userDie, computerDie;
		int computerScore = 0; 
		int userScore = 0;
		
		for (int roll = 1; roll <=10; roll++) 
		{			
			userDie = rand.nextInt(6) + 1;
			computerDie = rand.nextInt(6) + 1;
			System.out.println("Roll #:"+ roll);
				for (int x = 1; x <=1; x++) 
				{
					
					System.out.printf("The computer rolled a %d and the user rolled a %d\n", computerDie, userDie);
					
					if (computerDie > userDie) {
						computerScore = computerScore +1;
					} else if (computerDie < userDie) {
						userScore = userScore + 1;
					}else {
						System.out.println("Tie!\n\n");
					}
				}
				if (computerDie < userDie) {
				System.out.println("The user won this round!\n\n");	
				} else if (computerDie > userDie) {
					System.out.println("The computer won this round!\n\n");
				}
		}
		System.out.println("The user won a grand total of "+ userScore +" times");
		System.out.println("The computer won a grand total of "+computerScore+" times");
		System.out.println("");
		if (userScore > computerScore) {
			System.out.println("The user is the grand winner!!!" );
		}else {
			System.out.println("The computer is the grand winner!!!");
		}
	}		
}

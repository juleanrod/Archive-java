import java.util.Scanner;

public class StringManipulatorScanner 
{
	public static void main(String[] args) 
	{	
		String favoriteCity; // To hold user's favorite city 
		
		// Create a Scanner object to read input.
	    Scanner keyboard = new Scanner(System.in);
	      
	    // Get the user's favorite city.
	    System.out.print("Enter the name of your favorite city: ");
	    favoriteCity = keyboard.nextLine();
	    
	    String upper = favoriteCity.toUpperCase();
	    String lower = favoriteCity.toLowerCase();
	    int stringSize = favoriteCity.length();
	    char letter = favoriteCity.charAt(0);
	    
	    System.out.println("You Entered: " + favoriteCity);
	    System.out.println("Uppercase: " + upper);
	    System.out.println("Lowercase: " + lower);
	    System.out.println("Number of Characters: " + stringSize);
	    System.out.println("First Character: " + letter);
	    
	}

}

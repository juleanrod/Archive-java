import java.util.Scanner;

public class BarChart {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		int minNumber = 0;
		int numOfStores = 5;
		int sales1, sales2, sales3, sales4, sales5;
		int salesPercent1, salesPercent2, salesPercent3, salesPercent4, salesPercent5;
		Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter sales for store 1:");
        sales1 = keyboard.nextInt();
        salesPercent1 = (sales1 / 100);

        System.out.println("Enter sales for store 2:");
        sales2 = keyboard.nextInt();
        salesPercent2 = (sales2 / 100);
        
        System.out.println("Enter sales for store 3:");
        sales3 = keyboard.nextInt();
        salesPercent3 = (sales3 / 100);
        
        System.out.println("Enter sales for store 4:");
        sales4 = keyboard.nextInt();
        salesPercent4 = (sales4 / 100);

        System.out.println("Enter sales for store 5:");
        sales5 = keyboard.nextInt();
        salesPercent5 = (sales5 / 100);
        
        System.out.println("\nSALES BAR CHART");
        
        System.out.print("Store 1: "); 
        for (int count = minNumber; count < salesPercent1; count ++) 
        {
        	System.out.print("*");   	
        }
        System.out.println();
        System.out.print("Store 2: "); 
        for (int count = minNumber; count < salesPercent2; count ++) 
        {
        	System.out.print("*");
        }
        System.out.println();
        System.out.print("Store 3: "); 
        for (int count = minNumber; count < salesPercent3; count ++) 
        {
        	System.out.print("*");
        	
        }
        System.out.println();
        System.out.print("Store 4: "); 
        for (int count = minNumber; count < salesPercent4; count ++) 
        {
        	System.out.print("*");
        	
        	
        }
        System.out.println();
        System.out.print("Store 5: "); 
        for (int count = minNumber; count < salesPercent5; count ++) 
        {
        	System.out.print("*");
        }
        System.exit(0);
	}
}


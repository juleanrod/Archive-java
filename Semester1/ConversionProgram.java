import java.util.Scanner;
public class ConversionProgram 
{
    public static void main(String[] args) 
    {
        int distance_in_meters;
        int option;
        
        Scanner input = new Scanner(System.in);
        System.out.println("Ener your distance in meters:");
        distance_in_meters = input.nextInt();
        
        
        do {
            menu();
            option = input.nextInt();
            
            while (option < 1 || option > 5)
            {
                System.out.println("Invalid choice, Reenter a valid choice");
                menu();
                option = input.nextInt();
            }
            switch (option) 
            {
                case 1:
                    showKilometers(distance_in_meters);
                    break;
                case 2:
                    showInches(distance_in_meters);
                    break;
                case 3:
                    showFeet(distance_in_meters);
                    break;
                case 4:
                   System.out.println("Thank you, the program will now end");               
             }
            } while (option != 4);
       
    }
    public static void showKilometers(int num){   
        double kilometers;
        kilometers = num * 0.001;
        System.out.println(num + " meters is " + kilometers + " kilometers.\n");
        }
    public static void showInches(int num){
        double inches;
        inches = num * 39.37;
        System.out.printf("%d meters is %.1f inches.\n\n", num, inches);
    }
    public static void showFeet(int num){
        double feet;
        feet = num * 3.281;
        System.out.printf("%d meters is %.1f feet.\n\n", num, feet);
    }
    public static void menu(){
        System.out.println("1. Convert to kilometers");
        System.out.println("2. Convert to inches");
        System.out.println("3. Convert to feet");
        System.out.println("4. Quit the program");
    }
}t

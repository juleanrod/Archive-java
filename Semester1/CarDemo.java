
class Car
{
   // fields
   private String make;      // make of the car
   private int speed;          // cars current speed
   private int yearModel;   // cars year model
   // The constructor accepts the car's year model and make as arguments
   public Car(int newYearModel, String newMake)
   {
       yearModel = newYearModel;
       make = newMake;  
       speed = 0;
   }

   // The getYearModel accessor method returns the value stored in the
   // object's yearModel field.
   public int getYearModel()
   {
       return yearModel;
   }

   // The getMake accessor method returns the value stored in the object's
   // make field.
   public String getMake()
   {
       return make;
   }

   // The getSpeed accessor method returns the value stored in the object's
   // speed field.
   public int getSpeed()
   {
       return speed;
   }

   // The accelerate method adds 5 to the speed field each time it is called.
   public void accelerate()
   {
       speed = speed + 5;
   }

   // The brake method subtracts 5 from the speed field each time it is called.
   public void brake()
   {
       speed = speed - 5;
   }
} 
// end of Car class


// CarDemo class implementation
public class CarDemo
{
   // start main method
   public static void main(String[] args)
   {
       // create a Car object
       Car stang = new Car(2005, "Mustang");
      
       // display the car's model and make
      
     
        System.out.println("We will now call the accelerate function 5 times");
      
       // call the accelerate method five times and
       // get the current speed of the car and display it each time
      
       for(int i = 0; i < 5; i++)
       {
           stang.accelerate();
           System.out.println("The current speed is: " + stang.getSpeed());
       }
      
      System.out.println();
      System.out.println("We will now call the brake function 5 times");
       // call the brake method five times and
       // get the current speed of the car and display it each time
       
       for(int i = 0; i < 5; i++)
       {
           stang.brake();
           System.out.println("The current speed is: " + stang.getSpeed());
       }
      System.out.println();
      System.out.println("The program will end now.");
   } // end of main method
} // end of CarDemo class

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UppercaseFileConverter 
{
   
 public static void main(String[] args)

 {  // Declare the file

 String filename;

 // Ask from the user for the names of the file


     Scanner keyboard = new Scanner(System.in);

     System.out.print("Enter the name of the file to be read: ");

     filename = keyboard.nextLine();

     // to find any errors then we try{}
     
     try {

     // Open the input file for reading

         File readfile = new File(filename);

         Scanner inFile = new Scanner(readfile);
         
         System.out.println("Here is the file converted into Uppercase.");
         // while loop to read from the file
         while (inFile.hasNext())

         { // reading from the file

            String str = inFile.nextLine();

            System.out.println(str.toUpperCase());

         }
         
        // outgoing message 
         System.out.println("Files read, converted and then closed.");
        
         // close the input file
         inFile.close();
       
     }
     // error message if the wrong file is input by the user.
     catch (FileNotFoundException e) 
     {
         System.out.println("The file " +  filename + " does not exist or could not be opened.");// CHANGE FILE
     }
   System.exit (0);
 }

}

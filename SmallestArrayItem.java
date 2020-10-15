import java.util.Scanner;
public class SmallestArrayItem 
{


    public static void main(String[] arg) 
    {
        Scanner keyboard = new Scanner(System.in);
        int array1[] = new int[5];
        int array2[] = new int[5];
        
        System.out.println("Please enter 5 values for array 1");
        for (int i = 0; i < array1.length; i++) {
            array1[i] = keyboard.nextInt();
        }
        
        System.out.println("Please enter 5 values for array 2");
        for (int i = 0; i < array2.length; i++)
        {
            array2[i] = keyboard.nextInt();
        }

        selectionSort(array1);
        selectionSort(array2);
  
        int i = 0, k= 0;
        while (i < array1.length && k < array2.length) 
        {
            if (array1[i] == array2[k]) 
            {
                System.out.println("The Smallest match in the array is : " + array1[i]);
                return;
            }
            else if (array1[i] < array2[k]) 
            {
                i++;
            } 
            else 
            {
                k++;
            }
        }
        System.out.println("There is no smallest matching integer!");
    }


    public static void selectionSort(int[] array) 
    {
        int startScan, index, minIndex, minValue;

        for (startScan = 0; startScan < (array.length - 1); startScan++) 
        {
            minIndex = startScan;
            minValue = array[startScan];
            for (index = startScan + 1; index < array.length; index++) 
            {
                if (array[index] < minValue) 
                {
                    minValue = array[index];
                    minIndex = index;
                }
            }
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
    }
}

import java.util.Scanner;

public class Multiply {

    static int result = 0;

    public static void operation(int x, int y) {

        if (x > 0){

            result += y;
            operation(x-1,y);
        }
    }

    public static void main (String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int x = in.nextInt();

        System.out.print("Enter another number (to multiply): ");
        int y = in.nextInt();


        Multiply.operation(x, y);

        System.out.printf("%d times %d is %d", x,y, Multiply.result);


    }
}

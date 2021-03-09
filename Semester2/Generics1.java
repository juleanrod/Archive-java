import java.util.*;
class MyList<T extends Comparable<T>> implements Comparable<MyList>{

    // Create a field of ArrayList T
    private ArrayList<T> myList = new ArrayList<T>();


    public void add(T n) {
        myList.add(n);
    }


    public T largest() {
        T largest = myList.get(0);
        for(int i = 1; i < myList.size(); i++) {
            if (!(myList.get(i).compareTo(largest)<0)) {
                largest = myList.get(i);
            }
        }
        return largest;
    }

    public T smallest() {
        T smallest = myList.get(0);
        for(int i = 1; i < myList.size(); i++) {
            if (myList.get(i).compareTo(smallest)<0) {
                smallest = myList.get(i);
            }
        }
        return smallest;
    }


    @Override
    public int compareTo(MyList o) {
        return 0;
    }

}
public class Generics1 {

    public static void main(String[] args) {

        MyList<Integer> intList = new MyList<>();
        MyList<Double> doubleList = new MyList<>();

        intList.add(2);
        intList.add(5);
        intList.add(-13);
        intList.add(11);
        intList.add(12);

        doubleList.add(27.3);
        doubleList.add(5.7);
        doubleList.add(-23.9);
        doubleList.add(1.11);
        doubleList.add(5.12);


        System.out.println("PART 1");
        System.out.printf("\nThe Integer largest:\n%d\nThe Integer smallest:\n%d\nThe Double largest:\n%.1f\nThe Double smallest:\n%.1f\n\n"
                        , intList.largest(), intList.smallest(), doubleList.largest(), doubleList.smallest());

        MyList<String> strList = new MyList<>();
        MyList<Integer> secondIntList = new MyList<>();
        System.out.println("PART 2");


        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        secondIntList.add(in.nextInt());
        in.nextLine();
        System.out.println("Please another number: ");
        secondIntList.add(in.nextInt());
        in.nextLine();
        System.out.println("Please enter your name: ");
        strList.add(in.nextLine());
        System.out.println("Please enter your City: ");
        strList.add(in.nextLine());
        System.out.println("First number is " + secondIntList.smallest());
        System.out.println("First number is " + secondIntList.largest());
        System.out.println("Name is: " + strList.smallest());
        System.out.println("City is: " + strList.largest());

    }
}


import java.util.*;

class HighestLowest <T extends Comparable<? super T>>{
    private final ArrayList<T> list;

    public HighestLowest(ArrayList<T> array) {
        this.list = array;
    }
    public T largest() {
        T largest = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            if (!(list.get(i).compareTo(largest)<0)) {
                largest = list.get(i);
            }
        }
        return largest;
    }

    public T smallest() {
        T smallest = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(smallest)<0) {
                smallest = list.get(i);
            }
        }
        return smallest;
    }


}
class Employee implements Comparable<Employee>{
    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Employee o) {
        if (age == o.age){
            return 0;
        } else if (age < o.age){
            return -1;
        } else
            return 1;
    }

    @Override
    public String toString() {

        return (name);

    }
}
class City implements Comparable<City>{
    private final String name;
    private final int population;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    @Override
    public int compareTo(City o) {
        if (population == o.population){
            return 0;
        } else if (population < o.population){
            return -1;
        } else
            return 1;
    }
    @Override
    public String toString() {

        return (name);

    }
}

public class Generics2 {
    public static void main(String[] args) {
        ArrayList<Employee> employeeoArray = new ArrayList<>();
        ArrayList<City> cityoArray = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        for (int i = 1; i < 4 ; i++) {

            System.out.printf("\nCollecting Employee%d Information\n", i);
            System.out.printf("\nPlease enter Employee%d name:\n", i);
            String name = in.nextLine();
            System.out.printf("Please enter Employee%d Age:\n", i);
            int age = in.nextInt();
            in.nextLine();


            Employee employee = new Employee(name, age);
            employeeoArray.add(employee);

        }
        for (int i = 1; i < 4 ; i++) {

            System.out.printf("\nCollecting City%d Information\n", i);
            System.out.printf("\nPlease enter City%d name:\n", i);
            String name = in.nextLine();
            System.out.println(new StringBuilder().append("Please enter City").append(i).append(" population:").toString());
            String population = in.nextLine();

            int populationint = Integer.parseInt(population);


            City city = new City(name, populationint);
            cityoArray.add(city);

        }


        HighestLowest<Employee> empHtoL =  new HighestLowest<>(employeeoArray);
        HighestLowest<City> cityHtoL =  new HighestLowest<>(cityoArray);


        System.out.println("The oldest employee is: " + empHtoL.largest());
        System.out.println("The youngest employee: " + empHtoL.smallest());
        System.out.println("The city with highest population is: " + cityHtoL.largest());
        System.out.println("The city with lowest population is: " + cityHtoL.smallest());


    }
}

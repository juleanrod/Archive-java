import java.util.*;

class Employees {

    private int id;
    private String name;

    public Employees(int x, String y) {
        this.id = x;
        this.name = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class EmployeeMap {

    Map<String, Employees> indexMap = new HashMap<>();

    public EmployeeMap()
    { }


    public void add(Employees e)
    {
        indexMap.put(String.valueOf(e.getId()), e);
    }

    public Employees get(String idNumber)
    {
        return indexMap.get(idNumber);
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int inputInt;
        EmployeeMap employeeMap = new EmployeeMap();

        employeeMap.add(new Employees(1,"Eason"));
        employeeMap.add(new Employees(2,"Lisa"));
        employeeMap.add(new Employees(3,"Anna"));
        employeeMap.add(new Employees(4,"Olivia"));
        employeeMap.add(new Employees(5,"Madelyn"));
        employeeMap.add(new Employees(6,"Sam"));
        employeeMap.add(new Employees(7,"Will"));
        employeeMap.add(new Employees(8,"Johnson"));

        do {
            String input;
            System.out.print("Please enter the employee ID or Negative Number to Exit: ");
            input = in.nextLine();
            inputInt = Integer.parseInt(input);

            Employees employeeFound = employeeMap.get(input);

            //If employee was found, display it
            if(inputInt <0)
                System.out.println("Exiting the program!");
            else if (employeeFound == null || !(inputInt > 0))
                System.out.println("This employee id doesn't exist!\n");
            else
               System.out.println("This employee is: " + employeeFound.getName());




        }while((inputInt >= 0));


    }


}


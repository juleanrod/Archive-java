import java.util.*;

class Employe implements Comparable<Employe> {
    private String name;
    private String salary;

    public Employe(){
        this.name = "";
        this.salary = "";
    }
    public Employe(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employe o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Employee Name: " + this.name + " - Hourly Wage: $" + this.salary;

    }
}
/**
 This class implements a queue based
 on linked lists.
 */

class LinkedQueue <T extends Comparable<T>>
{
    private class Node<T>
    {
        T value;
        Node<T> next;
        public Node(T value)
        {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> front = null;
    private Node<T> rear = null;

    /**
     The method enqueue adds a value
     to the queue.
     @param s The value to be added
     to the queue.
     */

    public void enqueue(T s)
    {
        if (rear != null)
        {
            rear.next = new Node(s);
            rear = rear.next;
        }
        else
        {
            rear = new Node(s);
            front = rear;
        }
    }

    /**
     The empty method checks to see if
     the queue is empty.
     @return true if and only if queue
     is empty.
     */

    public boolean empty()
    {

        return front == null;
    }

    /**
     The method peek returns value at the
     front of the queue.
     @return item at front of queue.
     @excepton EmptyQueueException When the
     queue is empty.
     */

    public T peek() {
        if (empty()) {
            System.out.println("Error: No more items in QUEUE\n");
            return null;
        }else
            return front.value;
    }

    /**
     The dequeue method removes and returns
     the item at the front of the queue.
     @return item at front of queue.
     @exception EmptyQueueException When
     the queue is empty.
     */

    public T dequeue() throws EmptyQueueException {
        if (empty())
            throw new EmptyQueueException();
        else
        {
            T value = front.value;
            front = front.next;
            if (front == null) rear = null;
            return value;
        }
    }

    /**
     The toString method concatenates all strings
     in the queue to give a string representation
     of the contents of the queue.
     @return string representation of this queue.
     */

    public String toString()
    {
        StringBuilder sBuilder = new StringBuilder();

        // Walk down the list and append all values
        Node p = front;
        while (p != null)
        {
            sBuilder.append(p.value + "\n");
            p = p.next;
        }
        return sBuilder.toString();
    }


}
class EmptyQueueException extends Exception{
    public EmptyQueueException (){
        super("Error: No more items in QUEUE\n");
    }
}
public class QueueLinkedListDemo {

    static Scanner in = new Scanner(System.in);
    static LinkedQueue<Employe> queue = new LinkedQueue<>();

    public static void main(String[] args) {


        queue.enqueue(new Employe("Alfonso", "15"));
        queue.enqueue(new Employe("Bob", "17"));
        queue.enqueue(new Employe("Carol", "21"));
        queue.enqueue(new Employe("Deborah", "18"));
        queue.enqueue(new Employe("Elaine", "22"));
        int choice;
        try {
            do{
                stateOfQueue();
                menu();
                System.out.println("Please enter selection: ");
                choice = Integer.parseInt(in.nextLine());
                switch(choice){
                    case 1:
                        addEntry();
                        brek;
                    case 2:
                        removeEntry();
                        break;

                }

            } while(choice != 3);
            System.out.println("Thank you, goodbye!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void menu(){

        String x = ("QUEUE MENU\n1: Add Entry (enqueue)\n" +
                "2: Remove Entry (dequeue)\n" +
                "3: QUIT");
        System.out.println(x);
        return;
    }

    public static void addEntry(){
        String name;
        String salary;
        System.out.println("\nEmployee Name:");
        name =  in.nextLine();
        System.out.println("Employee Hourly Wage:");
        salary = in.nextLine();
        System.out.println();
        Employe emp = new Employe(name, salary);
        queue.enqueue(emp);

        return;
    }

    public static void removeEntry() throws EmptyQueueException {
        if (queue.peek() == null){
            return;
        } else
            queue.dequeue();
            System.out.println();
        return;
    }
    public static void stateOfQueue() {
        System.out.println("State of Queue: ");
        System.out.println(queue);
        return;
    }
}

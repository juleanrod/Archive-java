package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

class InvalidId extends Exception{
    public InvalidId () {
        super("Error, Invalid studentID");
    }
}
class InvalidCourseNum extends Exception{
    public InvalidCourseNum() {
        super("Error, Invalid course Number");
    }
}
class StudentFile {
    private static final int RECORD_SIZE = 134;
    private static RandomAccessFile file;
    private static Student s;


    public StudentFile(String fileName) throws FileNotFoundException {
        file = new RandomAccessFile(fileName, "rw");

    }
    public void updateRecord(Student s, long x) throws IOException {

        file = new RandomAccessFile("Student.dat", "rw");
        moveFilePointerStudent(x-1);
        String delimeter = "/";
        String str = s.getStudentFirstName() + delimeter +  s.getStudentLastName()+ delimeter + s.getStudentAddress() + delimeter
                + s.getStudentCity() + delimeter + s.getStudentZipCode()+ delimeter+ s.getStudentState();
        str.toLowerCase();
        if (str.length() > RECORD_SIZE) {
            for (int i = 0; i < 67; i++) {
                file.writeChar(str.charAt(i));
            }
        } else {
            file.writeChars(str);
            for (int i = 0; i < (67 - str.length()); i++)
                file.writeChars(" ");
        }

    }
    public void writeStudentRecord(Student s) throws IOException {


        try {
            file.seek(getByteNum(getNumOfRecords()));
            String delimeter = "/";
            String str = s.getStudentFirstName() + delimeter +  s.getStudentLastName()+ delimeter + s.getStudentAddress() + delimeter
                    + s.getStudentCity() + delimeter + s.getStudentZipCode()+ delimeter+ s.getStudentState();
            str.toLowerCase();
            if (str.length() > RECORD_SIZE) {
                for (int i = 0; i < 67; i++) {
                    file.writeChar(str.charAt(i));
                }
            } else {
                file.writeChars(str);
                for (int i = 0; i < (67 - str.length()); i++)
                    file.writeChars(" ");
            }
        } catch (IOException e) {
            System.out.println("Error, file not found");
        }


    }

    public static Student readFromFile(long x) throws IOException {

        try {
            file = new RandomAccessFile("Student.dat", "r");

            file.seek(getByteNum(x-1));

            char[] charArray = new char[67];
            for (int i = 0; i < 67; i++) {
                charArray[i] = file.readChar();
            }
            String str = new String(charArray);
            str = str.toUpperCase().trim();
            String[] tokens = str.split("/");
            s = new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

            file.close();


        } catch (IOException e) {
            System.out.println("Record Not Found");

        }
        return s;
    }

    private static long getByteNum(long recordNum) {
        return RECORD_SIZE * recordNum;
    }
    public void moveFilePointerStudent(long recordNum) throws  IOException{

        file.seek(getByteNum(recordNum));

    }
    public static long getNumOfRecords() throws IOException{

        return file.length() / RECORD_SIZE;
    }
    public void close() throws IOException
    {
        file.close();
    }

}

class DepartmentFile implements Comparable<DepartmentFile> {
    private static final int RECORD_SIZE = 80;
    private static RandomAccessFile file;
    private static Department d;


    public DepartmentFile(String fileName) throws FileNotFoundException {
        file = new RandomAccessFile(fileName, "rw");

    }
    public void updateRecord(Deartment d, long x) throws IOException {

        file = new RandomAccessFile("Department.dat", "rw");
        moveFilePointerDepartment(x-1);
        String delimeter = "/";
        String str = d.getDepName()+ delimeter;
        str.toLowerCase();
        if (str.length() > RECORD_SIZE) {
            for (int i = 0; i < 40; i++) {
                file.writeChar(str.charAt(i));
            }
        } else {
            file.writeChars(str);
            for (int i = 0; i < (40 - str.length()); i++)
                file.writeChars(" ");
        }

    }
    public void writeDepartmentRecord(Department d) throws IOException {

        try {
            file.seek(getByteNum(getNumOfRecords()));
            String delimeter = "/";
            String str = d.getDepName() + delimeter;
            str.toLowerCase();
            if (str.length() > RECORD_SIZE) {
                for (int k = 0; k < 40; k++) {
                    file.writeChar(str.charAt(k));
                }
            } else {
                file.writeChars(str);
                for (int j = 0; j < (40 - str.length()); j++)
                    file.writeChars(" ");
            }
        } catch (IOException e) {
            System.out.println("Error, file not found");
        }


    }

    public static Department readFromFile(long x) throws IOException {

        try {
            file = new RandomAccessFile("Department.dat", "r");

            file.seek(getByteNum(x-1));

            char[] charArray = new char[40];
            for (int i = 0; i < 40; i++) {
                charArray[i] = file.readChar();
            }
            String str = new String(charArray);
            str = str.toUpperCase().trim();
            String[] tokens = str.split("/");
            d = new Department(tokens[0]);



        } catch (IOException e) {
            System.out.println("Record Not Found");
        }
        return d;
    }
    private static long getByteNum(long recordNum) {
        return RECORD_SIZE * recordNum;
    }
    public void moveFilePointerDepartment(long recordNum) throws  IOException{
        file.seek(getByteNum(recordNum));
    }
    public static long getNumOfRecords() throws  IOException{
        return file.length() / RECORD_SIZE;
    }
    public void close() throws IOException{
        file.close();
    }

    @Override
    public int compareTo(DepartmentFile o) {
        return 0;
    }
}
class InstructorFile implements Comparable<InstructorFile> {
    private static final int RECORD_SIZE = 80;
    private static RandomAccessFile file;
    private static Instructor i;


    public InstructorFile(String fileName) throws FileNotFoundException {
        file = new RandomAccessFile(fileName, "rw");

    }
    public void updateRecord(Instructor i, long x) throws IOException {

        file = new RandomAccessFile("Instructor.dat", "rw");
        moveFilePointerInstructor(x-1);
        String delimeter = "/";
        String str = i.getInstructorName()+ delimeter + i.getDepartmentName() + delimeter;
        str.toLowerCase();
        if (str.length() > RECORD_SIZE) {
            for (int k = 0; k < 40; k++) {
                file.writeChar(str.charAt(k));
            }
        } else {
            file.writeChars(str);
            for (int k = 0; k < (40 - str.length()); k++)
                file.writeChars(" ");
        }

    }
    public void writeInstructorRecord(Instructor i) throws IOException {

        try {
            file.seek(getByteNum(getNumOfRecords()));
            String delimeter = "/";
            String str = i.getInstructorName() + delimeter + i.getDepartmentName() + delimeter;
            str.toLowerCase();
            if (str.length() > RECORD_SIZE) {
                for (int k = 0; k < 40; k++) {
                    file.writeChar(str.charAt(k));
                }
            } else {
                file.writeChars(str);
                for (int j = 0; j < (40 - str.length()); j++)
                    file.writeChars(" ");
            }
        } catch (IOException e) {
            System.out.println("Error, file not found");
        }


    }

    public static Instructor readFromFile(long x) throws IOException {

        try {
            file = new RandomAccessFile("Instructor.dat", "r");

            file.seek(getByteNum(x-1));

            char[] charArray = new char[40];
            for (int i = 0; i < 40; i++) {
                charArray[i] = file.readChar();
            }
            String str = new String(charArray);
            str = str.toUpperCase().trim();
            String[] tokens = str.split("/");
            i = new Instructor(tokens[0], tokens[1]);



        } catch (IOException e) {
            System.out.println("Record Not Found");
        }
        return i;
    }
    private static long getByteNum(long recordNum) {
        return RECORD_SIZE * recordNum;
    }
    public void moveFilePointerInstructor(long recordNum) throws  IOException{
        file.seek(getByteNum(recordNum));
    }
    public static long getNumOfRecords() throws  IOException{
        return file.length() / RECORD_SIZE;
    }
    public void close() throws IOException{
        file.close();
    }

    @Override
    public int compareTo(InstructorFile o) {
        return 0;
    }
}

class EnrollmentFile {
    private static final int RECORD_SIZE = 134;
    private static RandomAccessFile file;
    private static Enrollment e;


    public EnrollmentFile(String fileName) throws FileNotFoundException {
        file = new RandomAccessFile(fileName, "rw");

    }

    public void updateRecord(Enrollment e, long x) throws IOException {

        file = new RandomAccessFile("Enrollment.dat", "rw");
        moveFilePointer(x-1);
        String delimeter = "/";
        String str = e.getStudentid() + delimeter + e.getCourseNum() + delimeter +e.getYear() + delimeter + e.getSemester()+
                delimeter + e.getGrade();
        str.toLowerCase();
        if (str.length() > RECORD_SIZE) {
            for (int i = 0; i < 67; i++) {
                file.writeChar(str.charAt(i));
            }
        } else {
            file.writeChars(str);
            for (int i = 0; i < (67 - str.length()); i++)
                file.writeChars(" ");
        }

    }

    public void writeRecord(Enrollment e) throws IOException {


        file.seek(getByteNum(getNumOfRecords()));
        String delimeter = "/";
        String str = e.getStudentid() + delimeter + e.getCourseNum() + delimeter +e.getYear() + delimeter + e.getSemester()+
                delimeter + e.getGrade();
        str.toLowerCase();
        if (str.length() > RECORD_SIZE) {
            for (int i = 0; i < 67; i++) {
                file.writeChar(str.charAt(i));
            }
        } else {
            file.writeChars(str);
            for (int i = 0; i < (67 - str.length()); i++)
                file.writeChars(" ");
        }

    }

    public static Enrollment readFromFile(long x) throws IOException {

        try {
            file = new RandomAccessFile("Enrollment.dat", "r");

            file.seek(getByteNum(x-1));

            char[] charArray = new char[54];
            for (int i = 0; i < 54; i++) {
                charArray[i] = file.readChar();
            }
            String str = new String(charArray);
            str = str.toUpperCase().trim();
            String[] tokens = str.split("/");
            e = new Enrollment(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            e.setStudentid(tokens[0]);
            e.setCourseNum(tokens[1]);
            e.setYear(tokens[2]);
            e.setSemester(tokens[3]);
            e.setGrade(tokens[4]);

            file.close();


        } catch (IOException e) {
            System.out.println("Record Not Found");

        } catch (InvalidCourseNum invalidCourseNum) {
            invalidCourseNum.printStackTrace();
        } catch (InvalidId invalidId) {
            invalidId.printStackTrace();
        }
        return e;
    }
    private static long getByteNum(long recordNum) {
        return RECORD_SIZE * recordNum;
    }
    public void moveFilePointer(long recordNum) throws IOException{
        file.seek(getByteNum(recordNum));
    }
    public static long getNumOfRecords() throws IOException{

        return file.length() / RECORD_SIZE;
    }
    public void close() throws IOException
    {
        file.close();
    }
}

class Instructor implements Comparable<Instructor>{

    private String instructorName;
    private String departmentName;

    GenericLinkedList<Instructor> instLL = new GenericLinkedList<>();

    public Instructor(String instName, String dName) {
        this.instructorName= instName;
        this.departmentName = dName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName= instructorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String depName) {
        this.departmentName = depName;
    }

    public void add(Instructor o){
        instLL.add(o);
    }

    public GenericLinkedList getList(){
        return instLL;
    }

    @Override
    public int compareTo (Instructor o) {
        int compareResult = this.instructorName.compareTo(o.instructorName);
        if (compareResult < 0){
            return -1;
        } else if (compareResult > 0){
            return 1;
        } else
            return 0;
    }
    public String toString() {
        return getInstructorName();
    }
}

class Department implements Comparable<Department>{
    private String depName;

    GenericLinkedList<Department> depLL = new GenericLinkedList<>();

    public Department(String depName) {
        this.depName = depName;
    }

    public String getDepName() {
        return depName;
    }

    public GenericLinkedList getList(){
        return depLL;
    }
    @Override
    public int compareTo (Department o) {
        int compareResult = this.depName.compareTo(o.depName);
        if (compareResult < 0){
            return -1;
        } else if (compareResult > 0){
            return 1;
        } else
            return 0;
    }

    public String toString() {
        return getDepName();
    }
}
class CourseFile {
    private static final int RECORD_SIZE = 108;
    private static RandomAccessFile file;
    private static Course c;


    public CourseFile(String fileName) throws FileNotFoundException{
        file = new RandomAccessFile(fileName, "rw");

    }

    public void updateRecord(Course c, long x) throws IOException {

        file = new RandomAccessFile("Course.dat", "rw");
        moveFilePointer(x-1);
        String delimeter = "/";
        String str = c.getCourseId() + delimeter + c.getCourseName() + delimeter + c.getInstructorName() + delimeter + c.getDepartment();
        str.toLowerCase();
        if (str.length() > 108) {
            for (int i = 0; i < 54; i++) {
                file.writeChar(str.charAt(i));
            }
        } else {
            file.writeChars(str);
            for (int i = 0; i < (54 - str.length()); i++)
                file.writeChars(" ");
        }

    }

    public void writeRecord(Course c) throws IOException {


        try {
            file.seek(getByteNum(getNumOfRecords()));
            String delimeter = "/";
            String str = c.getCourseId() + delimeter + c.getCourseName() + delimeter + c.getInstructorName() + delimeter + c.getDepartment();
            str.toLowerCase();
            if (str.length() > RECORD_SIZE) {
                for (int i = 0; i < 54; i++) {
                    file.writeChar(str.charAt(i));
                }
            } else {
                file.writeChars(str);
                for (int i = 0; i < (54 - str.length()); i++)
                    file.writeChars(" ");
            }
        } catch (IOException e) {
            System.out.println("Error, file not found");
        }


    }

    public static Course readFromFile(long x) throws IOException {

        try {
            file = new RandomAccessFile("Course.dat", "r");

            file.seek(getByteNum(x-1));

            char[] charArray = new char[54];
            for (int i = 0; i < 54; i++) {
                charArray[i] = file.readChar();
            }
            String str = new String(charArray);
            str = str.toUpperCase().trim();
            String[] tokens = str.split("/");
            c = new Course(tokens[0], tokens[1], tokens[2], tokens[3]);
            file.close();
        } catch (IOException e) {
            System.out.println("Record Not Found");
        }
        return c;
    }
    private static long getByteNum(long recordNum) {
        return RECORD_SIZE * recordNum;
    }
    public void moveFilePointer(long recordNum) throws  IOException{

        file.seek(getByteNum(recordNum));
    }
    public static long getNumOfRecords() throws  IOException{

        return file.length() / RECORD_SIZE;
    }
    public void close() throws IOException
    {
        file.close();
    }
}
class GenericLinkedList <T extends Comparable<T>>{

    //represent the head an tail of the singly linked list
    private Node<T> first = null;
    private Node<T> last = null;
    int count = 0;

    public class Node<T>{
        T value;
        Node<T> next = null;

        public Node(T value){
            this.value = value;
            this.next = null;
        }
    }

    public void add (T element){
        Node<T> newnode = new Node<T>(element);

        if(first==null){
            first = newnode;
            last = newnode;
        }
        else{
            Node<T> lastnode = gotolastnode(first);
            lastnode.next = newnode;
        }
        count++;
    }

    public T get(int pos)
    {
        Node<T> Nodeptr = first;
        int hopcount=0;
        while (hopcount < count && hopcount<pos)
        {   if(Nodeptr!=null)
        {
            Nodeptr = Nodeptr.next;
        }
            hopcount++;
        }
        return  Nodeptr.value;
    }

    private Node<T> gotolastnode(Node<T> nodepointer) {
        if (nodepointer == null) {
            return nodepointer;
        } else {
            if (nodepointer.next == null)
                return nodepointer;
            else
                return gotolastnode(nodepointer.next);

        }
    }

    public void sortList(){
        Node<T> currentNode = first;
        Node<T> index = null;
        T temp;

        if(first == null){
            return;
        }else{
            while (currentNode != null){
                //node index will point to node next to current
                index = currentNode.next;
                while(index != null) {
                    //If current node's value is greater than index's value, swap the values
                    if(currentNode.value.compareTo(index.value) > 0){
                        temp = currentNode.value;
                        currentNode.value = index.value;
                        index.value = temp;
                    }
                    index = index.next;
                }currentNode = currentNode.next;
            }
        }
    }
    public void display(){
        Node<T> current = first;
        if(first == null){
            System.out.println("List is empty");
            return;
        }
        while(current != null){
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
    public int size(){
        Node<T> current = first;
        int size= 0;
        while(current != null){
            current = current.next;
            size++;
        }
        return size;
    }
    /* Given a key, deletes the first
        occurrence of key in
      * linked list */
    public void deleteNode(T key)
    {
        // Store head node
        Node temp = first, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.value == key) {
            first = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (temp != null && temp.value != key) {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null)
            return;

        // Unlink the node from linked list
        prev.next = temp.next;
    }
}

class Course implements Comparable<Course>{

    public static int uniqueCourseNum = 0 ;
    private String courseId;
    private String courseName ;
    private String instructorName ;
    private String department;
    // sample.Course class constructor
    public Course(String courseId, String courseName, String instructorName, String department, int uniqueCourseNum) throws InvalidCourseNum{

        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.department = department;
        this.uniqueCourseNum = uniqueCourseNum;

    }
    public Course(String courseID, String courseName, String instructorName, String department) {
        this.courseId = courseID;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.department = department;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public int getUniqueCourseNumber() {
        return uniqueCourseNum;
    }
    public void setCourseIdString(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getInstructorName() {
        return instructorName;
    }
    public void setIntructorName(String intructorName) {
        this.instructorName = intructorName;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int compareTo(Course o) {
        int compareResult = this.courseName.compareTo(o.courseName);
        if (compareResult < 0){
            return -1;
        } else if (compareResult > 0){
            return 1;
        } else
            return 0;
    }
}

class Enrollment {
    private String studentid;
    private String courseNum;
    private String year, semester, grade;


    public Enrollment(String studentID, String courseNum, String year, String semester, String grade) throws IOException, InvalidId, InvalidCourseNum {
        this.studentid = studentID;
        this.courseNum = courseNum;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentID) {
        this.studentid = studentID;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}

class Student implements Comparable<Student>{

    public static int uniqueStudentID  = 1;
    private String studentFirstName;
    private String studentLastName;
    private String studentAddress;
    private String studentCity;
    private String studentZipCode;
    private String studentState ;



    public Student(String firstName,String lastName, String address, String city, String zipCode, String state) {
        this.studentFirstName = firstName;
        this.studentLastName = lastName;
        this.studentAddress = address;
        this.studentCity = city;
        this.studentZipCode = zipCode;
        this.studentState = state;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentZipCode() {
        return studentZipCode;
    }

    public void setStudentZipCode(String studentZipCode) {
        this.studentZipCode = studentZipCode;
    }

    public int getStudentID() {
        return uniqueStudentID;
    }

    public void setStudentID(int studentID) throws InvalidId {

        if (isValidStudentID(studentID))
            Student.uniqueStudentID = studentID;
        else
            throw new InvalidId();
    }

    private boolean isValidStudentID(int studentID) {
        boolean status = true;
        if (studentID <= 0 || studentID > uniqueStudentID)
            status = false;
        else {
            status = true;
        }
        return status;
    }
    public String getStudentFirstName() {
        return studentFirstName;
    }
    public void setStudentName(String studentName) {
        this.studentFirstName = studentName;
    }
    public String getStudentAddress() {
        return studentAddress;
    }
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
    public String getStudentCity() {
        return studentCity;
    }
    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }
    public String getStudentState() {
        return studentState;
    }
    public void setStudentState(String studentState) {
        this.studentState = studentState;
    }

    @Override
    public int compareTo (Student o) {
        int compareResult = this.studentFirstName.compareTo(o.studentFirstName);
        if (compareResult < 0){
            return -1;
        } else if (compareResult > 0){
            return 1;
        } else
            return 0;
    }


}

public class Main extends Application {

    public static long uniqueSID = 0;
    public static long uniqueCNUM = 0;
    MenuBar menuBar;
    Stage window;
    Scene addInstructorScene, editInstructorScene;
    Scene addDepartmentScene, editDepartmentScene;
    Scene addStudentScene, editStudentScene, viewStudentScene;
    Scene addCourseScene, editCourseScene, viewCourseScene;
    Scene addEnrollmentScene, viewEnrollmentScene;
    Scene byStudentScene;
    Scene reportScene;


    public static void main(String[] args) {
        launch(args);
    }

    public String getChoice(ChoiceBox<String> choiceBox){
        return choiceBox.getValue();
    }


    public static void displayError(String title, String message){

        Stage window =  new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout =  new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

    }
    public String getUniqueSID () throws IOException {
        StudentFile file = new StudentFile("Student.dat");
        uniqueSID = file.getNumOfRecords();
        file.close();
        uniqueSID++;
        String str = String.valueOf(uniqueSID);

        return str;
    }
    public String getUniqueCNUM () throws IOException {
        CourseFile file = new CourseFile("Course.dat");
        uniqueCNUM = file.getNumOfRecords();
        file.close();
        uniqueCNUM++;
        String str = String.valueOf(uniqueCNUM);

        return str;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        //set tittle for the Stage=Window
        window.setTitle("University Enrollment");

        //Create the menu bar
        menuBar = new MenuBar();

        //Create the File menu
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitItem);

        //Register an event handler for the exit item.
        exitItem.setOnAction(e -> {
            window.close();
        });

        //Create the Faculty menu
        Menu facultyMenu = new Menu("Faculty");
        Menu instructor = new Menu("Instructor");
        Menu department = new Menu("Department");
        facultyMenu.getItems().addAll(department, instructor);
        MenuItem addInst = new MenuItem("Add");
        MenuItem editInst= new MenuItem("Edit");
        instructor.getItems().addAll(addInst, editInst);
        MenuItem addDep = new MenuItem("Add");
        MenuItem editDep= new MenuItem("Edit");
        department.getItems().addAll(addDep, editDep);

        //Register event handler for the Faculty menu items

        addInst.setOnAction(e -> {
            try {
                window.setScene(addInstructorScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        addDep.setOnAction(e -> {
            try {
                window.setScene(addDepartmentScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editInst.setOnAction(e -> {
            try {
                window.setScene(editInstructorScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editDep.setOnAction(e -> {
            try {
                window.setScene(editDepartmentScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Create the Student menu
        Menu studentsMenu = new Menu("Students");
        MenuItem addStudent = new MenuItem("Add Student");
        MenuItem editStudent = new MenuItem("Edit Student");
        MenuItem viewStudent = new MenuItem("View Student");

        //Register an event handler for the Student menu items
        addStudent.setOnAction(e -> {
            try {
                window.setScene(addStudentScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editStudent.setOnAction(e -> {
            window.setScene(editStudentScene());
        });
        viewStudent.setOnAction(e -> {
            window.setScene(viewStudentScene());
        });
        studentsMenu.getItems().addAll(addStudent, editStudent, viewStudent);

        //Create Courses menu
        Menu coursesMenu = new Menu("Courses");
        MenuItem addCourse = new MenuItem("Add Course");
        MenuItem editCourse = new MenuItem("Edit Course");
        MenuItem viewCourse = new MenuItem("View Course");

        //Register Event handlers for the Course menu items
        addCourse.setOnAction(e -> {
            try {
                window.setScene(addCourseScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editCourse.setOnAction(e -> {
            try {
                window.setScene(editCourseScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        viewCourse.setOnAction(e -> {
            window.setScene(viewCourseScene());
        });
        coursesMenu.getItems().addAll(addCourse, editCourse, viewCourse);

        //Create Enrollments menu
        Menu enrollmentsMenu = new Menu("Enrollments");
        MenuItem addEnrollments = new MenuItem("Add Enrollment");
        //MenuItem editEnrollments = new MenuItem("Edit Enrollment");
        MenuItem viewEnrollments = new MenuItem("View/Edit Enrollment");
        //Register Event handlers for the Enrollements menu items

        addEnrollments.setOnAction(e -> {

            try {
                window.setScene(addEnrollmentScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        viewEnrollments.setOnAction(e -> {
            window.setScene(viewEnrollmentScene());

        });
        enrollmentsMenu.getItems().addAll(addEnrollments, viewEnrollments);

        //Create Grades menu
        Menu gradesMenu = new Menu("Grades");
        Menu addEditGrades= new Menu("Add/Edit Grades");
        gradesMenu.getItems().add(addEditGrades);
        MenuItem byStudent = new MenuItem("By Student");
        MenuItem byCourse = new MenuItem("By Course");
        //Register Event handlers for the Enrollments menu items
        byStudent.setOnAction(e -> {
            try {
                window.setScene(byStudentScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        addEditGrades.getItems().addAll(byStudent);

        //Create Reports menu
        Menu reportMenu = new Menu("Reports");
        MenuItem generateRep = new MenuItem("Generate Report");
        //Register Event handlers for the Reports menu items
        generateRep.setOnAction(e -> {
            try {
                window.setScene(reportScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        reportMenu.getItems().add(generateRep);

        //Add the File/Students/Courses/Enrollments/Grades/Reports menus to the menuBar
        menuBar.getMenus().addAll(fileMenu, facultyMenu, studentsMenu, coursesMenu, enrollmentsMenu, gradesMenu, reportMenu);


        window.setScene(addDepartmentScene());
        window.setResizable(false);
        window.show();


    }

    public Scene addStudentScene () throws IOException {

        TextField tf1 = new TextField(getUniqueSID());
        tf1.setDisable(true);
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();

        //6:30 pm, 12/13/2020
        //create a labels to display prompt
        Label sceneLabel = new Label("New Student Information");
        Label studentIDLabel = new Label("Student ID");
        Label firstName = new Label("First Name");
        Label lastName = new Label("Last Name");
        Label address = new Label("Address");
        Label city = new Label("City");
        Label state = new Label("State");
        Label zip = new Label("Zip Code");
        ChoiceBox<String> statesList = new ChoiceBox<>();

        statesList.getItems().addAll("AL", "AK", "AZ", "AR", "AS", "CA", "CO", "DE", "DC", "FM", "FL", "GA", "GU",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MS", "MO", "MT", "NE", "NV", "NH",
                "NJ", "NM", "NY", "NC", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "VI",
                "WA", "WV", "WI", "WY");
        statesList.setValue("");
        //int index = statesList.getSelectionModel().getSelectedIndex();


        HBox hbox0 = new HBox(10, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(10, studentIDLabel, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(9, firstName, tf2);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(11, lastName, tf3);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(24, address, tf4);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(46, city, tf5);
        hbox5.setAlignment(Pos.CENTER);
        HBox hbox6 = new HBox(19, zip, tf6);
        hbox6.setAlignment(Pos.CENTER);
        HBox hbox7 = new HBox(40, state, statesList);
        hbox7.setAlignment(Pos.CENTER);


        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox0, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);


        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        Button button = new Button("Create Student");

        VBox vCreateStud = new VBox(button);
        vCreateStud.setPadding(new Insets(30));
        vCreateStud.setAlignment(Pos.CENTER);
        layout.setBottom(vCreateStud);

        button.setOnAction(e-> {

            try {
                if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() ||
                        tf4.getText().isEmpty() || tf5.getText().isEmpty() || tf6.getText().isEmpty() ||
                getChoice(statesList).isEmpty()) {
                    displayError("Invalid", "Missing Information to Create Student");
                } else {
                    tf1.setText(getUniqueSID());
                    StudentFile file = new StudentFile("Student.dat");
                    Student student = new Student(tf2.getText(),tf3.getText(), tf4.getText(), tf5.getText(), tf6.getText(), getChoice(statesList));
                    long l = Long.parseLong(tf1.getText());
                    file.writeStudentRecord(student);
                    tf2.clear();
                    tf3.clear();
                    tf4.clear();
                    tf5.clear();
                    tf6.clear();
                    statesList.setValue("");
                    tf1.setText(getUniqueSID());
                    file.readFromFile(l);
                    displayError("Success!", "Student Profile created!");
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        addStudentScene = new Scene(layout, 450, 400);

        return addStudentScene;
    }

    public Scene editStudentScene () {
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();
        //create a labels to display prompt
        Label sceneLabel = new Label("Edit Student Information");
        Label studentIDLabel = new Label("Student ID");
        Label firstName = new Label("First Name");
        Label lastName = new Label("Last Name");
        Label address = new Label("Address");
        Label city = new Label("City");
        Label state = new Label("State");
        Label zip = new Label("Zip Code");

        ChoiceBox<String> statesList = new ChoiceBox<>();

        statesList.getItems().addAll("AL", "AK", "AZ", "AR", "AS", "CA", "CO", "DE", "DC", "FM", "FL", "GA", "GU",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MS", "MO", "MT", "NE", "NV", "NH",
                "NJ", "NM", "NY", "NC", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "VI",
                "WA", "WV", "WI", "WY");


        HBox hbox0 = new HBox(40, sceneLabel);
        hbox0.setAlignment(Pos.TOP_RIGHT);
        hbox0.setPadding(new Insets(30));
        HBox hbox1 = new HBox(10, studentIDLabel, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(9, firstName, tf2);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(11, lastName, tf3);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(24, address, tf4);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(46, city, tf5);
        hbox5.setAlignment(Pos.CENTER);
        HBox hbox6 = new HBox(19, zip, tf6);
        hbox6.setAlignment(Pos.CENTER);
        HBox hbox7 = new HBox(40, state, statesList);
        hbox7.setAlignment(Pos.CENTER);


        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox0, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.TOP_LEFT);

        Button update = new Button("Update");
        Button reset =new Button("Reset");
        Button searchButton = new Button("Search");
        searchButton.setAlignment(Pos.CENTER);
        update.setOnAction(e-> {
            try {

                if (tf1.getText().isEmpty() ||  (tf1.getText() == "")){

                    displayError("Invalid", "Missing Information to Update Student");

                } else {
                    StudentFile file = new StudentFile("Student.dat");
                    Student student = new Student(tf2.getText(),tf3.getText(), tf4.getText(), tf5.getText(), tf6.getText(), getChoice(statesList));
                    long recordNum = Long.parseLong(tf1.getText());
                    file.updateRecord(student, recordNum);
                    tf1.setDisable(false);
                    tf1.clear();
                    tf2.clear();
                    tf3.clear();
                    tf4.clear();
                    tf5.clear();
                    tf6.clear();

                    file.readFromFile(recordNum);
                    displayError("Updated", "Student Info Updated");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        reset.setOnAction(e-> {
            tf1.clear();
            tf1.setDisable(false);
            tf2.clear();
            tf3.clear();
            tf4.clear();
            tf5.clear();
            tf6.clear();
            statesList.setValue("");

        });
        searchButton.setOnAction(e-> {
            try {

                long l = Long.parseLong(tf1.getText());

                StudentFile file = new StudentFile("Student.dat");
                Student s;
                if (l < 0 || l > file.getNumOfRecords()){
                    displayError("Invalid StudentID", "Record not found");
                    tf1.setDisable(false);
                }
                else {
                    s = file.readFromFile(l);
                    tf1.setDisable(true);
                    tf2.setText(s.getStudentFirstName());
                    tf3.setText(s.getStudentLastName());
                    tf4.setText(s.getStudentAddress());
                    tf5.setText(s.getStudentCity());
                    tf6.setText(s.getStudentZipCode());
                    statesList.setValue(s.getStudentState());
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        });
        VBox column3 = new VBox(20,searchButton,update, reset);
        column3.setPadding(new Insets(20));
        column3.setAlignment(Pos.CENTER_LEFT);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);
        layout.setRight(column3);

        editStudentScene = new Scene(layout, 450, 400);

        return editStudentScene;
    }

    public Scene viewStudentScene () {
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();
        //create a labels to display prompt
        Label sceneLabel = new Label("New Student Information");
        Label studentIDLabel = new Label("Student ID");
        Label firstName = new Label("First Name");
        Label lastName = new Label("Last Name");
        Label address = new Label("Address");
        Label city = new Label("City");
        Label state = new Label("State");
        Label zip = new Label("Zip Code");
        ChoiceBox<String> statesList = new ChoiceBox<>();

        statesList.getItems().addAll("AL", "AK", "AZ", "AR", "AS", "CA", "CO", "DE", "DC", "FM", "FL", "GA", "GU",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MS", "MO", "MT", "NE", "NV", "NH",
                "NJ", "NM", "NY", "NC", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "VI",
                "WA", "WV", "WI", "WY");

        HBox hbox1 = new HBox(10, studentIDLabel, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(9, firstName, tf2);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(11, lastName, tf3);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(24, address, tf4);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(46, city, tf5);
        hbox5.setAlignment(Pos.CENTER);
        HBox hbox6 = new HBox(19, zip, tf6);
        hbox6.setAlignment(Pos.CENTER);
        HBox hbox7 = new HBox(40, state, statesList);
        hbox7.setAlignment(Pos.CENTER);

        Button search_student = new Button("Search Student");
        VBox vCreateStud = new VBox(search_student);
        vCreateStud.setPadding(new Insets(10));
        vCreateStud.setAlignment(Pos.CENTER);

        search_student.setOnAction(e-> {
            try {

                long l = Long.parseLong(tf1.getText());

                StudentFile file = new StudentFile("Student.dat");
                Student s;
                if (l < 0 || l > file.getNumOfRecords()){
                    displayError("Invalid StudentID", "Record not found");
                    tf1.setDisable(false);
                }
                else {
                    s = file.readFromFile(l);
                    tf2.setText(s.getStudentFirstName());
                    tf3.setText(s.getStudentLastName());
                    tf4.setText(s.getStudentAddress());
                    tf5.setText(s.getStudentCity());
                    tf6.setText(s.getStudentZipCode());
                    statesList.setValue(s.getStudentState());
                    tf2.setDisable(true);
                    tf3.setDisable(true);
                    tf4.setDisable(true);
                    tf5.setDisable(true);
                    tf6.setDisable(true);
                    statesList.setDisable(true);


                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });


        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox1, vCreateStud, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        viewStudentScene = new Scene(layout, 450, 400);

        return viewStudentScene;
    }

    public Scene addCourseScene () throws IOException {

        //create a labels to display prompt
        Label sceneLabel = new Label("New Course Information");
        Label courseNum = new Label("Course Number");
        Label courseID = new Label("Course ID");
        Label courseName = new Label("Course Name");
        Label instructorName = new Label("Instructor Name");
        Label departmentName = new Label("Department       ");


        DepartmentFile depFile = new DepartmentFile("Department.dat");

        GenericLinkedList<Department> depLL = new GenericLinkedList<>();
        ArrayList<String> depL = new ArrayList<>();
        Long z = depFile.getNumOfRecords();

        for (long j = 0; j < z; j++ ){
            Department x;
            x = depFile.readFromFile(j+1);
            depLL.add(x);
        }
        for(int i=0; i < depLL.size(); i++){
            String x;
            x = depLL.get(i).toString();
            depL.add(x);

        }

        ChoiceBox<String> departmentsList = new ChoiceBox<>();
        departmentsList.setMinWidth(150);
        for (int s = 0; s < depL.size();s++){

            departmentsList.getItems().add(depL.get(s));
        }

        ChoiceBox<String> instructorsList = new ChoiceBox<>();
        instructorsList.setMinWidth(150);

//*************************** display instructorList dynamically depending on the department selected
        //listen for selection changes
        InstructorFile instFile = new InstructorFile("Instructor.dat");
        Long n = instFile.getNumOfRecords();
        ArrayList<String> values = new ArrayList<>();
        departmentsList.getSelectionModel().selectedItemProperty().addListener((v, oldV, newV)->{

            ArrayList<Instructor> iLL = new ArrayList<>();
            iLL.clear();
            values.clear();
            instructorsList.getItems().setAll();
            for (long j = 0; j < n; j++ ){

                try {
                    Instructor x;
                    x = instFile.readFromFile(j+1);
                    if(newV.equals(x.getDepartmentName().toUpperCase()))
                        iLL.add(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0; i < iLL.size(); i++){
                String x;
                x = iLL.get(i).getInstructorName();
                values.add(x);
            }

            for (int s = 0; s < values.size();s++) {
                instructorsList.getItems().add(values.get(s));
            }
        });

        TextField tf1 = new TextField();
        tf1.setDisable(true);
        tf1.setText(getUniqueCNUM());
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();

        HBox hbox0 = new HBox(10, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(30, courseNum, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(62, courseID, tf2);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(41, courseName, tf3);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(28, departmentName, departmentsList);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(32, instructorName, instructorsList);
        hbox5.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox0, hbox1, hbox2, hbox3, hbox4, hbox5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        Button createCourse = new Button("Create Course");
        createCourse.setOnAction(e-> {
            try {
                if (tf1.getText().isEmpty() || tf2.getText().isBlank() || tf3.getText().isBlank()
                || getChoice(instructorsList).isEmpty() || getChoice(departmentsList).isEmpty()) {
                    displayError("Invalid", "Missing Information to Create Course");
                } else {
                    CourseFile file = new CourseFile("Course.dat");
                    Course course = new Course(tf2.getText(),tf3.getText(), getChoice(instructorsList), getChoice(departmentsList));
                    long l = Long.parseLong(tf1.getText());
                    file.writeRecord(course);
                    tf2.clear();
                    tf3.clear();
                    instructorsList.setValue("");
                    departmentsList.setValue("");
                    tf1.setText(getUniqueCNUM());
                    file.readFromFile(l);
                    displayError("Success!", "Course Profile created!");
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox forButton = new VBox(createCourse);
        forButton.setPadding(new Insets(30));
        forButton.setAlignment(Pos.CENTER);
        layout.setBottom(forButton);

        addCourseScene = new Scene(layout, 450, 400);

        return addCourseScene;
    }

    public Scene editCourseScene () throws IOException {

        //create labels to display prompt
        Label sceneLabel = new Label("Edit Course Information");
        Label courseNum = new Label("Course Number");
        Label courseID = new Label("Course ID");
        Label courseName = new Label("Course Name");
        Label instructorName = new Label("Instructor Name");
        Label departmentName = new Label("Department     ");

        DepartmentFile depFile = new DepartmentFile("Department.dat");
        InstructorFile instFile = new InstructorFile("Instructor.dat");
        GenericLinkedList<Department> depLL = new GenericLinkedList<>();
        ArrayList<String> depL = new ArrayList<>();
        Long z = depFile.getNumOfRecords();

        for (long j = 0; j < z; j++ ){
            Department x;
            x = depFile.readFromFile(j+1);
            depLL.add(x);
        }
        for(int i=0; i < depLL.size(); i++){
            String x;
            x = depLL.get(i).toString();
            depL.add(x);

        }

        ChoiceBox<String> departmentsList = new ChoiceBox<>();
        departmentsList.setMinWidth(150);
        for (int s = 0; s < depL.size();s++){

            departmentsList.getItems().add(depL.get(s));
        }
        ChoiceBox<String> instructorsList = new ChoiceBox<>();
        instructorsList.setMinWidth(150);
//*************************** display instructorList dynamically depending on the department selected
        //listen for selection changes
        Long n = instFile.getNumOfRecords();
        ArrayList<String> values = new ArrayList<>();
        values.clear();
        departmentsList.getSelectionModel().selectedItemProperty().addListener((v, oldV, newV)->{

            ArrayList<Instructor> iLL = new ArrayList<>();
            iLL.clear();
            values.clear();
            instructorsList.getItems().setAll();
            for (long j = 0; j < n; j++ ){

                try {
                    Instructor x;
                    x = instFile.readFromFile(j+1);
                    if(newV.equals(x.getDepartmentName().toUpperCase()))
                        iLL.add(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            for(int i=0; i < iLL.size(); i++){
                String x;
                x = iLL.get(i).getInstructorName();
                values.add(x);
            }


            for (int s = 0; s < values.size();s++) {
                instructorsList.getItems().add(values.get(s));
            }
        });

        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();

        HBox hbox0 = new HBox(10, sceneLabel);
        hbox0.setAlignment(Pos.TOP_RIGHT);
        hbox0.setPadding(new Insets(30));
        HBox hbox1 = new HBox(30, courseNum, tf1);
        hbox1.setAlignment(Pos.CENTER_RIGHT);
        HBox hbox2 = new HBox(62, courseID, tf2);
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        HBox hbox3 = new HBox(41, courseName, tf3);
        hbox3.setAlignment(Pos.CENTER_RIGHT);
        HBox hbox4 = new HBox(32, departmentName, departmentsList);
        hbox4.setAlignment(Pos.CENTER_RIGHT);
        HBox hbox5 = new HBox(28, instructorName, instructorsList);
        hbox5.setAlignment(Pos.CENTER_RIGHT);

        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox0, hbox1, hbox2, hbox3, hbox4, hbox5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.TOP_RIGHT);

        Button update = new Button("Update");
        Button reset =new Button("Reset");
        Button searchButton = new Button("Search");
        searchButton.setAlignment(Pos.CENTER);
        update.setOnAction(e-> {
            try {
                if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() ||
                getChoice(instructorsList).isEmpty()){

                    displayError("Invalid", "Missing Information to Update Course");

                } else {
                    CourseFile file = new CourseFile("Course.dat");
                    Course course = new Course(tf2.getText(),tf3.getText(), getChoice(instructorsList), getChoice(departmentsList));
                    long recordNum = Long.parseLong(tf1.getText());
                    file.updateRecord(course, recordNum);
                    tf1.setDisable(false);
                    tf1.clear();
                    tf2.clear();
                    tf3.clear();
                    departmentsList.setValue("");
                    instructorsList.setValue("");


                    file.readFromFile(recordNum);
                    displayError("Complete", "Course Info Updated");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        reset.setOnAction(e-> {
            tf1.setDisable(false);
            tf1.clear();
            tf2.clear();
            tf3.clear();
            departmentsList.setValue("");
            instructorsList.setValue("");

        });
        searchButton.setOnAction(e-> {
            try {
                long l = Long.parseLong(tf1.getText());

                CourseFile file = new CourseFile("Course.dat");
                Course c;
                if (l < 0 || l > file.getNumOfRecords()){
                    displayError("Invalid Course Number", "Record not found");
                    tf1.setDisable(false);
                }
                else {

                    c = file.readFromFile(l);
                    tf1.setDisable(true);
                    tf2.setText(c.getCourseId());
                    tf3.setText(c.getCourseName());
                    instructorsList.setValue(c.getInstructorName());
                    departmentsList.setValue(c.getDepartment());

                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        VBox column3 = new VBox(20,searchButton,update, reset);
        column3.setPadding(new Insets(20));
        column3.setAlignment(Pos.CENTER_LEFT);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);
        layout.setRight(column3);


        editCourseScene = new Scene(layout, 450, 400);

        return editCourseScene;
    }

    public Scene viewCourseScene () {
        Label sceneLabel = new Label("Edit Course Information");
        Label courseNum = new Label("Course Number");
        Label courseID = new Label("Course ID");
        Label courseName = new Label("Course Name");
        Label instructorName = new Label("Instructor Name");
        Label departmentName = new Label("Department");

        ChoiceBox<String> departmentsList = new ChoiceBox<>();
        departmentsList.setMinWidth(150);
        departmentsList.setDisable(true);


        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();



        HBox hbox0 = new HBox(10, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        hbox0.setPadding(new Insets(30));
        HBox hbox1 = new HBox(30, courseNum, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(62, courseID, tf2);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(41, courseName, tf3);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(28, instructorName, tf4);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(49, departmentName, departmentsList);
        hbox5.setAlignment(Pos.CENTER);


        Button search_student = new Button("Search Course");
        VBox vCreateStud = new VBox(search_student);
        vCreateStud.setPadding(new Insets(10));
        vCreateStud.setAlignment(Pos.CENTER);
        search_student.setOnAction(e-> {
            try {

                long l = Long.parseLong(tf1.getText());

                CourseFile file = new CourseFile("Course.dat");
                Course c;
                if (l < 0 || l > file.getNumOfRecords()){
                    displayError("Invalid Course Number", "Record not found");
                    tf1.setDisable(false);
                }
                else {
                    c = file.readFromFile(l);
                    tf2.setText(c.getCourseName());
                    tf3.setText(c.getCourseId());
                    tf4.setText(c.getInstructorName());
                    departmentsList.setValue(c.getDepartment());
                    tf2.setDisable(true);
                    tf3.setDisable(true);
                    tf4.setDisable(true);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox1, vCreateStud, hbox2, hbox3, hbox4, hbox5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        viewCourseScene = new Scene(layout, 450, 400);

        return viewCourseScene;
    }

    public Scene addEnrollmentScene () throws IOException {
        Label sceneLabel = new Label("New Enrollment Information");
        Label semesterLabel = new Label("Semester");
        Label yearLabel = new Label("Year");
        Label empty1 = new Label("");
        Label empty2 = new Label("");
        Label empty3 = new Label("");

        TextField tfFindStudent = new TextField();
        tfFindStudent.setPromptText("StudentID");
        TextField tfNameStudent = new TextField();
        tfNameStudent.setPromptText("Student Name");
        TextField tfCourseNumber = new TextField();
        tfCourseNumber.setPromptText("Course Number");
        TextField tfCourseID = new TextField();
        tfCourseID.setPromptText("Course ID");
        TextField tfCourseName = new TextField();
        tfCourseName.setPromptText("Course Name");

        ChoiceBox<String> yearList =  new ChoiceBox<>();
        yearList.getItems().addAll("2021","2020", "2019", "2018", "2017", "2016");
        yearList.setValue("");
        ChoiceBox<String> semesterList =  new ChoiceBox<>();
        semesterList.getItems().addAll("SPRING", "SUMMER", "FALL", "WINTER");
        semesterList.setValue("");

        Button createEnrollmentButton = new Button("Create Enrollment");
        createEnrollmentButton.setOnAction(e ->{
            try {
                EnrollmentFile fileEnrollment = new EnrollmentFile("Enrollment.dat");
                if (tfFindStudent.getText().isEmpty() || tfNameStudent.getText().isEmpty() ||
                    tfCourseNumber.getText().isEmpty() || tfCourseID.getText().isEmpty() || tfCourseName.getText().isEmpty()){
                    displayError("Invalid", "Missing Information to create enrollment");
                }
                else {
                    String grade = "*";
                    Enrollment en = new Enrollment(tfFindStudent.getText().trim(),
                            tfCourseNumber.getText().trim(), getChoice(yearList).trim(), getChoice(semesterList).trim(), grade);

                    fileEnrollment.writeRecord(en);

                    System.out.println(fileEnrollment.getNumOfRecords());
                    displayError("New enrollment", "Enrollment completed successfully");
                    tfFindStudent.clear();
                    tfNameStudent.clear();
                    tfCourseNumber.clear();
                    tfCourseID.clear();
                    yearList.setValue("");
                    tfCourseName.clear();
                    semesterList.setValue("");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InvalidCourseNum invalidCourseNum) {
                invalidCourseNum.printStackTrace();
            } catch (InvalidId invalidId) {
                invalidId.printStackTrace();
            }
        });

        Button findStudentButton = new Button("Find Student");
        findStudentButton.setOnAction(e ->{
            try {
                long sId = Long.parseLong(tfFindStudent.getText());
                StudentFile studentFile = new StudentFile("Student.dat");
                if (sId < 0 || sId > studentFile.getNumOfRecords()){
                    tfFindStudent.clear();
                    tfNameStudent.clear();
                    displayError("Invalid StudentID", "Record not found");
                }
                else {
                    Student s = studentFile.readFromFile(sId);
                    String studentName = s.getStudentFirstName() + " " + s.getStudentLastName();
                    tfNameStudent.setText(studentName);
                }

            } catch (IOException x) {
                x.printStackTrace();
            }
        });

        Button findCourseButton = new Button("Find Course");
        findCourseButton.setOnAction(e ->{
            try {
                CourseFile courseFile = new CourseFile("Course.dat");
                long cNum = Long.parseLong(tfCourseNumber.getText());
                if (cNum <= 0 || cNum > courseFile.getNumOfRecords()){
                    tfCourseID.clear();
                    tfCourseName.clear();
                    tfCourseNumber.clear();
                    displayError("Invalid", "Record not found");
                }
                else {
                    Course c = courseFile.readFromFile(cNum);
                    tfCourseID.setText(c.getCourseId());
                    tfCourseName.setText(c.getCourseName());
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        BorderPane layout = new BorderPane();
        HBox l1 = new HBox(10, sceneLabel);
        l1.setAlignment(Pos.CENTER);
        HBox l2 = new HBox(14, tfFindStudent, findStudentButton);
        l2.setAlignment(Pos.CENTER);
        HBox l3 = new HBox(95, tfNameStudent, empty1);
        l3.setAlignment(Pos.CENTER);
        HBox l4 = new HBox(18, tfCourseNumber, findCourseButton);
        l4.setAlignment(Pos.CENTER);
        HBox l5 = new HBox(95, tfCourseID, empty2);
        l5.setAlignment(Pos.CENTER);
        HBox l6 = new HBox(95, tfCourseName, empty3);
        l6.setAlignment(Pos.CENTER);
        HBox l7 = new HBox(110, semesterLabel, yearLabel);
        l7.setAlignment(Pos.CENTER);
        HBox l8 = new HBox(77, semesterList, yearList);
        l8.setAlignment(Pos.CENTER);
        HBox l9 = new HBox(createEnrollmentButton);
        l9.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        addEnrollmentScene = new Scene(layout, 450, 400);
        return addEnrollmentScene;
    }
    //get all departments
    public GenericLinkedList<Department> getAllDepartments() throws IOException {
        GenericLinkedList<Department> depLL = new GenericLinkedList<>();
        DepartmentFile file = new DepartmentFile("Department.dat");
        long l = file.getNumOfRecords();
        Department d;
        for (int i = 0; i < l; i++){
            d = file.readFromFile(i);
            depLL.add(d);
        }
        return depLL;

    }
    //get all of the enrollments
    public ArrayList<Enrollment> getEnrollments() throws IOException {

        EnrollmentFile file = new EnrollmentFile("Enrollment.dat");
        ArrayList<Enrollment> enrollmentsList = new ArrayList<>();
        long l = file.getNumOfRecords();
        for (long i = 1; i < l+1; i++){
            enrollmentsList.add(file.readFromFile(i));
        }
        file.close();
        return enrollmentsList;
    }

    public ArrayList<Course> getCourses() throws IOException {

        CourseFile file = new CourseFile("Course.dat");
        ArrayList<Course> courseList = new ArrayList<>();
        long l = file.getNumOfRecords();
        for (long i = 1; i < l+1; i++){
            courseList.add(file.readFromFile(i));
        }
        file.close();
        return courseList;
    }

    public ArrayList<Student> getStudents() throws IOException {

        StudentFile file = new StudentFile("Student.dat");
        ArrayList<Student> studentList = new ArrayList<>();
        long l = file.getNumOfRecords();
        for (long i = 1; i < l+1; i++){
            studentList.add(file.readFromFile(i));
        }
        file.close();
        return studentList;
    }

    private boolean isLong(TextField input, String message){
        try {
            long id = Long.parseLong(input.getText().trim());
            System.out.println("User id is: " + id);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + " is not a number");
            displayError("Invalid", "Invalid ID");
            return false;
        }
    }

   /* public Scene tableViewEnrollments () throws IOException {
        TableView<Enrollment> table;
        TextField tfStudentID =  new TextField();
        tfStudentID.setPromptText("StudentID");
        TextField tfCourseNum =  new TextField();
        tfCourseNum.setPromptText("Course Number");
        Button dropClassButton =  new Button("Drop Class");

        //StudentID column
        TableColumn<Enrollment, String> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setMinWidth(90);
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        //Course# column
        TableColumn<Enrollment, String> courseNumColumn = new TableColumn<>("Course#");
        courseNumColumn.setMinWidth(90);
        courseNumColumn.setCellValueFactory(new PropertyValueFactory<>("courseNum"));
        //Year column
        TableColumn<Enrollment, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setMinWidth(90);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        //semester column
        TableColumn<Enrollment, String> semesterColumn = new TableColumn<>("Semester");
        semesterColumn.setMinWidth(90);
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        //grade column
        TableColumn<Enrollment, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setMinWidth(90);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        HBox options = new HBox(10, tfStudentID, tfCourseNum, dropClassButton);
        options.setAlignment(Pos.CENTER);
        options.setPadding(new Insets(10));

        table = new TableView<>();
        table.setItems(getEnrollments());
        table.getColumns().addAll(studentIdColumn, courseNumColumn, yearColumn, semesterColumn, gradeColumn);
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(options);
        layout.setBottom(table);

        viewEnrollmentScene = new Scene(layout, 450, 400);
        return viewEnrollmentScene;
    }*/

    public Scene viewEnrollmentScene() {

        Label sceneLabel = new Label("View/Edit Enrollment");
        Label semesterLabel = new Label("Semester");
        Label yearLabel = new Label("Year");
        Label gradeLabel = new Label("Grade");
        Label studentIdLabel = new Label("StudentID");
        Label enrollmentidLabel = new Label("EnrollmentID");
        Label courseNum = new Label("Course Number");
        Label empty1 = new Label("                          ");
        Label empty2 = new Label("                                   ");
        Label empty3 = new Label("                  ");
        Label empty4 = new Label("                        ");
        Label empty5 = new Label("                   ");

        TextField tfFindEnrollment = new TextField();
        tfFindEnrollment.setPromptText("EnrollmentID");
        TextField tfStudentID = new TextField();
        tfStudentID.setPromptText("StudentID");
        TextField tfCourseNumber = new TextField();
        tfCourseNumber.setPromptText("Course Number");

        ChoiceBox<String> yearList =  new ChoiceBox<>();
        yearList.getItems().addAll("2021","2020", "2019", "2018", "2017", "2016");
        yearList.setValue("");
        ChoiceBox<String> semesterList =  new ChoiceBox<>();
        semesterList.getItems().addAll("SPRING", "SUMMER", "FALL", "WINTER");
        semesterList.setValue("");
        ChoiceBox<String> gradeList =  new ChoiceBox<>();
        gradeList.getItems().addAll("A", "B", "C", "D", "F");
        gradeList.setValue("");

        Button updateEnrollmentButton = new Button("Update Enrollment");
        updateEnrollmentButton.setOnAction(e ->{
            try {
                EnrollmentFile fileEnrollment = new EnrollmentFile("Enrollment.dat");
                if (tfFindEnrollment.getText().isEmpty() || tfStudentID.getText().isEmpty() ||
                        tfCourseNumber.getText().isEmpty()){
                    tfFindEnrollment.clear();
                    tfStudentID.clear();
                    tfCourseNumber.clear();
                    displayError("Invalid", "Missing Fields to Update Enrollment");
                }
                else {
                    Enrollment x;
                    x = new Enrollment(tfStudentID.getText(), tfCourseNumber.getText(), getChoice(yearList),
                            getChoice(semesterList), getChoice(gradeList));
                    long l = Long.parseLong(tfFindEnrollment.getText());
                    fileEnrollment.updateRecord(x, l);
                    displayError("Updated", "Enrollment Successfully Updated!");
                    tfFindEnrollment.clear();
                    tfStudentID.clear();
                    tfCourseNumber.clear();
                    yearList.setValue("");
                    semesterList.setValue("");
                    gradeList.setValue("");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InvalidCourseNum invalidCourseNum) {
                invalidCourseNum.printStackTrace();
            } catch (InvalidId invalidId) {
                invalidId.printStackTrace();
            }
        });

        Button findEnrollment = new Button("Find Enrollment");
        findEnrollment.setOnAction(e ->{
            try {
                long eId = Long.parseLong(tfFindEnrollment.getText());
                EnrollmentFile enrollmentFile = new EnrollmentFile("Enrollment.dat");

                if (eId < 0 ||eId > enrollmentFile.getNumOfRecords()){
                    tfFindEnrollment.clear();
                    tfStudentID.clear();
                    tfCourseNumber.clear();
                    yearList.setValue("");
                    semesterList.setValue("");
                    gradeList.setValue("");
                    displayError("Invalid", "Record not found");
                    if ((tfFindEnrollment.getText().isEmpty() || tfStudentID.getText().isEmpty() ||
                            tfCourseNumber.getText().isEmpty() || getChoice(yearList).equals("") ||
                            getChoice(semesterList).equals("") || getChoice(gradeList).equals(""))){
                        tfFindEnrollment.clear();
                        tfStudentID.clear();
                        tfCourseNumber.clear();
                        yearList.setValue("");
                        semesterList.setValue("");
                        gradeList.setValue("");
                    }
                }
                else {
                    tfStudentID.setDisable(true);
                    tfCourseNumber.setDisable(true);
                    Enrollment enrollment = enrollmentFile.readFromFile(eId);
                    tfStudentID.setText(enrollment.getStudentid());
                    tfCourseNumber.setText(enrollment.getCourseNum());
                    yearList.setValue(enrollment.getYear());
                    semesterList.setValue(enrollment.getSemester());
                    gradeList.setValue(enrollment.getGrade());
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
        });

        BorderPane layout = new BorderPane();
        HBox l1 = new HBox(10, sceneLabel);
        l1.setAlignment(Pos.CENTER);
        HBox l2 = new HBox(14, enrollmentidLabel, tfFindEnrollment, findEnrollment);
        l2.setAlignment(Pos.CENTER);
        HBox l3 = new HBox(10, studentIdLabel, tfStudentID, empty1);
        l3.setAlignment(Pos.CENTER);
        HBox l4 = new HBox(10, courseNum, tfCourseNumber, empty2);
        l4.setAlignment(Pos.CENTER);
        HBox l5 = new HBox(10, yearLabel, yearList, empty3);
        l5.setAlignment(Pos.CENTER);
        HBox l6 = new HBox(10, semesterLabel, semesterList, empty4);
        l6.setAlignment(Pos.CENTER);
        HBox l7 = new HBox(10, gradeLabel, gradeList, empty5);
        l7.setAlignment(Pos.CENTER);
        HBox l9 = new HBox(updateEnrollmentButton);
        l9.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(l1,l2,l3,l4,l5,l6, l7,l9);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        layout.setTop(menuBar);
        layout.setCenter(vbox);
        yearList.setMinWidth(150);
        semesterList.setMinWidth(150);
        gradeList.setMinWidth(150);

        viewEnrollmentScene = new Scene(layout, 450, 400);


        return viewEnrollmentScene;
    }

    public Scene byStudentScene() throws IOException {
        TextField tfStudentID =  new TextField();
        Label studentIDLabel = new Label("Student ID");
        tfStudentID.setPromptText("StudentID");
        TextField tfCourseNum =  new TextField();
        tfCourseNum.setPromptText("Course Number");
        TextField tfGrade =  new TextField();
        tfGrade.setPromptText("Grade");
        Button populateButton =  new Button("Populate");
        Button updateButton =  new Button("Update Grade");

        TextArea textArea =  new TextArea();
        textArea.setPadding(new Insets(10));
        textArea.setMinHeight(250);
        textArea.setMinWidth(250);
        textArea.setFont(Font.font(14));
        textArea.setWrapText(true);
        textArea.setEditable(false);

        HBox options = new HBox(10, studentIDLabel, tfStudentID, populateButton);
        VBox vBox = new VBox(10, options, textArea);
        HBox moreOptions = new HBox(10, tfCourseNum, tfGrade, updateButton);
        moreOptions.setAlignment(Pos.CENTER);
        moreOptions.setPadding(new Insets(10));
        options.setAlignment(Pos.TOP_CENTER);
        options.setPadding(new Insets(10));
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vBox);
        layout.setBottom(moreOptions);

        StudentFile studentFile = new StudentFile("Student.dat");
        CourseFile courseFile = new CourseFile("Course.dat");
        EnrollmentFile enrollmentsFile = new EnrollmentFile("Enrollment.dat");

        ArrayList<Enrollment> enrollmentsArray = new ArrayList<Enrollment>();
        GenericLinkedList<Student> studentsArray = new GenericLinkedList<Student>();
        GenericLinkedList<Course> coursesArray = new GenericLinkedList<Course>();
        int s = (int) studentFile.getNumOfRecords();
        int c = (int) courseFile.getNumOfRecords();
        int e = (int) enrollmentsFile.getNumOfRecords();

        for(int i = 0; i < s; i++) {
            studentsArray.add(getStudents().get(i));
        }
        System.out.println(studentsArray);

        for(int i = 0; i < c; i++) {
            coursesArray.add(getCourses().get(i));
        }
        System.out.println(coursesArray);

        for(int i = 0; i < e; i++) {
            enrollmentsArray.add(getEnrollments().get(i));
        }

        System.out.println(enrollmentsArray);
        populateButton.setOnAction(event->{
            String sb ="";
            for (Enrollment enrollmentDetails: enrollmentsArray) {
                if (enrollmentDetails.getStudentid().equals(tfStudentID.getText())) {
                    sb += "===================\n";

                    sb+= sb.format("StudentID %25s \nCourse Number %16s",enrollmentDetails.getStudentid(), enrollmentDetails.getCourseNum());
                    sb += sb.format("\nGrade %32s\n", enrollmentDetails.getGrade());
                    textArea.setText(sb);
                }
            }

        });
        updateButton.setOnAction(event->{
            String sb ="";
            if((isGrade(tfGrade.getText().toUpperCase()))){
                for (Enrollment enrollmentDetails: enrollmentsArray) {
                    if (enrollmentDetails.getCourseNum().equals(tfCourseNum.getText())) {
                        enrollmentDetails.setGrade(tfGrade.getText().toUpperCase());
                        try {
                            long input = Long.parseLong(tfStudentID.getText());
                            enrollmentsFile.updateRecord(enrollmentDetails, input);

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            } else
                displayError("Invalid Input", "Invalid Grade Input");

        });

        byStudentScene = new Scene(layout, 450, 400);
        return byStudentScene;
    }

    private boolean isGrade(String s){
        ArrayList<String> gradeList = new ArrayList<>();
        gradeList.add("A");
        gradeList.add("B");
        gradeList.add("C");
        gradeList.add("D");
        gradeList.add("F");
        return gradeList.contains(s);
    }

    public Scene reportScene() throws IOException{
        Label courseNumLabel = new Label("Course Number");
        Label semesterLabel = new Label("Semester");
        Label yearLabel = new Label("Year");
        Label emptyLabel = new Label("       ");

        ChoiceBox<String> yearList =  new ChoiceBox<>();
        yearList.getItems().addAll("2021","2020", "2019", "2018", "2017", "2016");

        ChoiceBox<String> semesterList =  new ChoiceBox<>();
        semesterList.getItems().addAll("SPRING", "SUMMER", "FALL", "WINTER");

        TextField tfCourseNum =  new TextField();
        tfCourseNum.setPromptText("Course #");
        tfCourseNum.setMaxWidth(90);
        Button generateReportButton =  new Button("Generate Report");
        generateReportButton.setMaxWidth(150);
        HBox options = new HBox(10, tfCourseNum, semesterList, yearList, generateReportButton, emptyLabel);
        HBox labels = new HBox(30, courseNumLabel, semesterLabel, yearLabel);
        labels.setAlignment(Pos.CENTER_LEFT);
        labels.setPadding(new Insets(30));
        VBox vBox = new VBox(10, labels, options);
        options.setAlignment(Pos.CENTER);

        TextArea textArea =  new TextArea();
        textArea.setPadding(new Insets(10));
        textArea.setMinHeight(200);
        textArea.setMinWidth(200);
        textArea.setFont(Font.font(14));
        textArea.setWrapText(true);
        textArea.setEditable(false);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vBox);
        layout.setBottom(textArea);

        StudentFile studentFile = new StudentFile("Student.dat");
        CourseFile courseFile = new CourseFile("Course.dat");
        EnrollmentFile enrollmentsFile = new EnrollmentFile("Enrollment.dat");

        ArrayList<Enrollment> enrollmentsArray = new ArrayList<Enrollment>();
        GenericLinkedList<Student> studentsArray = new GenericLinkedList<Student>();
        GenericLinkedList<Course> coursesArray = new GenericLinkedList<Course>();
        int s = (int) studentFile.getNumOfRecords();
        int c = (int) courseFile.getNumOfRecords();
        int e = (int) enrollmentsFile.getNumOfRecords();

        for(int i = 0; i < s; i++) {
            studentsArray.add(getStudents().get(i));
        }
        System.out.println(studentsArray);

        for(int i = 0; i < c; i++) {
            coursesArray.add(getCourses().get(i));
        }
        System.out.println(coursesArray);

        for(int i = 0; i < e; i++) {
            enrollmentsArray.add(getEnrollments().get(i));
        }

        generateReportButton.setOnAction(event->{

            textArea.clear();
            String sb ="";
            int input = Integer.parseInt(tfCourseNum.getText());
            Course courseObj = coursesArray.get(input-1);
            sb += sb.format( "%s %10s \n==========================",courseObj.getCourseId(),courseObj.getCourseName());
            textArea.setText(sb);
            for (Enrollment enrollment: enrollmentsArray) {

                if (enrollment.getSemester().equals(getChoice(semesterList)) ||
                        enrollment.getYear().equals(getChoice(yearList))){
                    int sid = Integer.parseInt(enrollment.getStudentid());
                    sid = sid - 1;
                    Student student = studentsArray.get(sid);
                    String str = sb.format("\n%s %s %10s", student.getStudentFirstName(), student.getStudentLastName(), enrollment.getGrade());
                    textArea.appendText(str);
                }else{
                    displayError("Completed", "Report generated");
                }
            }
        });

        reportScene = new Scene(layout, 450, 400);
        return reportScene;
    }
    public Scene addInstructorScene() throws IOException {

        //create a labels to display prompt
        Label sceneLabel = new Label("Instructor Information");
        Label instNum= new Label("Instructor ID#");
        Label instructorName = new Label("Instructor Name");
        Label departmentName = new Label("Department");

        DepartmentFile depFile = new DepartmentFile("Department.dat");
        InstructorFile instFile = new InstructorFile("Instructor.dat");
        GenericLinkedList<Department> depLL = new GenericLinkedList<>();
        ArrayList<String> depL = new ArrayList<>();
        Long z = depFile.getNumOfRecords();

        for (long j = 0; j < z; j++ ){
            Department x;
            x = depFile.readFromFile(j+1);
            depLL.add(x);
        }
        for(int i=0; i < depLL.size(); i++){
            String x;
            x = depLL.get(i).toString();
            depL.add(x);
        }

        ChoiceBox<String> departmentsList = new ChoiceBox<>();
        departmentsList.setMinWidth(150);
        for (int s = 0; s < depL.size();s++){

            departmentsList.getItems().add(depL.get(s));
        }

        TextField tf1 = new TextField();
        tf1.setDisable(true);
        String numOfRecords = String.valueOf(instFile.getNumOfRecords()+1);
        tf1.setText(numOfRecords);
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();

        HBox hbox0 = new HBox(20, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(39, instNum, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(28, instructorName, tf4);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(50, departmentName, departmentsList);
        hbox5.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox0, hbox1, hbox4, hbox5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        Button addInstructor = new Button("Add Instructor");
        addInstructor.setOnAction(e-> {
            try {
                if (tf1.getText().isEmpty() ||tf4.getText().isBlank() || getChoice(departmentsList).isEmpty()) {
                    displayError("Invalid", "Missing Information to Add Instructor");
                } else {
                    Instructor inst = new Instructor(tf4.getText(), getChoice(departmentsList));
                    long l = Long.parseLong(tf1.getText());
                    instFile.writeInstructorRecord(inst);
                    tf4.clear();
                    tf5.clear();
                    departmentsList.setValue("");
                    String newRecordsNum = String.valueOf(instFile.getNumOfRecords()+1);
                    tf1.setText(newRecordsNum);
                    displayError("Success!", "Instructor Profile created!");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox forButton = new VBox(addInstructor);
        forButton.setPadding(new Insets(30));
        forButton.setAlignment(Pos.CENTER);
        layout.setBottom(forButton);

        addInstructorScene = new Scene(layout, 450, 400);
        return addInstructorScene;
    }

    public Scene addDepartmentScene() throws IOException {

        //create a labels to display prompt
        Label sceneLabel = new Label("Department Information");
        Label depNum = new Label("Department ID#");
        Label departmentName = new Label("Department Name");

        ChoiceBox<String> departmentsList = new ChoiceBox<>();
        departmentsList.setMinWidth(150);
        departmentsList.getItems().addAll("Arts", "Business", "Computer Science","Humanities", "Mathematics", "English");

        DepartmentFile file = new DepartmentFile("Department.dat");
        GenericLinkedList<DepartmentFile> depLL = new GenericLinkedList<>();

        TextField tf1 = new TextField();
        tf1.setDisable(true);
        String numOfRecords = String.valueOf(file.getNumOfRecords()+1);
        tf1.setText(numOfRecords);

        tf1.setMinWidth(150);
        TextField tf4 = new TextField();

        HBox hbox0 = new HBox(20, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(39, depNum, tf1);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(29, departmentName, tf4);
        hbox5.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(12);
        vbox.getChildren().addAll(hbox0, hbox1, hbox5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        Button addDepartment = new Button("Add Department");
        addDepartment.setOnAction(e-> {
            try {
                if (tf1.getText().isEmpty() ||tf4.getText().isBlank()) {
                    displayError("Invalid", "Missing Information to Add Department");
                } else {
                    Department dept= new Department(tf4.getText());
                    long l = Long.parseLong(tf1.getText());
                    file.writeDepartmentRecord(dept);
                    tf4.clear();
                    departmentsList.setValue("");
                    String newRecordsNum = String.valueOf(file.getNumOfRecords()+1);
                    tf1.setText(newRecordsNum);
                    displayError("Success!", "Department Profile created!");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox forButton = new VBox(addDepartment);
        forButton.setPadding(new Insets(30));
        forButton.setAlignment(Pos.TOP_CENTER);
        layout.setBottom(forButton);
        addDepartmentScene = new Scene(layout, 450, 400);

        return addDepartmentScene;
    }
    public Scene editDepartmentScene() throws IOException {

        //create a labels to display prompt
        Label sceneLabel = new Label("Edit Department Information");
        Label depNum = new Label("  Department ID#   ");
        Label departmentName = new Label("Department Name");

        DepartmentFile file = new DepartmentFile("Department.dat");
        GenericLinkedList<DepartmentFile> depLL = new GenericLinkedList<>();


        TextField findDep= new TextField();
        findDep.setDisable(false);
        //findDep.setMinWidth(150);
        TextField updateDep = new TextField();


        Button updateButton = new Button("Update");
        updateButton.setMinWidth(105);
        updateButton.setOnAction(e ->{
            try {
                //DepartmentFile file = new DepartmentFile("Department.dat");
                //long l = Long.parseLong(tfFindEnrollment.getText());
                if (updateDep.getText().isEmpty()){
                    findDep.clear();
                    findDep.setDisable(false);


                    displayError("Invalid", "Missing Fields to Update Department");

                }
                else {
                    Department x;
                    x = new Department(updateDep.getText());
                    long l = Long.parseLong(findDep.getText());
                    file.updateRecord(x, l);
                    displayError("Updated", "Department Successfully Updated!");
                    findDep.clear();
                    updateDep.clear();
                    findDep.setDisable(false);

                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        Button resetButton = new Button("Reset");
        resetButton.setMinWidth(110);
        resetButton.setOnAction(e -> {
            findDep.setDisable(false);
            findDep.clear();
            updateDep.clear();
        });
        Button findDepartment= new Button("Find Department");
        findDepartment.setMinWidth(100);
        findDepartment.setOnAction(e ->{
            try {

                long eId = Long.parseLong(findDep.getText());

                if (eId < 0 ||eId > file.getNumOfRecords()){
                    findDep.clear();
                    displayError("Invalid", "Record not found");
                }
                else {
                    findDep.setDisable(true);
                    Department depO = file.readFromFile(eId);
                    updateDep.setText(depO.getDepName());
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
        });

        HBox hbox0 = new HBox(20, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(10, depNum, findDep, findDepartment);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(10, departmentName, updateDep, updateButton);
        hbox5.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(15);
        vbox.getChildren().addAll(hbox0, hbox1, hbox5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);


        VBox forButton = new VBox(resetButton);
        forButton.setPadding(new Insets(30));
        forButton.setAlignment(Pos.TOP_CENTER);
        layout.setBottom(forButton);

        editDepartmentScene = new Scene(layout, 450, 400);

        return editDepartmentScene;
    }

    public Scene editInstructorScene() throws IOException {
        //create a labels to display prompt
        Label sceneLabel = new Label("Edit Instructor Information");
        Label instNumber = new Label("Instructor ID#    ");
        Label instructorName = new Label("Instructor Name");
        Label departmentName = new Label("           Department Name");

        InstructorFile file = new InstructorFile("Instructor.dat");
        DepartmentFile dfile = new DepartmentFile("Department.dat");
        GenericLinkedList<Department> depLL = new GenericLinkedList<>();
        ArrayList<String> depL= new ArrayList<>();

        Long z = dfile.getNumOfRecords();

        for (long j = 0; j < z; j++ ){
            Department x;
            x = dfile.readFromFile(j+1);
            depLL.add(x);
        }
        for(int i=0; i < depLL.size(); i++){
            String x;
            x = depLL.get(i).toString();
            depL.add(x);

        }

        ChoiceBox<String> departmentsList = new ChoiceBox<>();
        departmentsList.setMinWidth(145);

        for (int s = 0; s < depL.size();s++){

            departmentsList.getItems().add(depL.get(s));
        }

        TextField instNum= new TextField();
        instNum.setDisable(false);
        TextField dep = new TextField();
        TextField instName = new TextField();

        Button findDepartment= new Button("Find Instructor");
        findDepartment.setMinWidth(100);
        findDepartment.setOnAction(e ->{
            try {
                long eId = Long.parseLong(instNum.getText());

                if (instNum.getText().isEmpty() || file.getNumOfRecords() < eId){
                    instNum.clear();
                    instNum.setDisable(false);
                    displayError("Invalid", "Record not found");
                }
                else {
                    instNum.setDisable(true);
                    Instructor o = file.readFromFile(eId);
                    instName.setText(o.getInstructorName());
                    departmentsList.setValue(o.getDepartmentName());
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
        });
        Button updateButton = new Button("Update");
        updateButton.setMinWidth(100);
        updateButton.setOnAction(e ->{
            try {
                long l = Long.parseLong(instNum.getText());
                if (instName.getText().isBlank() || getChoice(departmentsList).isEmpty()){
                    instNum.clear();
                    instName.clear();
                    departmentsList.setValue("");
                    instNum.setDisable(false);
                    displayError("Invalid", "Missing Fields to Update Instructor");

                }
                else {
                    Instructor x;
                    x = new Instructor(instName.getText(), getChoice(departmentsList));
                    file.updateRecord(x, l);
                    displayError("Updated", "Instructor Successfully Updated!");
                    instNum.clear();
                    instName.clear();
                    departmentsList.setValue("");
                    instNum.setDisable(false);

                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        Button resetButton = new Button("Reset");
        resetButton.setMinWidth(110);
        resetButton.setOnAction(e -> {
            instNum.clear();
            instName.clear();
            instNum.setDisable(false);
            departmentsList.setValue("");

        });

        HBox hbox0 = new HBox(20, sceneLabel);
        hbox0.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox(10, instNumber, instNum , findDepartment);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(10, instructorName, instName, updateButton);
        hbox5.setAlignment(Pos.CENTER);
        HBox hbox6 = new HBox(4, departmentName, departmentsList);
        hbox5.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(15);
        vbox.getChildren().addAll(hbox0, hbox1, hbox5, hbox6);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(vbox);

        VBox forButton = new VBox(resetButton);
        forButton.setPadding(new Insets(30));
        forButton.setAlignment(Pos.TOP_CENTER);
        layout.setBottom(forButton);

        editInstructorScene = new Scene(layout, 450, 400);

        return editInstructorScene;

    }
}


import java.util.*;
import java.io.*;

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
class Course implements Serializable {
	
	public static int uniqueCourseNum = 1 ;
	private String courseIdString;
	private String courseName ;
	private String intructorName ;
	private String department;
	// Course class instructor
	public Course(String courseIdString, String courseName, String instructorName, String department, int uniqueCourseNum) throws InvalidCourseNum{
		
		super();
		this.courseIdString = courseIdString;
		this.courseName = courseName;
		this.intructorName = instructorName;
		this.department = department;
		this.uniqueCourseNum = uniqueCourseNum++;
		
	}
	public Course(String str, int id) {
		// TODO Auto-generated constructor stub
	}
	public String getCourseIdString() {
		return courseIdString;
	}

	public int getUniqueCourseNumber() {
		return uniqueCourseNum;
	}
	public void setCourseIdString(String courseIdString) {
		this.courseIdString = courseIdString;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getIntructorName() {
		return intructorName;
	}
	public void setIntructorName(String intructorName) {
		this.intructorName = intructorName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}

class Enrollment implements Serializable{
	
	private int  uniqueStudentID;
	private int courseNumber;
	private int year;
	private String semester;
	private String grade;
	
// Enrollment class constructor	
	public Enrollment(int studentID, int courseNumber, int year, String semester, String grade) {
		super();
		this.uniqueStudentID = studentID;
		this.courseNumber = courseNumber;
		this.year = year;
		this.semester = semester;
		this.grade = grade;
	}
	public Enrollment(String str, int id) {
	// TODO Auto-generated constructor stub
}
	public int getStudentID() {
		return uniqueStudentID;
	}

	public int getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
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

class Student implements Serializable{
	
	public static int uniqueStudentID  = 1;
	private String studentName ;
	private String studentAddress;
	private String studentCity ;
	private String studentState ;

// Student class constructor	
	public Student(int studentID, String studentName, String studentAddress, String studentCity, String studentState) throws InvalidId{
		super();
		
		this.uniqueStudentID = studentID++;
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentCity = studentCity;
		this.studentState = studentState;
		
	}	
	
	public Student(String str, int id) {
	// TODO Auto-generated constructor stub
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
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	publi String getStudentCity() {
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
	
}


public class MidtermProject {
	private static final long RECORD_SIZE = 204;
	private static int choice;
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] args) throws IOException, InvalidId, InvalidCourseNum
	{
		
		mainMenu();
	
	
		System.exit(0);
	}
	public static void gradesSubMenu() throws IOException, InvalidId, InvalidCourseNum{
		do {
		int x;
		System.out.println("\nGrades Sub Menu\n");
		System.out.println("1. View Grades by Student\n2. View Grades by Course\n3. Edit Grades by Student\n4. Edit Grades by Course"
				+ "\n0. -- Exit to Menu --\nPlease enter a valid choice (1-4, 0 to Exit): ");
		x = input.nextInt();
		input.nextLine();
		switch(x) {
		    case 0:
		    		System.out.println("Returning to the main menu...\n");
		    		mainMenu();
		    		break;
			case 1:
				System.out.println("Viewing Grades by Student...");
				displayStudent();
				break;
			case 2:
				System.out.println("Viewing Grades by Course...");
				displayCourse();
				break;
			case 3:
				System.out.println("Edit Grades by Student...");
				editStudent();
				break;
			case 4:
				System.out.println("Edit Grades by Course...");
				editGrade();
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid choice (1-4, 0 to Exit):");
			}
		
		}while (choice >= 0);
		
	}
	
	public static void mainMenu() throws IOException, InvalidId, InvalidCourseNum{
		do {
				System.out.println("Welcome to University Enrollment");
				System.out.println("1. Create Student");
				System.out.println("2. Create Course");
				System.out.println("3. Create Enrollment");
				System.out.println("4. Edit Student");
				System.out.println("5. Edit Course");
				System.out.println("6. Edit Enrollment");
				System.out.println("7. Display Student(s)");
				System.out.println("8. Display Course(s)");
				System.out.println("9. Display Enrollment(s)");
				System.out.println("10. Grade Sub Menu");
				System.out.println("0. --- Quit ---");
				System.out.println("Please enter a valid choice(1-10, 0 to Quit): ");
				choice = input.nextInt();	
				input.nextLine();
				if(choice == 10)
					gradesSubMenu();
				
				switch (choice) {
				case 1:
					System.out.println("Creating student...");
					createStudent();
					break;	
				case 2:
					System.out.println("Creating course...");
					createCourse();
					break;
				case 3:
					System.out.println("Creating enrollment...");
					createEnrollment();
					break;
				case 4:
					System.out.println("Editing student...");
					editStudent();
					break;
				case 5:
					System.out.println("Editing course..");
					editCourse();
					break;
				case 6:
					System.out.println("Editing enrollment..");
					editEnrollment();
					break;
				case 7:
					System.out.println("Displaying student..");
					displayStudent();
					break;
				case 8:
					System.out.println("Displaying course..");
					displayCourse();
					break;
				case 9:
					System.out.println("Displaying enrollment..");
					displayEnrollment();
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid choice(1-10, 0 to Quit): \n");
					}
					
		} while (choice != 0);
	}
	
	private static void displayEnrollment() {
		System.out.println("need to work on it\n");
	}

	private static void displayCourse() {
		int courseNum;
		
		System.out.print("Please enter Course Number: ");
		courseNum = input.nextInt();
		input.nextLine();
		if (courseNum <= Course.uniqueCourseNum && courseNum > 0)
			// open course file and display course record to edit
		
		System.out.println("need to work on it\n");
		
	}

	private static void displayStudent() {
		int studentID;
		
		System.out.print("Please enter studentID: ");
		studentID = input.nextInt();
		input.nextLine();
		if (studentID <= Student.uniqueStudentID && studentID > 0)
			// open student file and display student record
			System.out.println("need to work on it\n");
		
	}

	private static void editEnrollment() {
		//need to collect studentID and course number
		//need to check them, if valid proceed to let user edit enrollment fields
		System.out.println("need to work on it\n");
	}

	private static void editCourse() {
		int courseNum;
		
		System.out.print("Please enter Course Number: ");
		courseNum = input.nextInt();
		input.nextLine();
		if (courseNum <= Course.uniqueCourseNum && courseNum > 0)
			// open course file and display course record to edit
		System.out.println("need to work on it\n");
	}

	private static void editStudent() {
		int studentID;
		
		System.out.print("Please enter studentID: ");
		studentID = input.nextInt();
		input.nextLine();
		if (studentID <= Student.uniqueStudentID && studentID > 0)
			// open student file and display student record to edit
			
			System.out.println("need to work on it\n");
		
	}
	private static void editGrade() {
		
		System.out.println("need to work on it\n");
	}

	private static void createEnrollment() throws IOException {
		System.out.println("Please enter Enrollment information: ");
		/*user enters uniqueStudentID/uniqueCourseNum and the program should check for those, if they are real 
		 * the program should collect for year/semester/grade, else let the user know that the ids entered are not valid*/
		int studentID, courseNum, year;
		String  semester, grade;
		Enrollment enrollment;
		System.out.print("Enter StudentID: ");
		studentID = input.nextInt();
		input.nextLine();
		System.out.print("Enter Course Number: ");
		courseNum = input.nextInt();
		input.nextLine();
		System.out.print("Enter Year: ");
		year = input.nextInt();
		input.nextLine();
		System.out.print("Enter Semester: ");
		semester = input.nextLine();
		System.out.print("Enter Grade: ");
		grade = input.nextLine();
		
		
		enrollment = new Enrollment(studentID, courseNum, year, semester, grade);
		//random access file 
		try {
			RandomAccessFile enrollmentFile = createFile("Enrollment.dat");
			writeToFile(enrollment, enrollmentFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		
		System.out.println("Thank you. Re-directing to the main menu..\n");	
		
	}

	private static void createCourse() throws IOException, InvalidCourseNum {
		Course course;
		String courseName;
		int cNum = Course.uniqueCourseNum;
		String department;
		String intructorName;
		String courseIDString;
		
		System.out.print("Please enter the following information:\n");
		System.out.print("Course Number: " + cNum);
		System.out.print("\nEnter CourseID, (ex: CS213): ");
		courseIDString = input.nextLine();
		System.out.print("Enter Course Name: ");
		courseName = input.nextLine();
		System.out.print("Enter Instructor Name: ");
		intructorName = input.nextLine();
		System.out.print("Enter Department Name: ");
		department = input.nextLine();
		cNum = cNum +1;
		
		course = new Course(courseIDString, courseName, intructorName, department, cNum);
		//random access file 
		try {
			RandomAccessFile courseFile = createFile("Course.dat");
			writeToFile(course, courseFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	
		
		
		System.out.println("Thank you. Re-directing to the main menu..\n");
	}
	/*writeFile(courseObject, courseFile) */
	
	
	private static void createStudent() throws IOException, InvalidId{
		Student student;
		int studentID = Student.uniqueStudentID ;
		String name, address, city, state;
		
		System.out.println("Please enter the following information: ");
		System.out.println("Student ID: " + studentID);
		System.out.print("Enter Name: ");
		name = input.nextLine();
		System.out.print("Enter Address: ");
		address =  input.nextLine();
		System.out.print("Enter City: ");
		city = input.nextLine();
		System.out.print("Enter State: ");
		state = input.nextLine();
		studentID = studentID +1;
		
		student = new Student(studentID, name, address, city, state);
		
				//random access file 
		try {
			RandomAccessFile studentFile = createFile("Student.dat");
			writeToFile(student, studentFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		System.out.println("Thank you. Re-directing to the main menu..\n");
		
		
	}
	private static RandomAccessFile createFile(String filename) throws FileNotFoundException {
		
		RandomAccessFile inputFile = new RandomAccessFile(filename, "rw");
		return inputFile;
	
	}
	//create write file
	private static void writeToFile(Object obj, RandomAccessFile fileName) throws IOException {
		String str = obj.toString();
		if (str.length() > 100) {
			for (int i = 0; i < 100; i++) {
				fileName.writeChar(str.charAt(i));
				}
			}
			else {
				fileName.writeChars(str);
				for (int j = 0; j < (100 - str.length()); j++) {
					fileName.writeChar(' ');
				}
				
				//file.write(obj.int.examp);
			}
	}
	
	//method to read file
	private static void readFromFile(RandomAccessFile fileName) throws IOException {
		char[] charArray	= new char[100];
		//read description character by character, from the file into the char array.
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = fileName.readChar();
		}
		//store the char array in a String
		String str = new String(charArray);
		//trim any trailing spaces from the string
		str.trim();
		//read the units of the file
		int id = fileName.readInt();
		//create an object depending on the given filename
		if (fileName == fileName) {
			Student student = new Student(str, id);
		} else if (fileName == fileName) {
			Course course = new Course(str, id);
		} else if (fileName == fileName) {
			Enrollment enrollment = new Enrollment(str, id);
		}
	}
	
	//getByteNum method returns a record's starting byte number.
	//@param recordNum returns number of desired record
	private long getByteNum(long recordNum) {
		return RECORD_SIZE *recordNum;
	}
	private void moveFilePointer(RandomAccessFile fileName, long recordNum) throws IOException{
		fileName.seek(getByteNum(recordNum));
	}
	/*the getNumberOfRecords method returns the number of records stored in the file
	 * @return the number of records in the file
	*/ 
	private long getNumberOfRecords(RandomAccessFile fileName) throws IOException {
		return fileName.length() / RECORD_SIZE;
	}
	
	
	//create update file
	//how to write multiple field to a binary data file
}


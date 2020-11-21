import java.util.*;

class Person{
	public String name;
	public String address;
	public String telNum;
	
	public Person(String name, String address, String telNum) {
		this.name =  name;
		this.address = address;
		this.telNum = telNum;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getTelNum() {
		return telNum;
	}
	
}

class customer extends Person {
	public String custID;
	public boolean mailStatus =  true;
	
	public customer(String name, String address, String telNum, String custID) {
		super(name, address, telNum);
		this.custID = custID;
		this.mailStatus = mailStatus;
		
	}
	
	public String getCustID() {
		return custID;
	}
	
	public String getMailStatus() {
		String str = String.valueOf(mailStatus);
		return str;
	}
	
	public void setCustID(String cID) {
		this.custID = cID;
	}
	
	
}
public class CustomerDemo {

	public static void main(String[] args) {
		
		String name, address, telNum, custID;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter customer Name: ");
		name = input.nextLine();
		System.out.print("Enter customer Address: ");
		address = input.nextLine();
		System.out.print("Enter customer Telephone Number: ");
		telNum = input.nextLine();
		System.out.print("Enter CustID: ");
		custID = input.nextLine();
		input.close();
		
		Person objCustumer = new customer(name, address, telNum, custID);
		
		System.out.print("Cust Name:" + objCustumer.getName()+ "\n");
		System.out.print("CustID: " + ((customer) objCustumer).getCustID()+ "\n");
		System.out.print("Telephone Number: " + objCustumer.getTelNum()+ "\n");
		System.out.print("Mail List Status: " + ((customer) objCustumer).getMailStatus());
	}

}

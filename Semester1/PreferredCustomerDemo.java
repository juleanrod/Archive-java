class Personx{
	private String name;
	private String address;
	private String telNum;
	
	public Personx(String name, String address, String telNum) {
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
	
	public String toString() {
		String str = "Cust Name:"+name;
		
		return str;
	}
}

class Customerx extends Personx {
	private int custID;
	private boolean mailStatus =  true;
	
	public Customerx(String name, String address, String telNum, int custID, boolean mailStatus) {
		super(name, address, telNum);
		this.custID = custID;
		this.mailStatus = mailStatus;
		
	}
	
	public int getCustID() {
		return custID;
	}
	
	public String getMailStatus() {
		String str = String.valueOf(mailStatus);
		return str;
	}
	
	public void setCustID(int cID) {
		this.custID = cID;
	}
	@Override
	public String toString() {
		String str = super.toString();
		str+= "\nCustID: "+getCustID();
		str+= "\nTelephone Number: "+getTelNum();
		str+= "\nMail List Status: "+getMailStatus();
		
		return str;
	}
}
class PreferredCustomer extends Customerx {
	
	private double amountPurchased;
	private double discountLevel;
	
	public PreferredCustomer(String name, String address, String telNum, int custID, boolean mailStatus, double amountPurchased) {
		super(name, address, telNum, custID, mailStatus);
		this.amountPurchased = amountPurchased;
		this.discountLevel = getdiscountLevel(amountPurchased); 
	}
	
	public double getdiscountLevel(double amountPurchased) {
		double discountPercent;
		if (amountPurchased >= 500 && amountPurchased < 1000) {
			discountPercent = 5;
		} else if (amountPurchased >= 1000 && amountPurchased <1500) {
			discountPercent = 6;
		} else if (amountPurchased >= 1500 && amountPurchased < 2000) {
			discountPercent = 7;
		} else if (amountPurchased >= 2000) {
			discountPercent = 10;
		} else {
			discountPercent = 0;
		}
		return discountPercent;
	}
	@Override
	public String toString() {
		String str = super.toString();
		str += "\nPurchase "+ amountPurchased +"\nDiscount Percent " + discountLevel;
		return str;
	
	}
	
	
}
public class PreferredCustomerDemo {

	public static void main(String[] args) {
		
		PreferredCustomer preferredcustomer1 = new PreferredCustomer("John Adams",
				"Los Angeles, CA", "3235331234", 933, true, 400);
				System.out.println(preferredcustomer1.toString() + "\n");

				PreferredCustomer preferredcustomer2 = new PreferredCustomer("Susan Adams",
				"Los Angeles, CA", "3235331234", 933, true, 600);
				System.out.println(preferredcustomer2.toString()+ "\n");

				PreferredCustomer preferredcustomer3 = new PreferredCustomer("Mary Adams",
				"Los Angeles, CA", "3235331234", 933, true, 1100);
				System.out.println(preferredcustomer3.toString()+ "\n");

				PreferredCustomer preferredcustomer4 = new PreferredCustomer("Larry Adams",
				"Los Angeles, CA", "3235331234", 933, true, 1600);
				System.out.println(preferredcustomer4.toString()+ "\n");

				PreferredCustomer preferredcustomer5 = new PreferredCustomer("Mony Adams",
				"Los Angeles, CA", "3235331234", 933, true, 2600);
				System.out.println(preferredcustomer5.toString()+ "\n");
				
	}

}


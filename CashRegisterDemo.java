import java.util.*;

class CashRegister{

	String itemName;
	int quantity;
	float price;

	CashRegister(String name,int quant,float p){
		this.itemName= name;
		this.quantity = quant;
		this.price = p;
	}
	float getSubtotal(int quant,float p){
		return (p*quant);
	}
	float getTax(float subtotal){
		return (subtotal*6)/100;
	}
	float getTotal (float subtotal,float tax){
		return (subtotal+tax);
	}
}
//main class
public class CashRegisterDemo {

	public static void main(String[] args) {
		//variables
		String name;
		int quantity, available_units;
		float price,subtotal,tax,total;
		//start scanner to collect input
		Scanner scanner =new Scanner(System.in);  
		
		System.out.println("We need information about the retail item.");
		System.out.print("What is the name of the item? ");
		name = scanner.next();
		System.out.print("How many units are available? ");
		available_units = scanner.nextInt();
		if(available_units < 1) {
			System.out.println("Invalid Entry. Please try again.");
			available_units = scanner.nextInt();
		}
		System.out.print("How much does the item cost per unit? ");
		price = scanner.nextFloat();
		if (price < 1) {
			System.out.println("Invalid Entry. Please try again.");
			price = scanner.nextFloat();
		} 
		System.out.print("How many items are you going to purchase? ");
		quantity = scanner.nextInt();
		if(quantity<1) {
		System.out.println("Invalid Entry. Please try again.");
		quantity = scanner.nextInt();
		}
		//close scanner
		scanner.close();
		
		//start object for cash register class
		CashRegister cr = new CashRegister(name,quantity,price);
		  
		//call functions
		subtotal =cr.getSubtotal(quantity, price);
		tax = cr.getTax(subtotal);
		total = cr.getTotal(subtotal, tax);
		
		System.out.println("Subtotal: "+ subtotal +"\nTax: "+tax+ "\nTotal: "+total);
		
		System.exit(0);
	  }
}

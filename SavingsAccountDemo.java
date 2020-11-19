import java.util.*;

import java.io.*;

class InvalidInput extends Exception {
	public InvalidInput() {
		super("Error. Invalid input\n");
	}
}
class InvalidAmount extends Exception {
	public InvalidAmount() {
		super("Error: Must enter positive value\n");
	}
}

//class InvalidInput extends

class OverdraftProtection extends Exception {
	public OverdraftProtection() {
		super("ERROR: Transaction declined!! This transaction will cause overdraft or zero balance");
	}
}

abstract class BankAccount {
	public static double balance = 0;
	private int numDeposits = 0;
	public static int numWithdrawals = 0;
	private double aInteresRate;

	public BankAccount(double balance, double aInteresRate) {
		this.balance = balance;
		this.aInteresRate = aInteresRate;
	}

	public double getBalance() {
		return balance;
	}

	public void depositAmount(double depositAmount) throws InvalidAmount {
		this.balance += depositAmount;
		this.numDeposits++;
	}

	public void withdrawalAmount(double withdrawalAmount) throws InvalidAmount, OverdraftProtection {
		this.balance -= withdrawalAmount;
		this.numWithdrawals++;
	}

	// a method that updates the balance by calculating the monthly interest earned
	// by the account, and adding this
	// interest to the balance.
	public double calcInteresRate() {
		double monthlyIntRate;
		double monthlyInterest;
		monthlyIntRate = ((aInteresRate / 100) / 12);
		monthlyInterest = (balance * monthlyIntRate);
		balance = (balance + monthlyInterest);
		return balance;
	}

	public double montlyProcess() {
		double mp;
		mp = calcInteresRate();
		this.numDeposits = 0;
		this.numWithdrawals = 0;
		return mp;
	}

}

class SavingsAccount extends BankAccount {
	// field status represents if an account is active or not. true = active, false
	// = inactive.
	boolean status = false;
	double savingsBalance = 0;

	public SavingsAccount(double balance, double aInteresRate) {
		super(balance, aInteresRate);

	}

	public boolean accountStatus() {
		if (BankAccount.balance < 25) {
			status = false; // not active
		} else {
			status = true; // active
		}
		return status;
	}

	public void withdrawalAmount(double withdrawalAmount) throws InvalidAmount, OverdraftProtection {
		double minBalance = 25;
		if (!(withdrawalAmount > 0)) {
			throw new InvalidAmount();
		}
		if (withdrawalAmount > balance) {
			throw new OverdraftProtection();
		}
		if (balance - withdrawalAmount <= minBalance) {
			System.out.println("Your balance is less than minimum balance. Your account is now INACTIVE");
			status = false;
		}
		if (accountStatus() == true) {
			super.withdrawalAmount(withdrawalAmount);
			if (BankAccount.numWithdrawals > 4) {
				System.out.println("You have exceeded monthly limit of withdrawals. Fee of $1 charged\n");
				double serviceCharge = 1;
				balance = (balance - serviceCharge);

			}
		}
	}

	public void depositAmount(double depositAmount) throws InvalidAmount {
		double initialBalance = balance;
		if (!(depositAmount > 0)) {
			throw new InvalidAmount();
		} else {
			if (balance < 25 && depositAmount + balance > initialBalance) {
				System.out.println("Your account is now ACTIVE\n");

			}

		}
		balance = balance + depositAmount;
	}

	public double monthlyProcess() {
		double mp;
		mp = super.montlyProcess();
		return mp;
	}

}

public class SavingsAccountDemo {

	public static void main(String[] args) throws InvalidAmount, OverdraftProtection, InvalidInput{
		Scanner input = new Scanner(System.in);
		char choice;
		double balance;
		int interestRate;

		System.out.print("Enter beginning balance :$");
		balance = input.nextDouble();
		System.out.print("Enter interest rate(whole number) :%");
		interestRate = input.nextInt();
		input.nextLine();

		BankAccount obj = new SavingsAccount(balance, interestRate);
		do {

			System.out.println("Enter D to deposit");
			System.out.println("Enter W to Withdraw");
			System.out.println("Enter B for Balance");
			System.out.println("Enter M for Monthly Process");
			System.out.println("Enter E to Exit");
			choice = input.next().toUpperCase().charAt(0);

			switch (choice) 
			case 'D':
				System.out.print("Enter the amount you want to Deposit :$");
				try {
					obj.depositAmount(input.nextDouble());
				} catch (InvalidAmount e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'W':
				System.out.print("Enter the amount you want to withdraw :$");
				try {
					obj.withdrawalAmount(input.nextDouble());
				
				} catch (InvalidAmount | OverdraftProtection e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException  e) {
					System.out.println("Error");
				}
				break;
			case 'B':
				System.out.printf("Your Balance is: %.2f\n", obj.getBalance());
				break;
			case 'M':
				System.out.printf("Your Balance after Monthly process is: %.2f\n", obj.montlyProcess());
				break;
			default:
				if (choice == 'E') {
					break;
				} else {
					System.out.println("Invalid choice. Try again\n");
				}

				break;
			}

		} while (choice != 'e' && choice != 'E');
		input.close();
		System.out.printf("Balance : $%.2f\n", obj.balance);
		System.out.println("Thank you. Bye");
		System.exit(0);
	}

}

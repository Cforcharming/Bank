package Bank;

class BankAccount {

	private int accNo;
	private String accName;

	double getBalance() {
		return balance;
	}

	private double balance;

	BankAccount(int accNo, String accName) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0;
	}

	void deposit(double amount) {
		balance += amount;
	}

	void withdraw(double amount) {
		balance -= amount;
	}

	public String toString() {
		return "Account number: " + accNo
				+ "\n" +"Account name: " + accName
				+ "\n" +"Balance: " + balance
				+ "\n";
	}
}

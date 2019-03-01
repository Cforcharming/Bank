package Bank;

public class BankAccountTest {

	public static void main(String[] args) {

		BankAccount acc1 = new BankAccount(0b01111111111111111111111111111111, "John Smith");
		System.out.println(acc1);
		acc1.deposit(500);
		acc1.withdraw(400);
		System.out.println(acc1);

		BankAccount acc2 = new CurrentAccount(0b10000000000000000, "Tom Will");
		System.out.println(acc2);
		acc2.deposit(3000);
		acc2.withdraw(3500);
		System.out.println(acc2);
	}
}

package Bank;

class CurrentAccount extends BankAccount {

    CurrentAccount(int accNo, String accName) {
        super(accNo, accName);
    }

    void withdraw(double amount) {
        if (amount <= getBalance() + 500) {
            super.withdraw(amount);
        } else {
            System.out.println("Illegal");
        }
    }
}

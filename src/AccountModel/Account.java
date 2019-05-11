package AccountModel;

/**
 * store the information of a user in the memory
 * one instance of this class represents one particular
 * account.
 * @see AccountDao
 * @author zhanghanwen
 * @version 1.0
 */
public class Account {

    /**
     * saver account
     */
    public static final int SAVER = 1;

    /**
     * junior account
     */
    public static final int JUNIOR = 2;

    /**
     * current account
     */
    public static final int CURRENT = 3;

    /**
     * normal means that the account status is fine.
     */
    public static final int NORMAL = 4;

    /**
     * suspend means the account cannot perform as it is expected to.
     * If the balance is minus for saver and junior accounts,
     * or the current account owes more than 2000, the status will be set
     * to suspend, until the debts are paid.
     */
    public static final int SUSPEND = 5;

    /**
     * close means the account is unregistered, but yet not deleted
     * from the database. No action shall be performed ever, and
     * before it is closed, there shall be no balance remaining.
     */
    public static final int CLOSE = 6;

    private String name;
    private String accountNo;
    private String pin;
    private double balance;
    private int status;
    private int type;
    private String address;
    private int birthYear;
    private int birthMonth;
    private int birthDate;

    /**
     * This is constructor is used for new user's registry
     * @param name the user's name
     * @param pin the PIN number
     * @param type type of the account
     * @param balance the balance
     * @param accountNo the account number
     * @param address the address of the user
     * @param birthYear the birth year of the user
     * @param birthMonth the birth year of the user
     * @param birthDate the birth year of the user
     */
    public Account(String name, String accountNo, String pin, double balance, int status, int type, String address, int birthYear, int birthMonth, int birthDate) {
        this.name = name;
        this.accountNo = accountNo;
        this.pin = pin;
        this.balance = balance;
        this.status = status;
        this.type = type;
        this.address = address;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public String toString() {
        return getAccountNo() + ","
                + getName() + ","
                + getPin() + ","
                + getAddress() + ","
                + getBirthYear() + "-"
                + getBirthMonth() + "-"
                + getBirthDate() + ","
                + getBalance() + ","
                + getStatus() + ",";
    }
}

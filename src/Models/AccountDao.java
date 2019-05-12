package Models;

import java.io.*;
import java.util.ArrayList;

/**
 * provide access for user information from the files
 * and stores the data in the memory, in the form of
 * {@code ArrayList&lgtAccount&gt}.
 * This Object also synchronise the file and the memory
 * each time the memory is changed.
 * @see Account
 * @author zhanghanwen
 * @version 1.2
 */
public class AccountDao {

    /**
     * the only instance of the {@code Account} class in the program
     */
    volatile private Account account;

    public AccountDao() {}

    /**
     * The method for log in. The parameters are after the regex at the pages
     * @param accountNo the account number
     * @param pin the PIN number
     * @return true success<br />false not found
     */
    public boolean login(String accountNo, String pin) {

        Account temp = getAccountFromNumber(accountNo);

        if (temp == null) {
            return false;
        }

        else if (!temp.getPin().equals(pin)) {
            return false;
        }

        this.account = temp;
        return true;
    }

    /**
     * check and register new user
     * @return 0 OK<br />1 blacklist<br />2 cannot apply current account<br />
     *          3 wrong format of date<br />4 empty
     */
    public int register(String name, String address, String date, String type) {

        try (
                FileReader blackReader = new FileReader("resources/data/blacklist.csv");
                LineNumberReader blackLine = new LineNumberReader(blackReader)
        ) {

            String blackString;
            blackLine.readLine();
            while (true) {
                blackString = blackLine.readLine();
                String[] splits;
                if (blackString != null) {
                    splits = blackString.split(",");
                } else {
                    break;
                }
                if (name.equals(splits[0])) {
                    return 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int trueType;
        switch (type) {
            case "saver account":
                trueType = Account.SAVER;
                break;
            case "junior account":
                trueType = Account.JUNIOR;
                break;
            case "current account":
                trueType = Account.CURRENT;
                break;
            default:
                return 3;
        }

        //the regex that checks the date in the format of yyyy-mm-dd
        //it also checks the validation of the date
        if (!date.matches(
                "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|" +
                "[0-9][1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|" +
                "[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|" +
                "[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|" +
                "((0[48]|[2468][048]|[3579][26])00))-02-29) ")
        ) {
            return 3;
        }

        if (name.equals("") || address.equals("")) {
            return 4;
        }

        String[] dates = date.split("-");

        int year;
        int month;
        int day;

        year = Integer.parseInt(dates[0]);
        month = Integer.parseInt(dates[1]);
        day = Integer.parseInt(dates[2]);

        if (year >= 2013 && trueType == Account.CURRENT) {
            return 2;
        }

        String accountNo = generateAccountNo(trueType);
        String pin = accountNo.split("a")[1] + "0";

        //finally
        this.account = new Account(
                name,
                accountNo,
                pin,
                0,
                Account.NORMAL,
                trueType,
                address,
                year,
                month,
                day
        );
        writeFile();
        return 0;
    }

    /**
     * deposit by cash
     * @param money how much
     * @param pin PIN
     * @return 0 OK<br />1 wrong format<br />4 PIN error
     */
    public int depositCash(String money, String pin) {

        if (pin.equals("") || !money.matches("^([1-9][0-9]*)+(.[0-9]{1,2})?$")) {
            return 1;
        } else if (!pin.equals(account.getPin())) {
            return 4;
        }
        //noinspection SynchronizeOnNonFinalField
        synchronized (account) {
            account.setBalance(account.getBalance() + Double.parseDouble(money));
        }
        updateFile(account);
        return 0;
    }

    /**
     * deposit to others by cash
     * @param accountNo the account number that to be transferred
     * @param money how much
     * @param pin PIN
     * @return 0 OK<br />1 wrong format<br />2 no account<br />3 not enough
     *          <br />4 PIN error
     */
    public int transferCash(String accountNo, String money, String pin) {

        double current = account.getBalance();
        int stat = withdraw(money, pin);

        if (stat != 0) {
            return stat;
        } else if (accountNo.equals("") || !money.matches("^([1-9][0-9]*)+(.[0-9]{1,2})?$")) {
            return 1;
        }

        Account temp = getAccountFromNumber(accountNo);

        if (temp == null) {
            account.setBalance(current);
            updateFile(account);
            return 2;
        }

        temp.setBalance(temp.getBalance() + Double.parseDouble(money));

        if (temp.getAccountNo().equals(account.getAccountNo())) {
            account = temp;
        }
        updateFile(temp);
        return 0;
    }

    /**
     * deposit to others by cheque
     * @param accountNo the account number that to be transferred
     * @param money how much
     * @param pin PIN
     * @return 0 OK<br />1 wrong format<br />2 no account<br />3 not enough
     *          <br />4 PIN error<br />5 account suspended
     */
    public int transferCheque(String accountNo, String money, String pin) {

        int stat = withdraw(money, pin);
        if (stat != 0) {
            return stat;
        }

        if (accountNo.equals("")) {
            return 1;
        }

        Account temp = getAccountFromNumber(accountNo);
        if (temp == null) {
            return 2;
        }
        if (temp.getStatus() == Account.SUSPEND) {
            return 5;
        }

        String chequeNo = generateChequeNo();
        try (
                FileWriter accountWriter = new FileWriter("resources/data/cheque.csv", true)
        ) {
            String preparedString = "\n" + chequeNo + "," + money + "," + accountNo + ",";
            accountWriter.write(preparedString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * This method clears off cheques
     * @return true cleared<br />
     *          false nothing to clear
     */
    public boolean clearCheque() {
        try (
                FileReader accountReader = new FileReader("resources/data/cheque.csv");
                LineNumberReader accountLine = new LineNumberReader(accountReader)
        ) {
            Account temp = null;
            String accountString;
            accountLine.readLine();
            while (true) {
                accountString = accountLine.readLine();
                if (accountString == null) {
                    break;
                }
                String[] splits = accountString.split(",");
                temp = getAccountFromNumber(splits[2]);
                temp.setBalance(temp.getBalance() + Double.parseDouble(splits[1]));
                updateFile(temp);

                if (temp.getAccountNo().equals(account.getAccountNo())) {
                    account = temp;
                }
            }
            if (temp == null) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileWriter accountWriter = new FileWriter("resources/data/cheque.csv");
                BufferedWriter accountBufferedWriter = new BufferedWriter(accountWriter)
                ) {
            accountBufferedWriter.write("accountNo,money,account,");
        } catch (IOException ignored) {}

        return true;
    }

    /**
     * withdraw from account
     * @param money how much
     * @param pin PIN
     * @return 0 OK<br />1 wrong format<br />3 not enough
     *          <br />4 PIN error
     */
    public int withdraw(String money, String pin) {

        if (pin.equals("") || !money.matches("^([1-9][0-9]*)+(.[0-9]{1,2})?$")) {
            return 1;
        } else if (!pin.equals(account.getPin())) {
            return 4;
        }

        //noinspection SynchronizeOnNonFinalField
        synchronized (account) {
            double current = account.getBalance();
            double after = current - Double.parseDouble(money);

            if (account.getType() == Account.CURRENT) {
                if (after < -2000) {
                    return 3;
                }
            } else {
                if (after < 0) {
                    return 3;
                }
            }
            account.setBalance(after);
        }
        updateFile(account);

        return 0;
    }

    /**
     * alter the file, update the user
     * @param anotherAccount the account that to be updated
     */
    public void updateFile(Account anotherAccount) {

        ArrayList<String> lines = new ArrayList<>();

        try (
                FileReader accountReader = new FileReader("resources/data/accountStat.csv");
                LineNumberReader accountLineReader = new LineNumberReader(accountReader)
        ) {
            String accountString = accountLineReader.readLine();
            lines.add(accountString);
            while (true) {
                accountString = accountLineReader.readLine();
                String[] splits;
                if (accountString != null) {
                    splits = accountString.split(",");

                    if (anotherAccount.getAccountNo().equals(splits[0])) {
                        lines.add(anotherAccount.toString());
                    } else {
                        lines.add(accountString);
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileWriter accountWriter = new FileWriter("resources/data/accountStat.csv");
                BufferedWriter accountBufferedWriter = new BufferedWriter(accountWriter)
        ) {
            for (int i = 0; i < lines.size() - 1; i++) {
                accountBufferedWriter.write(lines.get(i));
                accountBufferedWriter.newLine();
            }
            accountBufferedWriter.write(lines.get(lines.size() - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * generate account number in the format of
     * account type "a" count
     * <br />e.g.<br />
     * 1a001, type of account: 1 number of people: 1
     * @param type the type of account
     * @return the accountNo
     */
    private String generateAccountNo(int type) {
        int count = 1;
        try (
                FileReader accountReader = new FileReader("resources/data/accountStat.csv");
                LineNumberReader accountLine = new LineNumberReader(accountReader)
        ) {

            String accountString;
            accountLine.readLine();
            while (true) {

                accountString = accountLine.readLine();
                String[] splits;
                if (accountString != null) {
                    splits = accountString.split(",");
                } else {
                    break;
                }
                if (splits[0].split("a")[0].equals("" + type)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count < 10) {
            return "" + type + "a00" + count;
        } else if (count < 100) {
            return "" + type + "a0" + count;
        } else {
            return "" + type + "a" + count;
        }
    }

    /**
     * generate cheque number in the format of
     * number
     * e.g. 0001
     * @return the chequeNo
     */
    private String generateChequeNo() {
        int count = 1;
        try (
                FileReader accountReader = new FileReader("resources/data/cheque.csv");
                LineNumberReader accountLine = new LineNumberReader(accountReader)
        ) {

            String accountString;
            accountLine.readLine();
            while (true) {
                accountString = accountLine.readLine();
                if (accountString == null) {
                    break;
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count < 10) {
            return "000" + count;
        } else if (count < 100) {
            return "000" + count;
        } else {
            return "" + count;
        }
    }

    /**
     * append the user to the file after the registration
     */
    private void writeFile() {
        try (
                FileWriter accountWriter = new FileWriter("resources/data/accountStat.csv", true)
                ) {
            String preparedString = "\n" + account.toString();
            accountWriter.write(preparedString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the account instance from the account number
     * @param accountNo the account No. of the account
     * @return the account instance, null if not found
     */
    private Account getAccountFromNumber(String accountNo) {

        Account temp = null;

        try (
                FileReader accountReader = new FileReader("resources/data/accountStat.csv");
                LineNumberReader accountLine = new LineNumberReader(accountReader)
        ) {

            String accountString;
            accountLine.readLine();
            while (true) {

                accountString = accountLine.readLine();
                String[] splits;
                if (accountString != null) {
                    splits = accountString.split(",");
                } else {
                    break;
                }
                if (splits[0].equals(accountNo) && !splits[6].equals("6")) {

                    String[] date = splits[4].split("-");
                    int status;
                    status = (splits[6].equals("4"))? Account.NORMAL: Account.SUSPEND;
                    int type;
                    switch (splits[0].split("a")[0]) {
                        case "1":
                            type = Account.SAVER;
                            break;
                        case "2":
                            type = Account.JUNIOR;
                            break;
                        case "3":
                        default:
                            type = Account.CURRENT;
                            break;
                    }
                    temp = new Account(
                            splits[1],
                            splits[0],
                            splits[2],
                            Double.parseDouble(splits[5]),
                            status,
                            type,
                            splits[3],
                            Integer.parseInt(date[0]),
                            Integer.parseInt(date[1]),
                            Integer.parseInt(date[2])
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Account getAccount() {
        return account;
    }
}

package AccountModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * provide access for user information from the files
 * and stores the data in the memory, in the form of
 * {@code ArrayList<Account>}.
 * This Object also synchronise the file and the memory
 * each time the memory is changed.
 * @see Account
 * @author zhanghanwen
 * @version 1.0
 */
public class AccountDao {

    private Account account;

    public AccountDao() {
    }

    /**
     * The method for log in. The parameters are after the regex at the pages
     * @param accountNo the account number
     * @param pin the PIN number
     * @return true success<br />false not found
     */
    public boolean login(String accountNo, String pin) {
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
                 if (splits[0].equals(accountNo) && splits[2].equals(pin) && !splits[6].equals("6")) {

                    System.out.println("@AccountDao::login: " + accountString);
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
                    this.account = new Account(
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
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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


        if (!date.matches(
                "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|" +
                "[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|" +
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
     * generate account number in the format of
     * account type "a" count
     * e.g.
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

    public Account getAccount() {
        return account;
    }
}

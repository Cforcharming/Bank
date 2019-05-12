package Models;

import org.junit.jupiter.api.Test;

class AccountDaoTest {

    private AccountDao accountDao = new AccountDao();

    @Test
    void login() {
        accountDao.login("2a001", "0010");
    }

    @Test
    void register() {
        accountDao.register("lalala", "aaa", "1999-01-01", "current account");
    }

    @Test
    void updateFile() {
        accountDao.login("2a001", "0010");
        accountDao.getAccount().setBalance(10000);
        accountDao.updateFile(accountDao.getAccount());
    }

    @Test
    void depositCash() {
        accountDao.login("2a001", "0010");
        accountDao.depositCash("100", "0010");
    }

    @Test
    void withdraw() {
        accountDao.login("2a001", "0010");
        accountDao.withdraw("10", "0010");
        accountDao.login("1a001", "0010");
        accountDao.withdraw("500", "0010");
        accountDao.login("3a001", "0010");
        accountDao.withdraw("6000", "0010");
    }

    @Test
    void transferCash() {
        accountDao.login("3a001", "0010");
        accountDao.transferCash("2a001", "6000", "0010");
    }
}
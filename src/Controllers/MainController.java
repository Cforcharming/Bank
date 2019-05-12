package Controllers;

import Models.AccountDao;

/**
 * The main controller that controls almost everything
 * @see PanelController
 * @see AccountDao
 * @version 1.0
 * @author zhanghanwen
 */
public class MainController {

    private PanelController panelController;
    private AccountDao accountDao;

    public MainController() {
        this.panelController = new PanelController();
        this.accountDao = new AccountDao();
    }

    public PanelController getPanelController() {
        return panelController;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }
}

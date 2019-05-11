package Main;

import Controllers.MainController;
import Pages.LoginPanel;

/**
 * The access of the main {@code Thread}.
 * Initialise the daemon {@code Thread} for back-stage management.
 * @see MainController
 * @author zhanghanwen
 * @version 1.0
 */
public class Bank {

    public static void main(String[] args) {

        MainController mainController = new MainController();

        LoginPanel loginPanel = new LoginPanel(mainController);
        mainController.getPanelController().push(loginPanel);
    }
}

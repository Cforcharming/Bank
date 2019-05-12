package Main;

import Controllers.MainController;
import Pages.LoginPanel;

/**
 * The access of the main method.
 * Initialise the controllers for back-stage management.
 * @see MainController
 * @author zhanghanwen
 * @version 1.1
 */
public class Bank {

    public static void main(String[] args) {

        MainController mainController = new MainController();

        LoginPanel loginPanel = new LoginPanel(mainController);
        mainController.getPanelController().push(loginPanel);
    }
}

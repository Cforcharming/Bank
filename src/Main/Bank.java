package Main;

import Controllers.PanelController;
import Pages.LoginPanel;

/**
 * The access of the main {@code Thread}.
 * Initialise the daemon {@code Thread} for back-stage management.
 * @see PanelController
 * @author zhanghanwen
 * @version 0.1
 */
public class Bank {

    public static void main(String[] args) {

        PanelController panelController = new PanelController();

        LoginPanel loginPanel = new LoginPanel(panelController);
        panelController.push(loginPanel);
    }
}

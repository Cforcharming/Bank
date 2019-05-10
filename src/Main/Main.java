package Main;

import Welcome.LoginPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The access of the main <code>Thread</code>.
 * Initialise the <code>MainManagement</code> daemon <code>Thread</code> for back-stage management.
 * @see MainManagement
 * @author zhanghanwen
 * @version 0.1
 */
public class Main {

    private static JFrame mainFrame;
    private static ArrayList<AutoRefreshableJPanel> Pages;

    private static void initGUI() {

        mainFrame = new JFrame("Charming Bank");
        mainFrame.setSize(400, 800);
        mainFrame.setLocation(300, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBackground(new Color(255, 255, 255));

        Pages = new ArrayList<>(7); //WORKS AS THE STACK
    }

    public static void main(String[] args) {

        initGUI();

        MainManagement mainManagement = new MainManagement(Pages, mainFrame);
        Thread thread = new Thread(mainManagement);
        thread.setDaemon(true);
        thread.start();

        LoginPane loginPane = new LoginPane(mainManagement);
        mainManagement.push(loginPane);
    }
}

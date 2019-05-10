package Welcome;

import Main.MainManagement;

import javax.swing.*;
import java.awt.*;

/**
 * Log in page, the first page showed.
 * @author zhanghanwen
 * @version 0.1
 */
public class LoginPane extends Main.AutoRefreshableJPanel {

    private MainManagement mainManagement;

    public LoginPane(MainManagement mainManagement) {

        this.setBackground(new Color(255, 255, 255));

        JPanel head = new JPanel();
        head.setLayout(new GridLayout(1, 2));
        JLabel name = new JLabel("Charming Bank");
        JLabel login = new JLabel("LOG IN");
        Font font1 = new Font("Menlo", Font.BOLD, 30);
        name.setFont(font1);
        login.setFont(font1);
        name.setForeground(new Color(255, 23, 229));
        login.setForeground(new Color(255, 23, 229));

        head.setBackground(new Color(255, 255, 255));
        name.setBackground(new Color(255, 255, 255));
        login.setBackground(new Color(255, 255, 255));

        head.add(name);
        head.add(login);
        this.add(head);
        this.setVisible(false);

        this.mainManagement = mainManagement;
    }

    public void refresh() {

    }
}

package Pages;

import Util.FontDao;
import Util.IconDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Log in page, the first page showed.
 * @author zhanghanwen
 * @version 0.1
 */
public class LoginPanel extends AutoRefreshableJPanel implements MouseListener {

    private PanelController panelController;
    private JTextField nameText;
    private JTextField passwordText;
    private JLabel logInButton;
    private JLabel addUserButton;

    public LoginPanel(PanelController panelController) {

        this.setBackground(Color.WHITE);
        this.setLayout(null);

        JLabel title = new JLabel("BANK");
        {
            title.setBounds(65, 30, 300, 70);
            title.setFont(FontDao.getFont(FontDao.SOURCE_CODE_PRO_BOLD, 70));
            title.setForeground(new Color(255, 25, 230));
            title.setBackground(Color.WHITE);
        }

        JLabel login = new JLabel("log in");
        {
            login.setBounds(120, 120, 300, 40);
            login.setFont(FontDao.getFont(FontDao.IMPACT, 25));
            login.setForeground(new Color(58, 58, 58));
            login.setBackground(Color.WHITE);
        }

        JLabel name = new JLabel("Name");
        JLabel pin = new JLabel("PIN");
        {
            name.setForeground(new Color(58, 58, 58));
            pin.setForeground(new Color(58, 58, 58));
            name.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            pin.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            name.setBounds(45, 175, 50, 20);
            pin.setBounds(45, 205, 50, 20);
        }

        nameText = new JTextField();
        passwordText = new JPasswordField();
        {
            nameText.setBounds(105, 175, 155, 20);
            passwordText.setBounds(105, 205, 155, 20);
        }

        {
            logInButton = new JLabel(IconDao.getIcon(IconDao.RIGHT_CIRCLE_FILL, 32, 32), SwingConstants.CENTER);
            addUserButton = new JLabel(IconDao.getIcon(IconDao.ADDUSER, 32, 32), SwingConstants.CENTER);
            logInButton.setBounds(170, 255, 32, 32);
            addUserButton.setBounds(60, 255, 32, 32);

            logInButton.addMouseListener(this);
            addUserButton.addMouseListener(this);
        }

        {
            this.add(title);
            this.add(login);
            this.add(name);
            this.add(pin);
            this.add(nameText);
            this.add(passwordText);
            this.add(logInButton);
            this.add(addUserButton);
        }

        this.setVisible(false);

        this.panelController = panelController;
    }

    @Override
    public void refresh() {}

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("@LoginPanel\nName: " + nameText.getText() + ", PIN: " + passwordText.getText());

        if (e.getSource().equals(logInButton)) {
            System.out.println("Log in button clicked");
        } else if (e.getSource().equals(addUserButton)) {
            System.out.println("Add user button clicked");
            panelController.push(new RegisterPanel(panelController));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

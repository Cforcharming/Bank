package Pages;

import Controllers.PanelController;
import Util.FontDao;
import Util.IconDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * the registry page
 * @see AutoRefreshableJPanel
 * @see PanelController
 * @author zhanghanwen
 * @version 0.1
 */
public class RegisterPanel extends AutoRefreshableJPanel implements MouseListener {

    private PanelController panelController;
    private JLabel backButton;
    private JTextField nameText;
    private JTextField addressText;
    private JTextField dateText;
    private JComboBox<String> typeBox;
    private JLabel logInButton;

    RegisterPanel(PanelController panelController) {

        this.setBackground(Color.WHITE);
        this.setLayout(null);

        backButton = new JLabel(IconDao.getIcon(IconDao.BACK, 25, 25), SwingConstants.CENTER);
        backButton.setBounds(10, 10, 25, 25);
        backButton.addMouseListener(this);

        JLabel registry = new JLabel("Add your account");
        {
            registry.setBounds(60, 75, 300, 40);
            registry.setFont(FontDao.getFont(FontDao.IMPACT, 25));
            registry.setForeground(new Color(255, 25, 230));
            registry.setBackground(Color.WHITE);
        }

        JLabel name = new JLabel("Name");
        JLabel address = new JLabel("Address");
        JLabel date = new JLabel("Date of Birth");
        JLabel type = new JLabel("Account Type");
        {
            name.setForeground(new Color(58, 58, 58));
            address.setForeground(new Color(58, 58, 58));
            date.setForeground(new Color(58, 58, 58));
            type.setForeground(new Color(58, 58, 58));
            name.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            address.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            date.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            type.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            name.setBounds(20, 155, 100, 20);
            address.setBounds(20, 185, 100, 20);
            date.setBounds(20, 215, 100, 20);
            type.setBounds(20, 245, 100, 20);
        }

        nameText = new JTextField();
        addressText = new JTextField();
        dateText = new JTextField();
        typeBox = new JComboBox<>();
        {
            nameText.setBounds(115, 155, 145, 20);
            addressText.setBounds(115, 185, 145, 20);
            dateText.setBounds(115, 215, 145, 20);
            typeBox.addItem("saver account");
            typeBox.addItem("junior account");
            typeBox.addItem("current account");
            typeBox.setBounds(115, 245, 145, 20);
        }

        logInButton = new JLabel(IconDao.getIcon(IconDao.NEXT, 32, 32), SwingConstants.CENTER);
        logInButton.setBounds(125, 300, 32, 32);
        logInButton.addMouseListener(this);

        {
            this.add(backButton);
            this.add(registry);
            this.add(name);
            this.add(address);
            this.add(date);
            this.add(type);
            this.add(nameText);
            this.add(addressText);
            this.add(dateText);
            this.add(typeBox);
            this.add(logInButton);
        }

        this.setVisible(false);

        this.panelController = panelController;
    }

    @Override
    protected void refresh() {}

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("@RegisterPanel");
        System.out.println(
                "name: " + nameText.getText()
                        + " address: " + addressText.getText()
                        + " birth: " + dateText.getText()
                        + " type: " + Objects.requireNonNull(typeBox.getSelectedItem()).toString());
        if (e.getSource().equals(logInButton)) {
            System.out.println("Log in button clicked");
            panelController.pop();
            panelController.push(new MainPanel(panelController));
        } else if (e.getSource().equals(backButton)) {
            System.out.println("Back button clicked");
            panelController.pop();
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

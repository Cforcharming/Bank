package Pages;

import Controllers.MainController;
import Util.FontDao;
import Util.IconDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * This page is used when the deposit button is clicked
 * @see MainPanel
 * @see Models.AccountDao
 * @author zhanghanwen
 * @version 1.1
 */
public class DepositPanel extends AutoRefreshableJPanel implements MouseListener {

    private MainController mainController;
    private JLabel backButton;
    private JTextField moneyText;
    private JPasswordField pinText;
    private JComboBox<String> typeBox;
    private JLabel depositButton;

    public DepositPanel(MainController mainController) {

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        backButton = new JLabel(IconDao.getIcon(IconDao.BACK, 25, 25), SwingConstants.CENTER);
        backButton.setBounds(10, 10, 25, 25);
        backButton.addMouseListener(this);

        JLabel title = new JLabel("Deposit");
        {
            title.setBounds(20, 80, 300, 25);
            title.setFont(FontDao.getFont(FontDao.IMPACT, 25));
            title.setForeground(new Color(255, 25, 230));
            title.setBackground(Color.WHITE);
        }

        JLabel money = new JLabel("Credits:");
        JLabel password = new JLabel("Your PIN:");
        JLabel type = new JLabel("Via");
        {
            money.setForeground(new Color(58, 58, 58));
            password.setForeground(new Color(58, 58, 58));
            type.setForeground(new Color(58, 58, 58));
            money.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            password.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            type.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            money.setBounds(20, 155, 100, 20);
            password.setBounds(20, 185, 100, 20);
            type.setBounds(20, 215, 100, 20);
        }

        moneyText = new JTextField();
        pinText = new JPasswordField();
        typeBox = new JComboBox<>();
        {
            moneyText.setBounds(115, 155, 145, 20);
            pinText.setBounds(115, 185, 145, 20);
            typeBox.addItem("cash");
            typeBox.addItem("cheque");
            typeBox.setBounds(115, 215, 145, 20);
        }

        depositButton = new JLabel(IconDao.getIcon(IconDao.NEXT, 32, 32), SwingConstants.CENTER);
        depositButton.setBounds(125, 270, 32, 32);
        depositButton.addMouseListener(this);

        this.add(backButton);
        this.add(title);
        this.add(money);
        this.add(password);
        this.add(type);
        this.add(moneyText);
        this.add(pinText);
        this.add(typeBox);
        this.add(depositButton);
        this.setVisible(false);
        this.mainController = mainController;
    }

    @Override
    protected void refresh() {}

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().equals(depositButton)) {

            int result;
            String password =  new String(pinText.getPassword());

            if (Objects.requireNonNull(typeBox.getSelectedItem()).toString().equals("cash")) {
                result = mainController.getAccountDao().depositCash(moneyText.getText(), password);
            } else {
                result = mainController.getAccountDao().depositCheque(moneyText.getText(), password);
            }

            switch (result) {
                case 0:
                    JOptionPane.showMessageDialog(this, "You have successfully deposited!", "Success", JOptionPane.PLAIN_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this, "You have input wrong format of credits!", "Warning", JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                    pinText.setText("");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(this, "You have input wrong PIN!", "Warning", JOptionPane.PLAIN_MESSAGE);
                    pinText.setText("");
                    break;
            }
        } else if (e.getSource().equals(backButton)) {
            mainController.getPanelController().pop();
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

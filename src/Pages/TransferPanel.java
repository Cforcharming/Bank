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
 * This page is used when the transfer button is clicked
 * @see MainPanel
 * @see Models.AccountDao
 * @author zhanghanwen
 * @version 1.1
 */
class TransferPanel extends AutoRefreshableJPanel implements MouseListener {

    private MainController mainController;
    private JLabel backButton;
    private JTextField accountText;
    private JTextField moneyText;
    private JPasswordField pinText;
    private JComboBox<String> typeBox;
    private JLabel transFer;

    TransferPanel(MainController mainController) {

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        backButton = new JLabel(IconDao.getIcon(IconDao.BACK, 25, 25), SwingConstants.CENTER);
        backButton.setBounds(10, 10, 25, 25);
        backButton.addMouseListener(this);

        JLabel title = new JLabel("Transfer");
        {
            title.setBounds(20, 80, 300, 25);
            title.setFont(FontDao.getFont(FontDao.IMPACT, 25));
            title.setForeground(new Color(255, 25, 230));
            title.setBackground(Color.WHITE);
        }

        JLabel account = new JLabel("Transfer to:");
        JLabel money = new JLabel("Credits:");
        JLabel password = new JLabel("Your PIN:");
        JLabel type = new JLabel("Via");
        {
            account.setForeground(new Color(58, 58, 58));
            money.setForeground(new Color(58, 58, 58));
            password.setForeground(new Color(58, 58, 58));
            type.setForeground(new Color(58, 58, 58));
            account.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            money.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            password.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            type.setFont(FontDao.getFont(FontDao.IMPACT, 17));
            account.setBounds(20, 155, 100, 20);
            money.setBounds(20, 185, 100, 20);
            password.setBounds(20, 215, 100, 20);
            type.setBounds(20, 245, 100, 20);
        }

        accountText = new JTextField();
        moneyText = new JTextField();
        pinText = new JPasswordField();
        typeBox = new JComboBox<>();
        {
            accountText.setBounds(115, 155, 145, 20);
            moneyText.setBounds(115, 185, 145, 20);
            pinText.setBounds(115, 215, 145, 20);
            typeBox.addItem("cash");
            typeBox.addItem("cheque");
            typeBox.setBounds(115, 245, 145, 20);
        }

        transFer = new JLabel(IconDao.getIcon(IconDao.NEXT, 32, 32), SwingConstants.CENTER);
        transFer.setBounds(125, 300, 32, 32);
        transFer.addMouseListener(this);

        this.add(backButton);
        this.add(title);
        this.add(account);
        this.add(money);
        this.add(password);
        this.add(type);
        this.add(accountText);
        this.add(moneyText);
        this.add(pinText);
        this.add(typeBox);
        this.add(transFer);

        this.setVisible(false);
        this.mainController = mainController;
    }

    @Override
    protected void refresh() {}

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().equals(transFer)) {

            int result;
            String password =  new String(pinText.getPassword());

            if (Objects.requireNonNull(typeBox.getSelectedItem()).toString().equals("cash")) {
                result = mainController.getAccountDao().transferCash(accountText.getText(), moneyText.getText(), password);
            } else {
                result = mainController.getAccountDao().transferCheque(accountText.getText(), moneyText.getText(), password);
            }

            switch (result) {
                case 0:
                    JOptionPane.showMessageDialog(this, "You have successfully deposited!", "Success", JOptionPane.PLAIN_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this, "You have input wrong format of credits", "Warning", JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                    pinText.setText("");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "The account No. does not exist!", "Warning", JOptionPane.PLAIN_MESSAGE);
                    accountText.setText("");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(this, "You don't have enough credits", "Warning", JOptionPane.PLAIN_MESSAGE);
                    moneyText.setText("");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(this, "You have input wrong PIN", "Warning", JOptionPane.PLAIN_MESSAGE);
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

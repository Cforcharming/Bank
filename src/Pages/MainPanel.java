package Pages;

import Controllers.PanelController;
import Util.FontDao;
import Util.IconDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

/**
 * The main page, which has the functions of
 * <ul>
 *     <li>deposit</li>
 *     <li>withdraw</li>
 *     <li>transfer</li>
 *     <li>clear</li>
 * </ul>
 * <br />
 * And display the data of
 * <ul>
 *     <li>balance</li>
 *     <li>personal credit</li>
 * </ul>
 * @author zhanghanwen
 * @version 0.1
 */
public class MainPanel extends AutoRefreshableJPanel implements MouseListener {

    private PanelController panelController;
    private double balance = 1000;
    private int credit = 100;
    private JLabel balanceLabel;
    private JLabel creditLabel;
    private JLabel deposit;
    private JLabel withdraw;
    private JLabel transfer;
    private JLabel clear;
    private JLabel ME;
    private DecimalFormat format;

    MainPanel(PanelController panelController) {

        format = new DecimalFormat("#.00");

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        JLabel balanceHeader = new JLabel("YOUR BALANCE");
        {
            balanceHeader.setBounds(60, 70, 300, 25);
            balanceHeader.setFont(FontDao.getFont(FontDao.SOURCE_CODE_PRO_BOLD, 25));
            balanceHeader.setForeground(Color.BLACK);
            balanceHeader.setBackground(Color.WHITE);
        }

        balanceLabel = new JLabel("RMB " + format.format(balance));
        {
            balanceLabel.setBounds(30, 100, 300, 35);
            balanceLabel.setFont(FontDao.getFont(FontDao.MENLO, 35));
            balanceLabel.setForeground(new Color(255, 25, 230));
            balanceLabel.setBackground(Color.WHITE);
        }

        JLabel creditHeader = new JLabel("your credit:");
        {
            creditHeader.setBounds(90, 150, 300, 15);
            creditHeader.setFont(FontDao.getFont(FontDao.MENLO, 15));
            creditHeader.setForeground(Color.BLUE);
            creditHeader.setBackground(Color.WHITE);
        }

        creditLabel = new JLabel("" + credit);
        {
            creditLabel.setBounds(200, 150, 300, 15);
            creditLabel.setFont(FontDao.getFont(FontDao.MENLO, 15));
            creditLabel.setForeground(Color.BLUE);
            creditLabel.setBackground(Color.WHITE);
        }

        JLabel depositText = new JLabel("deposit");
        JLabel withdrawText = new JLabel("withdraw");
        JLabel transferText = new JLabel("transfer");
        JLabel clearText = new JLabel("clear");
        {
            depositText.setBackground(Color.WHITE);
            withdrawText.setBackground(Color.WHITE);
            transferText.setBackground(Color.WHITE);
            clearText.setBackground(Color.WHITE);

            withdrawText.setFont(FontDao.getFont(FontDao.SOURCE_CODE_PRO_EXTRA_LIGHT, 15));
            depositText.setFont(FontDao.getFont(FontDao.SOURCE_CODE_PRO_EXTRA_LIGHT, 15));
            transferText.setFont(FontDao.getFont(FontDao.SOURCE_CODE_PRO_EXTRA_LIGHT, 15));
            clearText.setFont(FontDao.getFont(FontDao.SOURCE_CODE_PRO_EXTRA_LIGHT, 15));

            depositText.setBounds (75, 265, 80, 20);
            withdrawText.setBounds(165, 265, 80, 20);
            transferText.setBounds(70, 355, 80, 20);
            clearText.setBounds   (175, 355, 80, 20);

            deposit = new JLabel(IconDao.getIcon(IconDao.DEPOSIT, 64, 64));
            withdraw = new JLabel(IconDao.getIcon(IconDao.WITHDRAW, 64, 64));
            transfer = new JLabel(IconDao.getIcon(IconDao.TRANSFER, 64, 64));
            clear = new JLabel(IconDao.getIcon(IconDao.CLEAR, 64, 64));
            deposit.setBounds (65, 190, 80, 80);
            withdraw.setBounds(160, 190, 80, 80);
            transfer.setBounds(65, 285, 80, 80);
            clear.setBounds   (160, 285, 80, 80);

            deposit.addMouseListener(this);
            withdraw.addMouseListener(this);
            transfer.addMouseListener(this);
            clear.addMouseListener(this);
        }

        JPanel line = new JPanel();
        {
            line.setBackground(Color.LIGHT_GRAY);
            line.setBounds(0, 400, 300, 1);
        }

        JLabel THIS = new JLabel(IconDao.getIcon(IconDao.CREDITCARD_FILL, 32, 32));
        ME = new JLabel(IconDao.getIcon(IconDao.IDCARD, 32, 32));
        {
            THIS.setBounds(75, 420, 32, 32);
            ME.setBounds(200, 420, 32, 32);
            ME.addMouseListener(this);
        }

        this.add(balanceHeader);
        this.add(balanceLabel);
        this.add(creditHeader);
        this.add(creditLabel);
        this.add(deposit);
        this.add(depositText);
        this.add(withdraw);
        this.add(withdrawText);
        this.add(transfer);
        this.add(transferText);
        this.add(clear);
        this.add(clearText);
        this.add(line);
        this.add(THIS);
        this.add(ME);

        this.panelController = panelController;
    }

    @Override
    protected void refresh() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("@MainPanel: ");
        System.out.println("balance: " + balance + " credit: " + credit);
        if (e.getSource().equals(deposit)) {
            System.out.println("deposit clicked");
        } else if (e.getSource().equals(withdraw)) {
            System.out.println("withdraw clicked");
        } else if (e.getSource().equals(transfer)) {
            System.out.println("transfer clicked");
        } else if (e.getSource().equals(clear)) {
            System.out.println("clear clicked");
        } else if (e.getSource().equals(ME)) {
            panelController.push(new UserPanel(panelController));
            System.out.println("ME clicked");
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

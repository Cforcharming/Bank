package Pages;

import Controllers.MainController;
import Models.Account;
import Util.FontDao;
import Util.IconDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This panel is about the user status,
 * which includes the functions of
 * <ul>
 *     <li>suspend account</li>
 *     <li>resume account</li>
 *     <li>close account</li>
 *     <li>logout</li>
 * </ul>
 * @author zhanghanwen
 * @version 1.1
 */
public class UserPanel extends AutoRefreshableJPanel implements MouseListener {

    private MainController mainController;
    volatile private String name = "C for charming";
    private JLabel balanceHeader;
    private JLabel suspend;
    private JLabel resume;
    private JLabel close;
    private JLabel logout;
    private JLabel logoutText;
    private JLabel FUNCTIONS;

    UserPanel(MainController mainController) {

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        balanceHeader = new JLabel(name);
        {
            balanceHeader.setBounds(10, 40, 300, 25);
            balanceHeader.setFont(FontDao.getFont(FontDao.IMPACT, 25));
            balanceHeader.setForeground(new Color(255, 25, 230));
            balanceHeader.setBackground(Color.WHITE);
        }

        JLabel user = new JLabel(IconDao.getIcon(IconDao.USER, 32, 32));
        {
            user.setBounds(240, 40, 32, 32);
        }

        suspend = new JLabel("suspend account");
        {
            suspend.setFont(FontDao.getFont(FontDao.IMPACT, 20));
            suspend.setBackground(Color.WHITE);
            suspend.setBounds(0, 100, 300, 60);
            suspend.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(224, 224, 224)));
            suspend.addMouseListener(this);
        }

        resume = new JLabel("resume account");
        {
            resume.setFont(FontDao.getFont(FontDao.IMPACT, 20));
            resume.setBackground(Color.WHITE);
            resume.setBounds(0, 160, 300, 60);
            resume.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(224, 224, 224)));
            resume.addMouseListener(this);
        }

        close = new JLabel("close account");
        {
            close.setFont(FontDao.getFont(FontDao.IMPACT, 20));
            close.setBackground(Color.WHITE);
            close.setBounds(0, 220, 300, 60);
            close.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(224, 224, 224)));
            close.addMouseListener(this);
        }

        logout = new JLabel(IconDao.getIcon(IconDao.LOGOUT, 48, 48));
        logoutText = new JLabel("log out");
        {
            logoutText.setBackground(Color.WHITE);
            logoutText.setForeground(new Color(80, 80, 80));
            logoutText.setFont(FontDao.getFont(FontDao.IMPACT, 20));
            logout.setBounds(80, 330, 48, 48);
            logoutText.setBounds(155, 330, 100, 48);
            logout.addMouseListener(this);
            logoutText.addMouseListener(this);
        }

        JPanel line = new JPanel();
        {
            line.setBackground(Color.LIGHT_GRAY);
            line.setBounds(0, 400, 300, 1);
        }

        JLabel THIS = new JLabel(IconDao.getIcon(IconDao.IDCARD_FILL, 32, 32));
        FUNCTIONS = new JLabel(IconDao.getIcon(IconDao.CREDITCARD, 32, 32));
        {
            FUNCTIONS.setBounds(75, 420, 32, 32);
            THIS.setBounds(200, 420, 32, 32);
            FUNCTIONS.addMouseListener(this);
        }

        this.add(balanceHeader);
        this.add(user);
        this.add(suspend);
        this.add(resume);
        this.add(close);
        this.add(logout);
        this.add(logoutText);
        this.add(line);
        this.add(THIS);
        this.add(FUNCTIONS);

        this.mainController = mainController;
        this.refresh();
    }

    @Override
    protected void refresh() {
        Thread t = new Thread(() -> {
            while (true) {
                name = mainController.getAccountDao().getAccount().getName();
                balanceHeader.setText(name);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().equals(FUNCTIONS)) {
            mainController.getPanelController().pop();

        } else if (e.getSource().equals(logout) || e.getSource().equals(logoutText)) {
            mainController.getPanelController().pop();
            mainController.getPanelController().pop();

        } else if (e.getSource().equals(suspend)) {

            if (mainController.getAccountDao().getAccount().getStatus() == Account.SUSPEND) {
                JOptionPane.showMessageDialog(this, "Your account is already suspended.", "Warning", JOptionPane.PLAIN_MESSAGE);

            } else {
                mainController.getAccountDao().getAccount().setStatus(Account.SUSPEND);
                mainController.getAccountDao().updateFile(mainController.getAccountDao().getAccount());
                JOptionPane.showMessageDialog(this, "Your account is now suspended.", "Success", JOptionPane.PLAIN_MESSAGE);

            }
        } else if (e.getSource().equals(resume)) {

            if (mainController.getAccountDao().getAccount().getStatus() == Account.NORMAL) {
                JOptionPane.showMessageDialog(this, "Your account is currently normal!", "Warning", JOptionPane.PLAIN_MESSAGE);

            } else {
                mainController.getAccountDao().getAccount().setStatus(Account.NORMAL);
                mainController.getAccountDao().updateFile(mainController.getAccountDao().getAccount());
                JOptionPane.showMessageDialog(this, "Your account is now resumed.", "Success", JOptionPane.PLAIN_MESSAGE);

            }
        } else if (e.getSource().equals(close)) {

            if (mainController.getAccountDao().getAccount().getBalance() != 0) {
                JOptionPane.showMessageDialog(this, "You need to clear your balance before closing the accont.", "Success", JOptionPane.PLAIN_MESSAGE);
            } else {
                Object[] options ={ "yes", "no" };
                int m = JOptionPane.showOptionDialog(this, "Closing an account cannot be undone.\nAre you sure to close your account?", "Waring",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (m == 0) {
                    mainController.getAccountDao().getAccount().setStatus(Account.CLOSE);
                    mainController.getAccountDao().updateFile(mainController.getAccountDao().getAccount());
                    mainController.getPanelController().pop();
                    mainController.getPanelController().pop();
                }
            }
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

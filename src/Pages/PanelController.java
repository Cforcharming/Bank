package Pages;

import Main.Bank;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class is used for the back-stage management of the visions.
 * @see Bank
 * @see AutoRefreshableJPanel
 * @author zhanghanwen
 * @version 0.1
 */
public class PanelController {

    private ArrayList<AutoRefreshableJPanel> Pages;
    private JFrame mainFrame;

    public PanelController() {
        this.Pages = new ArrayList<>(3); //WORKS AS THE STACK;
        this.mainFrame = new MainFrame();
    }

    /**
     * Push a {@code JPanel} into the {@code Pages},
     * and then add it into {@code JFrame} for display.
     * @param panel The {@code AutoRefreshableJPanel} that to be pushed in.
     */
    public void push(AutoRefreshableJPanel panel) {

        if (Pages.size() > 0) {
            Pages.get(Pages.size() - 1).setVisible(false);
        }
        Pages.add(panel);
        panel.setVisible(true);
        mainFrame.getContentPane().add(Pages.get(Pages.size() - 1));
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    /**
     * Pop a {@code JPanel} out of the {@code Pages}
     */
    public void pop() {

        mainFrame.getContentPane().remove(Pages.get(Pages.size() - 1));
        if (Pages.size() > 0) {
            Pages.remove(Pages.size() - 1);
            Pages.get(Pages.size() - 1).setVisible(true);
            mainFrame.add(Pages.get(Pages.size() - 1));
        }
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
}

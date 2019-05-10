package Main;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This <code>Thread</code> is used for the main back-stage management.
 * @see Main
 * @see AutoRefreshableJPanel
 * @author zhanghanwen
 * @version 0.1
 */
public class MainManagement implements Runnable {

    private static final int NO_OPERATION = 0;

    private ArrayList<AutoRefreshableJPanel> Pages;
    private JFrame mainFrame;
    volatile private int beacon;

    MainManagement(ArrayList<AutoRefreshableJPanel> Pages, JFrame mainFrame) {
        this.Pages = Pages;
        this.mainFrame = mainFrame;
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            switch (beacon) {
                case NO_OPERATION:
                    break;
            }
        }
    }

    /**
     * Push a <code>JPanel</code> into the <code>Pages</code>,
     * and then add it into <code>JFrame</code> for display.
     * @param panel 被推入的<code>JPanel</code>
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
     * Pop a <code>JPanel</code> out of the <code>Pages</code>
     */
    public void pop() {

        mainFrame.getContentPane().remove(Pages.get(Pages.size()-1));
        if (Pages.size() > 0) {
            Pages.remove(Pages.size() - 1);
            Pages.get(Pages.size() - 1).setVisible(true);
            mainFrame.add(Pages.get(Pages.size() - 1));
        }
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    synchronized public void setBeacon(int beacon) {
        this.beacon = beacon;
    }

}

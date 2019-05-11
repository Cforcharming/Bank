package Pages;

import javax.swing.*;
import java.awt.*;

/**
 * The only {@code JFrame}, which is basically empty.
 * Only the last {@code AutoRefreshablePanel} from the
 * {@code ArrayList<AutoRefreshablePanel>} in the
 * {@code PanelController} will be put on this {@code JFrame}.
 * So, it is a stack.
 * @see Controllers.PanelController
 * @author zhanghanwen
 * @version 0.1
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        this.setTitle("BANK");
        this.setSize(300, 500);
        this.setLocation(400, 75);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setResizable(false);
    }
}

package Pages;

/**
 * An abstract class inherited from{@code javax.swing.JPanel},
 * such that every instance of it has the {@code refresh()} method.
 * @see javax.swing.JPanel
 * @author zhanghanwen
 * @version 0.1
 */
public abstract class AutoRefreshableJPanel extends javax.swing.JPanel {

    /**
     * Used for refreshing all the pages.
     */
    public abstract void refresh();
}

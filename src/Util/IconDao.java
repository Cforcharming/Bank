package Util;

import javax.swing.*;
import java.awt.*;

/**
 * get a certain icon
 * @author zhanghanwen
 * @version 0.1
 */
public class IconDao {

    public static final String ADDUSER = "resources/icons/adduser.png";
    public static final String CHECK = "resources/icons/check.png";
    public static final String CLOSE = "resources/icons/close.png";
    public static final String PROPERTYSAFETY = "resources/icons/propertysafety.png";
    public static final String RIGHT_CIRCLE_FILL = "resources/icons/right-circle-fill.png";
    public static final String USER = "resources/icons/user.png";

    public static ImageIcon getIcon(String nameURL, int width, int height) {
        ImageIcon icon = new ImageIcon(nameURL);
        Image temp = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(temp);
    }
}

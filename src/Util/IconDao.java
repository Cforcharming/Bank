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
    public static final String USER = "resources/icons/user.png";
    public static final String LOGIN = "resources/icons/login.png";
    public static final String LOGOUT = "resources/icons/logout.png";
    public static final String BACK = "resources/icons/left.png";
    public static final String NEXT = "resources/icons/xiayibu.png";
    public static final String DEPOSIT = "resources/icons/yucunkuan.png";
    public static final String WITHDRAW = "resources/icons/qukuanji.png";
    public static final String TRANSFER = "resources/icons/zhuanzhang.png";
    public static final String CLEAR = "resources/icons/tixian.png";
    public static final String CREDITCARD_FILL = "resources/icons/creditcard-fill.png";
    public static final String CREDITCARD = "resources/icons/creditcard.png";
    public static final String IDCARD_FILL = "resources/icons/idcard-fill.png";
    public static final String IDCARD = "resources/icons/idcard.png";

    public static ImageIcon getIcon(String nameURL, int width, int height) {
        ImageIcon icon = new ImageIcon(nameURL);
        Image temp = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(temp);
    }
}

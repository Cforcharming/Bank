package Util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * get a certain font
 * @author zhanghanwen
 * @version 0.1
 */
public class FontDao {

    public static final String MENLO = "resources/fonts/Menlo.ttc";
    public static final String HELVETICA_NEUE = "resources/fonts/HelveticaNeue.ttc";
    public static final String IMPACT = "resources/fonts/Impact.ttf";
    public static final String SOURCE_CODE_PRO_BOLD = "resources/fonts/SourceCodePro-Bold.ttf";
    public static final String PREMIER_LIGHTLINE_STD = "resources/fonts/PremierLightlineStd.otf";
    public static final String SOURCE_CODE_PRO_EXTRA_LIGHT = "resources/fonts/SourceCodePro-ExtraLight.ttf";

    public static Font getFont(String typeURL, float size) {

        File file = new File(typeURL);

        try {
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, file);
            dynamicFont = dynamicFont.deriveFont(size);

            return dynamicFont;

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return new Font("Arial", Font.PLAIN, 30);
    }
}

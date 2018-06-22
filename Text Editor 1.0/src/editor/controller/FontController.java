/*
This class is to make code simple 
 */
package editor.controller;

import editor.launcher.Main;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.swing.JList;
import javax.swing.JSpinner;

/**
 *
 * @author ISSA-PC
 */
public class FontController {

    public static Font getConfigFont() throws FileNotFoundException, IOException { // Get Font from config file

        String[] data = getInfoFromConfig().split("#"); // split array
        Font font = new Font(data[0], Font.PLAIN, Integer.valueOf(data[1])); // default font

        String style = data[2]; // font style

        switch (style) { // select correct style
            case "BOLD":
                font = new Font(data[0], Font.BOLD, Integer.valueOf(data[1]));
                break;
            case "ITALIC":
                font = new Font(data[0], Font.ITALIC, Integer.valueOf(data[1]));
                break;
            case "BOLD ITALIC":
                font = new Font(data[0], Font.ITALIC | Font.BOLD, Integer.valueOf(data[1]));
                break;
            default:
                break;
        }

        return font;
    }

    public static String getInfoFromConfig() throws IOException {

        FileReader reader = new FileReader(Main.getConfigFile()); // read file
        Properties props = new Properties(); // read properties 
        props.load(reader); // load reader

        String configFont = props.getProperty("Font"); // load font
        String configSize = props.getProperty("Size"); // load font size
        String configStyle = props.getProperty("Style"); // load font size

        return configFont + "#" + configSize + "#" + configStyle;
    }

    public static String[] getAvailableFonts() { // get all font in the OS
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); // return all font in a string array
    }

    public static void saveFontFile(JList<String> fontLists, JList<String> fontStyles, JSpinner sizeSpinner) {
        File config = new File(System.getenv("AppData") + "\\Text Editor 1.0\\Config.properties");
        PrintWriter pw = null;
        //Creating an option file to change the font in the central App.
        try {

            pw = new PrintWriter(config);

            pw.println("#CONFIG");
            pw.println("Font=" + fontLists.getSelectedValue());
            pw.println("Size=" + sizeSpinner.getValue());
            pw.println("Style=" + fontStyles.getSelectedValue());

        } catch (IOException ex) {
            System.out.println("IOException : " + ex.getMessage());
        } finally {
            pw.close();
        }

    }

    public static Font getFont(JList<String> fontLists, JList<String> fontStyles, JSpinner sizeSpinner) {

        Font newFont = null;
        int size = (int) sizeSpinner.getValue();
        switch (fontStyles.getSelectedValue()) {
            case "PLAIN":
                newFont = new Font(fontLists.getSelectedValue(), Font.PLAIN, size);
                break;
            case "BOLD":
                newFont = new Font(fontLists.getSelectedValue(), Font.BOLD, size);
                break;
            case "ITALIC":
                newFont = new Font(fontLists.getSelectedValue(), Font.ITALIC, size);
                break;
            case "BOLD ITALIC":
                newFont = new Font(fontLists.getSelectedValue(), Font.BOLD | Font.ITALIC, size);
                break;
        }
        return newFont;
    }
}

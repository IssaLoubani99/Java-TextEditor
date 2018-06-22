package editor.launcher;

import editor.graphic.MainFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author ISSA-PC
 */
public class Main {

    // Var
    private static final File config = new File(System.getenv("AppData") + "\\Text Editor 1.0\\Config.properties");

    public static File getConfigFile() { // to access from different classes
        return config;
    }

    public static void onLaunchConfigCheck(File file) { // check if config file exists

        File dir = new File(System.getenv("AppData") + "\\Text Editor 1.0"); // dir file
        if (file.exists() != true && dir.exists() != true) { // file file and dir does not exists
            PrintWriter pw = null;
            dir.mkdir();
            try {
                pw = new PrintWriter(file);

                // Default config 
                pw.println("#CONFIG");
                pw.println("Font=Candara");
                pw.println("Size=12");
                pw.println("Style=BOLD ITALIC");

                System.out.println("Config File successfully created !"); // in case of successes 

            } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException : " + ex.getMessage());
            } finally {
                pw.close();
            }
        } else {
            System.out.println("Config File already exists !");
        }

    }

    public static void main(String[] args) {

        onLaunchConfigCheck(config); // check for config file 
        new MainFrame().setVisible(true); // launch main frame

    }
}

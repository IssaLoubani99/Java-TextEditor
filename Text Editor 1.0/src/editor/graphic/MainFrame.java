// Main TextEditor Frame
package editor.graphic;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ISSA-PC , that's me :P
 */
public class MainFrame extends javax.swing.JFrame {

    // Static textArea to always set the font to the config file
    public static JTextArea textArea = new JTextArea();

    public MainFrame() {

        initComponents();
        this.setLocationRelativeTo(null); // set location in the middle of the screen
        this.setTitle("Text Editor 1.0"); // set title
        new Thread(() -> { // Thread to set textEditor Font
            try {

                while (true) {

                    textEditor.setFont(textArea.getFont());
                }
            } catch (NullPointerException ex) {
                System.out.println("NullPointerException : " + ex.getMessage());
            }
        }).start(); // start thread
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textEditor = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenuContainer = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenuContainer = new javax.swing.JMenu();
        fontMenuItem = new javax.swing.JMenuItem();
        helpMenuContainer = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textEditor.setColumns(20);
        textEditor.setRows(5);
        jScrollPane1.setViewportView(textEditor);

        fileMenuContainer.setText("File");

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenuContainer.add(saveMenuItem);

        loadMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadMenuItem.setText("Open");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        fileMenuContainer.add(loadMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenuContainer.add(exitMenuItem);

        jMenuBar1.add(fileMenuContainer);

        editMenuContainer.setText("Edit");

        fontMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        fontMenuItem.setText("Font...");
        fontMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontMenuItemActionPerformed(evt);
            }
        });
        editMenuContainer.add(fontMenuItem);

        jMenuBar1.add(editMenuContainer);

        helpMenuContainer.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenuContainer.add(aboutMenuItem);

        jMenuBar1.add(helpMenuContainer);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed

        //Create the File Chooser.
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "//Desktop");
        //Set The Aprrove Button's Text
        chooser.setApproveButtonText("Save");
        //Set The File Chooser Visible & Set a variable named "resultVal" equal the value of the Approve Button
        int resultVal = chooser.showOpenDialog(null);

        //When The User Click The Approve Button a try&catch is activated!       
        if (resultVal == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter pw = new PrintWriter(chooser.getSelectedFile() + ".txt")) {
                pw.write(textEditor.getText());
                pw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed

        //Creating a File Filter to Show *.txt files only
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File(*.txt)", "txt");
        //Create the File Chooser.
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "//Desktop");
        //Adding filter to The File Filter
        chooser.addChoosableFileFilter(filter);
        //Set The File Chooser Visible & Set a variable named "resultVal" equal the value of the Approve Button
        int returnVal = chooser.showOpenDialog(null);

        //When The User Click The Approve Button a try&catch is activated!
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            //Getting the path of file
            File myfile = chooser.getSelectedFile();
            String path = "" + myfile;
            /* System.out.println(path);
         
           Enable the path to be writen in the Console !
             */
            try {
                String content = new String(Files.readAllBytes(Paths.get(path)));
                textEditor.setText(content);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed

        //End the Program
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void fontMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontMenuItemActionPerformed

        //Open The font Main_Frame.
        FontChangerFrame frame = new FontChangerFrame();
        frame.setVisible(true);


    }//GEN-LAST:event_fontMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed

        //Show About Main_Frame in help section.
        new About().setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu editMenuContainer;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenuContainer;
    private javax.swing.JMenuItem fontMenuItem;
    private javax.swing.JMenu helpMenuContainer;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextArea textEditor;
    // End of variables declaration//GEN-END:variables
}

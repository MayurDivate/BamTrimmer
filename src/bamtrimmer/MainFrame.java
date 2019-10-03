/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author n10337547
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    private static boolean isBamFile = false;
    private static boolean isOutputFolder = false;
    public static MainFrame mainframe;
    
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonOutputFolderBrowser = new javax.swing.JButton();
        jButtonBamFileBrowser = new javax.swing.JButton();
        jTextFieldInputBam = new javax.swing.JTextField();
        jTextFieldOutputFolder = new javax.swing.JTextField();
        jLabelBamfile = new javax.swing.JLabel();
        jLabelOutputFolder = new javax.swing.JLabel();
        jButtonReset = new javax.swing.JButton();
        jButtonRun = new javax.swing.JButton();
        jLabelHeader = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));

        jButtonOutputFolderBrowser.setText("Browse");
        jButtonOutputFolderBrowser.setPreferredSize(new java.awt.Dimension(30, 25));
        jButtonOutputFolderBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutputFolderBrowserActionPerformed(evt);
            }
        });

        jButtonBamFileBrowser.setText("Browse");
        jButtonBamFileBrowser.setPreferredSize(new java.awt.Dimension(30, 25));
        jButtonBamFileBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBamFileBrowserActionPerformed(evt);
            }
        });

        jTextFieldInputBam.setEditable(false);
        jTextFieldInputBam.setText("/path/BAM");

        jTextFieldOutputFolder.setEditable(false);
        jTextFieldOutputFolder.setText("/path/output/folder");
        jTextFieldOutputFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOutputFolderActionPerformed(evt);
            }
        });

        jLabelBamfile.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelBamfile.setText("BAM File");

        jLabelOutputFolder.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelOutputFolder.setText("Output Folder");

        jButtonReset.setText("Reset");
        jButtonReset.setPreferredSize(new java.awt.Dimension(30, 25));
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jButtonRun.setText("Run");
        jButtonRun.setPreferredSize(new java.awt.Dimension(30, 25));
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });

        jLabelHeader.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHeader.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelHeader.setForeground(new java.awt.Color(0, 51, 255));
        jLabelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHeader.setText("BAM File Trimmer Tool for Transfusion Catalyst");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBamfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jButtonRun, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldOutputFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jTextFieldInputBam, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBamFileBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonOutputFolderBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBamfile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldInputBam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButtonOutputFolderBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonBamFileBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jMenu1.setText("Help");

        jMenuItem1.setText("Manual");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOutputFolderBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutputFolderBrowserActionPerformed
        // TODO add your handling code here:
        JFileChooser outputFolderChooser = new JFileChooser();
        outputFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        
        outputFolderChooser.setDialogTitle("Select output folder");
        
        int retrunVal = outputFolderChooser.showOpenDialog(null);
        if(retrunVal == JFileChooser.APPROVE_OPTION){
            File outputFolder = outputFolderChooser.getSelectedFile();
            jTextFieldOutputFolder.setText(outputFolder.getAbsolutePath());
            isOutputFolder = true;
            jTextFieldOutputFolder.setForeground(Color.black);
        }
        
    }//GEN-LAST:event_jButtonOutputFolderBrowserActionPerformed

    private void jButtonBamFileBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBamFileBrowserActionPerformed
        // TODO add your handling code here:
        JFileChooser bamFileChooser = new JFileChooser();
        bamFileChooser.setAcceptAllFileFilterUsed(false);
        bamFileChooser.addChoosableFileFilter(getFileNameExtensionFilter());
        bamFileChooser.setDialogTitle("Select input bam file");
        
        int retrunVal = bamFileChooser.showOpenDialog(null);
        if(retrunVal == JFileChooser.APPROVE_OPTION){
            File inputBam = bamFileChooser.getSelectedFile();
            jTextFieldInputBam.setText(inputBam.getAbsolutePath());
            isBamFile = true;
            jTextFieldInputBam.setForeground(Color.black);
        }
               
    }//GEN-LAST:event_jButtonBamFileBrowserActionPerformed

    FileNameExtensionFilter getFileNameExtensionFilter(){
        return new FileNameExtensionFilter("bam file", "bam");

    }
    
    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunActionPerformed
        // TODO add your handling code here:
        
        boolean flag = false;
        if(isBamFile && isOutputFolder){
            flag = true;
        }
        
        if(flag) {
            
            OutputFrame.outputframe = new OutputFrame();
            OutputFrame.outputframe.setVisible(true);
            OutputFrame.outputframe.setLocationRelativeTo(null);
            MainFrame.mainframe.dispose();
            jButtonRun.setEnabled(false);
            
            File inputBam = new File(jTextFieldInputBam.getText());
            File outputFolder = new File(jTextFieldOutputFolder.getText());
            Input bamTrimInput = new Input(inputBam, outputFolder);

            File packageDir = BamTrimmer.getPackageBase();
            
            Tool t = new Tool(packageDir, bamTrimInput);
            String[] slog = t.runJar(bamTrimInput);
            
            if(isSuccessful(slog[1])){
                OutputFrame.outputframe.setLog("Finished");
            }
            else{
                OutputFrame.outputframe.setLog(slog[1]);
            }

            
        }
        else{
            if(!isBamFile){
                jTextFieldInputBam.setForeground(Color.red);
             }
            if(!isOutputFolder){
                jTextFieldOutputFolder.setForeground(Color.red);
            }
        }

    }//GEN-LAST:event_jButtonRunActionPerformed

    
    private boolean isSuccessful(String logString){
        if(logString.contains("ERROR")){
            return false;
        }
         return true;
    }
    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
        
        jTextFieldInputBam.setText("/path/BAM");
        jTextFieldOutputFolder.setText("/path/output/folder");
        
        isBamFile = false;
        isOutputFolder = false;
        
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jTextFieldOutputFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOutputFolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOutputFolderActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JButton jButtonBamFileBrowser;
    private javax.swing.JButton jButtonOutputFolderBrowser;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JLabel jLabelBamfile;
    private javax.swing.JLabel jLabelHeader;
    private javax.swing.JLabel jLabelOutputFolder;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldInputBam;
    private javax.swing.JTextField jTextFieldOutputFolder;
    // End of variables declaration//GEN-END:variables
}

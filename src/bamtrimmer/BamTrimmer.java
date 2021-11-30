/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author n10337547
 */
public class BamTrimmer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // TODO code application logic here
         
        /* 
        File cordFile = new File("/Users/mayurdivate/Documents/RBCeq/BamTrimmer-4/BamStatX.bed");
        GeneCordinates g = new GeneCordinates(cordFile);
        g.parseUserCordinates(cordFile.getParentFile());
        */
        
        InputFrame.mainframe = new InputFrame();
        InputFrame.mainframe.setLocationRelativeTo(null);
        InputFrame.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputFrame.mainframe.setVisible(true);
        
         getPackageBase();
    }

    public static File getPackageBase() {
         try {
                  File jarFile = new File(BamTrimmer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                  return jarFile.getParentFile();
         } catch (URISyntaxException ex) {
                  Logger.getLogger(BamTrimmer.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    
    
}

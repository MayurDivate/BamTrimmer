/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

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
        MainFrame mainwindow = new MainFrame();
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainwindow.setVisible(true);
        
        
    }
    
    public void printInput(Input in){
        
        String input_parameters = in.getBamFile() + "\n" +
                in.getOutputDir()+ "\n" +
                in.getOutputBamFile() + "\n" +
                in.getTargetedRegions()+ "\n";
        
        System.out.println("bamtrimmer.BamTrimmer.printInput()");
        System.out.println(input_parameters);
        
    }
    
}

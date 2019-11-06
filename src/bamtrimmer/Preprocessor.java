/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author n10337547
 */
public class Preprocessor {
    
    public void runBamTrimming(InputData inputData){
                   
         System.out.println("bamtrimmer.AnalysisWorkflow.runBamTrimming()");
         File packageDir = BamTrimmer.getPackageBase();
         inputData.getLogFile().delete();
         
         // get the Tool object 
         Tool tool = new Tool(packageDir, inputData);
         if(this.setOutputFrameVisible()){
             OutputFrame.OUTPUTFOLDER = inputData.getOutputDir();
             JOptionPane.showMessageDialog(OutputFrame.outputframe, "Processing wait");
             boolean isSuccess = this.preprocessBam(tool);
             if(isSuccess){
                inputData.getLogFile().getAbsoluteFile();
            }
         }
         
    }
    
    private boolean setOutputFrameVisible(){
        OutputFrame.outputframe = new OutputFrame();
        OutputFrame.outputframe.setVisible(true);
        OutputFrame.outputframe.setLocationRelativeTo(null);
        
        return OutputFrame.outputframe.isVisible();
    }
    
    private boolean preprocessBam(Tool tool){
         boolean flag = false;
         
         // Filter Sam Reads
            flag = tool.runJar(tool.getFilterSamReadsCommand(), "Filter Sam Reads");
         
        // Mark Duplicates 
         if(flag){
            flag = tool.runJar(tool.getMarkDuplicateCommand(), "Mark Duplictes");
         }
         else{
             OutputFrame.outputframe.setLog("ERROR : Filte Sam Reads Failed, check log file for more details.");
             return false;
         }
         
         if(flag){
             flag = tool.runJar(tool.getCoverageBedCommand(), "Coverage Bed");
         }
         else{
             OutputFrame.outputframe.setLog("ERROR : Mark Duplicates Failed, check log file for more details.");
             return false;
         }
         
         if(flag){
             System.out.println(" ---- Done --- )");
             OutputFrame.outputframe.setLog("BAM file processing finished!");
             return true;
         }
         else{
             OutputFrame.outputframe.setLog("ERROR : Coverage bed failed, check log file for more details.");
             return false;
         }
    }
    
    
}

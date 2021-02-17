/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author n10337547
 */
public class Preprocessor {

    public void runBamTrimming(InputData inputData) {

        
        File packageDir = BamTrimmer.getPackageBase();
        inputData.getLogFile().delete();

        // get the Tool object 
        Tool tool = new Tool(packageDir, inputData);

        if (this.setOutputFrameVisible()) {
            OutputFrame.OUTPUTFOLDER = inputData.getOutputDir();
            JOptionPane.showMessageDialog(OutputFrame.outputframe, "Please Click OK to proceed!");

            // what to run 
            if (inputData.isIsTrim()) {
                System.out.println("Trimming Bam File ... ");
                boolean isSuccess = this.preprocessBam(tool);
                if (isSuccess) {
                    inputData.getLogFile().getAbsoluteFile();
                }
            } else {
                System.out.println("Generating Bam Stat.. ");
                boolean isSuccess = this.getBamStat(tool);
                if (isSuccess) {
                    inputData.getLogFile().getAbsoluteFile();
                }
            }

        }

    }

    private boolean setOutputFrameVisible() {
        OutputFrame.outputframe = new OutputFrame();
        OutputFrame.outputframe.setVisible(true);
        OutputFrame.outputframe.setLocationRelativeTo(null);

        return OutputFrame.outputframe.isVisible();
    }

    private boolean preprocessBam(Tool tool) {
        boolean flag = false;
        
        // FixMateInformation
        flag = tool.runJar(tool.getFixMateInformationCommand(), "Filter Sam Reads");
        
        if(flag){
            // Filter Sam Reads
             flag = tool.runJar(tool.getFilterSamReadsCommand(), "Filter Sam Reads");
        }
         // Mark Duplicates 
        if (flag) {
            // delete fxmatebam
            tool.getInputData().getOutFxmateBamFile().delete();
            
            flag = tool.runJar(tool.getMarkDuplicateCommand(), "Mark Duplictes");
        } else {
            OutputFrame.outputframe.setLog("ERROR : Filter Sam Reads Failed, check log file for more details.");
            return false;
        }

        if (flag) {
            // delete filtered bam
            tool.getInputData().getFilteredBamFile().delete();
            
            flag = tool.runJar(tool.getBamIndexCommand(), "Bam Index");
        } else {
            OutputFrame.outputframe.setLog("ERROR : Mark Duplicates Failed, check log file for more details.");
            return false;
        }

        if (flag) {
            System.out.println("Coverage Bam");
            flag = tool.runJar(tool.getCoverageBedCommand(), "Coverage Bed");
        } else {
            OutputFrame.outputframe.setLog("ERROR : Bam indexing Failed, check log file for more details.");
            return false;
        }

        if (flag) {
            System.out.println(" ---- Done --- )");
            OutputFrame.outputframe.setLog("BAM file processing finished!");
            return true;
        } else {
            OutputFrame.outputframe.setLog("ERROR : Coverage bed failed, check log file for more details.");
            return false;
        }
    }

    private boolean getBamStat(Tool tool) {
        boolean flag = false;

        // create index
        flag = tool.runJar(tool.getBamIndexCommand(), "Bam Index");

        if (flag) {
            flag = tool.runJar(tool.getCoverageBedCommand(), "Coverage Bed");
        } else {
            OutputFrame.outputframe.setLog("ERROR : Bam indexing Failed, check log file for more details.");
            return false;
        }

        if (flag) {
            System.out.println(" ---- Done --- )");
            OutputFrame.outputframe.setLog("BAM file processing finished!");
            return true;
        } else {
            OutputFrame.outputframe.setLog("ERROR : Coverage bed failed, check log file for more details.");
            return false;
        }

    }

}

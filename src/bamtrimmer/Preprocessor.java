/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author n10337547
 */
public class Preprocessor {

    public void run(InputData inputData) {
        System.out.println("* --- -- RUN -- --- *");
        
        File packageDir = BamTrimmer.getPackageBase();
        if(inputData.getLogFile().exists()){
            inputData.getLogFile().delete();
        }
        
        // get the Tool object 
        Tool tool = new Tool(packageDir, inputData);

        if (this.setOutputFrameVisible()) {
            OutputFrame.OUTPUTFOLDER = inputData.getOutputDir();
            JOptionPane.showMessageDialog(OutputFrame.outputframe, "Please click OK to proceed!");
            
            // what to run 
            if (inputData.isIsTrim()) {
                System.out.println("Trimming Bam File...");
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
        flag = tool.runJar(tool.getFixMateInformationCommand(), "Fix mate pair information");
        
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
        System.out.println("Bam index created");
        if (flag) {
            System.out.println("Calculating coverage");
            flag = tool.runJar(tool.getCoverageBedCommand(), "Coverage Bed");

        } else {
            OutputFrame.outputframe.setLog("ERROR : Bam indexing Failed, check log file for more details.");
            return false;
        }
        
        if (flag) {
            System.out.println("RHD gene");
            if (tool.getInputData().getRhdFile().exists()) {
                flag = tool.runJar(tool.getCoverageBedCommand(tool.getInputData().getRhdFile()), "RHD Coverage");
            } else {
                flag = false;
            }
        } else {
            OutputFrame.outputframe.setLog("ERROR : Bam statfailed, check log file for more details.");
            return false;
        }
        if (flag) {
            System.out.println("RHCE gene");
            if (tool.getInputData().getRhceFile().exists()) {
                flag = tool.runJar(tool.getCoverageBedCommand(tool.getInputData().getRhceFile()), "RHCE Coverage");
            } else {
                flag = false;
            }
        } else {
            OutputFrame.outputframe.setLog("ERROR : RHD coverage failed, check log file for more details.");
            return false;
        }
        if (flag) {
            System.out.println("RHCE Exon 2");
            if (tool.getInputData().getRhceExon2File().exists()) {
                flag = tool.runJar(tool.getCoverageBedCommand(tool.getInputData().getRhceExon2File()), "RHCE Exon2 Coverage");
            } else {
                flag = false;
            }
        } else {
            OutputFrame.outputframe.setLog("ERROR : RHCE coverage failed, check log file for more details.");
            return false;
        }
        
        if (flag) {
            System.out.println("Get average coverage");
                flag = this.processBamStats(tool);
            
        } else {
            OutputFrame.outputframe.setLog("ERROR : RHCE Exon2 coverage failed, check log file for more details.");
            return false;
        }
        

        if (flag) {
            System.out.println(" ---- Done --- ");
            OutputFrame.outputframe.setLog("BAM file processing finished!");
            return true;
        } else {
            OutputFrame.outputframe.setLog("ERROR : Average coverage failed, check log file for more details.");
            return false;
        }

    }
    
    private boolean processBamStats(Tool tool){
    
        boolean flag = true;

        File exon2 = new File(tool.getInputData().getOutputDir().getAbsolutePath() + File.separator + "RHCE_exon2_coverage.bed");
        File rhce = new File(tool.getInputData().getOutputDir().getAbsolutePath() + File.separator + "RHCE_coverage.bed");
        File rhd = new File(tool.getInputData().getOutputDir().getAbsolutePath() + File.separator + "RHD_coverage.bed");

        float avgCovRHD = this.getAverageCoverage(rhd);
        System.out.println("RHD " + avgCovRHD);
        // RHD CHR1 : 25597897 - 25655628
        flag = this.replaceCoverage(tool.getInputData().getCoverageBed(), "chr1", "25597897", "25655628", avgCovRHD);
        if (flag) {
            float avgCovRHCE = this.getAverageCoverage(rhce);
            System.out.println("RHCE " + avgCovRHCE);
            // RHCE CHR1 : 25688926 - 25747405
            flag = this.replaceCoverage(tool.getInputData().getCoverageBed(), "chr1", "25688926", "25747405", avgCovRHCE);
        }
        if (flag) {
            float avgCovRHCEexon2 = this.getAverageCoverage(exon2);
            System.out.println("RHCE Exon " + avgCovRHCEexon2);
            // RHCE EXON2 chr1 : 25735173 - 25735360
            flag = this.replaceCoverage(tool.getInputData().getCoverageBed(), "chr1", "25735173", "25735360", avgCovRHCEexon2);
        }
        return flag;
    }
    
    private boolean replaceCoverage(File covBed, String chr, String start, String end, float coverage){
        
        try{
            FileReader fr = new FileReader(covBed);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            File tmpFile = new File(covBed.getAbsolutePath()+"_tmp");
            PrintWriter pr = new PrintWriter(tmpFile);
            
            while(line != null){
                String[] cols = line.split("\t");
                
              if((cols[0].equals(chr)) && (cols[1].equals(start) && cols[2].equals(end))){
                   cols[7] = ""+coverage;
                }
                
                for(String s: cols){
                    pr.write(s + "\t");
                    pr.flush();
                }
                pr.println();
                pr.flush();
                line = br.readLine();
            }
            
            pr.close();
            br.close();
            fr.close();
            
            tmpFile.renameTo(covBed);
            
            return true;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    
    private float getAverageCoverage(File bamStatFile){
        
        try{
            FileReader fr = new FileReader(bamStatFile);
            BufferedReader br = new BufferedReader(fr);
            
            float total = 0;
            int count = 0;
            //chr1	25688827	25689106	279	PS190-005	127	767	473.28	495.00	0	100
            
            String line = br.readLine();
            line = br.readLine();
            while(line != null){
                String[] cols = line.split("\t");
                total = Float.parseFloat(cols[7]);
                count = count + 1; 
                line = br.readLine();
            }
            
            br.close();
            fr.close();
            return total / count ;
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    

}

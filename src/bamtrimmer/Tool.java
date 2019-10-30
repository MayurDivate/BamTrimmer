/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import picard.sam.FilterSamReads;
    
/**
 *
 * @author n10337547
 */
public class Tool {
    private File packagePath;
    private InputData inputData;

    public Tool(File packagePath, InputData inputData) {
        this.packagePath = packagePath;
        this.inputData = inputData;
    }

    public InputData getInputData() {
        return inputData;
    }

    public File getPackagePath() {
        return packagePath;
    }
    
    
    String[] getCommand(){
        String[] command = {
            "java",
            "-jar",
            this.getPackagePath().getAbsolutePath() + File.separator + "lib"+ File.separator + "picard.jar",
            "FilterSamReads",
            "I=" + this.inputData.getInputBamFile().getAbsolutePath(),
            "O=" + this.inputData.getOutputBamFile(),
            "Filter=includePairedIntervals",
            "INTERVAL_LIST=" + this.packagePath.getAbsolutePath() + File.separator+ ".." + File.separator +"test.bed"
        };
        
        return command;
    }
    
    String[] getMarkDuplicateCommand(){
         String[] command = {
            "java",
            "-jar",
            this.getPackagePath().getAbsolutePath() + File.separator + "lib"+ File.separator + "picard.jar",
            "MarkDuplicates",
            "I=" + this.inputData.getInputBamFile().getAbsolutePath(),
            "O=" + this.inputData.getDuplicateMarkedBamFile(),
            "M=marked_dup_metrics.txt",
            "REMOVE_DUPLICATES=true"
        };
        
        return command;
        
    }
   
    
   String[] runJar(String[] command){
        
        String[] log =  new String[2];
        
        for(String s : command){
            System.out.println(s);
        }
        
        
        try{
            ProcessBuilder picardProcessBuilder = new ProcessBuilder(command);
            picardProcessBuilder.directory();
            Process process =  picardProcessBuilder.start();
            String stdOUT = this.getSTDoutput(process);
            String errorLog = this.getSTDerror(process);
            log[0] = stdOUT;
            log[1] = errorLog;
            
            System.out.println("0 >>>>>"+log[0]);
            System.out.println("1 <<<<<"+log[1]);
            
            return log;
            
        } catch (IOException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
                        
    }
    
    
    public String getSTDoutput(Process process){
        try {
            String stdOut = "";
            String readLineString;
            BufferedReader brProcess = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while((readLineString = brProcess.readLine()) != null){
                    stdOut = stdOut + readLineString + "\n";
                }
            return stdOut;
        } catch (IOException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
        
    }
    
    public String getSTDerror(Process process){
        
        try {
            String stdError = "";
            String readLineString;
            BufferedReader brProcess = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while((readLineString = brProcess.readLine()) != null){
                    stdError = stdError + readLineString +"\n";
                }
            return stdError;
        } catch (IOException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
        
    }
    
    
    private boolean isSuccessful(String logString){
        if(logString.contains("ERROR")){
            return false;
        }
         return true;
    }
    
}

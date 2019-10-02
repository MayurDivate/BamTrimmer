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
    private Input inputData;

    public Tool(File packagePath, Input inputData) {
        this.packagePath = packagePath;
        this.inputData = inputData;
    }

    public Input getInputData() {
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
            "I=" + this.inputData.getBamFile().getAbsolutePath(),
            "O=" + this.inputData.getOutputBamFile(),
            "INTERVAL_LIST=" + this.packagePath.getAbsolutePath() + File.separator+ ".." + File.separator +"test.bed"
        };
        
        return command;
    }
    
    void runJar(Input bamTrimInput){
        
        String[] log =  new String[2];
        
        try{
            ProcessBuilder picardProcessBuilder = new ProcessBuilder(this.getCommand());
            picardProcessBuilder.directory();
            Process process =  picardProcessBuilder.start();
            String stdOUT = this.getSTDoutput(process);
            String errorLog = this.getSTDerror(process);
            log[0] = stdOUT;
            log[1] = errorLog;
            
            System.out.println(log[0]);
            System.out.println(log[1]);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
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
    
    
}

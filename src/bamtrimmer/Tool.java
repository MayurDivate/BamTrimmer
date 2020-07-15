/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
    
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
     
    String[] getFilterSamReadsCommand(){
        String[] command = {
            "java",
            "-jar",
            this.getPackagePath().getAbsolutePath() + File.separator + "lib"+ File.separator + "picard.jar",
            "FilterSamReads",
            "I=" + this.inputData.getInputBamFile().getAbsolutePath(),
            "O=" + this.inputData.getFilteredBamFile(),
            "Filter=includePairedIntervals",
            "INTERVAL_LIST=" + this.packagePath.getAbsolutePath() + File.separator+ ".." + File.separator +"BGG.bed",
            "USE_JDK_DEFLATER=true", "USE_JDK_INFLATER=true"
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
            "REMOVE_DUPLICATES=true",
            "USE_JDK_DEFLATER=true", "USE_JDK_INFLATER=true"
        };
        
        return command;
        
    }
   
    String[] getCoverageBedCommand(){
        String[] command = {
            "java",
            "-jar",
            this.getPackagePath().getAbsolutePath() + File.separator + "lib"+ File.separator + "bamstats04.jar",
            "-B",
            this.packagePath.getAbsolutePath() + File.separator+ ".." + File.separator +"BAMSTAtinput.bed",
            this.inputData.getDuplicateMarkedBamFile().getAbsolutePath(),
            "-o",
            this.inputData.getCoverageBed().getAbsolutePath()
        };
        
        return command;
    }
    
    String[] getBamIndexCommand(){
         String[] command = {
            "java", "-jar",
            this.getPackagePath().getAbsolutePath() + File.separator + "lib"+ File.separator + "picard.jar",
            "BuildBamIndex",
            "I=" + this.inputData.getDuplicateMarkedBamFile().getAbsolutePath(),
            "USE_JDK_DEFLATER=true", "USE_JDK_INFLATER=true"
        };
        
        return command;
    }
    
   boolean runJar(String[] command, String header){
        
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
            
            this.writelog(header, log);
            return isSuccessful(stdOUT + errorLog);
            
        } catch (IOException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
                        
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

    private boolean writelog(String header, String[] log){
        
        System.out.println("bamtrimmer.Tool.writelog()");
        
        try {
                FileWriter fw = new FileWriter(this.getInputData().getLogFile(), true);
                BufferedWriter bw = new BufferedWriter(fw); 
                PrintWriter logPrintWriter = new PrintWriter(bw);
                logPrintWriter.append("---------------------------------------------------");
                logPrintWriter.append("\n");
                logPrintWriter.append(header);
                logPrintWriter.append("\n");
                logPrintWriter.flush();
                logPrintWriter.append("-------------------STDOUT-----------------------");
                logPrintWriter.append("\n");
                logPrintWriter.append(log[0]);
                logPrintWriter.append("\n");
                logPrintWriter.flush();
                logPrintWriter.append("------------------STDERROR---------------------");
                logPrintWriter.append("\n");
                logPrintWriter.append(log[1]);
                logPrintWriter.append("\n");
                logPrintWriter.flush();
                logPrintWriter.append("---------------------***--------------------------");
                logPrintWriter.append("\n");
                logPrintWriter.close();                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    private boolean isSuccessful(String logString){
        if(logString.contains("ERROR") || logString.contains("failure")){
            return false;
        }
         return true;
    }
    
    private String getChrFormat(String bamRecord){
        
        String[] alignmentRecord =  bamRecord.split("\t");
        Pattern chr_ChrX = Pattern.compile("Chr");
        Pattern chr_chrX = Pattern.compile("chr");
        Pattern chr_X = Pattern.compile("\\d+");
        
       if(chr_ChrX.matcher(alignmentRecord[1]).find()){
           
       }
        return "chr";
        
    }
    
}

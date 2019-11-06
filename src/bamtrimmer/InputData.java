/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bamtrimmer;

import java.io.File;

/**
 *
 * @author n10337547
 */
public class InputData {
    
    private File inputBamFile;
    private File outputDir;
    private File outputFilteredBamFile;
    private File duplicateMarkedBamFile;
    private File logFile;
    private File coverageBed;
    
    public InputData(File inputBamFile, File outputDir) {
        this.inputBamFile = inputBamFile;
        this.outputDir = outputDir;
        this.outputFilteredBamFile = getOutXBamFile(inputBamFile, outputDir, "_filtered.bam");
        this.duplicateMarkedBamFile = getOutXBamFile(inputBamFile, outputDir, "_trimmed_dupmark.bam");
        this.coverageBed = getOutXBamFile(inputBamFile, outputDir, "_coverage.bed");
        this.logFile = getOutXBamFile(inputBamFile, outputDir, "_log.txt");
        
    }
        
    public static File getOutXBamFile(File inputbam,  File outputdir, String suffix) {
        String baseName = inputbam.getName().replace(".bam", suffix);
        File outputBamFile = new File(outputdir, baseName);
        return outputBamFile;
    }

    @Override
    public String toString() {
        String input_data = "Input Data\n";
        input_data = input_data + "input bam: "+ this.getInputBamFile().getAbsolutePath()+ "\n";
        input_data = input_data + "trimmed bam: "+ this.getDuplicateMarkedBamFile().getAbsolutePath()+ "\n";
        input_data = input_data + "output folder: "+ this.getOutputDir().getAbsolutePath()+ "\n";
        input_data = input_data + "trimmed bam: "+ this.getFilteredBamFile().getAbsolutePath()+ "\n";
        input_data = input_data + " --- --- --- "+ "\n";
        
        return input_data; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the inputBamFile
     */
    public File getInputBamFile() {
        return inputBamFile;
    }

    /**
     * @param inputBamFile the inputBamFile to set
     */
    public void setInputBamFile(File inputBamFile) {
        this.inputBamFile = inputBamFile;
    }

    /**
     * @return the outputDir
     */
    public File getOutputDir() {
        return outputDir;
    }

    /**
     * @param outputDir the outputDir to set
     */
    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    /**
     * @return the duplicateMarkedBamFile
     */
    public File getDuplicateMarkedBamFile() {
        return duplicateMarkedBamFile;
    }

    public File getFilteredBamFile() {
        return outputFilteredBamFile;
    }

    public File getLogFile() {
        return logFile;
    }

    public File getCoverageBed() {
        return coverageBed;
    }
    
}

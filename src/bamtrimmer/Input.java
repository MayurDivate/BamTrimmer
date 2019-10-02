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
public class Input {
    
    private File bamFile;
    private File outputDir;
    private File outputBamFile;
    private File targetedRegions;

    public Input(File bamFile, File outputDir, File targetedRegions) {
        this.bamFile = bamFile;
        this.outputDir = outputDir;
        this.targetedRegions = targetedRegions;
    }
    
        
    public File getOutputBamFile() {
        String baseName = this.getBamFile().getName().replace("\\.bam", "_trimmed.bam");
        File outputBamFile = new File(this.getOutputDir(), baseName);
        return outputBamFile;
    }
    
    

    /**
     * @return the bamFile
     */
    public File getBamFile() {
        return bamFile;
    }

    /**
     * @param bamFile the bamFile to set
     */
    public void setBamFile(File bamFile) {
        this.bamFile = bamFile;
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
     * @return the outputBamFile
     */


    /**
     * @return the targetedRegions
     */
    public File getTargetedRegions() {
        return targetedRegions;
    }

    /**
     * @param targetedRegions the targetedRegions to set
     */
    public void setTargetedRegions(File targetedRegions) {
        this.targetedRegions = targetedRegions;
    }

    String get_command(){
        
        String bamTrimmerCommand = "";
        return bamTrimmerCommand;
    }
    
    
}

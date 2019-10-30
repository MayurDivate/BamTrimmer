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
public class Preprocessor {
    
    
    
    static void runBamTrimming(InputData inputData){
       
         File packageDir = BamTrimmer.getPackageBase();
         //Tool t = new Tool(packageDir, inputData);
         //String[] slog = t.runJar(inputData);
            
         System.out.println("bamtrimmer.AnalysisWorkflow.runBamTrimming()");
         System.out.println(inputData);
       
         boolean flag = false; 
         // 1. remove unmapped reads 
         
         Tool tool = new Tool(packageDir, inputData);
         
         String[] log = tool.runJar(tool.getMarkDuplicateCommand());
         
         String[] log2 = tool.runJar(tool.getCommand());
        
    }
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bamtrimmer;

import htsjdk.samtools.util.BufferedLineReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mayurdivate
 */
public class GeneCordinates {

    private File cordFile;
    
    public GeneCordinates(File cordFile) {
        this.cordFile = cordFile;
    }

    public File getCordFile() {
        return cordFile;
    }
    
    boolean parseUserCordinates(File outputFolder) {

        try {
            FileReader fr = new FileReader(this.getCordFile());
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                String out = line.split("\t", 2)[0];
                File outputfile = new File(outputFolder.getAbsoluteFile() + File.separator + out + ".bed");
                createUserGeneCordinateBed(line, outputfile);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneCordinates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneCordinates.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    boolean createUserGeneCordinateBed(String cordinates, File outputFile) {

        try {

            Pattern p = Pattern.compile("((\\d+)\\:(\\d+)\\-(\\d+))");
            Matcher m = p.matcher(cordinates);
            int pos = 0;

            PrintWriter pw = new PrintWriter(outputFile);

            while (m.find(pos)) {
                String chr = "chr" + m.group(2);
                int start = Integer.parseInt(m.group(3));
                int end = Integer.parseInt(m.group(4));

                pw.write(chr + "\t" + m.group(3) + "\t" + m.group(4) + "\n");
                pw.flush();
                pos = m.end();

            }
            pw.close();
            return true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneCordinates.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static File getDefaultFile(String locID){
        
        String filePath = BamTrimmer.getPackageBase().getAbsolutePath() + File.separator + ".." + File.separator + "bedfiles"+ File.separator + locID + ".bed";
        File bedFile = new File(filePath);
        return bedFile;
    }
    
}

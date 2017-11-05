package name.cdd.product.clzsearch.userlibmaker.business;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WarnMessageWriter
{
    private FileOutput output;
    
    public WarnMessageWriter(File outputFile)
    {
        try
        {
            if(outputFile.exists())
            {
                outputFile.delete();
            }
            
            output = new FileOutput(outputFile);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void write(ArrayList<File> jars, ArrayList<File> matchedJars)
    {
        StringBuffer buf = new StringBuffer("Candidate Jars:\n");
        for(File jarFile : jars)
        {
            buf.append(jarFile.getAbsolutePath()).append("\n");
        }
        
        buf.append("\n").append("Matched Jars:\n");
        for(File jarFile : matchedJars)
        {
            buf.append(jarFile.getAbsolutePath()).append("\n");
        }
        
        buf.append("--------------------\n\n");
        
        output.printString(buf.toString());
    }

    private class FileOutput
    {
        private File outputFile;

        public FileOutput(File outputFile) throws FileNotFoundException
        {
            if(!outputFile.getParentFile().exists())
            {
                outputFile.getParentFile().mkdirs();
            }
            this.outputFile = outputFile;
        }
        
        public void printString(String strOutput)
        {
            PrintWriter pw = null;
            try
            {
                pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile, true)));
                pw.write(strOutput);
                pw.close();
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        WarnMessageWriter w = new WarnMessageWriter(new File("D:\\Tool\\EclipseLibraryGenerator\\warnMessageTest.txt"));
        
        File f1 = new File("c:\\aa.txt");
        File f2 = new File("c:\\bb.txt");
        File f3 = new File("c:\\cc.txt");
        
        ArrayList<File> list1 = new ArrayList<File>();
        list1.add(f1);
        list1.add(f2);
        list1.add(f3);
        
        ArrayList<File> list2 = new ArrayList<File>();
        list2.add(f1);
        list2.add(f3);
        
        w.write(list1, list2);
        w.write(list1, list2);
        
    }
}

package name.cdd.product.clzsearch.userlibmaker.business;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class EclipseLibFileExporter
{
    private static final String HEAD = 
          "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>\n"
        + "<eclipse-userlibraries version=\"2\">";
    
    private static final String TAIL = 
          "    </library>\n"
        + "</eclipse-userlibraries>";
    
    public void export(Collection<File> archivedJarlist, String eclipseLibraryName, File exportToFile) throws FileNotFoundException
    {
        String strOutput = HEAD + "\n" + getLine4LibName(eclipseLibraryName) + "\n" + getArchivedPaths(archivedJarlist) + TAIL;

        new FileOutput(exportToFile).printFile(strOutput);
    }
    
    
    private String getArchivedPaths(Collection<File> archivedJarlist)
    {
        final String lineStart = "        <archive path=\"";
        final String lineEnd = "\"/>";
        
        StringBuffer output = new StringBuffer();
        for(File file : archivedJarlist)
        {
            output.append(lineStart).append(file.getAbsolutePath()).append(lineEnd).append("\n");
        }
        return output.toString();
    }


    private String getLine4LibName(String libName)
    {
        return "    <library name=\"" + libName + "\" systemlibrary=\"false\">";
    }
    
    private class FileOutput
    {
        private File outputFile;

        public FileOutput(File outputFile)
        {
            this.outputFile = outputFile;
        }

        public void printFile(String strOutput) throws FileNotFoundException
        {
            if(!outputFile.getParentFile().exists())
            {
                outputFile.getParentFile().mkdirs();
            }
            
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile)));
            pw.write(strOutput);
            pw.close();
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        Collection<File> archivedJarlist = new ArrayList<File>();
        archivedJarlist.add(new File("E:/4X/uif/lib/jboss-jmx.jar"));
        archivedJarlist.add(new File("E:/4X/uif/uifSetup.jar"));
        
        final File generatedLibraryFile = new File(System.getProperty("user.dir"), "output/aaa/uep4x.libraries");
        final String eclipseLibraryName = "UEP 4X Library";
        
        new EclipseLibFileExporter().export(archivedJarlist, eclipseLibraryName, generatedLibraryFile);
    }
}

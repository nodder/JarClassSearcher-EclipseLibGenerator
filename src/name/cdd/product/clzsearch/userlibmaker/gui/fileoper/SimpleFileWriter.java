package name.cdd.product.clzsearch.userlibmaker.gui.fileoper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class SimpleFileWriter
{
    public static void writeFile(File outputFile, String content) throws FileNotFoundException
    {
        if(!outputFile.getAbsoluteFile().getParentFile().exists())
        {
            outputFile.getParentFile().mkdirs();
        }

        PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile)));
        pw.write(content);
        pw.close();
    }

}

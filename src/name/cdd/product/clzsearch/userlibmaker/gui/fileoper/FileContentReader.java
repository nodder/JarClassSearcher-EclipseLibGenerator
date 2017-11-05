package name.cdd.product.clzsearch.userlibmaker.gui.fileoper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileContentReader
{
    public static String getFullContent(File file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        StringBuffer buf = new StringBuffer();
        
        String line;
        while((line = reader.readLine()) != null)
        {
            buf.append(line).append("\n");
        }
        
        return buf.toString();
    }
}

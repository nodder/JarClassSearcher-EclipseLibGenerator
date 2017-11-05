package name.cdd.product.clzsearch.business.search4jar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JarSearch
{
    ArrayList<File> jarFiles = new ArrayList<File>();
    
    public ArrayList<File> searchJars(File file) throws IOException
    {
        init();
        doSearchJarFile(file);
        return this.jarFiles;
    }

    private void init()
    {
        jarFiles.clear();
    }

    private void doSearchJarFile(File fromFile) throws IOException
    {
        if(fromFile.isDirectory())
        {
            File tmpFile[] = fromFile.listFiles();
            for(int i = 0; i < tmpFile.length; i++)
            {
                doSearchJarFile(tmpFile[i]);
            }
        }
        else
        {
            if(fromFile.isFile())
            {
                if(fromFile.getName().endsWith(".jar"))
                {
                    jarFiles.add(fromFile);
                }
            }
        }
    }
}

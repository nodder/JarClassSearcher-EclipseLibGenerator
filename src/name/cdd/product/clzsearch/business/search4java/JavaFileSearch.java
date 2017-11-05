package name.cdd.product.clzsearch.business.search4java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JavaFileSearch
{
    ArrayList<File> javaFiles = new ArrayList<File>();
    
    public ArrayList<File> searchJavaFiles(File path) throws IOException
    {
        init();
        doSearchJavaFile(path);
        return this.javaFiles;
    }

    private void init()
    {
        javaFiles.clear();
    }

    private void doSearchJavaFile(File fromFile) throws IOException
    {
        if(fromFile.isDirectory())
        {
            File tmpFile[] = fromFile.listFiles();
            for(int i = 0; i < tmpFile.length; i++)
            {
                doSearchJavaFile(tmpFile[i]);
            }
        }
        else
        {
            if(fromFile.isFile())
            {
                if(fromFile.getName().endsWith(".java"))
                {
                    javaFiles.add(fromFile);
                }
            }
        }
    }
}

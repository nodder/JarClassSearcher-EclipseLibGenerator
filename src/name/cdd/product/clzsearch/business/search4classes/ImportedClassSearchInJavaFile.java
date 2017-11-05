package name.cdd.product.clzsearch.business.search4classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ImportedClassSearchInJavaFile
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    public ArrayList<String> searchImportedClasses(File javaFile) throws IOException
    {
        BufferedReader buffReader = null;
        FileReader fileReader = null;

        ArrayList<String> importedPacksInfo = new ArrayList<String>();
        try
        {
            fileReader = new FileReader(javaFile);
            buffReader = new BufferedReader(fileReader);

            String line;
            int lineNum = 0;
            while((line = buffReader.readLine()) != null)
            {
                lineNum++;
                line = line.trim();
                
                if(line.startsWith("package") || line.equalsIgnoreCase(""))
                {
                    continue;
                }
                else if(line.startsWith("import"))
                {
                    try
                    {
                        importedPacksInfo.add(getClassNameFromImportLine(line));
                    }
                    catch(IllegalArgumentException e)
                    {
                        logger.warn("Invalid line:" + line + " for file:" + javaFile.getAbsolutePath());
                    }
                }
                else if(line.contains("public") && line.contains("class"))
                {
                    break;
                }
            }
        }
        finally
        {
            closeReader(fileReader);
            closeReader(buffReader);
        }
        
        return importedPacksInfo;
    }

    String getClassNameFromImportLine(String line) throws IllegalArgumentException
    {
        if(!line.endsWith(";") || line.contains("//"))
        {
            throw new IllegalArgumentException();
        }
        
        return line.substring(line.indexOf(" "), line.length() - 1).trim();
    }

    private static void closeReader(Reader reader)
    {
        if(reader != null)
        {
            try
            {
                reader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
}

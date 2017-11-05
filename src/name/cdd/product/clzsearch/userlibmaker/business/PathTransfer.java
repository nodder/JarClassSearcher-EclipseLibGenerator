package name.cdd.product.clzsearch.userlibmaker.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import name.cdd.product.clzsearch.userlibmaker.business.xml.UserConfigXml;

import org.apache.log4j.Logger;


public class PathTransfer
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String jarRootDir;
    
    public PathTransfer()
    {
        jarRootDir = getJarRootDir();
    }
    
    protected String getJarRootDir()
    {
        return UserConfigXml.getInstance().getJarDirectory();
    }

    public File[] filePatternListToFiles(String[] filePatternList)
    {
        List<File> rtnPaths = new ArrayList<File>();
        
        for(String filePattern : filePatternList)
        {
            File path = filePatternToPath(filePattern);
            if(path != null)
            {
                rtnPaths.add(path);
            }
        }
        return rtnPaths.toArray(new File[0]);
    }
    
    File filePatternToPath(String strFilePattern)
    {
        if(!strFilePattern.endsWith(".jar") && !strFilePattern.endsWith("*.jar"))
        {
            logger.warn("Unsupported file pattern:" + strFilePattern);
            return null;
        }
        
        File rtnFile;
        if(strFilePattern.endsWith("*.jar"))
        {
            rtnFile = new File(jarRootDir, strFilePattern.substring(0, strFilePattern.length() - "*.jar".length()));
        }
        else
        {
            rtnFile = new File(jarRootDir, strFilePattern);
        }
        
        if(rtnFile.exists())
        {
            return rtnFile;
        }
        else
        {
            logger.warn("File(" + rtnFile + ") does not exist for file pattern:" + strFilePattern);
            return null;
        }
        
    }

}

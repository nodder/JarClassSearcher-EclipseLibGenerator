package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ActiveDir
{
    private static Logger logger = Logger.getLogger(ActiveDir.class.getName());
    
    public static String getActiveDir()
    {
        return readActiveDirFromFile();
    }
    
    private static String readActiveDirFromFile()
    {
        InputStream is = null;
        File file = new File("conf/active-dir.properties");
        
        try
        {
            is = new FileInputStream(file);
            Properties p = new Properties();
            p.load(is);

            return p.getProperty("active-dir");
        }
        catch(IOException e)
        {
            logger.fatal("read from file [" + file.getAbsolutePath() + "] failed. Details:", e);
            System.exit(1);
        }
        finally
        {
            closeInputStream(is);
        }
        
        return null;
    }

    private static void closeInputStream(InputStream is)
    {
        try
        {
            is.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void setActiveDir(String activeDir) throws IOException
    {
        InputStream is = null;
        OutputStream os = null;
        
        File file = new File("conf/active-dir.properties");
        
        try
        {
            is = new FileInputStream(file);
            Properties p = new Properties();
            p.load(is);
            p.setProperty("active-dir", activeDir);
            
            os = new FileOutputStream(file);
            p.store(os, "");
        }
        finally
        {
            closeInputStream(is);
            closeOutputStream(os);
        }
    }

    private static void closeOutputStream(OutputStream os) throws IOException
    {
        if(os != null)
        {
            os.close();
        }
    }
    
}

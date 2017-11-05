package name.cdd.product.clzsearch.userlibmaker.business.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import name.cdd.product.clzsearch.business.search4classes.ClassSearch;
import name.cdd.product.clzsearch.business.search4jar.JarSearch;

import org.apache.log4j.Logger;
import org.springframework.util.AntPathMatcher;

public class JarSearcher implements IJarsSearcher
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Override
    public ArrayList<File> search(String targetClassName, File fromDirOrFile) throws IOException
    {
        if(fromDirOrFile == null)
        {
            return new ArrayList<File>();
        }
        
        if(!fromDirOrFile.exists())
        {
            logger.warn("The path (" + fromDirOrFile + ") deos not exist using Jar searcher.");
            return new ArrayList<File>();
        }
        
        ArrayList<File> jarFiles = new JarSearch().searchJars(fromDirOrFile);
        
        return doSearchClasssesInEachJarFile(targetClassName, jarFiles);
    }

    private ArrayList<File> doSearchClasssesInEachJarFile(String targetClassName, ArrayList<File> jarFiles) throws IOException
    {
        ArrayList<File> matchedFiles = new ArrayList<File>();
        AntPathMatcher matcher = new AntPathMatcher();
        ClassSearch classes = new ClassSearch();
        for(File file : jarFiles)
        {
            ArrayList<String> classNames;
            try
            {
                classNames = classes.searchClasses(new JarFile(file));

                for(String className : classNames)
                {
                    if(matcher.match(targetClassName, className))
                    {
                        matchedFiles.add(file);
                        break;
                    }
                }
            }
            catch(Exception e)
            {
                logger.error("Search classes in file:[" + file.getAbsolutePath() + "] failed. Detail:", e);
            }
        }
        return matchedFiles;
    }
}

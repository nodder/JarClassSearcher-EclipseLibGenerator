package name.cdd.product.clzsearch.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import name.cdd.product.clzsearch.business.search4classes.ClassSearch;
import name.cdd.product.clzsearch.business.search4jar.JarSearch;


public class Searcher
{
    public ArrayList<File> search(String targetClassName, File fromDir) throws IOException
    {
        ArrayList<File> matchedFiles = new ArrayList<File>();
        
        JarSearch jarSearch = new JarSearch();
        ArrayList<File> jarFiles = jarSearch.searchJars(fromDir);
        
        ClassSearch classes = new ClassSearch();
        for(File file : jarFiles)
        {
            ArrayList<String> classNames = classes.searchClasses(new JarFile(file));
            
            for(String className : classNames)
            {
                if(targetClassName.equals(className))
                {
                    matchedFiles.add(file);
                }
            }
        }
        
        return matchedFiles;
    }
}

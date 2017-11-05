package name.cdd.product.clzsearch.userlibmaker.business.manager;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class JarAndClassListManager
{
    private Set<File> jarList = new LinkedHashSet<File>();
    private Set<File> actualSelectedJarList = new TreeSet<File>();
    private Map<String, List<File>> classMapJarFiles = new HashMap<String, List<File>>();
    private Map<String, File> classMapSelectedJarFile = new HashMap<String, File>();

    public void add(String className, List<File> jars, File actualSelectedJarFile)
    {
        if(jars != null)
        {
            jarList.addAll(jars);
        }
        
        if(actualSelectedJarFile != null)
        {
            actualSelectedJarList.add(actualSelectedJarFile);
        }
        classMapJarFiles.put(className, jars);
        classMapSelectedJarFile.put(className, actualSelectedJarFile);
    }

    public Collection<File> getAllJarList()//Œﬁ”√
    {
        return jarList;
    }

    public List<File> getRelatedJarFileList(String className)
    {
        return classMapJarFiles.get(className);
    }
    
    public File getActualSelectedJarFile(String className)
    {
        return classMapSelectedJarFile.get(className);
    }
    
    public Collection<File> getArchivesJarList()
    {
        return actualSelectedJarList;
    }
}

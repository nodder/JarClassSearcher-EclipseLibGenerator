package name.cdd.product.clzsearch.userlibmaker.business.manager;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ClassAndJavaListManager
{
    private Set<String> classNameList = new TreeSet<String>();
    private Map<String, Set<File>> classMapFile = new HashMap<String, Set<File>>();

    public void add(File javaFile, List<String> classesRelatedToFile)
    {
        classNameList.addAll(classesRelatedToFile);
        
        for(String className : classesRelatedToFile)
        {
            if(!classMapFile.containsKey(className))
            {
                classMapFile.put(className, new TreeSet<File>());
            }
           
            classMapFile.get(className).add(javaFile);
        }
    }

    public Collection<String> getManagedClassNameList()
    {
        return classNameList;
    }

    public Collection<File> getRelatedFileList(String className)
    {
        return classMapFile.get(className);
    }
}

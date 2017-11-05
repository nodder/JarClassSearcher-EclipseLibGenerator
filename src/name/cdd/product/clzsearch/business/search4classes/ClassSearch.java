package name.cdd.product.clzsearch.business.search4classes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassSearch
{
    public ArrayList<String> searchClasses(JarFile jarFile)
    {
        Enumeration<JarEntry> entries = jarFile.entries();
        
        ArrayList<String> classNameList = new ArrayList<String>();
        while (entries.hasMoreElements()) 
        {
            String entryName = entries.nextElement().getName();
            
            if(isClass(entryName))
            {
                classNameList.add(format(entryName)); 
            }
        }
        
        return classNameList;
    }

    String format(String entryName)
    {
        return entryName.replace('/', '.').replace('$', '.').substring(0, entryName.length() - ".class".length());
    }

    private boolean isClass(String entryName)
    {
        return entryName.endsWith(".class");
    }
}

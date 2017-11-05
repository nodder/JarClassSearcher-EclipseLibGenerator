package name.cdd.product.clzsearch.userlibmaker.business.postprocess;

import java.io.File;
import java.util.Collection;
import java.util.TreeSet;

public class IncreaseProcess implements PostProcess
{
    private String jarRootDirectory;

    public IncreaseProcess(String jarRootDirectory)
    {
        this.jarRootDirectory = jarRootDirectory;
    }
    
    @Override
    public Collection<File> processFileCollection(Collection<File> jarFiles, String file)
    {
        Collection<File> result = new TreeSet<File>(jarFiles);
        result.add(new File(jarRootDirectory, file));
        
        return result;
    }

}

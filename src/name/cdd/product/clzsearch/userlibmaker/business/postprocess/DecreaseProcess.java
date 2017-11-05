package name.cdd.product.clzsearch.userlibmaker.business.postprocess;

import java.io.File;
import java.util.Collection;
import java.util.TreeSet;

import org.springframework.util.AntPathMatcher;

public class DecreaseProcess implements PostProcess
{

    @Override
    public Collection<File> processFileCollection(Collection<File> jarFiles, String filePattern)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        
        File[] arrFile = jarFiles.toArray(new File[0]);
        Collection<File> result = new TreeSet<File>();
        for(File tmpFile : arrFile)
        {
            if(!matcher.match("*" + filePattern, tmpFile.getAbsolutePath()))
            {
                result.add(tmpFile);
            }
        }
        
        return result;
    }

}

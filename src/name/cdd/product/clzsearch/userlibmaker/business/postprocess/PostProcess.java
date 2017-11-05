package name.cdd.product.clzsearch.userlibmaker.business.postprocess;

import java.io.File;
import java.util.Collection;

public interface PostProcess
{
    Collection<File> processFileCollection(Collection<File> jarFiles, String filePattern);

}

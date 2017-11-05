package name.cdd.product.clzsearch.userlibmaker.business.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface IJarsSearcher
{
    /** 如果搜索不到，返回空数组，而不是null */
    ArrayList<File> search(String targetClassName, File fromDirOrFile) throws IOException;
}

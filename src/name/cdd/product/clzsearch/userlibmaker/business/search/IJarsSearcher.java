package name.cdd.product.clzsearch.userlibmaker.business.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface IJarsSearcher
{
    /** ����������������ؿ����飬������null */
    ArrayList<File> search(String targetClassName, File fromDirOrFile) throws IOException;
}

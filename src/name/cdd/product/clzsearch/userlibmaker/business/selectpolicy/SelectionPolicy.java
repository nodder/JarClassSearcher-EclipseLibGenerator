package name.cdd.product.clzsearch.userlibmaker.business.selectpolicy;

import java.io.File;
import java.util.ArrayList;

public interface SelectionPolicy
{
    /**
     * 如果jars不包含元素，则返回null
     * @param jars
     * @return
     */
    File selectOneFromJars(ArrayList<File> jars);
}

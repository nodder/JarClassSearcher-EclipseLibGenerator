package name.cdd.product.clzsearch.userlibmaker.business.selectpolicy;

import java.io.File;
import java.util.ArrayList;

public interface SelectionPolicy
{
    /**
     * ���jars������Ԫ�أ��򷵻�null
     * @param jars
     * @return
     */
    File selectOneFromJars(ArrayList<File> jars);
}

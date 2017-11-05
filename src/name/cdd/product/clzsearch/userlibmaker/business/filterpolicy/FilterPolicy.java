package name.cdd.product.clzsearch.userlibmaker.business.filterpolicy;

import java.io.File;
import java.util.ArrayList;

public interface FilterPolicy
{
    /** 
     * 返回mathedJars中所有匹配项的索引值数组
     * 如果没有过滤掉任何值，则返回原数组 */
    ArrayList<File> filter(ArrayList<File> mathedJars);

}

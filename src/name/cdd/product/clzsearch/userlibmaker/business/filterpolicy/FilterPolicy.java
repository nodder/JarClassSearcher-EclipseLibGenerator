package name.cdd.product.clzsearch.userlibmaker.business.filterpolicy;

import java.io.File;
import java.util.ArrayList;

public interface FilterPolicy
{
    /** 
     * ����mathedJars������ƥ���������ֵ����
     * ���û�й��˵��κ�ֵ���򷵻�ԭ���� */
    ArrayList<File> filter(ArrayList<File> mathedJars);

}

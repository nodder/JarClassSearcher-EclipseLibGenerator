package name.cdd.product.clzsearch.userlibmaker.business.postprocess;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import name.cdd.product.clzsearch.userlibmaker.business.postprocess.PostProcess;
import name.cdd.product.clzsearch.userlibmaker.business.postprocess.PostProcessFacotry;

import junit.framework.TestCase;

public class PostProcessingTest extends TestCase
{
    public void test_empty_add_one()
    {
        Collection<File> jarFiles = new ArrayList<File>();
        
        PostProcess pp = PostProcessFacotry.getPostProcess("+", "c:\\");
        String jarFile = "aaa.jar";
        Collection<File> actualResult = pp.processFileCollection(jarFiles, jarFile);
        
        assertList(actualResult, "c:\\aaa.jar");
    }
    
    public void test_one_add_one()
    {
        Collection<File> jarFiles = new ArrayList<File>();
        jarFiles.add(new File("c:\\ori.jar"));
        
        PostProcess pp = PostProcessFacotry.getPostProcess("+", "c:\\");
        Collection<File> actualResult = pp.processFileCollection(jarFiles, "aaa.jar");
        
        assertList(actualResult, "c:\\aaa.jar", "c:\\ori.jar");
    }
    
    public void test_add_duplicate()
    {
        Collection<File> jarFiles = new ArrayList<File>();
        jarFiles.add(new File("c:\\ori.jar"));
        jarFiles.add(new File("c:\\aaa.jar"));
        
        PostProcess pp = PostProcessFacotry.getPostProcess("+", "c:\\");
        Collection<File> actualResult = pp.processFileCollection(jarFiles, "aaa.jar");
        
        assertList(actualResult, "c:\\aaa.jar", "c:\\ori.jar");
    }
    
    public void test_one_del_exist_one()
    {
        Collection<File> jarFiles = new ArrayList<File>();
        jarFiles.add(new File("c:\\ori.jar"));
        jarFiles.add(new File("c:\\aaa.jar"));
        jarFiles.add(new File("c:\\zzz.jar"));
        
        PostProcess pp = PostProcessFacotry.getPostProcess("-", "c:\\");
        Collection<File> actualResult = pp.processFileCollection(jarFiles, "ori.jar");
        
        assertList(actualResult, "c:\\aaa.jar", "c:\\zzz.jar");
    }
    
    public void test_one_del_not_exist_one()
    {
        Collection<File> jarFiles = new ArrayList<File>();
        jarFiles.add(new File("c:\\ori.jar"));
        jarFiles.add(new File("c:\\bbb.jar"));
        
        PostProcess pp = PostProcessFacotry.getPostProcess("-", "c:\\");
        Collection<File> actualResult = pp.processFileCollection(jarFiles, "aaa.jar");
        
        assertList(actualResult, "c:\\bbb.jar", "c:\\ori.jar");
    }
    
    public void test_del_with_pattern()
    {
        Collection<File> jarFiles = new ArrayList<File>();
        jarFiles.add(new File("c:\\ori.jar"));
        jarFiles.add(new File("c:\\aaa\\bbb.jar"));
        jarFiles.add(new File("d:\\eee.jar"));
        
        PostProcess pp = PostProcessFacotry.getPostProcess("-", "c:\\");
        Collection<File> actualResult = pp.processFileCollection(jarFiles, "aaa\\*.jar");
        
        assertList(actualResult, "c:\\ori.jar", "d:\\eee.jar");
    }
    
    
    private static void assertList(Collection<File> initList, String... items)
    {
        assertEquals(items.length, initList.size());
        
        int index = 0;
        for(File item : initList)
        {
            assertEquals(new File(items[index++]), item);
        }
    }
}

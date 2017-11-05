package name.cdd.product.clzsearch.userlibmaker.business.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import name.cdd.product.clzsearch.userlibmaker.business.manager.JarAndClassListManager;

import junit.framework.TestCase;

public class JarAndClassListManagerTest extends TestCase
{
    private JarAndClassListManager manager;
    private ArrayList<File> jarFiles;
    
    @Override
    protected void setUp() throws Exception
    {
        manager = new JarAndClassListManager();
        jarFiles = new ArrayList<File>();
    }
    
    public void test_insert_null()
    {
        manager.add("aaa.Test", null, null);
        assertList(manager.getAllJarList(), new String[0]);
        assertList(manager.getArchivesJarList(), new String[0]);
        assertNull(manager.getRelatedJarFileList("xxx"));
        assertNull(manager.getActualSelectedJarFile("xxx"));
    }
    
    public void test_empty()
    {
        assertList(manager.getAllJarList(), new String[0]);
        assertList(manager.getArchivesJarList(), new String[0]);
        assertNull(manager.getRelatedJarFileList("xxx"));
        assertNull(manager.getActualSelectedJarFile("xxx"));
    }
    
    public void test_one()
    {
        File selectedFile = new File("c:\\cdd-test.jar"); 
        jarFiles.add(selectedFile);
        
        manager.add("cdd.Test", jarFiles, selectedFile);
        
        assertList(manager.getAllJarList(), "c:\\cdd-test.jar");
        assertList(manager.getArchivesJarList(), "c:\\cdd-test.jar");
        assertList(manager.getRelatedJarFileList("cdd.Test"), "c:\\cdd-test.jar");
        assertEquals(selectedFile, manager.getActualSelectedJarFile("cdd.Test"));
    }
    
    public void test_multy_jarFiles()
    {
        File selectedFile = new File("b.jar"); 
        jarFiles.add(new File("c:\\cdd-test.jar"));
        jarFiles.add(new File("a.jar"));
        jarFiles.add(selectedFile);
        manager.add("cdd.Test", jarFiles, selectedFile);
        
        assertList(manager.getAllJarList(), "c:\\cdd-test.jar", "a.jar", "b.jar");
        assertList(manager.getArchivesJarList(), "b.jar");
        assertList(manager.getRelatedJarFileList("cdd.Test"), "c:\\cdd-test.jar", "a.jar", "b.jar");
        assertEquals(selectedFile, manager.getActualSelectedJarFile("cdd.Test"));
        assertNull(manager.getRelatedJarFileList("xxx"));
    }
    
    public void test_multy()
    {
        File selectedFile1 = new File("c:\\cdd-test.jar"); 
        jarFiles.add(selectedFile1);
        jarFiles.add(new File("a.jar"));
        jarFiles.add(new File("b.jar"));
        manager.add("cdd.Test", jarFiles, selectedFile1);
        
        File selectedFile2 = new File("b.jar"); 
        jarFiles = new ArrayList<File>();
        jarFiles.add(new File("c:\\test.jar"));
        jarFiles.add(selectedFile2);
        manager.add("new.Test", jarFiles, selectedFile2);
        
        assertList(manager.getAllJarList(), "c:\\cdd-test.jar", "a.jar", "b.jar", "c:\\test.jar");
        assertList(manager.getArchivesJarList(), "b.jar", "c:\\cdd-test.jar");
        assertList(manager.getRelatedJarFileList("cdd.Test"), "c:\\cdd-test.jar", "a.jar", "b.jar");;
        assertList(manager.getRelatedJarFileList("new.Test"), "c:\\test.jar", "b.jar");
        assertEquals(selectedFile1, manager.getActualSelectedJarFile("cdd.Test"));
        assertEquals(selectedFile2, manager.getActualSelectedJarFile("new.Test"));
        assertNull(manager.getRelatedJarFileList("xxx"));
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

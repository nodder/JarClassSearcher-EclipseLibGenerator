package name.cdd.product.clzsearch.userlibmaker.business.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import name.cdd.product.clzsearch.userlibmaker.business.manager.ClassAndJavaListManager;

import junit.framework.TestCase;

public class ClassAndJavaListManagerTest extends TestCase
{
    private ClassAndJavaListManager manager;
    
    @Override
    protected void setUp() throws Exception
    {
        manager = new ClassAndJavaListManager();
    }
    
    public void test_empty()
    {
        ArrayList<String> importedClasses = new ArrayList<String>();
        
        File javaFile = new File("c:\\Test.java");
        manager.add(javaFile, importedClasses);
        
        assertList(manager.getManagedClassNameList(), new String[0]);
        assertNull(manager.getRelatedFileList(""));
    }
    
    public void test_one_file()
    {
        ArrayList<String> importedClasses = new ArrayList<String>();
        importedClasses.add("cdd.Test");
        importedClasses.add("com.zte.ums.uni.cdf.CenterUtil");
        
        File javaFile = new File("c:\\Test.java");
        manager.add(javaFile, importedClasses);
        
        
        assertList(manager.getManagedClassNameList(), "cdd.Test", "com.zte.ums.uni.cdf.CenterUtil");
        assertFileSet(manager.getRelatedFileList("cdd.Test"), "c:\\Test.java");
        assertFileSet(manager.getRelatedFileList("com.zte.ums.uni.cdf.CenterUtil"), "c:\\Test.java");
        assertNull(manager.getRelatedFileList("xxx"));
    }

    public void test_two_files()
    {
        ArrayList<String> importedClasses = new ArrayList<String>();
        importedClasses.add("cdd.Test");
        importedClasses.add("com.zte.ums.uni.cdf.CenterUtil");
        
        File javaFile = new File("c:\\Test.java");
        manager.add(javaFile, importedClasses);
        
        importedClasses.clear();
        importedClasses.add("cdd.Test");
        importedClasses.add("aaa.cdd.test.Work");
        
        javaFile = new File("c:\\aaa\\AAA.java");
        manager.add(javaFile, importedClasses);
        
        
        assertList(manager.getManagedClassNameList(), "aaa.cdd.test.Work", "cdd.Test", "com.zte.ums.uni.cdf.CenterUtil");
        assertFileSet(manager.getRelatedFileList("cdd.Test"), "c:\\aaa\\AAA.java", "c:\\Test.java") ;
        assertFileSet(manager.getRelatedFileList("com.zte.ums.uni.cdf.CenterUtil"), "c:\\Test.java");
        assertFileSet(manager.getRelatedFileList("aaa.cdd.test.Work"), "c:\\aaa\\AAA.java");
        assertNull(manager.getRelatedFileList("xxx"));
    }
    
    public void test_classname_duplicate()
    {
        ArrayList<String> importedClasses = new ArrayList<String>();
        importedClasses.add("cdd.Test");
        importedClasses.add("com.zte.ums.uni.cdf.CenterUtil");
        importedClasses.add("com.zte.ums.uni.cdf.CenterUtil");
        
        File javaFile = new File("c:\\Test.java");
        manager.add(javaFile, importedClasses);
        
        importedClasses.clear();
        importedClasses.add("cdd.Test");
        importedClasses.add("cdd.Test");
        importedClasses.add("cdd.Test");
        importedClasses.add("aaa.cdd.test.Work");
        
        javaFile = new File("c:\\aaa\\AAA.java");
        manager.add(javaFile, importedClasses);
        
        
        assertList(manager.getManagedClassNameList(), "aaa.cdd.test.Work", "cdd.Test", "com.zte.ums.uni.cdf.CenterUtil");
        assertFileSet(manager.getRelatedFileList("cdd.Test"), "c:\\aaa\\AAA.java", "c:\\Test.java") ;
        assertFileSet(manager.getRelatedFileList("com.zte.ums.uni.cdf.CenterUtil"), "c:\\Test.java");
        assertFileSet(manager.getRelatedFileList("aaa.cdd.test.Work"), "c:\\aaa\\AAA.java");
        assertNull(manager.getRelatedFileList("xxx"));
    }
    
    public void test_javaFile_duplicate()
    {
        ArrayList<String> importedClasses = new ArrayList<String>();
        importedClasses.add("cdd.Test");
        importedClasses.add("com.zte.ums.uni.cdf.CenterUtil");
        importedClasses.add("com.zte.ums.uni.cdf.CenterUtil");
        
        File javaFile = new File("c:\\Test.java");
        manager.add(javaFile, importedClasses);
        
        importedClasses.clear();
        importedClasses.add("cdd.Test");
        importedClasses.add("cdd.Test");
        importedClasses.add("cdd.Test");
        importedClasses.add("aaa.cdd.test.Work");
        
        javaFile = new File("c:\\Test.java");
        manager.add(javaFile, importedClasses);
        
        
        assertList(manager.getManagedClassNameList(), "aaa.cdd.test.Work", "cdd.Test", "com.zte.ums.uni.cdf.CenterUtil");
        assertFileSet(manager.getRelatedFileList("cdd.Test"), "c:\\Test.java") ;
        assertFileSet(manager.getRelatedFileList("com.zte.ums.uni.cdf.CenterUtil"), "c:\\Test.java");
        assertFileSet(manager.getRelatedFileList("aaa.cdd.test.Work"), "c:\\Test.java");
        assertNull(manager.getRelatedFileList("xxx"));
    }

    
    private void assertFileSet(Collection<File> relatedFileList, String... items)
    {
        assertEquals(items.length, relatedFileList.size());
        
        int index = 0;
        for(File item : relatedFileList)
        {
            assertEquals(new File(items[index++]), item);
        }
    }

    private static void assertList(Collection<String> initList, String... items)
    {
        assertEquals(items.length, initList.size());
        
        int index = 0;
        for(String item : initList)
        {
            assertEquals(items[index++], item);
        }
    }
}

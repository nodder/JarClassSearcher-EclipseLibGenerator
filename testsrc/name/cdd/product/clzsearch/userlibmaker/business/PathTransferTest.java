package name.cdd.product.clzsearch.userlibmaker.business;

import java.io.File;

import name.cdd.product.clzsearch.userlibmaker.business.PathTransfer;

import junit.framework.TestCase;

public class PathTransferTest extends TestCase
{
    private PathTransfer trans;
    
    @Override
    protected void setUp() throws Exception
    {
        trans = new PathTransfer() {
            public String getJarRootDir()
            {
                return "c:\\";
            }
        };
        
        new File("c:\\aaa\\bbb\\").mkdirs();
        
        new File("c:\\aaa\\aaa.jar").createNewFile();
        new File("c:\\aaa\\bbb\\aaabbb.jar").createNewFile();
        new File("c:\\test.jar").createNewFile();
        
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception
    {    
        new File("c:\\test.jar").delete();
        new File("c:\\aaa\\bbb\\aaabbb.jar").delete();
        new File("c:\\aaa\\aaa.jar").delete();
        
        new File("c:\\aaa\\bbb").delete();
        new File("c:\\aaa\\").delete();
    }
    
    public void test_filePatternToPath()
    {
        assertEquals(new File("c:\\"), trans.filePatternToPath("*.jar"));
        assertEquals(new File("c:\\aaa"), trans.filePatternToPath("aaa\\*.jar"));
        assertEquals(new File("c:\\aaa\\bbb"), trans.filePatternToPath("aaa//bbb//*.jar"));
        assertEquals(new File("c:\\test.jar"), trans.filePatternToPath("test.jar"));
        assertEquals(new File("c:\\aaa\\aaa.jar"), trans.filePatternToPath("aaa//aaa.jar"));
    
        assertNull(trans.filePatternToPath("aaa\\*"));
        assertNull(trans.filePatternToPath("aaa\\not_exist_file.jar"));
        assertNull(trans.filePatternToPath("not_exist_file.jar"));
    }

    public void test_fromFileListPattern_1()
    {
        String[] patternList = new String[] {"*.jar"};
        File[] expectedPaths = new File[] {new File("c:\\")};
        
        assertArrs(expectedPaths, trans.filePatternListToFiles(patternList));
    }
    
    public void test_fromFileListPattern_2()
    {
        String[] patternList = new String[] {"*", "test.jar", "aaa/noExist.jar", "noExistDir/noExist.jar", "aaa/*.jar"};
        File[] expectedPaths = new File[] {new File("c:\\test.jar"), new File("c:\\aaa\\")};
        
        assertArrs(expectedPaths, trans.filePatternListToFiles(patternList));
    }

    public void test_fromFileListPattern_empty()
    {
        assertEquals(0, trans.filePatternListToFiles(new String[0]).length);
    }

    private void assertArrs(File[] expectedPaths, File[] actualPaths)
    {
        assertEquals(expectedPaths.length, actualPaths.length);
        
        for(int i = 0; i < expectedPaths.length; i++)
        {
            File expectedPath = expectedPaths[i];
            assertEquals(expectedPath, actualPaths[i]);
        }
    }
}

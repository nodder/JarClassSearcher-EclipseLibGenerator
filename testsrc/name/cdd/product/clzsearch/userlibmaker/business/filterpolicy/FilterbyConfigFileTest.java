package name.cdd.product.clzsearch.userlibmaker.business.filterpolicy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterByConfigFile;

import junit.framework.TestCase;

public class FilterbyConfigFileTest extends TestCase
{
    private ArrayList<File> jars;

    @Override
    protected void setUp() throws Exception
    {
        jars = new ArrayList<File>();
    }

    public void test_match_one_fullMatch()
    {
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\test2.jar"));
        jars.add(new File("c:\\test3.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\test2.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\test2.jar");
    }
    
    public void test_match_multy_Match()
    {
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\mytest.jar"));
        jars.add(new File("c:\\mytest2.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\my*jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\mytest.jar", "c:\\mytest2.jar");
    }
    
    public void test_match_multy_Match2()
    {
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\mytest.jar"));
        jars.add(new File("c:\\abc\\mytest2.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"*mytest*.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\mytest.jar", "c:\\abc\\mytest2.jar");
    }
    
    public void test_match_multy_Match3()
    {
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\abc\\mytest1.jar"));
        jars.add(new File("c:\\mytest.jar"));
        jars.add(new File("c:\\abc\\mytest2.jar"));
        jars.add(new File("c:\\abc\\ddd\\123.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\abc\\*.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\abc\\mytest1.jar", "c:\\abc\\mytest2.jar", "c:\\abc\\ddd\\123.jar");
    }
    
    public void test_match_multy_Match4()
    {
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\abc\\mytest1.jar"));
        jars.add(new File("c:\\mytest.jar"));
        jars.add(new File("c:\\abc\\mytest2.jar"));
        jars.add(new File("c:\\abc\\ddd\\123.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\abc\\**\\*.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\abc\\ddd\\123.jar");
    }
    
    public void test_match_one_Match1()
    {
        jars.add(new File("F:\\nomatch.jar"));
        jars.add(new File("c:\\test\\jboss-jmx.jar"));
        jars.add(new File("F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\right.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"
                                     , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                     , "c:\\test\\jboss-jmx.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\right.jar");
    }
    
    public void test_match_one_Match2()
    {
        jars.add(new File("F:\\nomatch.jar"));
        jars.add(new File("c:\\test\\jboss-jmx.jar"));
        jars.add(new File("F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\right.jar"));

        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"
                                     , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                     , "c:\\test\\jboss-jmx.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\right.jar");
    }
    
    public void test_match_one_Match3()
    {
        jars.add(new File("F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\jboss-jmx.jar"));
        jars.add(new File("c:\\test\\jboss-jmx.jar"));
        jars.add(new File("c:\\test\\aaa\\jboss-jmx.jar"));
        
        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\test\\jboss-jmx.jar"
                                     , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                     , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\test\\jboss-jmx.jar");
    }
    
    public void test_match_none()
    {
        jars.add(new File("c:\\nomatch.jar"));
        jars.add(new File("c:\\nomatch2.jar"));
        
        FilterByConfigFile filterByConfigFile = new FilterByConfigFile(null)
        {
            @Override
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"
                                     , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                     , "c:\\test\\jboss-jmx.jar"};
            }
        };

        assertFileList(filterByConfigFile.filter(jars), "c:\\nomatch.jar", "c:\\nomatch2.jar");
    }
    

//  private static void assertIntArr(int[] arrInt, int... expectedItems)
//  {
//      assertEquals(expectedItems.length, arrInt.length);
//      
//      for(int i = 0; i < expectedItems.length; i++)
//      {
//          assertEquals(expectedItems[i], arrInt[i]);
//      }
//  }
  
private static void assertFileList(List<File> actualList, String... expectedItems)
{
    assertEquals(expectedItems.length, actualList.size());
    
    for(int i = 0; i < expectedItems.length; i++)
    {
        assertEquals(new File(expectedItems[i]), actualList.get(i));
    }
}
}

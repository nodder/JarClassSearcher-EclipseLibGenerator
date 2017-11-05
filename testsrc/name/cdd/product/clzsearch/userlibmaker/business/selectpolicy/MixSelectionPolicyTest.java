package name.cdd.product.clzsearch.userlibmaker.business.selectpolicy;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;
import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterByConfigFile;
import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterPolicy;

public class MixSelectionPolicyTest extends TestCase
{
    private ArrayList<File> jars;
    
    @Override
    protected void setUp() throws Exception
    {
        jars = new ArrayList<File>();
    }
    
    public void test_empty()
    {
        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {new FilterLastOnePolicy()});
        
        assertNull(p.selectOneFromJars(jars));
    }
    
    public void test_filter_first_policy()
    {
        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {new FilterLastOnePolicy()});
        
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\test2.jar"));
        jars.add(new File("c:\\test3.jar"));
        
        assertEquals("c:\\test3.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_one_filterNone_one_filterFirst()
    {
        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] { new FilterNonePolicy(), new FilterLastOnePolicy()});
        
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\test2.jar"));
        jars.add(new File("c:\\test3.jar"));
        
        assertEquals("c:\\test3.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_one_filterFirst_one_filterNone()
    {
        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] { new FilterLastOnePolicy(), new FilterNonePolicy()});
        
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\test2.jar"));
        jars.add(new File("c:\\test3.jar"));
        
        assertEquals("c:\\test3.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_one_filteNone_one_selectFirst()
    {
        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {new FilterNonePolicy()});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());
        
        jars.add(new File("c:\\test1.jar"));
        jars.add(new File("c:\\test2.jar"));
        jars.add(new File("c:\\test3.jar"));
        
        assertEquals("c:\\test1.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filteBy_config_file()
    {
        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[]
            {new FilterByConfigFile(null)
            {
                public String[] initPriorityRankedJarFileList()
                {
                    return new String[] {"c:\\123\\*"};
                };
            }});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("c:\\1.jar"));
        jars.add(new File("c:\\123\\2.jar"));
        jars.add(new File("c:\\abc\\3.jar"));

        assertEquals("c:\\123\\2.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filteBy_two_config_files()
    {
        FilterPolicy configFileFilter1 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\abc\\subabc\\*"};
            };
        };
        
        FilterPolicy configFileFilter2 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[]
                    {"*my.jar"};
            };
        };

        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {configFileFilter1, configFileFilter2});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("c:\\1.jar"));
        jars.add(new File("c:\\123\\2.jar"));
        jars.add(new File("c:\\abc\\my.jar"));
        jars.add(new File("c:\\abc\\4.jar"));
        jars.add(new File("c:\\abc\\subabc\\my.jar"));
        jars.add(new File("c:\\abc\\subabc\\7.jar"));

        assertEquals("c:\\abc\\subabc\\my.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filteBy_two_config_files_2()
    {
        FilterPolicy configFileFilter1 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"
                                   , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                   , "c:\\test\\jboss-jmx.jar"};
            };
        };
        
        FilterPolicy configFileFilter2 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"E:\\test\\*.jar"
                                   , "F:\\test2\\*.jar"};
            };
        };

        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {configFileFilter1, configFileFilter2});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("F:\\test2\\match.jar"));

        assertEquals("F:\\test2\\match.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filteBy_config_files_both_match()
    {
        FilterPolicy configFileFilter1 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\abc\\subabc\\*"};
            };
        };
        
        FilterPolicy configFileFilter2 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[]
                    {"*my.jar"};
            };
        };

        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {configFileFilter1, configFileFilter2});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("c:\\1.jar"));
        jars.add(new File("c:\\123\\2.jar"));
        jars.add(new File("c:\\abc\\my.jar"));
        jars.add(new File("c:\\abc\\4.jar"));
        jars.add(new File("c:\\abc\\subabc\\testmy.jar"));
        jars.add(new File("c:\\abc\\subabc\\my.jar"));
        jars.add(new File("c:\\abc\\subabc\\7.jar"));

        assertEquals("c:\\abc\\subabc\\testmy.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filteBy_config_files_1()
    {
        FilterPolicy configFileFilter1 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"c:\\test\\jboss-jmx.jar"
                                   , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                   , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"};
            };
        };

        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {configFileFilter1});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\jboss-jmx.jar"));
        jars.add(new File("c:\\test\\jboss-jmx.jar"));
        jars.add(new File("c:\\test\\aaa\\jboss-jmx.jar"));

        assertEquals("c:\\test\\jboss-jmx.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filter_none()
    {
        FilterPolicy configFileFilter1 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"
                                   , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                   , "c:\\test\\jboss-jmx.jar"};
            };
        };

        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {configFileFilter1});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("c:\\nomatch.jar"));
        jars.add(new File("c:\\nomatch2.jar"));

        assertEquals("c:\\nomatch.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
    
    public void test_filter_two_configFile_result_snone()
    {
        FilterPolicy configFileFilter1 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\core\\lib\\*.jar"
                                   , "F:\\4x\\2012-12-31\\ums-server\\procs\\bsf\\thirdparty\\*.jar"
                                   , "c:\\test\\jboss-jmx.jar"};
            };
        };
        
        FilterPolicy configFileFilter2 = new FilterByConfigFile(null)
        {
            public String[] initPriorityRankedJarFileList()
            {
                return new String[] {"E:\\test\\*.jar"
                                   , "F:\\test2\\*.jar"};
            };
        };

        MixSelectionPolicy p = new MixSelectionPolicy();
        p.setFilterPolilcy(new FilterPolicy[] {configFileFilter1, configFileFilter2});
        p.setSelectionPolicyWhenFilterCannotDone(new AlwaysSelectFirstOne());

        jars.add(new File("c:\\nomatch.jar"));
        jars.add(new File("c:\\nomatch2.jar"));

        assertEquals("c:\\nomatch.jar", p.selectOneFromJars(jars).getAbsolutePath());
    }
}

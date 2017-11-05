package name.cdd.product.clzsearch.userlibmaker.business.ignorepolicy;

import name.cdd.product.clzsearch.userlibmaker.business.ignorepolicy.IgnorePolicy;
import name.cdd.product.clzsearch.userlibmaker.business.ignorepolicy.IngoredByConfigFile;
import junit.framework.TestCase;

public class IgnorePolicyTest extends TestCase
{
    public void test_empty()
    {
        IgnorePolicy ignorePolicy = new IngoredByConfigFile()
        {
            @Override
            public String[] initIgnoreListPattern()
            {
                return new String[0];
            };
        };
        
        String className = "xxx";
        assertFalse(ignorePolicy.isIgnored(className));
    }
    
    public void test_full_match()
    {
        IgnorePolicy ignorePolicy = new IngoredByConfigFile()
        {
            @Override
            public String[] initIgnoreListPattern()
            {
                return new String[] {"aaa.bbb.Test"};
            };
        };
        
        assertTrue(ignorePolicy.isIgnored("aaa.bbb.Test"));
        assertFalse(ignorePolicy.isIgnored("ddd.bbb.Test"));
    }
    
    public void test_full_match_2()
    {
        IgnorePolicy ignorePolicy = new IngoredByConfigFile()
        {
            @Override
            public String[] initIgnoreListPattern()
            {
                return new String[] {"aaa.bbb.Test", "ccc.Test"};
            };
        };
        
        assertTrue(ignorePolicy.isIgnored("aaa.bbb.Test"));
        assertTrue(ignorePolicy.isIgnored("ccc.Test"));
        assertFalse(ignorePolicy.isIgnored("ddd.bbb.Test"));
    }
    
    public void test_fuzzy_match()
    {
        IgnorePolicy ignorePolicy = new IngoredByConfigFile()
        {
            @Override
            public String[] initIgnoreListPattern()
            {
                return new String[] {"java.*"};
            };
        };
        
        assertTrue(ignorePolicy.isIgnored("java.awt.ActiveEvent"));
        assertFalse(ignorePolicy.isIgnored("ccc.Test"));
    }
    
    public void test_fuzzy_match_2()
    {
        IgnorePolicy ignorePolicy = new IngoredByConfigFile()
        {
            @Override
            public String[] initIgnoreListPattern()
            {
                return new String[] {"java.*", "javax.*", "aaa.Test"};
            };
        };
        
        assertTrue(ignorePolicy.isIgnored("java.awt.ActiveEvent"));
        assertFalse(ignorePolicy.isIgnored("ccc.Test"));
        assertTrue(ignorePolicy.isIgnored("javax.security.sasl.SaslClient"));
        assertTrue(ignorePolicy.isIgnored("aaa.Test"));
    }
}

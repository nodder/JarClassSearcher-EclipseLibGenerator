package name.cdd.product.clzsearch.business.search4classes;

import name.cdd.product.clzsearch.business.search4classes.ClassSearch;
import junit.framework.TestCase;

public class ClassSearchTest extends TestCase
{
    public void testFormat()
    {
        ClassSearch s = new ClassSearch();
        
        assertEquals("com.zte.ums.an.commonsh.MDUUtil", s.format( "com/zte/ums/an/commonsh/MDUUtil.class"));
        assertEquals("com.zte.ums.an.commonsh.MDUUtil.InsideClass", s.format( "com/zte/ums/an/commonsh/MDUUtil$InsideClass.class"));
    }
}

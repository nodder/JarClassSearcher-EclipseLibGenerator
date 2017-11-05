package name.cdd.product.clzsearch.business.search4classes;

import name.cdd.product.clzsearch.business.search4classes.ImportedClassSearchInJavaFile;
import junit.framework.TestCase;

public class ImportedClassSearchInJavaFileTest extends TestCase
{
    
    public void test_getClassNameFromImportLine()
    {
        assertEquals("junit.framework.TestCase", transfer("import junit.framework.TestCase;"));
        assertEquals("junit.framework.*", transfer("import junit.framework.*;"));
        
        
        try
        {
            assertEquals("junit.framework.TestCase", transfer("import junit.framework.TestCase; //aaa"));
            assertTrue(false);
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(true);
        }
        
        try
        {
            assertEquals("java.util.*", transfer("import java.util.*;  //Imports ZT..."));
            assertTrue(false);
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(true);
        }
        
        try
        {
            assertEquals("junit.framework.TestCase", transfer("import java.util.Test;  //Imports ZT..."));
            assertTrue(false);
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(true);
        }
        
        try
        {
            assertEquals("java.util.*", transfer("import java.util.*;  //Imports aaa.bbb.*;"));
            assertTrue(false);
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }

    private String transfer(String line)
    {
        return new ImportedClassSearchInJavaFile().getClassNameFromImportLine(line);
    }
}

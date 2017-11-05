package name.cdd.product.clzsearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.cdd.toolkit.junit.TestCaseClassesFinderFromSrcFolder;


/**
 * @description 
 * @author       ChenDuoduo
 * @date         2013-1-4
 */
public class TestSuite4ClzSearch
{
    private static final String testSrcBaSeFolder = "E:/SVN_3X/ZXNM01V4_DOC/summarize/个人总结/DSL开发人员/陈多多/8.MyProducts/EclipseLibraryMaker/develop/testsrc/";
    
    
    public static Test suite() throws ClassNotFoundException, IOException
    {
        ArrayList<String> allTestCases = new TestCaseClassesFinderFromSrcFolder().find(new File(testSrcBaSeFolder));
        
        TestSuite suite = new TestSuite();
        for(String className : allTestCases)
        {
            System.out.println("Class Name:" + className);
            suite.addTestSuite(Class.forName(className));
        }

        return suite;
    }
}

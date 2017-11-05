package name.cdd.product.clzsearch.userlibmaker.gui.foldermap;

import java.util.Arrays;

import name.cdd.product.clzsearch.userlibmaker.gui.foldermap.SimpleGuiSrcFolderMapping;
import name.cdd.product.clzsearch.userlibmaker.gui.foldermap.Uep4xGuiSrcFolderMapping;

import junit.framework.TestCase;

public class Uep4xguiSrcFolderMappingTest extends TestCase
{
    public  void test()
    {
        Uep4xGuiSrcFolderMapping map = new Uep4xGuiSrcFolderMapping();
        String[] results = map.oneToN("c:\\");
        System.out.println(Arrays.toString(results));
        System.out.println(map.nTo1(results));
        
        SimpleGuiSrcFolderMapping map2 = new SimpleGuiSrcFolderMapping();
        results = map2.oneToN("c:\\");
        System.out.println(Arrays.toString(results));
        System.out.println(map2.nTo1(results));
    }
    
//    private void strArrEquals(String[] expected, String[] actual)
//    {
//        assertEquals(expected.length, actual.length);
//        
//        for(int i = 0; i < expected.length; i++)
//        {
//            assertEquals(expected[i], actual[i]);
//        }
//    }
}

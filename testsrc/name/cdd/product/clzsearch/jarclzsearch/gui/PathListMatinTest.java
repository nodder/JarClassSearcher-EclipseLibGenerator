package name.cdd.product.clzsearch.jarclzsearch.gui;

import java.util.ArrayList;
import java.util.List;

import name.cdd.product.clzsearch.jarclzsearch.gui.PathListMaint;

import junit.framework.TestCase;

public class PathListMatinTest extends TestCase
{
    private List<String> initList;
    
    @Override
    protected void setUp() throws Exception
    {
        initList = getInitList();
        PathListMaint.getInstance().initList(initList);
    }
    
    private List<String> getInitList()
    {
        List<String> initList = new ArrayList<String>();
        initList.add("aa");
        initList.add("bb");
        initList.add("cc");
        
        return initList;
    }

    public void testInitList()
    {
        assertList(PathListMaint.getInstance().getPathList(), 3, "aa", "bb", "cc");
    }
    
    public void testAddNewPath()
    {
        PathListMaint.getInstance().addNewPath("dd");
        assertList(PathListMaint.getInstance().getPathList(), 4, "dd", "aa", "bb", "cc");
    }
    
    public void testSetMaxItemCount()
    {
        assertList(PathListMaint.getInstance().getPathList(), 3, "aa", "bb", "cc");
        
        PathListMaint.getInstance().setMaxItemCount(2);
        assertList(PathListMaint.getInstance().getPathList(), 2, "aa", "bb");
        
        PathListMaint.getInstance().setMaxItemCount(3);
        assertList(PathListMaint.getInstance().getPathList(), 2, "aa", "bb");
    }
    
    public void testAddTooMuchNewPath()
    {
        PathListMaint.getInstance().setMaxItemCount(3);
        PathListMaint.getInstance().addNewPath("dd");
        assertList(PathListMaint.getInstance().getPathList(), 3, "dd", "aa", "bb");
    }
    
    public void test÷ÿ∏¥≤È—Ø()
    {
        PathListMaint.getInstance().setMaxItemCount(3);
        PathListMaint.getInstance().addNewPath("bb");
        
        assertList(PathListMaint.getInstance().getPathList(), 3, "bb", "aa", "cc");
    }

    private void assertList(List<String> initList, int count, String... items)
    {
        assertEquals(count, initList.size());
        
        for(int i = 0; i < count; i++)
        {
            assertEquals(items[i], initList.get(i));
        }
    }
}

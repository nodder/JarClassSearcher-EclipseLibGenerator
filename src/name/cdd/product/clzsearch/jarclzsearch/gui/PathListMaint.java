package name.cdd.product.clzsearch.jarclzsearch.gui;

import java.util.LinkedList;
import java.util.List;

public class PathListMaint
{
    private int maxCount = 10;
    
    private static PathListMaint instance = new PathListMaint();
    
    private LinkedList<String> pathList = new LinkedList<String>();
    
    private PathListMaint()
    {
    }
    
    public static PathListMaint getInstance()
    {
        return instance;
    }
    
    public void setMaxItemCount(int maxCount)
    {
        this.maxCount = maxCount;
        removeExtraItems(maxCount);
    }

    private void removeExtraItems(int maxCount)
    {
        int extraItemNum = pathList.size() - maxCount;
        if (extraItemNum > 0)
        {
            for (int i = 0; i < extraItemNum; i++)
            {
                pathList.removeLast();
            }
        }
    }
    
    public void addNewPath(String newPath)
    {
        int index = getIndex(newPath, pathList);
        
        if(pathAlreadyExist(index))
        {
            pathList.remove(index);
        }
        else
        {
            if(pathList.size() >= maxCount)
            {
                pathList.removeLast();
            }
        }
        
        pathList.addFirst(newPath);
    }

    private boolean pathAlreadyExist(int index)
    {
        return index != -1;
    }
    
    private int getIndex(String newPath, LinkedList<String> pathList)
    {
        for (int i = 0; i < pathList.size(); i++)
        {
            if(pathList.get(i).equals(newPath))
            {
                return i;
            }
        }
        
        return -1;
    }

    public List<String> getPathList()
    {
        return pathList;
    }

    public void initList(List<String> initList)
    {
        pathList = new LinkedList<String>();
        
        for(String str : initList)
        {
            pathList.addLast(str);
        }
    }

}

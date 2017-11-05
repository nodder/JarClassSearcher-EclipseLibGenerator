package name.cdd.product.clzsearch.jarclzsearch.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ConditionHolder
{
    ConditionInfo info;
    
    public void load() throws Exception
    {
        this.info = getPersitentedInfo();
    }
    
    public String getClassName()
    {
        return info.className;
    }
    
    public String getPath()
    {
        return info.path;
    }
    
    public ArrayList<String> getPathList()
    {
        return info.pathList;
    }
    
    public void saveClassName(String className)
    {
        info.className = className;
    }

    public void savePath(String path)
    {
        info.path = path;
    }
    
    public void savePathList(ArrayList<String> pathList)
    {
        info.pathList = pathList;
    }
    
    public void commit() throws IOException
    {
        persistent(this.info);
    }
    
    private void persistent(ConditionInfo info) throws IOException
    {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream("params.obj"));
            out.writeObject(info);
        }
        finally
        {
            if(out != null)
            {
                out.close();
            }
        }
    }
    
    public ConditionInfo getPersitentedInfo() throws Exception
    {
        File paramObjFile = new File("params.obj");
        if(!paramObjFile.exists())
        {
            return new ConditionInfo();
        }
        
        ObjectInputStream oin = null;
        try
        {
            oin = new ObjectInputStream(new FileInputStream(paramObjFile));
            return (ConditionInfo)oin.readObject();
        }
        finally
        {
            if(oin != null)
            {
                oin.close();
            }
        }
    }
}

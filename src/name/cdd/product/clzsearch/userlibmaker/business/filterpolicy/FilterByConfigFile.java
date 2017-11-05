package name.cdd.product.clzsearch.userlibmaker.business.filterpolicy;

import java.io.File;
import java.util.ArrayList;

import name.cdd.product.clzsearch.userlibmaker.business.xml.PriorityRankedXml;

import org.springframework.util.AntPathMatcher;

public class FilterByConfigFile implements FilterPolicy
{
    private String[] priorityRanked_filePattern_list;
    private PriorityRankedXml configFile;
    
    public FilterByConfigFile(PriorityRankedXml configFile)
    {
        this.configFile = configFile;
        init();
    }
    
    
    private void init()
    {
        this.priorityRanked_filePattern_list = initPriorityRankedJarFileList();
    }


    protected String[] initPriorityRankedJarFileList()
    {
        return configFile.getFilePatternList();
    }

    @Override
    public ArrayList<File> filter(ArrayList<File> jars)
    {
        ArrayList<File> matchedList = null;
        AntPathMatcher matcher = new AntPathMatcher();
        
        boolean isMatched = false;
        for(String priorityRankedJarFile : priorityRanked_filePattern_list)
        {
            for(int i = 0; i < jars.size(); i++)
            {
                File jarFile = jars.get(i);
                if(matcher.match(priorityRankedJarFile, jarFile.getAbsolutePath()))
                {
                    if(!isMatched)
                    {
                        matchedList = new ArrayList<File>();
                        isMatched = true;
                    }
                    
                    matchedList.add(jarFile);
                }
            }
            
            if(isMatched)
            {
                return matchedList;
            }
        }
        
        return jars;
    }


//    private int[] toIntArr(ArrayList<Integer> list)
//    {
//        if(list == null)
//        {
//            return null;
//        }
//        
//        int[] rtnArr = new int[list.size()];
//        for(int i = 0; i < list.size(); i++)
//        {
//            rtnArr[i] = list.get(i);
//        }
//        
//        return rtnArr;
//    }
//
//
//    private void addToList(ArrayList<Integer> matchedList, int index)
//    {
//        if(matchedList == null)
//        {
//            matchedList = new ArrayList<Integer>();
//            matchedList.add(index);
//        }
//    }

}

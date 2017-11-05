package name.cdd.product.clzsearch.userlibmaker.business.selectpolicy;

import java.io.File;
import java.util.ArrayList;

import name.cdd.product.clzsearch.userlibmaker.business.WarnMessageWriter;
import name.cdd.product.clzsearch.userlibmaker.business.filterpolicy.FilterPolicy;

public class MixSelectionPolicy implements SelectionPolicy
{
    private FilterPolicy[] filters;
    private SelectionPolicy selectionPolicy;
    
    private boolean isNeedWarnWhenMoreThanOneLeftAfterFilter;
    private WarnMessageWriter warnWriter;
    
    public MixSelectionPolicy()
    {
        this(false);
    }
    
    public MixSelectionPolicy(boolean isNeedWarnWhenMoreThanOneLeftAfterFilter)
    {
        this.isNeedWarnWhenMoreThanOneLeftAfterFilter = isNeedWarnWhenMoreThanOneLeftAfterFilter;
        
        if(isNeedWarnWhenMoreThanOneLeftAfterFilter)
        {
            warnWriter = new WarnMessageWriter(new File("warningJarList.txt").getAbsoluteFile());
        }
    }

    public void setFilterPolilcy(FilterPolicy[] filters)
    {
        this.filters = filters;
    }
    
    public void setSelectionPolicyWhenFilterCannotDone(SelectionPolicy selectionPolicy)
    {
        this.selectionPolicy = selectionPolicy;
    }
    
    @Override
    public File selectOneFromJars(ArrayList<File> jars)
    {
        if(jars.size() == 0)
        {
            return null;
        }
        
        if(jars.size() == 1)
        {
            return jars.get(0);
        }
        
        ArrayList<File> mathedJars = jars;
        for(FilterPolicy filter : filters)
        {
            ArrayList<File> matched = filter.filter(mathedJars);
            
            if(matched != null)
            {
                if(matched.size() == 1)
                {
                    return matched.get(0);
                }
                
                mathedJars = matched;
            }
        }
        
        if(isNeedWarnWhenMoreThanOneLeftAfterFilter && mathedJars.size() > 1)
        {
            warnWriter.write(jars, mathedJars);
        }
        
        return selectionPolicy.selectOneFromJars(mathedJars);
    }

//    private ArrayList<File> reinitMatchedJar(ArrayList<File> jars, int[] matched)
//    {
//        ArrayList<File> matchedJars = new ArrayList<File>();
//        
//        for(int matchedIndex : matched)
//        {
//            matchedJars.add(jars.get(matchedIndex));
//        }
//        
//        return matchedJars;
//    }

}

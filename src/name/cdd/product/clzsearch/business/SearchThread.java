package name.cdd.product.clzsearch.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import name.cdd.product.clzsearch.business.search4classes.ClassSearch;
import name.cdd.product.clzsearch.business.search4jar.JarSearch;

import org.springframework.util.AntPathMatcher;

public class SearchThread extends Thread
{
    private String targetClassName;
    private File fromDir;
    private SearcherAsynchronous searcher;
    
    SearchThread(SearcherAsynchronous searcher, String targetClassName, File fromDir)
    {
        this.targetClassName = targetClassName;
        this.fromDir = fromDir;
        this.searcher = searcher;
    }

    public void run()
    {
        try
        {
            search();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void search() throws IOException
    {
        JarSearch jarSearch = new JarSearch();
        
        searcher.onTips("Searching jar files...");
        ArrayList<File> jarFiles = jarSearch.searchJars(fromDir);
        
        ClassSearch classes = new ClassSearch();
        
        AntPathMatcher matcher = new AntPathMatcher();
        
        int fileCount = jarFiles.size();
        for(int i = 0; i < fileCount; i++)
        {
            File file = jarFiles.get(i);
            
            searcher.onTips("(" + (i+1) + "/" + fileCount + ") Searching in " + file.getName());
            ArrayList<String> classNames = classes.searchClasses(new JarFile(file));
            
            for(String className : classNames)
            {
                if(matcher.match(targetClassName, className))
                {
                    searcher.onFindOneResult(file);
                    break;
                }
            }
        }
        
        searcher.onFinish();
    }
}

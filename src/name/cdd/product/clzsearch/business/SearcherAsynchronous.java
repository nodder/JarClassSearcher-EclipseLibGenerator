package name.cdd.product.clzsearch.business;

import java.io.File;
import java.io.IOException;

public class SearcherAsynchronous
{
    private SearchCaller caller;
    private boolean isFindSomething;

    public SearcherAsynchronous(SearchCaller caller)
    {
        this.caller = caller;
    }
    
    public void startSearch(String targetClassName, File fromDir) throws IOException
    {
        isFindSomething = false;
        SearchThread searchThread = new SearchThread(this, targetClassName, fromDir);
        searchThread.start();
        System.out.println("SearcherAsynchronous return");
    }

    public void onFindOneResult(File file)
    {
        isFindSomething = true;
        caller.onFindOneResult(file);
    }

    public void onFinish()
    {
        if(isFindSomething)
        {
            caller.onFinish();
        }
        else
        {
            caller.onFinishWithFindNothing();
        }
        
    }

    public void onTips(String tips)
    {
        caller.onTips(tips);
    }
}

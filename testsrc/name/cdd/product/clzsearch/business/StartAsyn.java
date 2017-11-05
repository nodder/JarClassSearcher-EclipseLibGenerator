package name.cdd.product.clzsearch.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import name.cdd.product.clzsearch.business.SearcherAsynchronous;

public class StartAsyn
{
    private static void searchAsyn() throws IOException
    {
        SearcherAsynchronous searcherAsyn = new SearcherAsynchronous(null);
        searcherAsyn.startSearch("com.zte.ums.an.uni.service.conf.svr.vlancfg.mibtable.AddVlanXConnectTable", new File("F:\\4x\\2012-12-24"));
    }

    private static void printFiles(ArrayList<File> files)
    {
        for(File file : files)
        {
            System.out.println(file.getAbsolutePath());
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        searchAsyn();
    }

}

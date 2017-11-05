package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;

public class UnifiedPriorityRankedXml extends PriorityRankedXml
{
    private static UnifiedPriorityRankedXml instance = new UnifiedPriorityRankedXml();
    
    private UnifiedPriorityRankedXml()
    {
        super(new File("conf/" +  ActiveDir.getActiveDir(), "Jar-Searching-Ranked-Priority.xml"));
    }
    
    public static UnifiedPriorityRankedXml getInstance() 
    {
        return instance;
    }
}

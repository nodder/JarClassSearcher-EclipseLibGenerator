package name.cdd.product.clzsearch.userlibmaker.gui.foldermap;

import java.io.File;

public class Uep4xGuiSrcFolderMapping implements GuiSrcFolderMapping
{
    private static final String[] mapConsts = new String[] {
        "common\\src", "common_sh\\src", 
        
        "uni\\dsl\\src", "uni\\framework\\src", "uni\\layer2\\src", "uni\\layer3\\src", 
        "uni\\service\\src", "uni\\simplevoip\\src",  "uni\\sys\\src", "uni\\voice\\src",  
        
        "map\\src", "dsl\\src", 
        "is\\src", "eodn\\src", 
        
        "ag\\eoc\\src", "ag\\lkteoc\\src", "ag\\msag\\src", "ag\\msg5600\\src", 
        "a10\\t600\\src", "a10\\u300\\src", 
    
        
        //"common\\testsrc",
        "common_sh\\testsrc", 
        "uni\\dsl\\testsrc", "uni\\framework\\testsrc", "uni\\layer2\\testsrc", "uni\\layer3\\testsrc", 
        "uni\\service\\testsrc", "uni\\simplevoip\\testsrc",  "uni\\sys\\testsrc", "uni\\voice\\testsrc",  
        
        "map\\testsrc", "dsl\\testsrc",         
        "is\\testsrc", "eodn\\testsrc", 
        
        "ag\\eoc\\testsrc", "ag\\lkteoc\\testsrc", "ag\\msag\\testsrc", "ag\\msg5600\\testsrc", 
        "a10\\t600\\testsrc", "a10\\u300\\testsrc", 
        
        "ag\\msag\\unittestsrc"  
    };
    
    @Override
    public String[] oneToN(String oneSrcFolder)
    {
        String[] rtn = new String[mapConsts.length];
        
        for(int i = 0; i < mapConsts.length; i++)
        {
            rtn[i] = new File(oneSrcFolder, mapConsts[i]).getAbsolutePath();
        }
        return rtn;
    }

    @Override
    public String nTo1(String[] sourceFolders)
    {
        for(String folder : sourceFolders)
        {
            if(folder.endsWith(mapConsts[0]))
            {
                return folder.substring(0, folder.length() - mapConsts[0].length());
            }
        }
        
        return "";
    }

}

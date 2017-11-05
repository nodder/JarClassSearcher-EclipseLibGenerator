package name.cdd.product.clzsearch.userlibmaker.gui.foldermap;


public class GuiSrcFolderMappingFactory
{
    public static GuiSrcFolderMapping getMapper(String generateType)
    {
        if(generateType.equals("uep4x_whole_src"))
        {
            return new Uep4xGuiSrcFolderMapping();
        }
        
        return new SimpleGuiSrcFolderMapping();
    }
}

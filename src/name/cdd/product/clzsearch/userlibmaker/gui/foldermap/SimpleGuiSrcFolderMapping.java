package name.cdd.product.clzsearch.userlibmaker.gui.foldermap;



public class SimpleGuiSrcFolderMapping implements GuiSrcFolderMapping
{
    @Override
    public String[] oneToN(String oneSrcFolder)
    {
        return new String[] {oneSrcFolder};
    }

    @Override
    public String nTo1(String[] sourceFolders)
    {
        return sourceFolders[0];
    }

}

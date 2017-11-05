package name.cdd.product.clzsearch.userlibmaker.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import name.cdd.product.clzsearch.userlibmaker.Start;
import name.cdd.product.clzsearch.userlibmaker.business.xml.ActiveDir;
import name.cdd.product.clzsearch.userlibmaker.business.xml.UserConfigXml;
import name.cdd.product.clzsearch.userlibmaker.gui.fileoper.FileContentReader;
import name.cdd.product.clzsearch.userlibmaker.gui.fileoper.SimpleFileWriter;
import name.cdd.product.clzsearch.userlibmaker.gui.foldermap.GuiSrcFolderMappingFactory;
import name.cdd.product.clzsearch.userlibmaker.gui.view.UserLibeMakerView;


public class UserLibMakerPresenter
{
    private UserLibeMakerView view;

    public UserLibMakerPresenter(UserLibeMakerView view)
    {
        this.view = view;
    }
    
    public void analysis(String javaSrcPath, String jarFilePath, String generateType, String libName) throws IOException
    {
        saveParameters(javaSrcPath, jarFilePath, generateType, libName);
        doAnalysis();
        presentLibContent();
    }
    
    public void exportLib(File outputFile, String libContent) throws FileNotFoundException
    {
        SimpleFileWriter.writeFile(outputFile, libContent);
//        saveLibFilePath(outputFile);
    }

    public File getLibFilePath()
    {
        return new File(UserConfigXml.getInstance().getOutputLibraryFileName());
    }
    
    private void presentLibContent() throws IOException
    {
        File libFile = new File(UserConfigXml.getInstance().getOutputLibraryFileName());
        String libContent = FileContentReader.getFullContent(libFile);
        view.presentLibContent(libContent);
    }

    private void saveParameters(String javaSrcPath, String jarFilePath, String generateType, String libName) throws IOException
    {
        ActiveDir.setActiveDir(generateType);
        
        UserConfigXml.getInstance().setSourceFolders(GuiSrcFolderMappingFactory.getMapper(generateType).oneToN(javaSrcPath));
        UserConfigXml.getInstance().setJarDirectory(jarFilePath);
        UserConfigXml.getInstance().setOutputLibraryName(libName);
        UserConfigXml.getInstance().save();
    }

    private void doAnalysis() throws IOException
    {
        Start.main(null);
    }

//    private void saveLibFilePath(File libFilePath)
//    {
//        UserConfigXml.getInstance().setOutputLibraryFileName(libFilePath.getPath());
//        UserConfigXml.getInstance().save();
//    }

    public void switchGeneratingType(String generateType) throws IOException
    {
        String activeDir = generateType;
        ActiveDir.setActiveDir(activeDir);
        UserConfigXml.getInstance().switchToNewActiveDir();

        this.view.updateUI(
                GuiSrcFolderMappingFactory.getMapper(generateType).nTo1(UserConfigXml.getInstance().getSourceFolders()), 
                UserConfigXml.getInstance().getJarDirectory(),
                UserConfigXml.getInstance().getOutputLibraryName(),
                "");
    }

    public String getGeneratingType()
    {
        String generateType = ActiveDir.getActiveDir();
        return generateType;
    }

    public void checkSpecialParams(String jarFilePath, String javaSrcPath, String generateType)
    {
        if(generateType.equals("uep4x_whole_src"))
        {
            if(!isEMSRootDir(jarFilePath))
            {
                throw new IllegalArgumentException(jarFilePath + " is not EMS root directory.");
            }
            
            if(!isSvnRootDir(javaSrcPath))
            {
                throw new IllegalArgumentException(jarFilePath + " is not SVN root directory.");
            }
        }
    }

    private boolean isSvnRootDir(String javaSrcPath)
    {
        return pathMustContainAtLeastOne(javaSrcPath, "a10", "ag", "common", "common_sh", "dsl", "is", "map", "uni");
    }

    private boolean pathMustContainAtLeastOne(String path, String... checkItems)
    {
        File[] subFiles = new File(path).listFiles();
        
        for(String item: checkItems)
        {
            for(File strSubFile : subFiles)
            {
                if(item.equals(strSubFile.getName()))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isEMSRootDir(String jarFilePath)
    {
        return pathMustContainAll(jarFilePath, "ums-server", "ums-client");
    }

    private boolean pathMustContainAll(String path, String... checkItems)
    {
        File[] subFiles = new File(path).listFiles();
        
        for(String item: checkItems)
        {
            boolean contains = false;
            for(File strSubFile : subFiles)
            {
                if(item.equals(strSubFile.getName()))
                {
                    contains = true;
                    break;
                }
            }
            
            if(!contains)
            {
                return false;
            }
        }

        return true;
    }
}

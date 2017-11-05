package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;
import java.util.ArrayList;

import org.jdom.Attribute;
import org.jdom.Element;

public class UserConfigXml
{
    private static UserConfigXml instance = new UserConfigXml();
    private XmlIO xmlIo;
    
    private UserConfigXml()
    {
        xmlIo = readFile();
    }

    public static UserConfigXml getInstance()
    {
        return instance; 
    }
    
    public void switchToNewActiveDir()
    {
        xmlIo = readFile();
    }
    
    private XmlIO readFile()
    {
        return new XmlIO(new File("conf/" +  ActiveDir.getActiveDir(), "User-Config.xml"));
    }
    
    public String getOutputLibraryFileName()
    {
        return xmlIo.getElement(new String[] {"output", "library_file_name"}).getText();
    }
    
    public void setOutputLibraryFileName(String libName)
    {
        xmlIo.getElement(new String[] {"output", "library_file_name"}).setText(libName);
    }

    public String getOutputLibraryName()
    {
        return xmlIo.getElement(new String[] {"output", "library_name"}).getText();
    }
    
    public void setOutputLibraryName(String libName)
    {
        xmlIo.getElement(new String[] {"output", "library_name"}).setText(libName);
    }
    
    public String getJarDirectory()
    {
        return xmlIo.getElement(new String[] {"input", "jar_directory"}).getText();
    }
    
    public void setJarDirectory(String jarFilePath)
    {
        xmlIo.getElement(new String[] {"input", "jar_directory"}).setText(jarFilePath);
    }
    
    public String[] getSourceFolders()
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(), "input", "source_folders", "folder");
        
        String[] fileListPattern = new String[elements.size()];
        for(int i = 0; i < elements.size(); i++)
        {
            Element e = elements.get(i);
            
            fileListPattern[i] = e.getAttributeValue("file");
        }
        
        return fileListPattern;
    }
    
    //´ýÖØ¹¹
    public void setSourceFolders(String[] sourceFolders)
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(),"input", "source_folders", "folder");
        ajustElement(sourceFolders.length - elements.size(), elements);
        
        elements = XmlIO.getAllElements(xmlIo.getRootElement(),"input", "source_folders", "folder");
        
        if(sourceFolders.length != elements.size())
        {
            System.out.println("Error!!! sourceFolders and elements not equal");
        }
        
        for(int i = 0; i < elements.size(); i++)
        {
            Element e = elements.get(i);
            e.setAttribute("file", sourceFolders[i]);
        }
    }

    private void ajustElement(int value, ArrayList<Element> elements)
    {
        if(value > 0)
        {
            for(int i = 0; i < value; i++)
            {
                Element plusOneE = new Element("folder");
                plusOneE.setAttribute(new Attribute("file", ""));

                elements.get(0).getParent().addContent(plusOneE);
            }
        }
        else
        {
            int removeIndex = elements.size() - 1;
            while(value < 0)
            {
                elements.get(0).getParent().removeContent(elements.get(removeIndex--));
                value++;
            }
        }
    }
    
    public void save()
    {
        this.xmlIo.save();
    }

    public static void main(String[] args)
    {
//        System.out.println(Arrays.toString(UserConfigXml.getInstance().getSourceFolders()));
//        
//        System.out.println(UserConfigXml.getInstance().getJarDirectory());
//        System.out.println(UserConfigXml.getInstance().getOutputLibraryFileName());
//        System.out.println(UserConfigXml.getInstance().getOutputLibraryName());
//        UserConfigXml.getInstance().setSourceFolders(null);
        
        UserConfigXml.getInstance().ajustElement(5, XmlIO.getAllElements(UserConfigXml.getInstance().xmlIo.getRootElement(),"input", "source_folders", "folder"));
        UserConfigXml.getInstance().save();
        
    }
}

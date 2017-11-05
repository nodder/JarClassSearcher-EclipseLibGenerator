package name.cdd.product.clzsearch.userlibmaker.business.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.jdom.Element;

public class IgnoreListXml
{
    private static IgnoreListXml instance = new IgnoreListXml();
    private XmlIO xmlIo;
    
    private IgnoreListXml()
    {
        xmlIo = new XmlIO(new File("conf/" +  ActiveDir.getActiveDir(), "Class-Ignore-List.xml"));
    }
    
    public static IgnoreListXml getInstance()
    {
        return instance; 
    }
    
    public String[] getClassPatternList()
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(), "ignoreList-pattern", "class-pattern");
        
        String[] fileListPattern = new String[elements.size()];
        for(int i = 0; i < elements.size(); i++)
        {
            Element e = elements.get(i);
            
            fileListPattern[i] = e.getAttributeValue("fullClassName");
        }
        
        return fileListPattern;
    }
    
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(IgnoreListXml.getInstance().getClassPatternList()));
    }
}
